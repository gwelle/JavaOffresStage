package entites;


import java.util.Date;

import com.toedter.calendar.JDateChooser;

public class OffreStage {
	
	private String numOffre;
	private String nomEntreprise;
	private String nomDomaine;
	private String libelleOffre;
	private String descriptifOffre;
	private Date dateDebutOffre;
	private int dureeOffre;
	private String cheminOffre;
	private String valide;
	
	public OffreStage(String unNumOffre, String unNomE, String unNomD, String unLib, String unDesc, Date uneDate, int uneDuree, String unChemin){
		this.numOffre = unNumOffre;
		this.nomEntreprise = unNomE;
		this.nomDomaine = unNomD;
		this.libelleOffre = unLib;
		this.descriptifOffre = unDesc;
		this.dateDebutOffre = uneDate;
		this.dureeOffre = uneDuree;
		this.cheminOffre = unChemin;
		
	}
	
	public OffreStage(String unNomE, String unNomD, String unLib, String unDesc, Date uneDate, int uneDuree, String unChemin){
		this.nomEntreprise = unNomE;
		this.nomDomaine = unNomD;
		this.libelleOffre = unLib;
		this.descriptifOffre = unDesc;
		this.dateDebutOffre = uneDate;
		this.dureeOffre = uneDuree;
		this.cheminOffre = unChemin;
		
	}
	
	public OffreStage(String unNomE, String unNomD, String unLib, Date uneDate, int uneDuree, String unChemin,  String uneDesc) {
		this.nomEntreprise = unNomE;
		this.nomDomaine = unNomD;
		this.libelleOffre = unLib;
		this.descriptifOffre = uneDesc;
		this.dateDebutOffre = uneDate;
		this.dureeOffre = uneDuree;
		this.cheminOffre = unChemin;
	}

	public String getNumOffre() {
		return numOffre;
	}

	public void setNumOffre(String numOffre) {
		this.numOffre = numOffre;
	}

	public String getNomEntreprise() {
		return nomEntreprise;
	}

	public void setNomEntreprise(String nomEntreprise) {
		this.nomEntreprise = nomEntreprise;
	}

	public String getDomaineOffre(){
		return nomDomaine;
	}

	public void setDomaineOffre(String nomDomaine) {
		this.nomDomaine = nomDomaine;
	}

	public String getLibelleOffre() {
		return libelleOffre;
	}

	public void setLibelleOffre(String libelleOffre) {
		this.libelleOffre = libelleOffre;
	}

	public String getDescriptifOffre() {
		return descriptifOffre;
	}

	public void setDescriptifOffre(String descriptifOffre) {
		this.descriptifOffre = descriptifOffre;
	}

	public Date getDateDebutOffre() {
		return dateDebutOffre;
	}

	public void setDateDebutOffre(Date dateDebutOffre) {
		this.dateDebutOffre = dateDebutOffre;
	}

	public int getDureeOffre() {
		return dureeOffre;
	}

	public void setDureeOffre(int dureeOffre) {
		this.dureeOffre = dureeOffre;
	}

	public String getCheminOffre() {
		return cheminOffre;
	}

	public void setCheminOffre(String cheminOffre) {
		this.cheminOffre = cheminOffre;
	}

	public String getValide() {
		return valide;
	}

	public void setValide(String valide) {
		this.valide = valide;
	}

	
}
