package client;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.rmi.RemoteException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import common.CouleurPion;
import common.PlayerImpl;
import javafx.scene.control.Tab;
import javafx.scene.shape.Circle;
import server.IGameNetwork;

@SuppressWarnings("serial")
public class JOthelloPanel extends JPanel{

	private IGameNetwork server;
	private PlayerImpl player;

	public void setServer(IGameNetwork server) {
		this.server = server;
	}
	
	public void SetPlayer(PlayerImpl player)
	{
		this.player= player;
	}

	public JOthelloPanel() {
		//jclientframe.setName(player.getName());
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelClicked(e);
			}
		});
	}

	protected void panelClicked(MouseEvent e) {
		if (!isEnabled()) {
			return;
		}
		try {
			Point cell_coords=getCoordsCell(e.getX(), e.getY());
			System.out.printf("Click at %s\n", cell_coords);
			//server.setTokenAt(cell_coords.x, cell_coords.y);
			//player = new PlayerImpl();
			System.out.printf("Couleur %s\n", player.getColor());
			
			boolean ajout_pion=	server.CheckPosition(cell_coords.x, cell_coords.y,player.getColor());
			if (!ajout_pion)
			{
				//System.out.printf("Impossible de poser le pion");
				javax.swing.JOptionPane.showMessageDialog(null,"Impossible de poser le pion"); 
			}
			invalidate();
			repaint();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
	}
	/**
	 * Retourne les coordonn�es en pixels de la cellule en (x, y)
	 * @param x
	 * @param y
	 * @return
	 * @throws RemoteException
	 */
	private Rectangle getCellCoords(int x, int y) throws RemoteException {

		int cellWidth = (int) getWidth() / 8;
		int cellHeight = (int) getHeight() / 8;
		return new Rectangle(x * cellWidth, y * cellHeight, cellWidth, cellHeight);
	}

	/**
	 * Retourne les coordonn�es de la cellule au pixels (x, y)
	 * @param x
	 * @param y
	 * @return
	 * @throws RemoteException
	 */
	private Point getCoordsCell(int x, int y) throws RemoteException {

		int cell_x = (int)(x/(getWidth() / 8));
		int cell_y = (int)(y/(getHeight() / 8));
		return new Point(cell_x, cell_y);
	}


	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			if (server==null)
			{
				return;
			}
			
			CouleurPion[][] color = server.getBoardState();
			//color[2][3]=CouleurPion.NOIR;
			for (int x= 0; x < 8 ; x++) {
				for (int y = 0; y < 8; y++) {
					//System.out.printf("In for paintComponent\n");

					Rectangle r = getCellCoords(x, y);
					if (color[x][y] != null) {
						Color c= color[x][y] == CouleurPion.BLANC ? Color.white : Color.BLACK;					
						g.setColor(c);
						((Graphics2D)g).setPaint(c);
						((Graphics2D)g).fill(new Ellipse2D.Float(r.x, r.y, r.width, r.height));
					}
					g.setColor(Color.BLACK);
					g.drawRect(r.x, r.y, r.width, r.height);
				}
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
