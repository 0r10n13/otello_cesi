package client;

<<<<<<< HEAD
import java.rmi.Naming;
import java.util.Scanner;

public class GameClient {

	private MainCanvas canvas;

	public static void main(String[] args) {

		// affichage de l'interface
		GameClient gClient = new GameClient();
		gClient.InitMain();

		try {
			String url = "rmi://10.176.130.24/Othello";
			server.Game stub = (server.Game) Naming.lookup(url);
			Scanner s = new Scanner(System.in);
			String name = s.nextLine();
			// add player
			stub.addPlayer(name);

			// response
			System.out.println(name + (name != null ? "Ajouté" : "déjà présent"));
			// System.out.println(stub.getPlayers());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

=======
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial") // ajout Q
public class GameClient extends JPanel{

	private MainCanvas canvas;

	@Override
	protected void paintComponent(Graphics g) { // ajout Q
		super.paintComponent(g);
		g.setColor(Color.RED);
		for (int i=0; i<getWidth()/2; i+=10) {
			g.drawOval(i,  i, getWidth()-i , getHeight()-i);
		}
	} // ajout Q
	public static void main(String[] args) {	
		
		GameClient toto = new GameClient();
		toto.InitMain();
		
		JFrame jf=new JFrame("Test"); // ajout Q
		jf.setContentPane(new GameClient()); // ajout Q
		jf.setVisible(true); // ajout Q 
>>>>>>> f1b439501d79b14cb128b19c04bfc294987f5920
	}

	public void InitMain() {
		this.canvas = new MainCanvas();
		this.canvas.Init();
		
	}

}
