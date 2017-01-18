package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import common.Player;
import common.TooManyPlayersException;
import common.UserExistsException;

public interface IGameNetwork extends Remote{
	public Player addPlayer(Player player) throws RemoteException, UserExistsException, TooManyPlayersException;
	
	public String[][] getBoardState() throws RemoteException;
}
