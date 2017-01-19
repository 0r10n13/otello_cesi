package common;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface IPlayer extends Remote, IObservable {
	public String getName() throws RemoteException;

	public CouleurPion getColor() throws RemoteException;

	public void setColor(CouleurPion color) throws RemoteException;
	
	public void changeTurn() throws RemoteException;
}
