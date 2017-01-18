package client;

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
	}
	
	public void InitMain(){
		this.canvas = new MainCanvas();
		this.canvas.Init();
		
	}
	
	

}
