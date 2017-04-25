package vuesEtudiant;


import java.awt.*;

import javax.swing.*;


import controleurs.Controleur;
import editeurs.ButtonEditor;
import jTable.ModelJTable;
import rendus.ButtonRenderer;

public class V_Consulter extends JFrame{
	

	private JTable table;
	private Object[][] listeOffreStage;
	private JPanel panelPrincipal;
	private ButtonEditor btnConsulter;
	private ButtonEditor btnPostuler;
	private ModelJTable modeltab ;
	
	//Constructeur
	public V_Consulter() {
	}
	
	/**
	 * contenant la vue qui va afficher une  ou plusieurs offres de stage en fonction du nom de l'entreprise et de la date de Debut
	 * @param controleur
	 * @param nameEntreprise
	 * @param dateDebutOffre
	 * @return
	 */
	public JPanel consulter(Controleur controleur, String nameEntreprise, String dateDebutOffre){
		
		//PanelPrincipal
		this.panelPrincipal = new JPanel();
		this.panelPrincipal.setBackground(new Color(210, 234, 252));
		this.panelPrincipal.setBounds(252, 30, 850, 682);
		this.panelPrincipal.setLayout(null);
		
		Font style1 = new Font("Arial",Font.BOLD,14);
		
		//Label JTable
		JLabel lblJTable = new JLabel("Liste des offres de stages");
		lblJTable.setBounds(10, 78, 166, 40);
		lblJTable.setFont(style1);
		lblJTable.setForeground(new Color(0, 0, 0));
		this.panelPrincipal.add(lblJTable);
		
		//JTable
		String[] entete = {"numOffre","Nom","Ville","Domaine","Libellé","Date","Consulter","Postuler"} ;
		
		this.listeOffreStage = controleur.afficheListeOffresDeStage(nameEntreprise,  dateDebutOffre);

		modeltab = new ModelJTable(listeOffreStage, entete,"etudiant", "");
		
		table = new JTable(modeltab);
		table.setBackground(new Color(255, 255, 255));
		table.getTableHeader().setBackground(new Color(27, 30, 46));
		table.getTableHeader().setForeground(new Color(210, 234, 252));
		table.setBounds(0, 107, 850, 565);
		
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.getColumnModel().getColumn(0).setResizable(false);
		
		
		JPasswordField pwf = new JPasswordField();
		DefaultCellEditor editor = new DefaultCellEditor(pwf);
		table.getColumnModel().getColumn(3).setCellEditor(editor);
		//A modifier
		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(0, 107, 850, 250);
		pane.getViewport().setBackground(table.getBackground());
		
	
		btnConsulter = new ButtonEditor(new JCheckBox(), controleur, 12);
		btnPostuler = new ButtonEditor(new JCheckBox(), controleur, 13);
	   
		this.table.getColumn("Consulter").setCellEditor(btnConsulter);
		this.table.getColumn("Consulter").setCellRenderer(new ButtonRenderer());
		
		this.table.getColumn("Postuler").setCellEditor(btnPostuler);
		this.table.getColumn("Postuler").setCellRenderer(new ButtonRenderer());
		//En lui spécifiant quel type d'affichage prendre en compte
	  
	    this.table.getTableHeader().setReorderingAllowed(false);
	    
	    modeltab.centerTable(table);
		
		this.panelPrincipal.add(pane);
		
		JDialog.setDefaultLookAndFeelDecorated(true);

		return panelPrincipal;
	}
	
	public ButtonEditor getBtnPostuler() {
		return btnPostuler;
	}
	
	public  JTable getTable(){
		return table;
	}
	
}
