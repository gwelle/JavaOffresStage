package vuesEmploye;

import jTable.ModelJTable;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import rendus.ButtonRenderer;
import controleurs.Controleur;
import editeurs.ButtonEditor;
import editeurs.JDateChooserCellEditor;

public class V_ModifierOffre {
	private Object[][] listeOffres;
	
	private JTable table;
	private ModelJTable modelJtable;
	private JPanel panelPrincipal;

	private ButtonEditor btnValider;
	private ButtonEditor btnModifier;
	private ButtonEditor btnSupprimer;

	//Constructeur
	public V_ModifierOffre() {
	}
		
	@SuppressWarnings("deprecation")
	public JPanel tableOffres(Controleur c, String action){
		
		//PanelPrincipal
		this.panelPrincipal = new JPanel();
		this.panelPrincipal.setBackground(new Color(210, 234, 252));
		this.panelPrincipal.setBounds(252, 30, 950, 682);
		this.panelPrincipal.setLayout(null);
				
		Font style1 = new Font("Arial",Font.BOLD,14);
		
		//JTable
		String compte = "employe";
		
		this.listeOffres = c.afficheListeOffreStage(action);
		
		if(action == "Modifier"){//Tableau pour modifier les offres
			String entete[] = {"idOffre", "idEntreprise", "Domaine", "Libelle", "Date de Debut", "Duree", "Modifier", "Supprimer"};
			modelJtable = new ModelJTable(listeOffres, entete, compte, action);
		}else{//Tableau pour valider les offres
			String entete[] = {"id", "idOffre", "idEntreprise", "Etudiant", "Libelle", "Date de Debut", "Valide", "Valider"};
			modelJtable = new ModelJTable(listeOffres, entete, compte, action);
		}
		
		this.listeOffres = c.afficheListeOffreStage(action);
		table = new JTable(modelJtable);
		table.setBackground(new Color(255, 255, 255));
		table.getTableHeader().setBackground(new Color(27, 30, 46));
		table.getTableHeader().setForeground(new Color(210, 234, 252));
		table.setBounds(0, 107, 850, 1000);
		
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.getColumnModel().getColumn(0).setResizable(false);
		
		table.getColumnModel().getColumn(1).setMaxWidth(0);
		table.getColumnModel().getColumn(1).setMinWidth(0);
		table.getColumnModel().getColumn(1).setPreferredWidth(0);
		table.getColumnModel().getColumn(1).setResizable(false);
		
		if(action != "Modifier"){
			table.getColumnModel().getColumn(2).setMaxWidth(0);
			table.getColumnModel().getColumn(2).setMinWidth(0);
			table.getColumnModel().getColumn(2).setPreferredWidth(0);
			table.getColumnModel().getColumn(2).setResizable(false);
		}
	
		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(0, 107, 900, 1000);
		pane.getViewport().setBackground(table.getBackground());
		
		if(action != "Modifier"){
			btnValider = new ButtonEditor(new JCheckBox(), c, 5);	
			this.table.getColumn("Valider").setCellEditor(btnValider);
			this.table.getColumn("Valider").setCellRenderer(new ButtonRenderer());
		}else{
			btnModifier = new ButtonEditor(new JCheckBox(), c, 6);	
			this.table.getColumn("Modifier").setCellEditor(btnModifier);
			this.table.getColumn("Modifier").setCellRenderer(new ButtonRenderer());
			
			btnSupprimer = new ButtonEditor(new JCheckBox(), c, 7);
			this.table.getColumn("Supprimer").setCellEditor(btnSupprimer);
			this.table.getColumn("Supprimer").setCellRenderer(new ButtonRenderer());
		}
	
		
		
			
		this.table.getTableHeader().setReorderingAllowed(false);
		
		modelJtable.centerTable(table);
			
		this.panelPrincipal.add(pane);
			
		return panelPrincipal;
	}
		
	public  JTable getTable(){
		return table;
	}
	
	public ButtonEditor getBtnValider(){
		return btnValider;
	}
		
	public ButtonEditor getBtnValiderModifier(){
		return btnModifier;
	}
	public ButtonEditor getBtnValiderSupprimer(){
		return btnSupprimer;
	}
}

