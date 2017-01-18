package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import common.Pion;
import common.Player;
import common.TooManyPlayersException;
import common.UserExistsException;

public class Game implements IGameNetwork {
	List<Player> players = new ArrayList<Player>();
	Pion board[][] = new Pion[8][8];
	@Override
	public Player addPlayer(Player newPlayer) throws RemoteException, UserExistsException, TooManyPlayersException {
		// TODO Auto-generated method stub
		boolean isExists = false;
		if (players.size() > 2)
		{
			throw new TooManyPlayersException("Too many players");
		}
		for (Player item : players) {
			if (item.getName() == newPlayer.getName())
				isExists = true;
			break;
		}
		
		if (!isExists)
		{
			if (players.size() == 0)
			{
				newPlayer.setColor(Player.Color.NOIR);
			}
			else
			{
				if (players.get(0).getColor() == Player.Color.NOIR)
					newPlayer.setColor(Player.Color.BLANC);
				else
					newPlayer.setColor(Player.Color.NOIR);
			}
			players.add(newPlayer);
			System.out.println("added : "+newPlayer.getName());
			return newPlayer;
		}
		else
		{
			throw new UserExistsException("Username already exists");
		}
	}

	@Override
	public String[][] getBoardState() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
		// TEST DE QUENTIN
	}
	
	public static void main(String[] args)
	{
		try
		{
			Game obj = new Game();
			Remote stub =  UnicastRemoteObject.exportObject(obj, 0);
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
