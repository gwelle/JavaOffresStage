package vues;
import vuesAdmin.*;
import vuesEmploye.*;
import vuesEtudiant.*;
import controleurs.Controleur;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.CompoundBorder;

// Cr�ation d�une classe Fenetre h�ritant de la classe JFrame

public class V_Accueil extends JFrame implements ActionListener{
	
	// VUES
	V_Connexion vueConnexion;
	V_MenuEmploye vueMenuEmploye = new V_MenuEmploye();
	V_MenuEtudiant vueMenuEtudiant = new V_MenuEtudiant();
	V_MenuAdmin vueMenuAdmin = new V_MenuAdmin();
	V_Haut vueHaut = new V_Haut();
	
	//Vues admin
	V_Selectionner vueSelectionner = new V_Selectionner();
	V_Ajouter vueAjouter = new V_Ajouter();
	V_Modifier vueModifier = new V_Modifier();
	
	//Vues employe
	V_Creer vueCreer = new V_Creer();
	V_Saisir vueSaisir = new V_Saisir();
	V_ModifierOffre vueModifierOffre = new V_ModifierOffre();
	
	//Vues etudiant
	V_Chercher vueChercher = new V_Chercher();
	V_Consulter vueConsulter = new V_Consulter();
	
	// PANEL
	JPanel panelGeneral;
	JPanel panelFond;
	JPanel panel;
	JPanel panelCentral;
	JPanel panelPrincipal;
	JPanel panelDroite;
	JPanel panelConnect;
	
	// JTF
	private JTextField jTFNomCompte;
	private JPasswordField passwordField;
		
	//LABEL
	private JLabel lblNomDeCompte;
	private JLabel lblConnexion;
	private	JLabel lblMotDePasse;
	private	JLabel lblNom;
	private	JLabel lblPrenom;
			
			
	// BOUTONS 
	private	JButton btnQuitter;
	private	JButton btnSeConnecter;

	// STYLE
	Font style1;

	public V_Accueil(Controleur c){
		this.setTitle("Gestion des offres de stages"); 

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setSize(1150, 720);
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
	
	
	public V_Accueil(String ok){
		this.dispose();
	}
	
	public JTextField getJtfNomCompte(){
		return jTFNomCompte;
	}
	
	public JPasswordField getPasswordField(){
		return passwordField;
	}
	
	public JPanel getPanelGeneral(){
		return panelGeneral;
	}
	
//Creation des differents panels ########################################################################################################
	/**
	 * Panel central Admin
	 *
	 * @param c
	 */
	public void panelAccueil(Controleur c, String compte){
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		this.setContentPane(panel);
		if(compte == "admin"){
			panel.add(vueMenuAdmin.menu(c));
		}else if(compte == "etudiant"){
			panel.add(vueMenuEtudiant.menu(c));
		}else{
			panel.add(vueMenuEmploye.menu(c));
		}
		panel.add(vueHaut.panelHaut());
		panelCentral = new JPanel();
		panelCentral.setBackground(new Color(58, 65, 94));
		panelCentral.setBounds(252, 0, 950, 1000);
		
		panel.add(panelCentral);
		
		this.setVisible(true);
		
	}

//Panel de l'admin ######################################################################################################################
	
	public void panelSelectionner(Controleur c, String action, String compte){
		JPanel panel = new JPanel();
		panel.setLayout(null);
		this.setContentPane(panel);
		if(compte == "admin"){
			panel.add(vueMenuAdmin.menu(c));
		}else if(compte == "employe"){
			panel.add(vueMenuEmploye.menu(c));
		}
		
		panel.add(vueHaut.panelHaut());
		panel.add(vueSelectionner.selectionner(c, action));
		panelCentral = new JPanel();
		panelCentral.setBackground(new Color(58, 65, 94));
		panelCentral.setBounds(252, 0, 950, 1000);
		panelCentral.setLayout(null);
		
		panel.add(panelCentral);
		
		this.setVisible(true);
	}
	
	/**
	 * Panel pour ajouter un etudiant/employ�
	 * 
	 * @param c
	 */
	public void panelAjouter(Controleur c, String entite, String action){
		JPanel panel = new JPanel();
		panel.setLayout(null);
		this.setContentPane(panel);
		
		panel.add(vueMenuAdmin.menu(c));
		panel.add(vueHaut.panelHaut());
		panel.add(vueAjouter.ajouter(c, entite, action));
		panelCentral = new JPanel();
		panelCentral.setBackground(new Color(58, 65, 94));
		panelCentral.setBounds(252, 0, 950, 1000);
		panelCentral.setLayout(null);
		
		panel.add(panelCentral);
		
		this.setVisible(true);
	}
	
	
	/**
	 * Panel pour modifier un etudiant
	 * 
	 * @param c
	 */
	public void panelModifierPersonne(Controleur c, String entite){
		JPanel panel = new JPanel();
		panel.setLayout(null);
		this.setContentPane(panel);
		
		panel.add(vueMenuAdmin.menu(c));
		panel.add(vueHaut.panelHaut());
		panel.add(vueModifier.table(c, entite));
		panelCentral = new JPanel();
		panelCentral.setBackground(new Color(58, 65, 94));
		panelCentral.setBounds(252, 0, 950, 1000);
		panelCentral.setLayout(null);
		
		panel.add(panelCentral);
		
		this.setVisible(true);
	}
//Panels de l'employe ###################################################################################################################
	
	/**
	 * Panel pour creer une entreprise
	 * 
	 * @param c
	 */
	public void panelCreer(Controleur c, String action){
		JPanel panel = new JPanel();
		panel.setLayout(null);
		this.setContentPane(panel);
		
		panel.add(vueMenuEmploye.menu(c));
		panel.add(vueHaut.panelHaut());
		panel.add(vueCreer.creer(c, action));
		panelCentral = new JPanel();
		panelCentral.setBackground(new Color(58, 65, 94));
		panelCentral.setBounds(252, 0, 950, 1000);
		panelCentral.setLayout(null);
		
		panel.add(panelCentral);
		
		this.setVisible(true);
	}
	
	/**
	 * Panel pour saisir une offre de stage
	 * 
	 * @param c
	 */
	public void panelSaisir(Controleur c, String action){
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		this.setContentPane(panel);
		
		panel.add(vueMenuEmploye.menu(c));
		panel.add(vueHaut.panelHaut());
		panel.add(vueSaisir.saisir(c, action));
		panelCentral = new JPanel();
		panelCentral.setBackground(new Color(58, 65, 94));
		panelCentral.setBounds(252, 0, 950, 1000);
		panelCentral.setLayout(null);
		
		panel.add(panelCentral);
		
		
		this.setVisible(true);
	}
	
	/**
	 * Panel pour creer une entreprise
	 * 
	 * @param c
	 */
	public void panelModifierOffre(Controleur c, String action){
		JPanel panel = new JPanel();
		panel.setLayout(null);
		this.setContentPane(panel);
		
		panel.add(vueMenuEmploye.menu(c));
		panel.add(vueHaut.panelHaut());
		panel.add(vueModifierOffre.tableOffres(c, action));
		panelCentral = new JPanel();
		panelCentral.setBackground(new Color(58, 65, 94));
		panelCentral.setBounds(252, 0, 950, 1000);
		panelCentral.setLayout(null);
		
		panel.add(panelCentral);
		
		this.setVisible(true);
	}
	
	
//Panels de l'etudiant ##################################################################################################################
	
	/**
	 * Panel pour chercher une offre de stage avant sa consulter
	 * 
	 * 
	 * @param controleur
	 */
	public void panelChercher(Controleur controleur){
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		this.setContentPane(panel);
		
		panel.add(vueMenuEtudiant.menu(controleur));
		panel.add(vueHaut.panelHaut());
		panel.add(vueChercher.chercher(controleur));
		panelCentral = new JPanel();
		panelCentral.setBackground(new Color(192, 192, 192));
		panelCentral.setBounds(252, 0, 850, 672);
		panelCentral.setLayout(null);
		
		panel.add(panelCentral);
		
		
		this.setVisible(true);
	}
	
	/**
	 * Panel pour saisir une offre de stage
	 * @param controleur
	 * @param name
	 * @param dateDebutOffre
	 */
	public void panelConsulter(Controleur controleur, String name, String dateDebutOffre){
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		this.setContentPane(panel);
		
		panel.add(vueMenuEtudiant.menu(controleur));
		panel.add(vueHaut.panelHaut());
		panel.add(vueConsulter.consulter(controleur, name, dateDebutOffre));
		panelCentral = new JPanel();
		panelCentral.setBackground(new Color(192, 192, 192));
		panelCentral.setBounds(252, 0, 850, 672);
		panelCentral.setLayout(null);
		
		panel.add(panelCentral);
		
		
		this.setVisible(true);
	}

	
	public void actionPerformed(ActionEvent e) {
	}
	
	
//Return des vues
	/**
	 * Appel la vue du haut
	 * 
	 * @return vueHaut
	 */
	public V_Haut getV_Haut(){
		return vueHaut;
	}
	
	public V_Selectionner getV_Selectionner(){
		return vueSelectionner;
	}
	
//Admin #############################################################################################################################
	
	
	//Ajouter un personne
	public V_Ajouter getV_Ajouter(){
		return vueAjouter;
	}
	
	//Modifier une personne
	public V_Modifier getV_Modifier(){
		return vueModifier;
	}
	//Modifier un etudiant/employe
/*	public V_ModifierPersonne getV_ModifierPersonne(){
		return vueModifierPersonne;
	}*/
	
//Employe ###########################################################################################################################
	/**
	 * Appel la vue pour cr�er une entreprise
	 * 
	 * @return vueCreer
	 */
	public V_Creer getV_Creer(){
		return vueCreer;
	}
	
	/**
	 * Appel la vue pour saisir une offre de stage
	 * 
	 * @return vueSaisir
	 */
	public V_Saisir getV_Saisir(){
		return vueSaisir;
	}
	
	/**
	 * Appel la vue pour consulter les offres
	 * 
	 * @return vueModifierOffre
	 */
	public V_ModifierOffre getV_ModifierOffre(){
		return vueModifierOffre;
	}

//Etudiant ##########################################################################################################################
	/**
	 * Appel la vue pour consulter une offre de stage
	 * 
	 * @return vueSaisir
	 */
	public V_Consulter getV_Consulter(){
		return vueConsulter;
	}
	
	/**
	 * Appel de la vue pour chercher une offre de stage
	 * @return the vueChercher
	 */
	public V_Chercher getVueChercher() {
		return vueChercher;
	}

	
	public JTextField getJTFPseudo(){
		return jTFNomCompte;
	}
	
}