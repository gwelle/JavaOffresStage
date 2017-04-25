package controleurs;

import vues.*;
import modeles.ModelePrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;
import java.util.Date;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import com.toedter.calendar.JDateChooser;

import editeurs.ButtonEditor;
import entites.*;

public class Controleur implements ActionListener{

	ModelePrincipal modele;
	V_Accueil vueAccueil ;
	private String pseudo;
	private String numOffre;
	private String numAncienneEntreprise;
	/**
	 * Constructeur
	 */
	public Controleur(){
		this.modele = new ModelePrincipal();
		vueAccueil = new V_Accueil(this);
		//vueAccueil.panelAccueil(this, "admin");
	}
	
//Connexion a l'application#######################################################################################################################
	/**
	 * Methode servant a crypter le mot de passe en MD5
	 * @param key mot de passe
	 * @return hashString.toString() le mot de passe crypte
	 */
	public static String getEncodedPassword(String key) throws NoSuchAlgorithmException {
		byte[] uniqueKey = key.getBytes();
		byte[] hash = null;
		hash = MessageDigest.getInstance("MD5").digest(uniqueKey);
		StringBuffer hashString = new StringBuffer();
		
		for ( int i = 0; i < hash.length; ++i ) {
			String hex = Integer.toHexString(hash[i]);
			if ( hex.length() == 1 ) {
				hashString.append('0');
				hashString.append(hex.charAt(hex.length()-1));
			} else {
				hashString.append(hex.substring(hex.length()-2));
			}
		}
		return hashString.toString();
	}
	
	public String enleverEspace(String unLogin){
		unLogin = Normalizer.normalize(unLogin, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
		unLogin = unLogin.replaceAll("\\p{Space}", "");
		return unLogin;
	}
	
	/**
	 * Appel de la methode dans le Modele pour la verification de la connexion
	 * 
	 * @param unLogin login de l'utilisateur
	 * @param unPass mot de passe de l'utilisateur
	 * @return laBdd.verifConnexion(unLogin, unPass) Appel de la methode dans le modele pour la connexion
	 */
	public Object[] connect(String unLogin, String unPass){
		String pass = enleverEspace(unPass);
		try {
			pass = getEncodedPassword(pass);	
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modele.verifConnexion(unLogin, pass);
	}
	
	/**
	 * Affiche les infos du comptables
	 * 
	 * @param unLogin login de l'utilisateur
	 * @return laBdd.getInfosComptable(unLogin) Appel de la mï¿½thode dans le modï¿½le
	 */
	public ArrayList afficheInfosU(String unLogin){
		return modele.getInfosUser(unLogin);
	}
	
	private void afficherPageAccueil(String compte) {
		vueAccueil.panelAccueil(this, compte );
		vueAccueil.getV_Haut().getInfo().setText("Gestion des Offres de Stage");
	}
	
	public String getPseudo(){
		
		return this.pseudo;
	}
	
//Affichage des pages commune ############################################################################################################
	private void afficherPageSelectionner(String action, String compte){
		vueAccueil.panelSelectionner(this, action, compte);
	}
	
//Affichage des pages de l'admin #########################################################################################################
	private void afficherPageAjouter(String entite, String action){
		vueAccueil.panelAjouter(this, entite, action);
	}

	public void afficherPageModifier(String entite){
		vueAccueil.panelModifierPersonne(this, entite);
  	   	vueAccueil.getV_Haut().getInfo().setText("Modifier un " + entite);
	}

	//Fonctions admin #####################################################################
	/**
	 * 
	 * @param entite = classe de la personne(etudiant/employe)
	 * @param compte = compte actuel(admin/employe/etudiant)
	 */
	@SuppressWarnings("deprecation")
	private void ajouterPersonne(String entite, String compte) {
		
		String nomPersonne;
    	String prenomPersonne;
    	String loginPersonne;
    	String mdpPersonne;
    
    	//Recuperation des valeurs saisies par l'utilisateur
    	nomPersonne = this.vueAccueil.getV_Ajouter().getjTFNom().getText();
    	prenomPersonne = this.vueAccueil.getV_Ajouter().getjTFPrenom().getText();
    	loginPersonne = this.vueAccueil.getV_Ajouter().getjTFLogin().getText();
    	try {
			mdpPersonne = getEncodedPassword(this.vueAccueil.getV_Ajouter().getMdp().getText());
			//Creation d'une Offre
	    	Personne unPersonne = new Personne(nomPersonne, prenomPersonne, loginPersonne, mdpPersonne);
	    	
	    	 //Insertion de l'entreprise creer dans la base de donnees
	    	  this.modele.insererPersonneAlaBdd(unPersonne, entite);
	    	   
	    	  //Message de confirmation
	    	  JOptionPane.showMessageDialog(null, "Ajout de l'"+entite, "Message de confirmation", JOptionPane.INFORMATION_MESSAGE);   
		} catch(Exception ee){	   
    		System.out.println("ERREUR" + ee.getMessage());
    		ee.printStackTrace();
    	}
    	   
    	//Fermer la vue de la creation des entreprises
    	this.vueAccueil.getV_Ajouter().dispose();
    	   
    	//Retour a la page principal
    	this.afficherPageAccueil(compte);
	}
	
	
	/** 
	 * Methode qui permet de recommencer la saisie des valeurs
	 * 
	 */
	private void recommencerAjouter() {
		   //Remettre les champs vide pour recommencer la saisie
		   this.vueAccueil.getV_Ajouter().getjTFNom().setText("");
    	   this.vueAccueil.getV_Ajouter().getjTFPrenom().setText("");
    	   this.vueAccueil.getV_Ajouter().getjTFLogin().setText("") ;
    	   this.vueAccueil.getV_Ajouter().getMdp().setText("");
	}
	
	/** 
	 * Fonction qui recupere la liste des etudiants
	 * 
	 */
	public Object[][] afficheListePersonne(String entite){
		
		int nbEtudiants = modele.getNbPersonne(entite);
		return modele.getListePersonne(nbEtudiants, entite);
	}
	
	/** 
	 * Fonction qui recupere la liste des etudiants
	 * 
	 */
	public String [][] listeLoginsPersonne(String entite){
		Object tab [][]  = this.afficheListePersonne(entite);
		String tabLogin [][]  = new String[tab.length][2];
		String mdp = null;
		for(int i = 0; i < tab.length; i++){
			tabLogin[i][0] = tab[i][2].toString();
			tabLogin[i][1] = tab[i][3].toString();
		}
		return tabLogin;
	}
	
	public Object [] modifierPersonne(int row, String entite){
		
		Object[] tab = new Object[2];
		String [][] tabLogin = this.listeLoginsPersonne(entite);
   	 
 	   	String unNom = (String) vueAccueil.getV_Modifier().getTable().getValueAt(row, 0);
 	   	String unPrenom = (String) vueAccueil.getV_Modifier().getTable().getValueAt(row, 1);
 	   	String unLogin = (String) vueAccueil.getV_Modifier().getTable().getValueAt(row, 2);
 	   	String unMdp = (String) vueAccueil.getV_Modifier().getTable().getValueAt(row, 3);  
 	   	String mdp = null;
 	   	
 	   	if(unMdp.equals(tabLogin[row][1])){
 	   		mdp = tabLogin[row][1];
 	   	}else {
 	   		try {
	   			mdp = getEncodedPassword(unMdp);
	   		} catch (NoSuchAlgorithmException e1) {
	   			// TODO Auto-generated catch block
	   			e1.printStackTrace();
	   		} 
 	   	}
 	  
 		String idPersonne = modele.getIdPersonne(tabLogin[row][0]);
 	   	Personne unePersonne = new Personne(unNom, unPrenom, unLogin, mdp);

 	   	tab[0] = idPersonne;
 	   	tab[1] = unePersonne;
 	   	
		return tab;
	}
	
	private void ajouterDomaine(String compte) {
		
		String libDomaine;
   
    	//Recuperation des valeurs saisies par l'utilisateur
    	libDomaine = this.vueAccueil.getV_Ajouter().getjTFLibelle().getText();
    	   
    	//Fermer la vue de la creation des entreprises
    	this.vueAccueil.getV_Ajouter().dispose();
    	   
    	this.modele.insererDomaineAlaBdd(libDomaine);
    	
    	//Retour a la page principal
    	this.afficherPageAccueil(compte);
	}
	
	/** 
	 * Fonction qui recupere la liste des etudiants
	 * 
	 */
	public Object[][] afficheListeDomaine(){
		return modele.getListeDomaine();
	}
	

//Affichage des pages de l'etudiant #################################################################################################
	
	/**
	 * Affichage de la page qui permet de chercher une offre de stage
	 */
	private void afficherPageChercherOffreStage(){
		vueAccueil.panelChercher(this);
		vueAccueil.getV_Haut().getInfo().setText("Chercher une Offre de Stage");
	}
	
	/**
	 * Affichage de la page qui permet de consulter une offre de stage en fonction du nom de l'entreprise et de la date de l'Offre
	 * @param name
	 * @param dateDebutOffre
	 */
	private void afficherPageConsultationOffreStage (String name, String dateDebutOffre) {
		
		   vueAccueil.panelConsulter(this, name, dateDebutOffre);
    	   vueAccueil.getV_Haut().getInfo().setText("Consulter les Offres de Stage");
	}
	
//Affichage des pages de l'employe ######################################################################################################
	/**
	 * Affichage de la page qui permet de creer une entreprise
	 */
	private void afficherPageCreerEntreprise(String action) {
		vueAccueil.panelCreer(this, action);
		if(action == "Creer"){
			vueAccueil.getV_Haut().getInfo().setText("Creation d'une entreprise");
		}else{
			vueAccueil.getV_Haut().getInfo().setText("Consulter l'entreprise");
		}	
	}
	
	/**
	 * Affichage de la page qui permet de saisir une offre de stage
	 */
	private void afficherPageSaisirOffreStage(String action) {
		vueAccueil.panelSaisir(this, action);
		if(action == "saisir"){
			vueAccueil.getV_Haut().getInfo().setText("Saisie d'une offre de stage");
		}else{
			vueAccueil.getV_Haut().getInfo().setText("Modifier l'offre de stage");
		}
	}
	
	/**
	 * Affichage de la page qui permet de modifier des offres	 
	 */
	private void afficherPageConsulterOffre(String action) {
		vueAccueil.panelModifierOffre(this, action);
    	vueAccueil.getV_Haut().getInfo().setText("Consulter les offres de stage");
	}

	
	//Fonctions employe ########################################################################################
	public boolean getUneEntreprise(){
		boolean result = false;
		String numEntreprise = modele.possedeUneEntreprise(modele.getIdPersonne(this.getPseudo()));
		if(numEntreprise !=null){
			result = true;
		}
		return result;
	}
	/**
	 * Retourne les noms des entreprises
	 * 
	 * @return modele.getNomEntreprise() methode dans le modele
	 */
	public ArrayList getListeNoms(){
		return modele.getNomEntreprise();
	}
	
	/**
	 * Retourne les domaines
	 * 
	 * @return modele.getDomaine() methode dans le modele
	 */
	public ArrayList getListeDomaines(){
		return modele.getDomaine();
	}
	
	/**
	 * Methode qui permet la creation d'une entreprise apres un clic sur bouton "Valider"
	 */
	private void saisirUneOffre(String compte, String action, String action2) {
		
		String nomEntreprise;
    	String unDomaine;
    	String libOffre;
    	Date uneDate;
    	int uneDuree;
    	String unChemin;
    	String descriptifOffre;
    	  
    	//Recuperation des valeurs saisies par l'utilisateur
    	nomEntreprise = this.vueAccueil.getV_Saisir().getjTFEntreprise().getText();
    	unDomaine = this.vueAccueil.getV_Saisir().getListeDomaine().getSelectedItem().toString();
    	libOffre = this.vueAccueil.getV_Saisir().getjTFLib().getText();
    	uneDate = (this.vueAccueil.getV_Saisir().getUneDate().getDate());
    	uneDuree = Integer.parseInt(this.vueAccueil.getV_Saisir().getjTFDuree().getText());
    	unChemin = this.vueAccueil.getV_Saisir().getjTFChemin().getText();
    	descriptifOffre = this.vueAccueil.getV_Saisir().getjTADescriptif().getText();
    	
    	//Creation d'une Offre
    	OffreStage uneOffre = new OffreStage(nomEntreprise, unDomaine, libOffre,
    	uneDate, uneDuree, unChemin, descriptifOffre );
    	 
    	try{
    		   
    		if(action == "Saisir"){
    			
    			//Insertion de l'entreprise creer dans la base de donnees
    			this.modele.insererOffreAlaBdd(uneOffre);
    	   
    			//Message de confirmation
    			JOptionPane.showMessageDialog(null, "Creation de l'offre",
    					"Message de confirmation", JOptionPane.INFORMATION_MESSAGE);  
    			//Retour e la page principal
    			this.afficherPageAccueil(compte);
    		}else{
    			JOptionPane.showMessageDialog(null, "Modification de l'offre",
    					"Message de confirmation", JOptionPane.INFORMATION_MESSAGE);  
    			this.modele.modifierOffre(this.numOffre, uneOffre);
    			//Retour e la page principal
    	    	this.afficherPageConsulterOffre(action2);
    		}
    	}catch(Exception ee){	   
    		System.out.println("ERREUR" + ee.getMessage());
    		ee.printStackTrace();
    	}
	}
	
	/**
	 * Methode qui recupere les infos d'une entreprise lors de la saisie dans la vue V_Creer() 
	 */
	private Entreprise recupUneEntreprise() {
		
		   String nomEntreprise ;
    	   String adresseEntreprise ;
    	   String codePostalEntreprise  ;
    	   String villeEntreprise ;
    	   String adresseMailEntreprise ;
    	   String numeroTelephoneEntreprise  ;
    	   String secteurActiviteEntreprise ;
    	   Entreprise uneEntreprise = null;
    	   //Recuperation des valeurs saisies par l'utilisateur
    	   nomEntreprise = this.vueAccueil.getV_Creer().getjTFNom().getText() ;
    	   adresseEntreprise = this.vueAccueil.getV_Creer().getjTFAdresse().getText();
    	   codePostalEntreprise = this.vueAccueil.getV_Creer().getjTFCP().getText();
    	   villeEntreprise = this.vueAccueil.getV_Creer().getjTFVille().getText();
    	   adresseMailEntreprise = this.vueAccueil.getV_Creer().getjTFMail().getText();
    	   numeroTelephoneEntreprise = this.vueAccueil.getV_Creer().getjTFTel().getText();
    	   secteurActiviteEntreprise = this.vueAccueil.getV_Creer().getjTFSecteur().getText() ;
    	  
    		//Creation d'une Entreprise
    	   uneEntreprise = new Entreprise(nomEntreprise,adresseEntreprise,codePostalEntreprise,
    			   villeEntreprise,adresseMailEntreprise,numeroTelephoneEntreprise,secteurActiviteEntreprise );

    		return uneEntreprise;
	}
	
	public boolean entrepriseValide(Entreprise uneEntrepise){
		boolean result = false;
		String regMail = "^[a-zA-Z0-9._-]+@[a-z0-9._-]{2,}\\.[a-z]{2,4}$";
		String regCP = "^[0-9]{5}$";
		String regTel = "^[0-9]{10}$";
		
		if(!uneEntrepise.getAdresseMailEntreprise().matches(regMail) || !uneEntrepise.getCodePostalEntreprise().matches(regCP) || !uneEntrepise.getNumeroTelephoneEntreprise().matches(regTel)){
			  
			if(!uneEntrepise.getAdresseMailEntreprise().matches(regMail)){
				JOptionPane.showMessageDialog(null, "Adresse mail invalide",
						"Message d'erreur", JOptionPane.INFORMATION_MESSAGE);  
			}else if(!uneEntrepise.getCodePostalEntreprise().matches(regCP)){
				JOptionPane.showMessageDialog(null, "Code postal invalide",
   					"Message d'erreur", JOptionPane.INFORMATION_MESSAGE);  
			}else if(!uneEntrepise.getNumeroTelephoneEntreprise().matches(regTel)){
				JOptionPane.showMessageDialog(null, "Telephone invalide",
  					"Message d'erreur", JOptionPane.INFORMATION_MESSAGE);  
			}
		}else{
			result = true;
		}
		return result;
	}
	
	/** 
	 * Methode qui permet de recommencer la saisie des valeurs dans la vue V_Creer() de l'employe
	 * 
	 */
	private void recommencerCreer() {
		   //Remettre les champs vide pour recommencer la saisie
    	   this.vueAccueil.getV_Creer().getjTFNom().setText("") ;
    	   this.vueAccueil.getV_Creer().getjTFAdresse().setText("");
    	   this.vueAccueil.getV_Creer().getjTFCP().setText("");
    	   this.vueAccueil.getV_Creer().getjTFVille().setText("");
    	   this.vueAccueil.getV_Creer().getjTFMail().setText("");
    	   this.vueAccueil.getV_Creer().getjTFTel().setText("");
    	   this.vueAccueil.getV_Creer().getjTFSecteur().setText("") ;
	}
	
	/** 
	 * Methode qui recupere les infos de l'entreprise de l'employe
	 * 
	 */
	public Entreprise getInfoEntreprise(){
		String idEmploye = modele.getIdPersonne(getPseudo());
		return modele.getEntreprise(idEmploye);
	}
	
	public Object[][] afficheListeOffreStage(String action){
		Object tab[][] = null;
		String idEmploye = modele.getIdPersonne(getPseudo());
		String numEntreprise = modele.getEntreprise(idEmploye).getNumEntreprise();
		if(action == "Modifier"){
			tab =  modele.getListeOffreStage(numEntreprise);
		}else{
			tab = modele.getListeOffreStageValide(numEntreprise);
		}
		return tab;
	}
	
	public void getUneOffre(String numOffre){
		OffreStage uneOffre = modele.getUneOffreStage(numOffre);
		
		vueAccueil.getV_Saisir().getListeDomaine().setSelectedItem(uneOffre.getDomaineOffre());
		
		vueAccueil.getV_Saisir().getjTFLib().setText(uneOffre.getLibelleOffre());
		
		vueAccueil.getV_Saisir().getUneDate().setDate(uneOffre.getDateDebutOffre());
		vueAccueil.getV_Saisir().getjTFDuree().setText("" + uneOffre.getDureeOffre());
		vueAccueil.getV_Saisir().getjTFChemin().setText(uneOffre.getCheminOffre());
		vueAccueil.getV_Saisir().getjTADescriptif().setText(uneOffre.getDescriptifOffre());
	}
	
	/**
	 * Methode qui permet la creation d'une entreprise apres un clic sur bouton "Valider"
	 */
	/*private void modifierUneOffre(String compte) {
		
		String nomEntreprise;
    	String unDomaine;
    	String libOffre;
    	Date uneDate;
    	int uneDuree;
    	String unChemin;
    	String descriptifOffre;
    	  
    	//Recuperation des valeurs saisies par l'utilisateur
    	nomEntreprise = this.vueAccueil.getV_Saisir().getjTFEntreprise().getText();
    	unDomaine = this.vueAccueil.getV_Saisir().getListeDomaine().getSelectedItem().toString();
    	libOffre = this.vueAccueil.getV_Saisir().getjTFLib().getText();
    	uneDate = (this.vueAccueil.getV_Saisir().getUneDate().getDate());
    	uneDuree = Integer.parseInt(this.vueAccueil.getV_Saisir().getjTFDuree().getText());
    	unChemin = this.vueAccueil.getV_Saisir().getjTFChemin().getText();
    	descriptifOffre = this.vueAccueil.getV_Saisir().getjTADescriptif().getText();
    	
    	//Creation d'une Offre
    	OffreStage uneOffre = new OffreStage(nomEntreprise, unDomaine, libOffre,
    	uneDate, uneDuree, unChemin, descriptifOffre );
    	 
    	try{
    		   
    	  //Insertion de l'entreprise creer dans la base de donnees
    	  this.modele.modifier(uneOffre);
    	   
    	  //Message de confirmation
    	  JOptionPane.showMessageDialog(null, "Creation de l'offre",
				"Message de confirmation", JOptionPane.INFORMATION_MESSAGE);   
    	}catch(Exception ee){	   
    		System.out.println("ERREUR" + ee.getMessage());
    		ee.printStackTrace();
    	}
    	   
    	//Fermer la vue de la creation des entreprises
    	this.vueAccueil.getV_Saisir().dispose();
    	   
    	
	}*/
	
	//Fonction Etudiant##########################################################################################
	
	/**
	 * Retourne les Dates de chaque Offres de Stage
	 * 
	 * @return
	 */
	public ArrayList<Date> afficheListeDates() {
		
		return modele.getDatesOffresStages();
	}
	
	/**
	 * Fonction qui récupére la liste des offres de stage en fonction du nom d'Entreprise et la Date de l'Offre de Stage
	 * @param nameEntreprise
	 * @param date
	 * @return
	 */
	public Object[][] afficheListeOffresDeStage(String nameEntreprise, String date){
		
		
   	 	//On vérifie le nom de l'entreprise et la date de l'offre avant la consultation d'une offre
   	 	return modele.getOffresStage(nameEntreprise, date);
	}

//Action sur les boutons #################################################################################################################
	public void actionPerformed(ActionEvent e){
		
       String commande = e.getActionCommand();
       String entite = "";
       String action = "";
       String action2 = "";
       String compte = "";
       String unLogin = "";
       String numDomaine = "";
       String numOffre = "";
       Object tab[] = null;
      
       int row;
       Entreprise uneEntreprise = null;
       switch (commande) {

       	case "btnSeConnecter":

       		unLogin = enleverEspace(vueAccueil.getJtfNomCompte().getText());
       		boolean b = (boolean)this.connect(unLogin, new String (vueAccueil.getPasswordField().getPassword()))[1];
       		compte = (String) this.connect(unLogin, new String (vueAccueil.getPasswordField().getPassword()))[0];
       		
       		if(b == true ){
       			this.pseudo = unLogin;
       			this.vueAccueil.panelAccueil(this, compte);  
       		}else{      		
       			JOptionPane jop = new JOptionPane();
       			jop.showMessageDialog(null, "Le nom de compte et le mot de passe ne correspondent pas" ,"Information", JOptionPane.INFORMATION_MESSAGE);
       		}

       		break;

       	case "Quitter":

       		System.exit(0);
        	 
       		break;

       	case "Deconnexion":

       		vueAccueil.setContentPane(vueAccueil.getPanelGeneral());
       		vueAccueil.getJtfNomCompte().setText("");
       		vueAccueil.getPasswordField().setText("");
       		vueAccueil.revalidate();
           
       		break;
       		
//#######################################################################################################################################  	
       	//Actions sur les vues de l'admin
        //Ajouter -----------------------------------------------------------------------------------------------------------------------
       	case "Ajouter": //Affiche la page Selectionner avec les boutons Ajouter

       		action = "ajouter";
       		compte = "admin";
       		afficherPageSelectionner(action, compte);
           
       		break;
       	
       	//Etudiant
    	case "AjouterEtudiant": //Affiche la page pour ajouter l'etudiant

    		entite = "Etudiant";
    		action = "ajouterPersonne";
    		afficherPageAjouter(entite, action);
    		vueAccueil.getV_Haut().getInfo().setText("Ajouter un etudiant");
           
       		break;
       		
    	case "ValiderAjouterEtudiant": //Ajout de l'etudiant

    		String entiteAEt = "etudiant";
    		compte = "admin";
    		ajouterPersonne(entiteAEt, compte);
           
       		break;
       		
       	//Employe
    	case "AjouterEmploye": //Affiche la page pour ajouter l'employe

    		entite = "Employe";
      	   	action = "ajouterPersonne";
      	   	afficherPageAjouter(entite, action);
      	   	vueAccueil.getV_Haut().getInfo().setText("Ajouter un employe");
           
       		break;
       		
    	case "ValiderAjouterEmploye": //Ajout de l'employe

    		String entiteAEm = "employe";
      	   	compte = "admin";
      	   	ajouterPersonne(entiteAEm, compte);
           
       		break;
       	
       	//Domaine
    	case "AjouterDomaine"://Affiche la page pour ajouter un domaine
    		
    		action = "ajouterDomaine";
     	   	afficherPageAjouter(entite, action);
     	   	vueAccueil.getV_Haut().getInfo().setText("Ajouter un domaine");
    		
    		break;
    		
    	case "ValiderAjouterDomaine"://Ajout du domaine
    		
    		compte = "admin";
    		ajouterDomaine(compte);
    		
    		break;
    	
    	case "AnnulerAjouter": //Annuler l'ajout

    		recommencerAjouter();
           
       		break;
       		
       	//Modifier -------------------------------------------------------------------------------------------------------------------
    	case "Modifier": //Affiche la page Selectionner avec les boutons Modifier

    		action = "modifier";
      	   	compte = "admin";
      	   	afficherPageSelectionner(action, compte);
           
       		break;
       
       	//ModifierEtudiant
    	case "ModifierEtudiant": //Affichage de la page V_Modifier pour modifier l'etudiant
    		
    		entite = "etudiant";
     	   	afficherPageModifier(entite);
           
       		break;
       		
    	case "ValiderModifierEtudiant": //Modifie l'etudiant
    		
    		entite = "etudiant";
    		row = vueAccueil.getV_Modifier().getBtnValiderModifier().getRow(vueAccueil.getV_Modifier().getTable(), this);
    		tab = this.modifierPersonne(row, entite);
    		modele.modifierPersonne((String) tab[0], (Personne)tab[1]);
    		afficherPageModifier(entite);
           
       		break;
    	
    	case "ValiderSupprimerEtudiant": //Supprime l'etudiant
    		
    		entite = "etudiant";
    		row = vueAccueil.getV_Modifier().getBtnValiderSupprimer().getRow(vueAccueil.getV_Modifier().getTable(), this);
    		unLogin = (String) vueAccueil.getV_Modifier().getTable().getValueAt(row, 2);
    		modele.supprimerPersonne( modele.getIdPersonne(unLogin));
    		afficherPageModifier(entite);
           
       		break;
       
       	//ModifierEmploye
    	case "ModifierEmploye": //Affichage de la page V_Modifier pour modifier l'employe 
    		
    		entite = "employe";
      	   	afficherPageModifier(entite);
           
       		break;
       		
    	case "ValiderModifierEmploye": //Modifie l'etudiant
    		
    		row = vueAccueil.getV_Modifier().getBtnValiderModifier().getRow(vueAccueil.getV_Modifier().getTable(), this);
    		entite = "employe";
    		tab = this.modifierPersonne(row, entite);
    		modele.modifierPersonne((String) tab[0], (Personne)tab[1]);
    		afficherPageModifier(entite);
           
       		break;
       		
    	case "ValiderSupprimerEmploye": //Supprime l'etudiant
    		
    		entite = "employe";
      	   	row = vueAccueil.getV_Modifier().getBtnValiderSupprimer().getRow(vueAccueil.getV_Modifier().getTable(), this);
      	   	unLogin = (String) vueAccueil.getV_Modifier().getTable().getValueAt(row, 2);
      	   	modele.supprimerPersonne( modele.getIdPersonne(unLogin));
      	   	afficherPageModifier(entite);
           
       		break;
       		
       	//ModifierDomaine
    	case "ModifierDomaine": //Affichage de la page V_Modifier pour modifier un domaine 
    		
    		entite = "domaine";
    		afficherPageModifier(entite); 
           
       		break;
       		
    	case "ValiderModifierDomaine": //Modifie le domaine
    		
    		entite = "domaine";
    		row = vueAccueil.getV_Modifier().getBtnValiderModifier().getRow(vueAccueil.getV_Modifier().getTable(), this);
    		numDomaine = (String) vueAccueil.getV_Modifier().getTable().getValueAt(row, 0);
    		String libDomaine = (String) vueAccueil.getV_Modifier().getTable().getValueAt(row, 1);
    		modele.modifierDomaine(numDomaine, libDomaine);
    		afficherPageModifier(entite);
           
       		break;
       		
    	case "ValiderSupprimerDomaine": //Supprime l'etudiant
    		
    		entite = "domaine";
    		row = vueAccueil.getV_Modifier().getBtnValiderSupprimer().getRow(vueAccueil.getV_Modifier().getTable(), this);
    		numDomaine = (String) vueAccueil.getV_Modifier().getTable().getValueAt(row, 0);
    		modele.supprimerDomaine(numDomaine);
    		afficherPageModifier(entite);
           
       		break;
       		
//#######################################################################################################################################       
        //Actions sur les vues de l'employe
       	//Creer -------------------------------------------------------------------------------------------------------------------------
       	//Entreprise
    	case "Creer": //Affichage de la page V_Creer pour creer une entreprise
    		
    		action ="Creer";
    		afficherPageCreerEntreprise(action);
           
       		break;
       		
    	case "ValiderCreer": //Creation d'une entreprise
    		action = "Creer";
    		compte = "employe";
    		uneEntreprise = recupUneEntreprise();
    		
    		if(entrepriseValide(uneEntreprise)!=false){
    			//Insertion de l'entreprise creer dans la base de donnees
    			this.modele.insererEntrepriseAlaBdd(uneEntreprise, this.modele.getIdPersonne(this.getPseudo()));
    			
    			//Message de confirmation
    			JOptionPane.showMessageDialog(null, "Creation de l'entreprise","Message de confirmation", JOptionPane.INFORMATION_MESSAGE); 
    			//Ferme la vue de la creation des entreprises
    			this.vueAccueil.getV_Creer().dispose();
    			
    			//Retour a la page principal
    			this.afficherPageAccueil(compte);
    		}
           
       		break;
       		
    	case "AnnulerCreer": //Annuler la creation d'une entreprise
    		
    		recommencerCreer();
    		afficherPageCreerEntreprise(action);
           
       		break;
       		
       	//Offre de stage
    	case "Saisir": //Affichage de la page V_Saisir pour saisir une offre de stage
    		
    		action ="Saisir";  
    		afficherPageSaisirOffreStage(action);
           
       		break;
       		
    	case "ValiderSaisirOffre": //Valide la creation d'une offre de stage
    		
    		compte = "employe";
    		action = "Saisir";
    		action2 = "Modifier";
    		saisirUneOffre(compte, action, action2);
    		
       		break;
       		
    	case "AnnulerSaisir": //Annule la creation d'une offre de stage
    		
    		action = "Saisir";
    		//Met les champs de la page V_Saisir à vide
    		afficherPageSaisirOffreStage(action);
    		
       		break;
       	
       	//Entreprise	
    	case "ConsulterEntreprise":	//Affichage de la page V_Creer pour modifier une entreprise
    		
    		action = "modifier";
    		afficherPageCreerEntreprise(action);
    		Entreprise ancienneEntreprise = modele.getEntreprise(modele.getIdPersonne(getPseudo()));
    		this.numAncienneEntreprise = ancienneEntreprise.getNumEntreprise();
    	
       		break;
       		
    	case "ModifierEntreprise":	//Modifie l'entreprise
    		
    		uneEntreprise = recupUneEntreprise();
    		
	    	if(entrepriseValide(uneEntreprise) != false){
	    		modele.modifierEntreprise(this.numAncienneEntreprise, uneEntreprise);
	    		action = "modifier";
	    		JOptionPane.showMessageDialog(null, "Modification de l'entreprise","Message de confirmation", JOptionPane.INFORMATION_MESSAGE); 
	    		afficherPageCreerEntreprise(action);
    		}
    		
       		break;
       		
    	case "ConsulterEmploye":
    		action = "consulterOffre";
    		compte = "employe";
    		afficherPageSelectionner(action, compte);
    		
    		break;
       		
       	//Offre de stage
    	case "ConsulterOffreStage":	//Consulter les offres de stage
    		action2 = "Modifier";
    		afficherPageConsulterOffre(action2);
    		
       		break;
       		
    	case "ModifierUneOffre": //Consulte l'offre a modifier via la page V_Saisir
    		
    		action = "Modifier";
    		row = vueAccueil.getV_ModifierOffre().getBtnValiderModifier().getRow(vueAccueil.getV_ModifierOffre().getTable(), this);
    	   	numOffre = vueAccueil.getV_ModifierOffre().getTable().getValueAt(row, 0).toString();
    	   	this.numOffre = numOffre;
    	   	OffreStage uneOffre = modele.getUneOffreStage(numOffre);
    	   	afficherPageSaisirOffreStage(action);
    	   	this.getUneOffre(numOffre);
    		
       		break;
       		
    	case "ValiderModifierOffre":	//
    		
    		compte = "employe";
    		action = "Modifier";
    		action2 = "Modifier";
    		this.saisirUneOffre(compte, action, action2);
        	   	
       		break;	
       		
    	case "ValiderSupprimerOffre": //Consulte l'offre a modifier via la page V_Saisir
    		
    		row = vueAccueil.getV_ModifierOffre().getBtnValiderSupprimer().getRow(vueAccueil.getV_ModifierOffre().getTable(), this);
    		numOffre = vueAccueil.getV_ModifierOffre().getTable().getValueAt(row, 0).toString();
    		this.modele.supprimerOffre(numOffre);
    		action2 = "Modifier";
    		afficherPageConsulterOffre(action2);
    		
       		break;
       		
    	case "ConsulterOffreStageValide":
    		action2 = "Valider";
    		afficherPageConsulterOffre(action2);
    		
    		break;
       		
    	case "ValiderOffre": //Valide l'offre de stage passe l'etat de oui a non
    		
    		String etat = "";
    		row = vueAccueil.getV_ModifierOffre().getBtnValider().getRow(vueAccueil.getV_ModifierOffre().getTable(), this);
    		
    		numOffre = vueAccueil.getV_ModifierOffre().getTable().getValueAt(row, 0).toString();
    
    		if(this.modele.etatOffre(numOffre).equals("non")){
    			etat = "oui";
    		}else{
    			etat = "non";
    		}
    		this.modele.validerOffre(numOffre, etat);
    		action2 = "Valider";
    		afficherPageConsulterOffre(action2);
    		
    		break;
    
//#######################################################################################################################################       
        //Actions sur les vues de l'etudiant       		
//    	case "Consulter": //Consulter les offres de stage
//    		
//    		action = "modifier";
//    		afficherPageCreerEntreprise(action);
//    		 
//       		break;
    		
    	case "EtudiantChercherConsulter": //Appuie sur le bouton Consulter les offres de stage
    		
    		this.afficherPageChercherOffreStage();
    		 
       		break;
       		
    	case "EtudiantAnnulerChercherConsulter": //Appuie sur le bouton "Annuler" pour la chercher d'une offre de stage
      		
      		this.afficherPageChercherOffreStage();
      		
      		break ;
      		
    	case "ValiderChercherConsulter": //Appuie sur le bouton "Valider" pour consulter une offre de stage
      		
    		String nameEntreprise = this.vueAccueil.getVueChercher().getListeEntreprises().getSelectedItem().toString();
	  		System.out.println("Nom de l'entreprise : "+nameEntreprise);
	     	 
	  		JComboBox<java.sql.Date> dateDebutOffre = this.vueAccueil.getVueChercher().getListeDates();
	     	String laDateConverti = dateDebutOffre.getSelectedItem().toString();
	     	System.out.println("Date converti  : "+laDateConverti);
	     	
	     	this.afficherPageChercherOffreStage();
	    	this.afficherPageConsultationOffreStage(nameEntreprise, laDateConverti);
      		
      		break ;
      		
    	case "ValiderBoutonConsulter": //Appuie sur le bouton "Consulter" pour consulter les infos de l'offre
      		
    		
     		JOptionPane.showMessageDialog(null, "Nom de l'Entreprise  : " + modele.getNameEntreprise() +"\n" 
     					+ "Ville de l'Entreprise : " + modele.getVilleEntreprise() + "\n"
     					+ "Mail de l'Entreprise : " + modele.getMailEntreprise() + "\n"
     					+ "Domaine de l'Offre :  " + modele.getDomaineOffre() + "\n"
     					+ "Libellé de l'Offre :  " + modele.getLibelleOffre() + "\n"
     					+ "Date de l'Offre :  " + modele.getDateDebutOffre() + "\n"
     					+ "Durée de l'Offre :  " + modele.getDureeOffre() + "\n"
     					+ "Descriptif de l'Offre :  " + modele.getDescriptifOffre() + "\n"
     					, "Consultation des informations de  l'offre de Stage", JOptionPane.INFORMATION_MESSAGE,null);
  
      		break ;
      		
    	case "ValiderBoutonPostuler":
      		
      		int choix =  JOptionPane.showConfirmDialog(this.vueAccueil, "Voulez-vous vraiment Postuler ?",
     			   "Confirmation de la demande", 
 					JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
 			
 			if(choix == JOptionPane.YES_OPTION){
 				
 				String numPersonne = modele.getIdPersonne(getPseudo());
 				row = vueAccueil.getV_Consulter().getBtnPostuler().getRow(vueAccueil.getV_Consulter().getTable(), this);
 		    	String numOffreStage = vueAccueil.getV_Consulter().getTable().getValueAt(row, 0).toString();
 		    			

 		    	modele.validerOffreStage(numPersonne, numOffreStage);

 		    	//EnregistrerOffreStage enregOffre = new EnregistrerOffreStage(numEtudiant,numOffreStage,valideOffre);
 		    	/*this.modele.enregistrerOffreStage(enregOffre);
 		    	System.out.print("Resultat EnregistreOffre : "+enregOffre.toString());*/
 		    	
 			}
 			
 			break ;
       }

	}	
}
