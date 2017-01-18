package client;

import java.rmi.Naming;
import java.util.Scanner;

public class GameClient {

	private JClientFrame canvas;

	public static void main(String[] args) {

		// affichage de l'interface
		JClientFrame canvas = new JClientFrame();
		canvas.setVisible(true);

		/*try {
			String url = "rmi://10.176.130.24/Othello";
			server.Game stub = (server.Game) Naming.lookup(url);
			Scanner s = new Scanner(System.in);
			String name = s.nextLine();
			// add player
			stub.addPlayer(name);

			// response
			System.out.println(name + (name != null ? "Ajout�" : "d�j� pr�sent"));
			// System.out.println(stub.getPlayers());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}
