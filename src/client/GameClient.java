package client;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import server.IGameNetwork;
import common.CouleurPion;
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
		gamePage = new JClientFrame();
		gamePage.setServer(this.getServer());
	}
	
	public void displayGame()
	{
		gamePage.SetPlayer(this.player);
		nameFrame.dispose();
		gamePage.setVisible(true);
	}

	public void setUrl(String url) {
		connect = new Connection();
		try {
			connect.Connect(url);
			displayAddPlayer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("connexion impossible");
			connectFrame.ErrorConnexion();
		}
	}

	public void SetPlayerName(String name) {

		player = new PlayerImpl();
		player.setName(name);

		try {
			player.addObserver(this);
			IPlayer stubPlayer =  (IPlayer) UnicastRemoteObject.exportObject(player, 0);
			connect.getStub().addPlayer(stubPlayer);
			System.out.println("adding player OK");
			String color = (player.getColor() == CouleurPion.BLANC) ? "blanc" : "noir";
			gamePage.setColorName(color);
			displayGame();

		} catch (TooManyPlayersException e) {
			System.out.println(e);
			nameFrame.ErrorAddPlayer();
		} catch (UserExistsException e) {
			System.out.println(e);
			nameFrame.ErrorAddPlayer();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println(e.getStackTrace());
			nameFrame.ErrorAddPlayer();
		}
	}

	public IGameNetwork getServer() {
		return connect.getStub();
	}

	@Override
	public void observableChanged(IObservable object) {
		if (object.getClass() == PlayerImpl.class)
		{
			try {
				System.out.println("turn changed");
				gamePage.Toggle(player.hasTurn());
				gamePage.refreshBoard();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
