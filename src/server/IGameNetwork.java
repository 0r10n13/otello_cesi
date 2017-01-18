package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IGameNetwork extends Remote{
	public void addUser() throws RemoteException;
	
	public String[][] getBoardState() throws RemoteException;
}
