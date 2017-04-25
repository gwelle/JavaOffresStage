package vuesAdmin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import controleurs.Controleur;

public class V_Ajouter extends JFrame{
	
	private JPanel panelPrincipal;

	private JTextField jTFNom;
	private JTextField jTFPrenom;
	private JTextField jTFLogin;
	private JPasswordField Mdp;	
	
	private JTextField jTFLib;
	
	private JLabel lblNom;
	private JLabel lblPrenom;
	private JLabel lblLogin;
	private JLabel lblMdp;

	private JLabel lblLib;
	
	//Constructeur
	public V_Ajouter() {
	}
	
	public JPanel ajouter(Controleur c, String entite, String action){
		
		//PanelPrincipal
		this.panelPrincipal = new JPanel();
		this.panelPrincipal.setBackground(new Color(58, 65, 94));
		this.panelPrincipal.setBounds(252, 0, 850, 672);
		getContentPane().add(this.panelPrincipal, BorderLayout.CENTER);
		this.panelPrincipal.setLayout(null);
		
		//Création des JTF et lbl
		//JTF et lbl nom de l'étudiant
		lblNom = new JLabel("Nom");
		Font style1 = new Font("Verdana",Font.BOLD,12);
		lblNom.setFont(style1);
		lblNom.setBounds(170, 230, 142, 22);
		lblNom.setForeground(Color.white);
		
		jTFNom = new JTextField();
		jTFNom.setBounds(400, 230, 142, 22);
		jTFNom.setBorder(new LineBorder(new Color(255,255,255)));
		jTFNom.setColumns(10);
		
		//JTF et lbl prénom de l'étudiant
		lblPrenom = new JLabel("Prénom");
		lblPrenom.setFont(style1);
		lblPrenom.setBounds(170, 290, 142, 22);
		lblPrenom.setForeground(Color.white);
	
		jTFPrenom = new JTextField();
		jTFPrenom.setBounds(400, 290, 142, 22);
		jTFPrenom.setBorder(new LineBorder(new Color(255,255,255)));
		jTFPrenom.setColumns(10);
				
		//JTF et lbl Date
		lblLogin = new JLabel("Login");
		lblLogin.setFont(style1);
		lblLogin.setBounds(170, 350, 122, 22);
		lblLogin.setForeground(Color.white);
		
		jTFLogin = new JTextField();
		jTFLogin.setBounds(400, 350, 142, 22);
		jTFLogin.setBorder(new LineBorder(new Color(255,255,255)));
		jTFLogin.setColumns(10);
		
		//JTF et lbl mot de passe de l'étudiant
		lblMdp = new JLabel("Mot de passe");
		lblMdp.setFont(style1);
		lblMdp.setBounds(170, 410, 142, 22);
		lblMdp.setForeground(Color.white);
		
		Mdp = new JPasswordField();
		Mdp.setBounds(400, 410, 142, 22);
		Mdp.setBorder(new LineBorder(new Color(255,255,255)));
		Mdp.setColumns(10);
		
		//JTF et lbl libelle du domaine
		lblLib = new JLabel("Libelle du domaine");
		lblLib.setFont(style1);
		lblLib.setBounds(170, 279, 142, 22);
		lblLib.setForeground(Color.white);
		
		jTFLib = new JTextField();
		jTFLib.setBounds(400, 279, 142, 22);
		jTFLib.setBorder(new LineBorder(new Color(255,255,255)));
		jTFLib.setColumns(10);
		
		//Crï¿½ation boutons Valier et Annuler
		JButton btnValiderAjouter = new JButton("Valider");
		btnValiderAjouter.setBounds(175, 550, 188, 76);
		btnValiderAjouter.addActionListener(c);
		
		btnValiderAjouter.setBackground(new Color(27, 30, 46));
		btnValiderAjouter.setForeground(new Color(210, 234, 252));
		
				
		JButton btnAnnulerAjout = new JButton("Annuler");
		btnAnnulerAjout.setBounds(400, 550, 188, 76);
		btnAnnulerAjout.addActionListener(c);
		btnAnnulerAjout.setActionCommand("AnnulerAjouter");
		btnAnnulerAjout.setBackground(new Color(27, 30, 46));
		btnAnnulerAjout.setForeground(new Color(210, 234, 252));
		
		
		if(action == "ajouterPersonne"){
			panelPrincipal.add(lblNom);
			panelPrincipal.add(jTFNom);
			
			panelPrincipal.add(lblPrenom);
			panelPrincipal.add(jTFPrenom);
			
			panelPrincipal.add(lblLogin);
			panelPrincipal.add(jTFLogin);
			
			panelPrincipal.add(lblMdp);
			panelPrincipal.add(Mdp);
			
			btnValiderAjouter.setActionCommand("ValiderAjouter"+entite);
		}else{
			panelPrincipal.add(lblLib);
			panelPrincipal.add(jTFLib);
			btnValiderAjouter.setActionCommand("ValiderAjouterDomaine");
			
		}
		
		panelPrincipal.add(btnValiderAjouter);
		panelPrincipal.add(btnAnnulerAjout);
				
		return panelPrincipal;
		
	}
	
	public JTextField getjTFNom() {
		return jTFNom;
	}

	public JTextField getjTFPrenom() {
		return jTFPrenom;
	}

	public JTextField getjTFLogin() {
		return jTFLogin;
	}

	public JPasswordField getMdp() {
		return 	Mdp;
	}
	
	public JTextField getjTFLibelle(){
		return jTFLib;
	}

}
