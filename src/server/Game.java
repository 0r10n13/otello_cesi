package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import common.Board;
import common.CouleurPion;
import common.IPlayer;
import common.Pion;
import common.TooManyPlayersException;
import common.UserExistsException;

public class Game implements IGameNetwork {
	List<IPlayer> players = new ArrayList<IPlayer>();
	Board board = new Board();
	boolean over;
	int lastTurn;
	
	@Override
	public int GetScoreByColor(CouleurPion couleur) throws RemoteException{
		return board.getNumberPion(couleur);
	}

	@Override
	public void addPlayer(IPlayer newPlayer) throws RemoteException, UserExistsException, TooManyPlayersException {

		// controle du nombre de joueurs
		if (players.size() == 2) {
			throw new TooManyPlayersException("Too many players");
		}

		// pour chaque joueur, controle du nom
		for (IPlayer item : players) {
			if (item.getName().equals(newPlayer.getName())) {
				throw new UserExistsException("Username already exists");
			}
		}

		// si premier joueur inscrit => couleur noire attribu�e
		if (players.size() == 0) {
			newPlayer.setColor(CouleurPion.NOIR);
		} else {
			if (players.get(0).getColor() == CouleurPion.NOIR) {
				newPlayer.setColor(CouleurPion.BLANC);
			} else
				newPlayer.setColor(CouleurPion.NOIR);
		}

		// ajout du joueur
		players.add(newPlayer);
		System.out.println("added : " + newPlayer.getName());
	}
	
	@Override
	public void AskStartGame() throws RemoteException
	{
		if (players.size() == 2)
		{
			startGame();
		}
	}

	public void startGame() throws RemoteException
	{
		over = false;
		players.get(0).setTurn(true);
		players.get(1).setTurn(false);
		lastTurn = 0;
		board.InitStartBoard(players);
		endTurn();
	}
	
	public boolean checkGameOver() throws RemoteException
	{
		for (int x = 0; x < 8; x++)
		{
			for (int y = 0; y < 8; y++)
			{
				Pion[][] mem = board.getOriginalBoard();
				boolean posNoir = CheckPosition(x, y, CouleurPion.NOIR);
				board.setOriginalBoard(mem);
				boolean posBlanc = CheckPosition(x, y, CouleurPion.BLANC);
				board.setOriginalBoard(mem);
				if (posNoir || posBlanc)
				{
					over = false;
					return false;
				}
			}
		}
		over = true;
		return true;
	}
	
	public boolean isGameOver() throws RemoteException
	{
		return over;
	}
	
	public IPlayer checkWinner() throws RemoteException
	{
		int black = 0, white = 0;
		IPlayer winner = null;
		try {
			black = board.getNumberPion(CouleurPion.NOIR);
			white = board.getNumberPion(CouleurPion.BLANC);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (black > white)
		{
			winner = (players.get(0).getColor() == CouleurPion.NOIR) ? players.get(0) : players.get(1);
		}
		else
		{
			winner = (players.get(0).getColor() == CouleurPion.BLANC) ? players.get(0) : players.get(1);
		}
		
		return winner;
	}
	
	public void endTurn()
	{
		try {
			checkGameOver();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (IPlayer item : players)
		{
			try {
				item.changeTurn();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (lastTurn == 0)
			lastTurn = 1;
		else
			lastTurn = 0;
	}
	
	@Override
	public CouleurPion[][] getBoardState() throws RemoteException {
		return board.GetBoardState();
	}

	@Override
	public boolean CheckPosition(int x, int y, CouleurPion couleur) throws RemoteException {
		
		//check si la case est vide
		if (!board.IsPositionFree(x, y)){
			return false;
		}
		
		//check si la position est autoris�e
		IPlayer current = null;
		for (IPlayer iPlayer : players) {
			if (iPlayer.hasTurn()){
				current = iPlayer;
				break;
			}
		}
		if (current == null)
		{
			if (lastTurn == 0)
				current = players.get(1);
			else
				current = players.get(0);
		}

		if (!board.IsPositionAuthorised(x, y, current)){
			return false;
		}
		
		//endTurn();
		return true;
	}

	public static void main(String[] args) {
		try {
			Game obj = new Game();
			Remote stub = UnicastRemoteObject.exportObject(obj, 0);
			// Bind the remote object's stub in the registry
			Registry registry = LocateRegistry.getRegistry();
			registry.rebind("Othello", stub);
			System.err.println("Server ready");
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}
}
