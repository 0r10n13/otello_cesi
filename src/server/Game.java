package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Game implements IGameNetwork {

	@Override
	public void addPlayer() throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public String[][] getBoardState() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void main(String[] args)
	{
		try
		{
			Game obj = new Game();
			Game stub = (Game) UnicastRemoteObject.exportObject(obj, 0);
			// Bind the remote object's stub in the registry
			Registry registry = LocateRegistry.getRegistry();
			registry.rebind("Othello", stub);
			System.err.println("Server ready");
		}
		catch(Exception e)
		{
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}

}
