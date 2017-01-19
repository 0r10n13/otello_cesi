package client;

import java.rmi.Naming;
import java.util.Scanner;

import server.IGameNetwork;

public class Connection {

	private IGameNetwork stub;
	
	public IGameNetwork getStub() {
		return stub;
	}

	public void Connect(String url) {

		try {
			url = "rmi://"+url+"/Othello";
			stub = (IGameNetwork) Naming.lookup(url);

			// Scanner s = new Scanner(System.in);
			// String name = s.nextLine();

			// add player
			// stub.addPlayer(name);

			// response

			System.out.println("connexion ok");
			// System.out.println(name + (name != null ? "Ajout�" : "d�j�
			// pr�sent"));
			// System.out.println(stub.getPlayers());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("connection FAILED");
			e.printStackTrace();
		}
	}

}
