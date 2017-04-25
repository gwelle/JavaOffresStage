package entites;

public class Etudiant {
	
	private String nomEtudiant;
	private String prenomEtudiant;
	private String loginEtudiant;
	private String mdpEtudiant;
	
	/**
	 *Constructeur
	 * @param nomEtudiant
	 * @param prenomEtudiant
	 * @param loginEtudiant
	 * @param mdpEtudiant
	 */
	public Etudiant(String unNomEtudiant, String unPrenomEtudiant, String unLogin, String unMdp) {
		this.nomEtudiant = unNomEtudiant;
		this.prenomEtudiant = unPrenomEtudiant;
		this.loginEtudiant = unLogin;
		this.mdpEtudiant = unMdp;
	}


	/**
	 * @return nomEtudiant
	 */
	public String getNomEtudiant() {
		return nomEtudiant;
	}


	/**
	 * @return the adresseEntreprise
	 */
	public String getPrenomEtudiant() {
		return prenomEtudiant;
	}


	/**
	 * @return loginEtudiant
	 */
	public String getLoginEtudiant() {
		return loginEtudiant;
	}


	/**
	 * @return mdpEtudiant
	 */
	public String getMdpEtudiant() {
		return mdpEtudiant;
	}
}
