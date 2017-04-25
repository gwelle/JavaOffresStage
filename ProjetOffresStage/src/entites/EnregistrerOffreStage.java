package entites;

public class EnregistrerOffreStage {
	
	private int idEtudiant ;
	private int idOffreStage ;
	private int valideOffre ;
	
	/**
	 * @param idEtudiant
	 * @param idOffreStage
	 */
	public EnregistrerOffreStage(int idEtudiant, int idOffreStage, int valideOffre) {
		super();
		this.idEtudiant = idEtudiant;
		this.idOffreStage = idOffreStage;
	}

	/**
	 * @return the idEtudiant
	 */
	public int getIdEtudiant() {
		return idEtudiant;
	}

	/**
	 * @return the idOffreStage
	 */
	public int getIdOffreStage() {
		return idOffreStage;
	}
	
	

	/**
	 * @return the valideOffre
	 */
	public int getValideOffre() {
		return valideOffre;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EnregistrerOffreStage [idEtudiant=" + idEtudiant + ", idOffreStage=" + idOffreStage + ", valideOffre="
				+ valideOffre + "]";
	}
}
