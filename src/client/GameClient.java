package client;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import server.IGameNetwork;
import common.IObservable;
import common.IObservator;
import common.IPlayer;
import common.PlayerImpl;
import common.TooManyPlayersException;
import common.UserExistsException;

public class GameClient implements IObservator{


	private Connection connect;
	private PlayerImpl player;
	private Connexionpage connectFrame;
	private NamePage nameFrame;
	private JClientFrame gamePage;

	public static void main(String[] args) {

		// affichage de l'interface
		GameClient gClient = new GameClient();
		gClient.InitMain();
	}

	public void InitMain() {
		connectFrame = new Connexionpage(this);
		connectFrame.setVisible(true);
		
//		JClientFrame toto = new JClientFrame();
//		toto.setVisible(true);
		
//TEST INTERFACAGE
		//setUrl("rmi://10.176.128.139/Othello");
		//SetPlayerName("toto");

	}
	
	public void displayAddPlayer()
	{
		connectFrame.setVisible(false);
		connectFrame.dispose();
		nameFrame = new NamePage();
		nameFrame.setVisible(true);
		nameFrame.setGameClient(this);
	}
	
	public void displayGame()
	{
		nameFrame.dispose();
		gamePage = new JClientFrame();
		gamePage.setServer(this.getServer());
		gamePage.SetPlayer(this.player);
		gamePage.setVisible(true);
	}

	public void setUrl(String url) {
		connect = new Connection();
		try {
			connect.Connect(url);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void SetPlayerName(String name) {

		player = new PlayerImpl();
		player.setName(name);

		try {
			IPlayer stubPlayer =  (IPlayer) UnicastRemoteObject.exportObject(player, 0);
			connect.getStub().addPlayer(stubPlayer);
			player.addObserver(this);
			System.out.println("adding player OK");
			displayGame();

		} catch (TooManyPlayersException e) {
			System.out.println(e);
		} catch (UserExistsException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public IGameNetwork getServer() {
		return connect.getStub();
	}

	@Override
	public void observableChanged(IObservable object) {
		if (object.getClass() == IPlayer.class)
		{
			try {
				gamePage.Toggle(player.hasTurn());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
