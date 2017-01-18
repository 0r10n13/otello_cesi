package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainCanvas extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public void Init() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainCanvas frame = new MainCanvas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainCanvas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

	@Override
	protected void paintComponent(Graphics g) { // ajout Q
		super.paintComponent(g);
		g.setColor(Color.RED);
		for (int i=0; i<getWidth()/2; i+=10) {
			g.drawOval(i,  i, getWidth()-i , getHeight()-i);
		}
	} // ajout Q
}
