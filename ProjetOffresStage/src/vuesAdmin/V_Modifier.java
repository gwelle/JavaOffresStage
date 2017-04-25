package vuesAdmin;
import jTable.*;
import editeurs.*;
import rendus.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import controleurs.Controleur;
import editeurs.ButtonEditor;
import rendus.*;


public class V_Modifier extends JFrame{
	
	private JTable table;
	private Object[][] liste;
	private JPanel panelPrincipal;
	private ButtonEditor btnModifier;
	private ButtonEditor btnSupprimer;
	private ModelJTable modelJtable;
	
	//Constructeur
	public V_Modifier() {
	}
	
	public JPanel table(Controleur c, String entite){
		
		//PanelPrincipal
		this.panelPrincipal = new JPanel();
		this.panelPrincipal.setBackground(new Color(210, 234, 252));
		this.panelPrincipal.setBounds(252, 30, 950, 1000);
		this.panelPrincipal.setLayout(null);
		Font style1 = new Font("Arial",Font.BOLD,14);
		
		String compte ="admin";
		if(entite == "employe" || entite == "etudiant"){
			String[] entete = {"Nom", "Prenom", "Login", "Mot de Passe", "Modifier", "Supprimer"};
			
			this.liste = c.afficheListePersonne(entite);
			
			modelJtable = new ModelJTable(liste, entete, compte, "");
		}else{
			String[] entete = {"id", "Libelle", "Modifier", "Supprimer"};
			
			this.liste = c.afficheListeDomaine();
			modelJtable = new ModelJTable(liste, entete, compte, "");
		}
		
		//JTable
		table = new JTable(modelJtable);
		table.setBackground(new Color(255, 255, 255));
		table.getTableHeader().setBackground(new Color(27, 30, 46));
		table.getTableHeader().setForeground(new Color(210, 234, 252));
		table.setBounds(0, 107, 850, 1000);
	
		if(entite == "etudiant" || entite == "employe"){
			JPasswordField pwf = new JPasswordField();
			DefaultCellEditor editor = new DefaultCellEditor(pwf);
			table.getColumnModel().getColumn(3).setCellEditor(editor);
		}
		
		//A modifier
		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(0, 107, 900, 1000);
		pane.getViewport().setBackground(table.getBackground());
		
		if(entite == "etudiant"){
			btnModifier = new ButtonEditor(new JCheckBox(), c, 1);	
			btnSupprimer = new ButtonEditor(new JCheckBox(), c, 2);
		}else if(entite == "employe"){
			btnModifier = new ButtonEditor(new JCheckBox(), c, 3);
			btnSupprimer = new ButtonEditor(new JCheckBox(), c, 4);
		}else{
			btnModifier = new ButtonEditor(new JCheckBox(), c, 10);
			btnSupprimer = new ButtonEditor(new JCheckBox(), c, 11);
			table.getColumnModel().getColumn(0).setMaxWidth(0);
			table.getColumnModel().getColumn(0).setMinWidth(0);
			table.getColumnModel().getColumn(0).setPreferredWidth(0);
			table.getColumnModel().getColumn(0).setResizable(false);
		}
		
		this.table.getColumn("Modifier").setCellEditor(btnModifier);
		this.table.getColumn("Modifier").setCellRenderer(new ButtonRenderer());
		
		this.table.getColumn("Supprimer").setCellEditor(btnSupprimer);
		this.table.getColumn("Supprimer").setCellRenderer(new ButtonRenderer());
			
		this.table.getTableHeader().setReorderingAllowed(false);
		
		modelJtable.centerTable(table);
			
		this.panelPrincipal.add(pane);
			
		return panelPrincipal;
	}
		
	public  JTable getTable(){
		return table;
	}
		
	public ButtonEditor getBtnValiderModifier(){
		return btnModifier;
	}
	public ButtonEditor getBtnValiderSupprimer(){
		return btnSupprimer;
	}
}

