package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.GridBagLayout;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Insets;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

public class Connexionpage extends JFrame {

	private JPanel contentPane;
	private JList JLDispo;
	private JTextField textField;

	private UserService2 userService;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UserService2 usrSrv=new UserService2();
	
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Connexionpage frame = new Connexionpage(usrSrv);
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 * @param userService 
	 */
	public Connexionpage(UserService2 userService_p) {
		userService=userService_p;
		addWindowListener(new WindowAdapter() {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void windowOpened(WindowEvent e) {
				refresh();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblNewLabel = new JLabel("Adresse IP");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nouveau=getTextField().getText();
				if (!nouveau.isEmpty()) {
					try {
						userService.addUser(nouveau);
						((DefaultListModel)getJLDispo().getModel()).addElement(nouveau);
					} catch (Exception e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(Connexionpage.this, "Impossible d'insérer en base");
					}
				}
			}
		});
		GridBagConstraints gbc_btnAjouter = new GridBagConstraints();
		gbc_btnAjouter.insets = new Insets(0, 0, 5, 5);
		gbc_btnAjouter.gridx = 1;
		gbc_btnAjouter.gridy = 2;
		contentPane.add(btnAjouter, gbc_btnAjouter);
		
		JButton btnNewButton = new JButton("Suivant");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 5;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		

		JButton btnValider = new JButton("Exporter");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jf=new JFileChooser();
				FileNameExtensionFilter ff=new FileNameExtensionFilter("Fichier texte", "txt");
				jf.setFileFilter(ff);
				if (jf.showSaveDialog(Connexionpage.this)==JFileChooser.APPROVE_OPTION) {
					try {
						saveUsers(jf.getSelectedFile());
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(Connexionpage.this, "Erreur à l'écriture");
					}
				}
			}
		});
	}

	

	protected void saveUsers(File selectedFile) throws IOException {
		
		String path=selectedFile.getAbsolutePath();
		if (!path.toLowerCase().endsWith(".txt")) {
			path=path+".txt";
		}
		PrintWriter pw=new PrintWriter(new FileWriter(path));

		pw.println("Utilisateurs disponibles");
		for (int i = 0; i < getJLDispo().getModel().getSize(); i++) {
			pw.println(getJLDispo().getModel().getElementAt(i));
		}
		pw.println();
		
		pw.close();
		
		JOptionPane.showMessageDialog(Connexionpage.this, "Fichier écrit");

	}

	protected JList getJLDispo() {
		return JLDispo;
	}
	
	public JTextField getTextField() {
		return textField;
	}

	private void refresh() {
		try {
			((DefaultListModel)getJLDispo().getModel()).removeAllElements();
			List dispo=userService.getUserDisponibles();
			for (Object o : dispo) {
				((DefaultListModel)getJLDispo().getModel()).addElement(o);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(Connexionpage.this, "Pas de connection BD");
		}
	}
}
