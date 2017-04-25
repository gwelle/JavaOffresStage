package vuesEtudiant;



import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Font;
import java.sql.Date;
import java.util.ArrayList;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controleurs.Controleur;

/**
 * @author Olga
 *
 */
public class V_Chercher extends JFrame {
	
	private JPanel panelPrincipal;
	
	private ArrayList<String> lesNomsEntreprises;
	private ArrayList<java.util.Date> lesDates;
	
	private JComboBox<String> listeEntreprises;
	private JComboBox<Date> listeDates;
	
	private JLabel lblEntreprises ;
	private JLabel lblDates ;
	
	/**
	 * Panel contenant la vue qui va chercher une offre de stage en fonction du nom de l'entreprise et de la date de Debut
	 * @param controleur
	 * @return
	 */
	public JPanel chercher(Controleur controleur) {
		
		//PanelPrincipal
		this.panelPrincipal = new JPanel();
		this.panelPrincipal.setBackground(new Color(58, 65, 94));
		this.panelPrincipal.setBounds(252, 0, 850, 672);
		getContentPane().add(this.panelPrincipal, BorderLayout.CENTER);
		this.panelPrincipal.setLayout(null);
		
		//Création des JComboBox et lbl
		//JComboBox et lbl Entreprises
		lblEntreprises = new JLabel("Nom de l'entreprise");
		Font style1 = new Font("Verdana",Font.BOLD,12);
		lblEntreprises.setFont(style1);
		lblEntreprises.setBounds(170, 129, 142, 22);
		lblEntreprises.setForeground(Color.white);
		panelPrincipal.add(lblEntreprises);
				
		this.listeEntreprises = new JComboBox<String>();
		int i;
				
		this.lesNomsEntreprises = controleur.getListeNoms();
				
		String unNom = null;
				
		for(i = 0; i < lesNomsEntreprises.size(); i++){
				
			unNom = lesNomsEntreprises.get(i).toString();
			listeEntreprises.addItem(unNom);
		}
		
		listeEntreprises.setSize(10,15);					
		listeEntreprises.setBounds(400, 129, 142, 22);
		listeEntreprises.addActionListener(controleur);
		listeEntreprises.setBackground(new Color(255, 255, 255));
		panelPrincipal.add(listeEntreprises);
		
		//Création des JComboBox et lbl
		//JComboBox et lbl Dates
		lblDates = new JLabel("Date");
		Font style2 = new Font("Verdana",Font.BOLD,12);
		lblDates.setFont(style2);
		lblDates.setBounds(170, 200, 142, 22);
		lblDates.setForeground(Color.white);
		panelPrincipal.add(lblDates);
				
		this.listeDates = new JComboBox<Date>();
		int ii;
				
		this.lesDates = controleur.afficheListeDates();
				
		Date uneDate = null;
		
		for(ii = 0; ii < lesDates.size(); ii++){
		
			uneDate = (Date) lesDates.get(ii);
			listeDates.addItem(uneDate);
			
		}
		
		listeDates.setSize(10,15);					
		listeDates.setBounds(400, 200, 142, 22);
		listeDates.addActionListener(controleur);
		listeDates.setBackground(new Color(255, 255, 255));
		panelPrincipal.add(listeDates);
		
		
		//Création boutons Valier et Annuler
		JButton btnValiderChercher = new JButton("Valider");
		btnValiderChercher.setBounds(175, 550, 188, 76);
		btnValiderChercher.addActionListener(controleur);
		btnValiderChercher.setActionCommand("ValiderChercherConsulter");
		btnValiderChercher.setBackground(new Color(27, 30, 46));
		btnValiderChercher.setForeground(new Color(210, 234, 252));
		panelPrincipal.add(btnValiderChercher);
				
		JButton btnAnnulerChercher = new JButton("Annuler");
		btnAnnulerChercher.setBounds(400, 550, 188, 76);
		btnAnnulerChercher.addActionListener(controleur);
		btnAnnulerChercher.setActionCommand("AnnulerChercherConulter");
		btnAnnulerChercher.setBackground(new Color(27, 30, 46));
		btnAnnulerChercher.setForeground(new Color(210, 234, 252));
		panelPrincipal.add(btnAnnulerChercher);
		
		
		
		return panelPrincipal;
	}

	/**
	 * @return the listeEntreprises
	 */
	public JComboBox<String> getListeEntreprises() {
		return listeEntreprises;
	}

	/**
	 * @return the listeDates
	 */
	public JComboBox<Date> getListeDates() {
		return listeDates;
	}
	
	
}
