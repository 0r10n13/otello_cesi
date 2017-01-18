package client;

import java.rmi.Naming;
import java.util.Scanner;

import server.IGameNetwork;

import common.Player;
import common.TooManyPlayersException;
import common.UserExistsException;

public class GameClient {

	private MainCanvas canvas;
	private Connection connect;
	private Player player;

	public static void main(String[] args) {
		// affichage de l'interface
		GameClient gClient = new GameClient();
		gClient.InitMain();
	}

	public void InitMain() {

		setUrl("rmi://10.176.128.139/Othello");
		SetPlayerName("toto");

	}

	public void setUrl(String url) {
		connect = new Connection();
		connect.Connect(url);
	}

	public void SetPlayerName(String name) {

		Player newPlayer = new Player();
		newPlayer.setName(name);

		try {
			player = connect.getStub().addPlayer(newPlayer);

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
