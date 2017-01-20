package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import common.PlayerImpl;
import server.IGameNetwork;
import javax.swing.JLabel;

public class JClientFrame extends JFrame {

	private JPanel contentPane;
	private JOthelloPanel panel;
	
	/**public void Init() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JClientFrame frame = new JClientFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	public JClientFrame() {
		setTitle("Othello");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel label_turn = new JLabel("Turn");
		GridBagConstraints gbc_label_turn = new GridBagConstraints();
		gbc_label_turn.insets = new Insets(0, 0, 5, 5);
		gbc_label_turn.gridx = 0;
		gbc_label_turn.gridy = 0;
		contentPane.add(label_turn, gbc_label_turn);
		
		JLabel label_nameplayer = new JLabel("New label");
		GridBagConstraints gbc_label_nameplayer = new GridBagConstraints();
		gbc_label_nameplayer.insets = new Insets(0, 0, 5, 0);
		gbc_label_nameplayer.gridx = 1;
		gbc_label_nameplayer.gridy = 0;
		contentPane.add(label_nameplayer, gbc_label_nameplayer);
		
		panel = new JOthelloPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		contentPane.add(panel, gbc_panel);
	}

	public JOthelloPanel getOthelloPanel() {
		return panel;
	}
	
	//public void setServer(OthelloServer server_p) {
	//	server=server_p;
		//getOthelloPanel().setServer(server_p);
	//}

	public JTextField getJtfUserName() {
		//return jtfUserName;
		return null;
	}


	public void refreshBoard() throws RemoteException {
		internRefreshBoard();
	}

	private void internRefreshBoard() {
		getOthelloPanel().invalidate();
		getOthelloPanel().repaint();
	}

	
	public String getPlayerName() throws RemoteException {
		return panel.getName();
	}


/*	public void setColor(TokenColor color_p) throws RemoteException {
		// TODO Auto-generated method stub
		
	}*/

	/*public void setName(String name){
		label_nameplayer.setText(name);
	}*/
	
	public void Toggle(boolean etat) throws RemoteException {
		if (etat==true){
			getOthelloPanel().setEnabled(true);
		}
		else{
			getOthelloPanel().setEnabled(false);
		}
		setTitle("Au tour de " + getPlayerName() + " de jouer");
	}
	public void setServer(IGameNetwork stub) {
		panel.setServer(stub);
		internRefreshBoard();
	}
	public void SetPlayer(PlayerImpl player)
	{
		panel.SetPlayer(player);
	}
}
