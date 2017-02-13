package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import common.CouleurPion;
import common.IPlayer;
import common.TooManyPlayersException;
import common.UserExistsException;

public interface IGameNetwork extends Remote {
	public void addPlayer(IPlayer player) throws RemoteException, UserExistsException, TooManyPlayersException;

	public CouleurPion[][] getBoardState() throws RemoteException;

	public boolean CheckPosition(int x, int y, CouleurPion couleur, boolean readOnly) throws RemoteException;

	public boolean isGameOver() throws RemoteException;
}
