package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import common.IPlayer;
import common.PlayerImpl;
import common.TooManyPlayersException;
import common.UserExistsException;

public interface IGameNetwork extends Remote{
	public IPlayer addPlayer(IPlayer player) throws RemoteException, UserExistsException, TooManyPlayersException;
	
	public String[][] getBoardState() throws RemoteException;
}
