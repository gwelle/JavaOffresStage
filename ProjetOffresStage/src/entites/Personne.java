package entites;

public class Personne {
	
	private String nomPersonne;
	private String prenomPersonne;
	private String loginPersonne;
	private String mdpPersonne;
	
	/**
	 *Constructeur
	 * @param nomPersonne
	 * @param prenomPersonne
	 * @param loginPersonne
	 * @param mdpPersonne
	 */
	public Personne(String unNomPersonne, String unPrenomPersonne, String unLogin, String unMdp) {
		this.nomPersonne = unNomPersonne;
		this.prenomPersonne = unPrenomPersonne;
		this.loginPersonne = unLogin;
		this.mdpPersonne = unMdp;
	}


	/**
	 * @return nomEtudiant
	 */
	public String getNomPersonne() {
		return nomPersonne;
	}


	/**
	 * @return the adresseEntreprise
	 */
	public String getPrenomPersonne() {
		return prenomPersonne;
	}


	/**
	 * @return loginEtudiant
	 */
	public String getLoginPersonne() {
		return loginPersonne;
	}


	/**
	 * @return mdpEtudiant
	 */
	public String getMdpPersonne() {
		return mdpPersonne;
	}

}
