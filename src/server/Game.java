package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import common.CouleurPion;
import common.IPlayer;
import common.Pion;
import common.PlayerImpl;
import common.TooManyPlayersException;
import common.UserExistsException;

public class Game implements IGameNetwork {
	List<IPlayer> players = new ArrayList<IPlayer>();
	Pion board[][] = new Pion[8][8];

	@Override
	public void addPlayer(IPlayer newPlayer) throws RemoteException, UserExistsException, TooManyPlayersException {
		if (players.size() == 2) {
			throw new TooManyPlayersException("Too many players");
		}

		for (IPlayer item : players) {
			if (item.getName() == newPlayer.getName()) {
				throw new UserExistsException("Username already exists");
			}
		}

		if (players.size() == 0) {
			newPlayer.setColor(CouleurPion.NOIR);
		} else {
			if (players.get(0).getColor() == CouleurPion.NOIR)
				newPlayer.setColor(CouleurPion.BLANC);
			else
				newPlayer.setColor(CouleurPion.NOIR);
		}
		players.add(newPlayer);
		System.out.println("added : " + newPlayer.getName());
		// return newPlayer;
	}

	@Override
	public String[][] getBoardState() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
		// TEST DE QUENTIN
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
