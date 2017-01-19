package common;

import java.rmi.RemoteException;

public interface IObservable {
	public void addObserver(IObservator observer) throws RemoteException;
	
	public void deleteObserver(IObservator observer) throws RemoteException;
	
	public void notifyObservers() throws RemoteException;
}
