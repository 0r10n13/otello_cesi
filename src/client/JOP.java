package client;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class JOP extends JPanel{

	int NBCOLONNE=10;
	int NBLIGNE=10;
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int posX = getWidth()/NBCOLONNE;
		int posY = getHeight()/NBLIGNE;
		
		for (int j=1 ; j<NBCOLONNE; j++){
			for (int h=1 ; h<NBLIGNE; h++){
				g.setColor(Color.BLUE);
				g.drawOval(posX*j,posY*h, 70, 70);
			}
		}
	}
	
	public static void main(String[] args) {
		JFrame jf=new JFrame("Test");
		jf.setContentPane(new JOP());
		jf.setVisible(true);
	}
}
