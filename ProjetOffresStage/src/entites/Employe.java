package entites;

public class Employe {

	private String nomEmploye;
	private String prenomEmploye;
	private String loginEmploye;
	private String mdpEmploye;
	
	/**
	 *Constructeur
	 * @param nomEmploye
	 * @param prenomEmploye
	 * @param loginEmploye
	 * @param mdpEmploye
	 */
	public Employe(String unNomEmploye, String unPrenomEmploye, String unLogin, String unMdp) {
		this.nomEmploye = unNomEmploye;
		this.prenomEmploye = unPrenomEmploye;
		this.loginEmploye = unLogin;
		this.mdpEmploye = unMdp;
	}


	/**
	 * @return nomEtudiant
	 */
	public String getNomEmploye() {
		return nomEmploye;
	}


	/**
	 * @return the adresseEntreprise
	 */
	public String getPrenomEmploye() {
		return prenomEmploye;
	}


	/**
	 * @return loginEtudiant
	 */
	public String getLoginEmploye() {
		return loginEmploye;
	}


	/**
	 * @return mdpEtudiant
	 */
	public String getMdpEmploye() {
		return mdpEmploye;
	}
}
