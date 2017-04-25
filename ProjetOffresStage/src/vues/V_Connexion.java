package vues;

import controleurs.Controleur;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class V_Connexion extends JFrame implements ActionListener{
	
	private JPanel panelGeneral;
	private JPanel panelConnect;
	
	// JTF
	private JTextField jTFNomCompte;
	private JPasswordField passwordField;
		
	//LABEL
	private JLabel lblNomDeCompte;
	private JLabel lblConnexion;
	private	JLabel lblMotDePasse;	
		
	// BOUTONS 
	private	JButton btnQuitter;
	private	JButton btnSeConnecter;
	
	public V_Connexion(Controleur c){
		
		this.setTitle("Gestion des offres de stages"); 

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setSize(1100, 680);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	
		panelGeneral = new JPanel();
		panelGeneral.setBackground(new Color(44, 67, 85));
		getContentPane().add(panelGeneral);
		panelGeneral.setLayout(null);
		

		panelConnect = new JPanel();
		panelConnect.setForeground(Color.BLACK);
		//panelConnect.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelConnect.setBackground(new Color(25, 48, 66));
		panelConnect.setBounds(359, 180, 400, 350);
		
		panelGeneral.add(panelConnect);
		panelConnect.setLayout(null);
		
		lblConnexion = new JLabel("CONNEXION");
		Font style1 = new Font("Verdana",Font.BOLD,18);
		lblConnexion.setFont(style1);
		lblConnexion.setBounds(150, 60, 160, 26);
		lblConnexion.setForeground(Color.white);
		panelConnect.add(lblConnexion);
		
		Font style2 = new Font("Verdana",Font.BOLD,13);
		lblNomDeCompte = new JLabel("Nom de compte");
		lblNomDeCompte.setFont(style2);
		lblNomDeCompte.setBounds(60, 123, 130, 32);
		lblNomDeCompte.setForeground(Color.white);
		panelConnect.add(lblNomDeCompte);
		
		jTFNomCompte = new JTextField();
		jTFNomCompte.setBounds(200, 129, 142, 22);
		jTFNomCompte.setBorder(new LineBorder(new Color(255,255,255)));
		panelConnect.add(jTFNomCompte);
		jTFNomCompte.setColumns(10);
		
		lblMotDePasse = new JLabel("Mot de passe");
		lblMotDePasse.setBounds(60, 172, 130, 32);
		lblMotDePasse.setFont(style2);
		lblMotDePasse.setForeground(Color.white);
		panelConnect.add(lblMotDePasse);
		
		passwordField = new JPasswordField();		
		passwordField.setBounds(200, 178, 142, 22);
		passwordField.setBorder(new LineBorder(new Color(255,255,255)));
		panelConnect.add(passwordField);
		
		btnQuitter = new JButton("QUITTER");
		btnQuitter.setBackground(new Color(25, 48, 66));
		btnQuitter.setBorder(new LineBorder(new Color(255,255,255)));
		btnQuitter.setForeground(new Color(255,255,255));
		btnQuitter.setBounds(60, 254, 121, 35);
		panelConnect.add(btnQuitter);
		
		btnSeConnecter = new JButton("VALIDER");
		btnSeConnecter.setBounds(220, 254, 121, 35);
		btnSeConnecter.setBackground(new Color(210, 234, 252));
		btnSeConnecter.setBorder(new LineBorder(new Color(255,255,255)));
		panelConnect.add(btnSeConnecter);
		
		// ECOUTE 
		this.btnSeConnecter.addActionListener(c);
		this.btnSeConnecter.setActionCommand("btnSeConnecter");
		
		this.btnQuitter.addActionListener(c);
		this.btnQuitter.setActionCommand("Quitter");
	
		this.setVisible(true);	
	}
	
	/**
	 * Panel central
	 * 
	 * @param pseudo
	 * @param c
	 * @return 
	 */
	public JPanel getPanelConnect(Controleur c){
		
		return panelConnect;
		
	}
	
	public JTextField getJtfNomCompte(){
		return jTFNomCompte;
	}
	
	public JPasswordField getPasswordField(){
		return passwordField;
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
