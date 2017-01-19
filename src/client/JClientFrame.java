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

public class JClientFrame extends JFrame {

	private JPanel contentPane;
	private JOthelloPanel panel;
	
	public static void main(String[] args) {
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
	}

	public JClientFrame() {
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
		
		JTextField jtfUserName = new JTextField();
		GridBagConstraints gbc_jtfUserName = new GridBagConstraints();
		gbc_jtfUserName.insets = new Insets(0, 0, 5, 5);
		gbc_jtfUserName.weightx = 1.0;
		gbc_jtfUserName.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfUserName.gridx = 0;
		gbc_jtfUserName.gridy = 0;
		contentPane.add(jtfUserName, gbc_jtfUserName);
		jtfUserName.setColumns(10);
		
		JButton btnNewButton = new JButton("Connect");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnConnectActionPerformed();
			}
		});
		
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 0;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		
		panel = new JOthelloPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		contentPane.add(panel, gbc_panel);
	}
	protected void btnConnectActionPerformed() {
		if (!getJtfUserName().getText().isEmpty()) {
			/*try {
				//playerName=getJtfUserName().getText();
				//server.addPlayer((OthelloPlayer)UnicastRemoteObject.exportObject(this, 0));
			} catch (RemoteException e) {
				e.printStackTrace();
			}*/
		}
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
		getOthelloPanel().invalidate();
		getOthelloPanel().repaint();
	}

	
	public String getPlayerName() throws RemoteException {
		//return playerName;
		return null;
	}


/*	public void setColor(TokenColor color_p) throws RemoteException {
		// TODO Auto-generated method stub
		
	}*/

	
	public void yourTurn() throws RemoteException {
		//getOthelloPanel().setEnabled(true);
		setTitle("Mon tour de jouer");
	}

	
	public void otherTurn() throws RemoteException {
		//getOthelloPanel().setEnabled(false);
		setTitle("Tour de l'autre de jouer");
	}
}
