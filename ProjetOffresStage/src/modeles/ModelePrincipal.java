package modeles;
import entites.*;
import techniques.ConnexionBD;

import java.util.Date;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;


/**
 * ModÃ¨le principal
 * 
 */
public class ModelePrincipal {

	private Connection connexion;
	private Statement st;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Object[][] listeConsultationOffre ;
	
	String nameEntreprise ;
	String villeEntreprise;
	String mailEntreprise ;
	String domaineOffre ;
	String libelleOffre;
	Date dateDebutOffre ;
	Integer dureeOffre ;
	String descriptifOffre ;

	String	numOffreStage ;
	
	


	public ModelePrincipal() {
		
		connexion = ConnexionBD.getConnexion();
		try {
			this.st = connexion.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//Fonction connexion/deconnexion ########################################################################################################
	/**
	 * Verification du login et du mot de passe
	 * 
	 * @param leLogin
	 * @param pass
	 * @return verifC ArrayList 
	 */
	public Object[] verifConnexion(String leLogin, String pass){
		String login ="";
		String id ="";
		String idPersonne ="";
		Object[] verifC = new Object[2];
		boolean result = false;
	
		try {
			verifC[1] = result;
		
			pstmt = connexion.prepareStatement("SELECT idPersonne, login FROM personne WHERE login= ?  AND mdp= ?");
			pstmt.setString(1, leLogin);
			pstmt.setString(2, pass);
			rs = pstmt.executeQuery();
			
			//Boucle pour le parcours
			//Rï¿½cupï¿½re l'id ou le login d'une personne
			while (rs.next() ) {
				login = rs.getString("login");
				if (leLogin.equals(login)){
					id = rs.getString("idPersonne");
					result = true;
				}
			}
			rs.close();
			if(result == true){
					
				pstmt = connexion.prepareStatement("SELECT idPersonne FROM admin WHERE idPersonne = ?");
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
			
				//Rï¿½cupï¿½re idPersonne de la table etudiant
				while(rs.next()){
					idPersonne = rs.getString("idPersonne");
				}
				
				if(idPersonne != null && idPersonne != ""){
					verifC[0] = "admin";
					verifC[1] = result;
				}else{
					
					pstmt = connexion.prepareStatement("SELECT idPersonne FROM etudiant WHERE idPersonne = ?");
					pstmt.setString(1, id);
					rs = pstmt.executeQuery();
				
					//Rï¿½cupï¿½re idPersonne de la table etudiant
					while(rs.next()){
						idPersonne = rs.getString("idPersonne");
					}
					
					if(idPersonne != null && idPersonne != ""){
						verifC[0] = "etudiant";
						verifC[1] = result;
					}else{
						verifC[0] = "employe";
						verifC[1] = result;
					}
				}
			}
			
		this.deconnexion();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return verifC;
	}
	
	/**
	 * Rï¿½cupï¿½re les infos de l'utilisateur dans la BDD
	 * 
	 * @param leLogin
	 * @return ArrayList infosC avec le nom et le prï¿½nom
	 */
	public ArrayList getInfosUser(String leLogin){
		ArrayList infosU = new ArrayList();
		try {
			
			PreparedStatement st = connexion.prepareStatement("SELECT nom, prenom FROM personne WHERE login= ?");
			st.setString(1, leLogin);
			rs = st.executeQuery();
			
			String nom = "";
			String prenom = "";
		
			while (rs.next()) {
				nom = rs.getString(1);
				prenom = rs.getString(2);
				infosU.add(nom);
				infosU.add(prenom);
			}
			this.deconnexion();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Driver non charge !" + e);
			//e.printStackTrace();
		}
		return infosU;
	}
	
	/**
	 * Deconnecte l'utilisateur
	 */
	public void deconnexion(){
		try {
			rs.close() ; // Permet de liberer la memoire utilisee.
		
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Driver non charge !" + e);
			//e.printStackTrace();
		}
	}
//Fonctions employe ###################################################################################################################################
	
	/**
	 * Inserer une Entreprise dans la base de donnees
	 * @param entreprise
	 */
	public void insererEntrepriseAlaBdd(Entreprise entreprise, String idEmploye){
		
		String requete = null ;
		String numEntreprise = null;
		pstmt = null;
	
		requete = "INSERT INTO entreprise(nomEntreprise, adresseEntreprise, cpEntreprise, villeEntreprise,"
				+ "mailEntreprise, telEntreprise, secteurActivite)"
				+ "VALUES(?,?,?,?,?,?,?);" ;
		try {
				
			pstmt = connexion.prepareStatement(requete);
				
			pstmt.setString(1, entreprise.getNomEntreprise());
			pstmt.setString(2, entreprise.getAdresseEntreprise());
			pstmt.setString(3, entreprise.getCodePostalEntreprise());
			pstmt.setString(4, entreprise.getVilleEntreprise());
			pstmt.setString(5, entreprise.getAdresseMailEntreprise());
			pstmt.setString(6, entreprise.getNumeroTelephoneEntreprise());
			pstmt.setString(7, entreprise.getSecteurActiviteEntreprise());
			pstmt.executeUpdate();

			numEntreprise = this.getNumEntreprise(entreprise.getNomEntreprise());
			pstmt = connexion.prepareStatement("UPDATE employe SET numEntreprise = ? WHERE idPersonne = ?;");	
			pstmt.setString(1, numEntreprise);
			pstmt.setString(2, idEmploye);
			pstmt.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @return listeNomE une liste des noms d'entreprise
	 */
	public ArrayList getNomEntreprise(){
		ArrayList listeNomE = new ArrayList();
		
		try {
			rs = st.executeQuery("SELECT DISTINCT(nomEntreprise) FROM entreprise");
			String nomE = "";
			while (rs.next()) {
				nomE = rs.getString(1);
				listeNomE.add(nomE);
			}
			this.deconnexion();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Driver non charge !" + e);
			//e.printStackTrace();
		}
		return listeNomE;
	}
	
	/**
	 * 
	 * @return listeNomE une liste des noms d'entreprise
	 */
	public ArrayList getDomaine(){
		ArrayList listeDomaine = new ArrayList();
	
		try {
			rs = st.executeQuery("SELECT libDomaineOffre FROM domaineoffre");
			int idDomaine;
			String domaine = "";
			
			while (rs.next()) {
				domaine = rs.getString(1);
				listeDomaine.add(domaine);
			}
			this.deconnexion();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Driver non charge !" + e);
			//e.printStackTrace();
		}
		return listeDomaine;
	}
	
	/**
	 * Recupere le numero du domaine selon le nom
	 * 
	 * @param unDomaine
	 * @return idDomaine ID du domaine
	 */
	public String getNumDomaine(String libDomaine){
		
		String numDomaine = null;
		pstmt = null;
		
		try {
			pstmt = connexion.prepareStatement("SELECT numDomaineOffre FROM domaineoffre WHERE libDomaineOffre = ?");
			pstmt.setString(1, libDomaine);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				numDomaine = rs.getString(1);
			}
			this.deconnexion();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return numDomaine;
	}
	
	public String getNumEntreprise(String nomEntreprise) {
		
		String numEntreprise = null;
		pstmt = null;
		
		try {
			
			pstmt = connexion.prepareStatement("SELECT numEntreprise FROM entreprise WHERE nomEntreprise = ?");
			pstmt.setString(1, nomEntreprise);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				numEntreprise = rs.getString(1);
			}
			this.deconnexion();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return numEntreprise;
		
	}
	
	public String getEmployeNumEntreprise(String idEmploye) {
		
		String numEntreprise = null;
		pstmt = null;
		
		try {
			
			pstmt = connexion.prepareStatement("SELECT numEntreprise FROM employe WHERE numEntreprise = ?");
			pstmt.setString(1, idEmploye);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				numEntreprise = rs.getString(1);
			}
			this.deconnexion();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return numEntreprise;
		
	}
	
	/**
	 * Inserer une Offre dans la base de donnees
	 * @param entreprise
	 */
	public void insererOffreAlaBdd(OffreStage uneOffre){
		
		String requete1 = null ;
		
		PreparedStatement pstmt = null;
	
		requete1 = "INSERT INTO offrestage(numDomaineOffre, descriptifOffre, libelleOffre, dateDebutOffre,"
				+ "dureeOffre, cheminOffre, numEntreprise)"
				+ "VALUES(?,?,?,?,?,?,?);";
		
		try {
				
			pstmt = connexion.prepareStatement(requete1);	
			
			pstmt.setString(1, this.getNumDomaine(uneOffre.getDomaineOffre()));
			pstmt.setString(2, uneOffre.getDescriptifOffre());
			pstmt.setString(3, uneOffre.getLibelleOffre());
			pstmt.setDate(4, new java.sql.Date(uneOffre.getDateDebutOffre().getTime()));
			pstmt.setInt(5, uneOffre.getDureeOffre());
			pstmt.setString(6, uneOffre.getCheminOffre());
			pstmt.setString(7, this.getNumEntreprise(uneOffre.getNomEntreprise()));
			pstmt.executeUpdate();
			
		//	pstmt = connexion.prepareStatement();	

		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Entreprise getEntreprise(String idEmploye){
		
		String numEntreprise = "";
		Entreprise uneEntreprise = null;
		try {
			pstmt = connexion.prepareStatement("SELECT numEntreprise FROM employe WHERE idPersonne = ?");
			pstmt.setString(1, idEmploye);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				numEntreprise = rs.getString(1);
			}
		
			pstmt = connexion.prepareStatement("SELECT numEntreprise, nomEntreprise, adresseEntreprise, cpEntreprise, villeEntreprise,"
				+ "mailEntreprise, telEntreprise, secteurActivite FROM entreprise WHERE numEntreprise = ?");
			pstmt.setString(1, numEntreprise);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				uneEntreprise = new Entreprise(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
			}
			this.deconnexion();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Driver non charge !" + e);
			//e.printStackTrace();
		}
		return uneEntreprise;
	}
	
	/**
	 * Modifie l'entreprise
	 * 
	 * @param idEtudiant, nomE, prenomE, loginE, mdpE
	 */
	public void modifierEntreprise (String numEntreprise, Entreprise uneEntreprise){
		
		try {
			//numEntreprise, nomEntreprise, adresseEntreprise, cpEntreprise, villeEntreprise," "mailEntreprise, telEntreprise, secteurActivite FROM entreprise WHERE numEntreprise = ?");
			pstmt = connexion.prepareStatement("UPDATE entreprise SET nomEntreprise = ?, adresseEntreprise = ?, cpEntreprise = ?, villeEntreprise = ?, mailEntreprise = ?, telEntreprise = ?, secteurActivite = ? WHERE numEntreprise = ?");
			System.out.println(uneEntreprise.getNomEntreprise());
			pstmt.setString(1, uneEntreprise.getNomEntreprise());
			pstmt.setString(2, uneEntreprise.getAdresseEntreprise());
			pstmt.setString(3, uneEntreprise.getCodePostalEntreprise());
			pstmt.setString(4, uneEntreprise.getVilleEntreprise());
			pstmt.setString(5, uneEntreprise.getAdresseMailEntreprise());
			pstmt.setString(6, uneEntreprise.getNumeroTelephoneEntreprise());
			pstmt.setString(7, uneEntreprise.getSecteurActiviteEntreprise());
			pstmt.setString(8, numEntreprise);
			pstmt.executeUpdate();
		
			this.deconnexion();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Driver non charge !" + e);
			//e.printStackTrace();
		}
	}
	
	//A modifier
	public int nbOffreStage(String numEntreprise){
		int nb = 0;
		
		try {
			pstmt = connexion.prepareStatement("SELECT COUNT(numOffreStage) AS nbOffre FROM offrestage WHERE numEntreprise = ?");
			pstmt.setString(1, numEntreprise);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				nb = rs.getInt(1);
			}
			this.deconnexion();
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Driver non charge !" + e);
			//e.printStackTrace();
		}
		return nb;
	}
	
	
	
	//recupere toutes les offres de stages
	public Object[][] getListeOffreStage(String numEntreprise){
	
		int nb = this.nbOffreStage(numEntreprise);
		System.out.println(nb);
		Object[][] tabOffre = new Object[nb][8];
	
		try {

			pstmt = connexion.prepareStatement("SELECT 	numOffreStage, numEntreprise, domaineoffre.libDomaineOffre,  libelleOffre,"
				  + "dateDebutOffre, dureeOffre FROM offrestage, domaineoffre " 
				  + "WHERE numEntreprise = ? AND offrestage.numDomaineOffre = domaineoffre.numDomaineOffre ORDER BY numOffreStage");
			pstmt.setString(1, numEntreprise);
			rs = pstmt.executeQuery();
	
			int i = 0;
				
			while (rs.next()) {
				tabOffre[i][0] = rs.getString(1);
				tabOffre[i][1] = rs.getString(2);
				tabOffre[i][2] = rs.getString(3);
				tabOffre[i][3] = rs.getString(4);
				//Date
				tabOffre[i][4] = rs.getDate(5);
				tabOffre[i][5] = rs.getString(6);
				
				tabOffre[i][6] = "Modifier";
				tabOffre[i][7] = "Supprimer";
				i++;
			}
				
			this.deconnexion();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Driver non charge !" + e);
			//e.printStackTrace();
		}
		return tabOffre;
	}
	
	
	public int nbOffreStageValide(String numEntreprise){
		int nb = 0;
		try {
			pstmt = connexion.prepareStatement("SELECT COUNT(idOffreStage) AS nbOffreValide FROM offrestageenregistree, offrestage WHERE offrestageenregistree.idOffreStage = offrestage.numOffreStage AND numEntreprise = ?");
			pstmt.setString(1, numEntreprise);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				nb = rs.getInt(1);
			}
			this.deconnexion();
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Driver non charge ! test 1" + e);
			//e.printStackTrace();
		}
		return nb;
	}
	
	//A modifier
	//Recupere les offres de stage valide
	public Object[][] getListeOffreStageValide(String numEntreprise){
		
		int nb = this.nbOffreStageValide(numEntreprise);
		Object[][] tabOffre = new Object[nb][8];
	
		try {

			pstmt = connexion.prepareStatement("SELECT DISTINCT(id), offrestageenregistree.idOffreStage, numEntreprise, nom, prenom, libelleOffre, dateDebutOffre, offrestageenregistree.valideOffre FROM offrestageenregistree, offrestage, domaineoffre, personne, etudiant WHERE offrestage.numEntreprise = ? AND offrestage.numOffreStage = offrestageenregistree.idOffreStage AND offrestage.numDomaineOffre = domaineoffre.numDomaineOffre AND offrestageenregistree.idEtudiant = personne.idPersonne AND personne.idPersonne = offrestageenregistree.idEtudiant");
			pstmt.setString(1, numEntreprise);
			rs = pstmt.executeQuery();
		
			int i = 0;
				
			while (rs.next()) {
				tabOffre[i][0] = rs.getString(1);//id
				tabOffre[i][1] = rs.getString(2);//idOffre
				tabOffre[i][2] = rs.getString(3);//numEnt
				tabOffre[i][3] = rs.getString(4)/*nom*/ + " " + rs.getString(5);//prenom
				tabOffre[i][4] = rs.getString(6);//lib
				tabOffre[i][5] = rs.getString(7);//date
				tabOffre[i][6] = rs.getString(8);//valide
				tabOffre[i][7] = "Valider";
				i++;
			}
				
			this.deconnexion();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Driver non charge ! test listeOffreValide" + e);
			//e.printStackTrace();
		}
		return tabOffre;
	}
	
	/**
	 * Modifie une offre
	 * 
	 * @param idPersonne, unePersonne
	 */
	public void modifierOffre (String numOffre, OffreStage offre){
		String numDomaine = "";
		try {
		
			pstmt = connexion.prepareStatement("SELECT numDomaineOffre AS num FROM domaineoffre WHERE libDomaineOffre =  ?");
			pstmt.setString(1, offre.getDomaineOffre());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				numDomaine = rs.getString(1);
			}																//1					//2					//3				//4					//5				//6						//7
			pstmt = connexion.prepareStatement("UPDATE offrestage SET numDomaineOffre = ?, descriptifOffre = ?, libelleOffre = ?, dateDebutOffre = ?, dureeOffre = ?, cheminOffre = ? WHERE numOffreStage = ?;");
			pstmt.setString(1, numDomaine);
			pstmt.setString(2, offre.getDescriptifOffre());
			pstmt.setString(3, offre.getLibelleOffre());
			pstmt.setDate(4, new java.sql.Date(offre.getDateDebutOffre().getTime()));
			pstmt.setInt(5, offre.getDureeOffre());
			pstmt.setString(6, offre.getCheminOffre());
			pstmt.setString(7, numOffre);
			pstmt.executeUpdate();
			this.deconnexion();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Driver non charge !" + e);
			//e.printStackTrace();
		}
	}
	
	public OffreStage getUneOffreStage(String numOffreStage){
		OffreStage uneOffre = null;
		
		String nomEntreprise;
		String nomDomaine;
		String libelleOffre;
		String descriptifOffre;
		Date dateDebutOffre;
		int dureeOffre;
		String cheminOffre;
		String valide;
		try {

			pstmt = connexion.prepareStatement("SELECT numEntreprise, domaineoffre.libDomaineOffre,libelleOffre, descriptifOffre,"
				  + "dateDebutOffre, dureeOffre, cheminOffre FROM offrestage, domaineoffre " 
				  + "WHERE numOffreStage = ? AND offrestage.numDomaineOffre = domaineoffre.numDomaineOffre");
			pstmt.setString(1, numOffreStage);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				
				nomEntreprise = rs.getString(1);
				nomDomaine = rs.getString(2);
				libelleOffre = rs.getString(3);
				descriptifOffre = rs.getString(4);
				dateDebutOffre = rs.getDate(5);
				dureeOffre = rs.getInt(6);
				cheminOffre = rs.getString(7);
				
										  //NumOffre	//numEntreprise //domaine	//libeleOffre  //descriptif	   //Date		   //duree     //Chemin		//Valide
				uneOffre = new OffreStage(numOffreStage, nomEntreprise, nomDomaine, libelleOffre, descriptifOffre, dateDebutOffre, dureeOffre, cheminOffre);
				
			}
				
			this.deconnexion();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Driver non charge !" + e);
			//e.printStackTrace();
		}
		return uneOffre;
	}
	
	/**
	 * Supprime une offre
	 * 
	 * @param numOffre
	 */
	public void supprimerOffre (String numOffre){
		
		try {
																				//bibi_ofrre
			pstmt = connexion.prepareStatement("DELETE FROM `offrestage` WHERE `offrestage`.`numOffreStage` = ?");
			pstmt.setString(1, numOffre);
			pstmt.executeUpdate();
		
			this.deconnexion();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Driver non charge !" + e);
			//e.printStackTrace();
		}
	}
	
	public String etatOffre (String numOffre){
		String etat = "";
		try {
																			
			pstmt = connexion.prepareStatement("SELECT valideOffre FROM offrestageenregistree WHERE id =  ?");
			pstmt.setString(1, numOffre);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				etat = rs.getString(1);
			}		
		
			this.deconnexion();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Driver non charge ! test etatoffre " + e);
			//e.printStackTrace();
		}
		return etat;
	}
	
	public void validerOffre (String numOffre, String etat){
		
		try {
																			
			pstmt = connexion.prepareStatement("UPDATE offrestageenregistree SET valideOffre = ? WHERE id = ?;");
			pstmt.setString(1, etat);
			pstmt.setString(2, numOffre);
			pstmt.executeUpdate();
		
			this.deconnexion();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Driver non charge !" + e);
			//e.printStackTrace();
		}
	}

//Fonctions admin ##################################################################################################################	
	
	//Action sur les personnes-----------------------------------------------------------------------------------------------------------
	/**
	 * Inserer une Personne dans la base de donnees
	 * @param entreprise
	 */
	public void insererPersonneAlaBdd(Personne unePersonne, String entite){
		
		String requeteP = null ;
		String requeteE = null ;
		String id = null;
		
		pstmt = null;
	
		requeteP = "INSERT INTO personne(nom, prenom, login, mdp)"
				+ "VALUES(?,?,?,?);" ;
		requeteE = "INSERT INTO "+entite+"(idPersonne) VALUES(?);" ;
		try {
			//Insertion de l'employe dans la table personne
			pstmt = connexion.prepareStatement(requeteP);
			pstmt.setString(1, unePersonne.getNomPersonne());
			pstmt.setString(2, unePersonne.getPrenomPersonne());
			pstmt.setString(3, unePersonne.getLoginPersonne());
			pstmt.setString(4, unePersonne.getMdpPersonne());
			pstmt.executeUpdate();
			
			//Selectionne l'id de l'employe ajoute
			pstmt = connexion.prepareStatement("SELECT idPersonne FROM personne WHERE login= ?");
			pstmt.setString(1, unePersonne.getLoginPersonne());
			rs = pstmt.executeQuery();
			while(rs.next()){
				id = rs.getString("idPersonne");
			}
			
			//Insertion de l'id employe dans la table employe
			pstmt = connexion.prepareStatement(requeteE);
			pstmt.setString(1, id);
			pstmt.executeUpdate();

		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Nombre d'etudiants
	 * 
	 * @return nb
	 */
	public int getNbPersonne(String entite){
		int nb = 0;
		try {
		
			pstmt = connexion.prepareStatement("SELECT COUNT(idPersonne) AS nbId FROM "+entite+"");
			rs = pstmt.executeQuery();
			
		
			while(rs.next()){
				nb = rs.getInt(1);
			}
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Driver non charge !" + e);
			//e.printStackTrace();
		}
		return nb;
	}
	
	/**
	 * Liste d'etudiants
	 * 
	 * @return tableauEtudiants contenant les infos des etudiants
	 */
	public Object[][] getListePersonne( int nbPersonne, String entite){
	
		Object[][] tableauPersonne = new Object[nbPersonne][6];
	
		try {
			
			String sql="SELECT nom, prenom, login, mdp FROM personne WHERE idPersonne IN (SELECT idPersonne FROM "+entite+")";
			rs = st.executeQuery("SELECT nom, prenom, login, mdp FROM personne WHERE idPersonne IN (SELECT idPersonne FROM "+entite+")");
		
			int i = 0;
		
			while (rs.next()) {
				tableauPersonne[i][0] = rs.getString(1);
				tableauPersonne[i][1] = rs.getString(2);
				tableauPersonne[i][2] = rs.getString(3);
				tableauPersonne[i][3] = rs.getString(4);
				tableauPersonne[i][4] = "Modifier";
				tableauPersonne[i][5] = "Supprimer";
				i++;
			}
			
			this.deconnexion();
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Driver non charge !" + e);
			//e.printStackTrace();
		}
			return tableauPersonne;
	}
	
	/**
	 * id d'une personne
	 * 
	 * @return id
	 */
	public String getIdPersonne(String login){
		String id = "";
		try {
				
			pstmt = connexion.prepareStatement("SELECT idPersonne FROM personne WHERE login = ?");
			pstmt.setString(1, login);
			rs = pstmt.executeQuery();
		
			while(rs.next()){
				id = rs.getString(1);
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Driver non charge !" + e);
			//e.printStackTrace();
		}
		return id;
	}
	
	
	/**
	 * Modifie une personne
	 * 
	 * @param idPersonne, unePersonne
	 */
	public void modifierPersonne (String idPersonne, Personne unePersonne){
		
		try {
		
			pstmt = connexion.prepareStatement("UPDATE personne SET nom = ?, prenom = ?, login = ?, mdp = ? WHERE idPersonne = ?");
			pstmt.setString(1, unePersonne.getNomPersonne());
			pstmt.setString(2, unePersonne.getPrenomPersonne());
			pstmt.setString(3, unePersonne.getLoginPersonne());
			pstmt.setString(4, unePersonne.getMdpPersonne());
			pstmt.setString(5, idPersonne);
			pstmt.executeUpdate();
		
			this.deconnexion();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Driver non charge !" + e);
			//e.printStackTrace();
		}
	}
	
	//DELETE FROM `projetoffrestage`.`etudiant` WHERE `etudiant`.`idEtudiant` = 7
	/**
	 * Supprime une personne
	 * 
	 * @param idPersonne
	 */
	public void supprimerPersonne (String idPersonne){
		
		try {
															//bibi_ofrre
			pstmt = connexion.prepareStatement("DELETE FROM `personne` WHERE `personne`.`idPersonne` = ?");
			pstmt.setString(1, idPersonne);
			pstmt.executeUpdate();

			this.deconnexion();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Driver non charge !" + e);
			//e.printStackTrace();
		}
	}
	
	public String possedeUneEntreprise(String idEmploye){
		String numEntreprise = null;
		try {
			
			pstmt = connexion.prepareStatement("SELECT numEntreprise FROM employe WHERE idPersonne = ?");
			pstmt.setString(1, idEmploye);
			rs = pstmt.executeQuery();
		
			while(rs.next()){
				numEntreprise = rs.getString(1);
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Driver non charge !" + e);
			//e.printStackTrace();
		}
		return numEntreprise;
	}
	
	//Actions sur les domaines----------------------------------------------------------------------------------------------------------
	/**
	 * Inserer une Personne dans la base de donnees
	 * @param entreprise
	 */
	public void insererDomaineAlaBdd(String lib){
		
		String requeteD = null ;

		pstmt = null;
	
		requeteD = "INSERT INTO domaineoffre(libDomaineOffre)"
				+ "VALUES(?);";
		try {
			//Insertion de l'employe dans la table personne
			pstmt = connexion.prepareStatement(requeteD);
			pstmt.setString(1, lib);
			pstmt.executeUpdate();
		
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Liste de domaines
	 * 
	 * @return tableauDomaines contenant les infos des domaines
	 */
	public Object[][] getListeDomaine( ){
	
		Object[][] tableauDomaines = null;
		int nbDomaine = 0;
		
		try {
			rs = st.executeQuery("SELECT COUNT(numDomaineOffre) AS nbOffre FROM domaineoffre");
			
			while(rs.next()){
				nbDomaine = rs.getInt(1);
			}
			tableauDomaines = new Object[nbDomaine][4];
			
			this.deconnexion();
			
			rs = st.executeQuery("SELECT numDomaineOffre, libDomaineOffre FROM domaineoffre");
		
			int i = 0;
			while (rs.next()) {
				tableauDomaines[i][0] = rs.getString(1);
				tableauDomaines[i][1] = rs.getString(2);
				tableauDomaines[i][2] = "Modifier";
				tableauDomaines[i][3] = "Supprimer";
				i++;
			}
			
			this.deconnexion();
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Driver non charge !" + e);
			//e.printStackTrace();
		}
			return tableauDomaines;
	}
	
	
		/**
		 * Supprime un domaine
		 * 
		 * @param numDomaine
		 */
		public void supprimerDomaine (String numDomaine){
			
			try {
				
				pstmt = connexion.prepareStatement("DELETE FROM offrestage WHERE `offrestage`.`numDomaineOffre` = ?");
				pstmt.setString(1, numDomaine);
				pstmt.executeUpdate();
															
				pstmt = connexion.prepareStatement("DELETE FROM `domaineoffre` WHERE `domaineoffre`.`numDomaineOffre` = ?");
				pstmt.setString(1, numDomaine);
				pstmt.executeUpdate();

				this.deconnexion();
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Driver non charge !" + e);
				//e.printStackTrace();
			}
		}
		
		/**
		 * Modifie une personne
		 * 
		 * @param idPersonne, unePersonne
		 */
		public void modifierDomaine (String numDomaine, String libDomaine){
			
			try {
			
				pstmt = connexion.prepareStatement("UPDATE domaineoffre SET libDomaineOffre = ? WHERE numDomaineOffre = ?");
				pstmt.setString(1, libDomaine);
				pstmt.setString(2, numDomaine);
				pstmt.executeUpdate();
			
				this.deconnexion();
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Driver non charge !" + e);
				//e.printStackTrace();
			}
		}
//#######################################################################################################################################
	
		/**
		 * Consulter les Offres de Stage en fonction du nom de l'entreprise et de la date de Début de l'offre
		 * @return
		 */
		public Object[][] getOffresStage(String nomEntreprise, String date){
					
			int nb = 0;
			
			try {
					//Récupération d'une nombre d'offres de stage en fonction du nom de l'entreprise
					pstmt = connexion.prepareStatement("SELECT COUNT(numOffreStage), nomEntreprise "
							+ "FROM offrestage INNER JOIN entreprise ON entreprise.numEntreprise = offrestage.numEntreprise "
							+ "WHERE nomEntreprise = ?");
					
					pstmt.setString(1, nomEntreprise);
					rs = pstmt.executeQuery();
				
					while(rs.next()){
						
						nb = rs.getInt(1);
				}
			
					listeConsultationOffre = new Object[nb][11];
					System.out.println("Combien y'a - t- il d'entreprise(s) ?" +" " +nb);
				
		
					//Récupération des Offres de Stage
					String requete = "SELECT offrestage.numOffreStage, offrestage.numEntreprise, offrestage.numDomaineOffre,"
							+ " nomEntreprise, villeEntreprise, mailEntreprise, libDomaineOffre, libelleOffre, dateDebutOffre, "
							+ "dureeOffre, descriptifOffre "
								   + "FROM offrestage INNER JOIN entreprise ON offrestage.numEntreprise = entreprise.numEntreprise "
								   				   + "INNER JOIN domaineoffre ON offrestage.numDomaineOffre = domaineoffre.numDomaineOffre "
								   + "WHERE nomEntreprise = ? "
								   + "AND dateDebutOffre = ?" ;

					
					this.pstmt = connexion.prepareStatement(requete);
					
					pstmt.setString(1, nomEntreprise);
					pstmt.setString(2,date);
					
					this.rs = pstmt.executeQuery();
					System.out.println("nom de l'entreprise : "+nomEntreprise);
					System.out.println("date de Debut : "+date);

					
					int i = 0;

					while (rs.next()) {
					
						numOffreStage = rs.getString("offrestage.numOffreStage");
						nameEntreprise = rs.getString("nomEntreprise");
						villeEntreprise = rs.getString("villeEntreprise");
						mailEntreprise = rs.getString("mailEntreprise");
						domaineOffre = rs.getString("libDomaineOffre");
						libelleOffre = rs.getString("libelleOffre");
						dateDebutOffre = rs.getDate("dateDebutOffre");
						dureeOffre = rs.getInt("dureeOffre");
						descriptifOffre = rs.getString("descriptifOffre");
						String[] comboData = {"Consulter"};
						String[] comboData2 = {"Postuler"};
						
						listeConsultationOffre[i][0] = numOffreStage;
						listeConsultationOffre[i][1] = nameEntreprise ;
						listeConsultationOffre[i][2] = villeEntreprise ;
						//listeConsultationOffre[i][2] = mailEntreprise ;
						listeConsultationOffre[i][3] = domaineOffre ;
						listeConsultationOffre[i][4] = libelleOffre ;
						listeConsultationOffre[i][5] = dateDebutOffre ;
						//listeConsultationOffre[i[6] = dureeOffre ;
						//listeConsultationOffre[i][7] = descriptifOffre ;
						listeConsultationOffre[i][6] = comboData[0] ;
						listeConsultationOffre[i][7] = comboData2[0] ;

						
						i++;


					} 
					
					this.deconnexion();		
		}	
				catch (SQLException e) {
				e.printStackTrace();
			}
		
			return listeConsultationOffre;	
		}


		/**
		 * @return the nameEntreprise
		 */
		public String getNameEntreprise() {
			return nameEntreprise;
		}


		/**
		 * @return the villeEntreprise
		 */
		public String getVilleEntreprise() {
			return villeEntreprise;
		}


		/**
		 * @return the mailEntreprise
		 */
		public String getMailEntreprise() {
			return mailEntreprise;
		}


		/**
		 * @return the domaineOffre
		 */
		public String getDomaineOffre() {
			return domaineOffre;
		}


		/**
		 * @return the libelleOffre
		 */
		public String getLibelleOffre() {
			return libelleOffre;
		}


		/**
		 * @return the dateDebutOffre
		 */
		public Date getDateDebutOffre() {
			return dateDebutOffre;
		}


		/**
		 * @return the dureeOffre
		 */
		public Integer getDureeOffre() {
			return dureeOffre;
		}


		/**
		 * @return the descriptifOffre
		 */
		public String getDescriptifOffre() {
			return descriptifOffre;
		}
		
		/**
		 * @return the numOffreStage
		 */
		public String getNumOffreStage() {
			return numOffreStage;
		}
		
		/**
		 * Retourne la liste de toute les dates des Offres de Stage
		 * @return
		 */
		public ArrayList<Date> getDatesOffresStages() {
			
			ArrayList<Date> listeDates = new ArrayList<Date>();
			
			String requete = "SELECT DISTINCT (dateDebutOffre) FROM offrestage ORDER BY dateDebutOffre;";
			
			try{
				
				this.st =  connexion.createStatement();
				
				rs = st.executeQuery(requete);
				
				while(rs.next()){
					
					Date lesDates = rs.getDate(1);
					
					System.out.println("Date : "+ lesDates.toString());
					
					listeDates.add(lesDates);
					
					System.out.println("Dates : "+ listeDates.toString());

				}
				
				this.deconnexion();
				
			}
			
			catch(SQLException e){
				e.printStackTrace();
			}
			
			return listeDates;
		}
		
//		/**
//		 *Enregistrer le fait qu'un Etudiant postule à une Offre de Stage
//		 * @param entreprise
//		 */
//		public void enregistrerOffreStage(EnregistrerOffreStage enregOffre){
//			
//			String requete = null ;
//			pstmt = null;
//			
//			requete = "INSERT INTO offrestageenregistree(idEtudiant, idOffreStage,valideOffre) VALUES(?,?,?);";
//			
//			try {
//				
//				pstmt = connexion.prepareStatement(requete);
//					
//				pstmt.setInt(1, enregOffre.getIdEtudiant());
//				pstmt.setInt(2, enregOffre.getIdOffreStage());
//				pstmt.setInt(3,enregOffre.getValideOffre());
//
//					
//				pstmt.executeUpdate();
//				System.out.println("Requête d'Insertion EngOffreStage : " + pstmt.toString()) ;
//
//			}
//			
//			catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}

		
		/**
		 * Valider une offre de stage après que l'étudiant ait postuler
		 * @param numPersonne
		 * @param numOffreStage
		 */
		public void validerOffreStage(String numPersonne, String numOffreStage){
			
			
			pstmt = null;
			
			try{
				
				String requete = "INSERT INTO offrestageenregistree (idEtudiant, idOffreStage) VALUES (?, ?) ;";

				pstmt = (PreparedStatement) connexion.prepareStatement(requete);
				pstmt.setString(1,numPersonne);
				pstmt.setString(2,numOffreStage);
				System.out.println("Requête : " + pstmt.toString()) ;
				int	resultatValideOffre =pstmt.executeUpdate();
		
			}
			
			catch(Exception e){
				System.out.println("Erreur a la modification du champ : "+e.getMessage());
				
			}		
		
		}
}
	
