package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

import common.PlayerImpl.Color;

public interface IPlayer extends Remote {
	public String getName() throws RemoteException;

	public Color getColor() throws RemoteException;

	public void setColor(Color color) throws RemoteException;
}
