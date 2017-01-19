package client;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import server.IGameNetwork;
import common.IPlayer;
import common.PlayerImpl;
import common.TooManyPlayersException;
import common.UserExistsException;

public class GameClient {

	private MainCanvas canvas;
	private Connection connect;
	private PlayerImpl player;
	private Connexionpage connectFrame;
	private NamePage nameFrame;

	public static void main(String[] args) {
		// affichage de l'interface
		GameClient gClient = new GameClient();
		gClient.InitMain();
	}

	public void InitMain() {
		connectFrame = new Connexionpage(this);
		connectFrame.setVisible(true);
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

	public void setUrl(String url) {
		connect = new Connection();
		connect.Connect(url);
	}

	public void SetPlayerName(String name) {

		player = new PlayerImpl();
		player.setName(name);

		try {
			IPlayer stubPlayer =  (IPlayer) UnicastRemoteObject.exportObject(player, 0);
			connect.getStub().addPlayer(stubPlayer);

			System.out.println("adding player OK");

		} catch (TooManyPlayersException e) {
			System.out.println(e);
		} catch (UserExistsException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
