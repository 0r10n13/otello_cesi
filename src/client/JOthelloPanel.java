package client;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.rmi.RemoteException;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class JOthelloPanel extends JPanel{

	int NBCOLONNE=10;
	int NBLIGNE=10;
	public JOthelloPanel() {
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

		int cellWidth = (int) getWidth() / getWidth();
		int cellHeight = (int) getHeight() / getHeight();
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

		int cell_x = (int)(x/(getWidth() / getWidth()));
		int cell_y = (int)(y/(getHeight() / getHeight()));
		return new Point(cell_x, cell_y);
	}


	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			for (int x= 0; x < getWidth() ; x++) {
				for (int y = 0; y < getHeight(); y++) {
					System.out.printf("In for paintComponent");
					//TokenColor color = server.getColorAt(x, y);
					//if (color != null) {
						Rectangle r = getCellCoords(x, y);
						//Color c=color == TokenColor.WHITE ? Color.white : Color.BLACK;
						Color c = Color.RED;
						g.setColor( Color.RED);
						((Graphics2D)g).setPaint(c);
						((Graphics2D)g).fill(new Ellipse2D.Float(r.x, r.y, r.width, r.height));
						g.drawOval(r.x, r.y, r.width, r.height);
						
					//}
				}
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
