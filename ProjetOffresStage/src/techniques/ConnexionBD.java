package techniques;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import controleurs.Controleur;

/**
 * Connexion � la base de donn�es MySQL
 * @author eleve
 */

public class ConnexionBD {
	
	private static String pilote = "com.mysql.jdbc.Driver";
	
	private static String url = "jdbc:mysql://localhost:3306/projetoffrestage?useUnicode=yes&characterEncoding=UTF-8";//mysql2.paris1.alwaysdata.com/bibi_ofrre  //localhost:3306/projetoffrestage?useUnicode=yes&characterEncoding=UTF-8
	private static String user = "root";//bibi //root
	private static String mdp = "";//test1234
	
	private static Connection connexion = null ;
	
	/**
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private ConnexionBD() throws ClassNotFoundException, SQLException{
		
		try{
	
		//Chargement du pilote	
		Class.forName(pilote);
			System.out.println("Ok");
		}
		
		catch(ClassNotFoundException e){
			e.printStackTrace();
			System.out.println("Non");
		}
		
		try{
			
		//Ouverture de la connexion
		connexion  = DriverManager.getConnection(url,user,mdp);
		}
		
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Une Instance de connexion unique
	 * @return the connexion
	 */
	public static Connection getConnexion() {
		
		if(connexion == null){
			 try {
				new ConnexionBD();
			} 
			 
			 catch (ClassNotFoundException e) {
				e.printStackTrace();
			} 
			 
			 catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return connexion;
	}
}
