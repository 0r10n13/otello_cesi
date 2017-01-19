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
import common.PlayerImpl;
import common.TooManyPlayersException;
import common.UserExistsException;

public class Game implements IGameNetwork {
	List<IPlayer> players = new ArrayList<IPlayer>();
	Board board = new Board();

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
		if (players.size() == 2)
		{
			startGame();
		}
	}

	public void startGame()
	{
		board.InitStartBoard(players);
	}
	
	@Override
	public CouleurPion[][] getBoardState() throws RemoteException {

		return board.GetBoardState();

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
