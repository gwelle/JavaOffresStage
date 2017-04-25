/**
 * 
 */
package entites;

public class Entreprise {
	
	private String numEntreprise;
	private String nomEntreprise ;
	private String adresseEntreprise ;
	private String codePostalEntreprise ;
	private String villeEntreprise ;
	private String adresseMailEntreprise ;
	private String numeroTelephoneEntreprise ;
	private String secteurActiviteEntreprise ;
	
	
	
	public Entreprise(String nomEntreprise, String adresseEntreprise, String codePostalEntreprise,
			String villeEntreprise, String adresseMailEntreprise, String numeroTelephoneEntreprise,
			String secteurActiviteEntreprise) {

		this.nomEntreprise = nomEntreprise;
		this.adresseEntreprise = adresseEntreprise;
		this.codePostalEntreprise = codePostalEntreprise;
		this.villeEntreprise = villeEntreprise;
		this.adresseMailEntreprise = adresseMailEntreprise;
		this.numeroTelephoneEntreprise = numeroTelephoneEntreprise;
		this.secteurActiviteEntreprise = secteurActiviteEntreprise;
	}
	
	/**
	 *Constructeur
	 * @param numEntreprise
	 * @param nomEntreprise
	 * @param adresseEntreprise
	 * @param codePostalEntreprise
	 * @param villeEntreprise
	 * @param adresseMailEntreprise
	 * @param numeroTelephoneEntreprise
	 * @param secteurActiviteEntreprise
	 */
	public Entreprise(String numEntreprise, String nomEntreprise, String adresseEntreprise, String codePostalEntreprise,
			String villeEntreprise, String adresseMailEntreprise, String numeroTelephoneEntreprise,
			String secteurActiviteEntreprise) {
		
		this.numEntreprise = numEntreprise;
		this.nomEntreprise = nomEntreprise;
		this.adresseEntreprise = adresseEntreprise;
		this.codePostalEntreprise = codePostalEntreprise;
		this.villeEntreprise = villeEntreprise;
		this.adresseMailEntreprise = adresseMailEntreprise;
		this.numeroTelephoneEntreprise = numeroTelephoneEntreprise;
		this.secteurActiviteEntreprise = secteurActiviteEntreprise;
	}

	
	/**
	 * @return the numEntreprise
	 */
	public String getNumEntreprise() {
		return numEntreprise;
	}

	/**
	 * @return the nomEntreprise
	 */
	public String getNomEntreprise() {
		return nomEntreprise;
	}


	/**
	 * @return the adresseEntreprise
	 */
	public String getAdresseEntreprise() {
		return adresseEntreprise;
	}


	/**
	 * @return the codePostalEntreprise
	 */
	public String getCodePostalEntreprise() {
		return codePostalEntreprise;
	}


	/**
	 * @return the villeEntreprise
	 */
	public String getVilleEntreprise() {
		return villeEntreprise;
	}


	/**
	 * @return the adresseMailEntreprise
	 */
	public String getAdresseMailEntreprise() {
		return adresseMailEntreprise;
	}


	/**
	 * @return the numeroTelephoneEntreprise
	 */
	public String getNumeroTelephoneEntreprise() {
		return numeroTelephoneEntreprise;
	}


	/**
	 * @return the secteurActiviteEntreprise
	 */
	public String getSecteurActiviteEntreprise() {
		return secteurActiviteEntreprise;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Entreprise [nomEntreprise=" + nomEntreprise
				+ ", adresseEntreprise=" + adresseEntreprise + ", codePostalEntreprise=" + codePostalEntreprise
				+ ", villeEntreprise=" + villeEntreprise + ", adresseMailEntreprise=" + adresseMailEntreprise
				+ ", numeroTelephoneEntreprise=" + numeroTelephoneEntreprise + ", secteurActiviteEntreprise="
				+ secteurActiviteEntreprise + "]";
	}
}