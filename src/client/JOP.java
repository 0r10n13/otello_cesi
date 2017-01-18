package client;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class JOP extends JPanel{

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.RED);
		for (int i=0; i<getWidth()/2; i+=10) {
			g.drawOval(i,  i, getWidth()/8 , getHeight()/8);
		}
	}
	
	public static void main(String[] args) {
		JFrame jf=new JFrame("Test");
		jf.setContentPane(new JOP());
		jf.setVisible(true);
	}
}
