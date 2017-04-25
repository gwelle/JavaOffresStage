package vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;

import controleurs.Controleur;

public class V_Selectionner extends JFrame{
	
	private JPanel panelPrincipal;
	
	private JButton btn1;
	private JButton btn2;
	private JButton btn3;
	
	
	//Constructeur
	public V_Selectionner() {
	}
	
	public JPanel selectionner(Controleur c, String action){
		
		//PanelPrincipal
		this.panelPrincipal = new JPanel();
		this.panelPrincipal.setBackground(new Color(58, 65, 94));
		this.panelPrincipal.setBounds(252, 0, 850, 672);
		getContentPane().add(this.panelPrincipal, BorderLayout.CENTER);
		this.panelPrincipal.setLayout(null);
		Font style1 = new Font("Arial",Font.BOLD,11);
		
		btn2 = new JButton();
		btn2.setBounds(350, 350, 200, 80);
		btn2.setForeground(new Color(255, 255, 255));
		btn2.setBackground(new Color(41, 46, 69));
		btn2.setBorder(new LineBorder(new Color(41, 46, 69)));
		btn2.addActionListener(c);
		
		if(action == "ajouter"){
			
			btn1 = new JButton("Ajouter un étudiant");
			btn1.setActionCommand("AjouterEtudiant");
			
			btn2.setText("Ajouter un employé");
			btn2.setActionCommand("AjouterEmploye");
			panelPrincipal.add(btn2);
			
			btn3 = new JButton("Ajouter un domaine");
			btn3.setActionCommand("AjouterDomaine");
			
		}else if(action == "modifier"){
			
			btn1 = new JButton("Modifier/Supprimer un étudiant");
			btn1.setActionCommand("ModifierEtudiant");
			
			btn2.setText("Modifier/Supprimer un employé");
			btn2.setActionCommand("ModifierEmploye");
			panelPrincipal.add(btn2);
			
			btn3 = new JButton("Modifier/Supprimer un domaine");
			btn3.setActionCommand("ModifierDomaine");
			
		}else if(action == "consulterOffre"){
			
			btn1 = new JButton("Modifier/Supprimer une offre");
			btn1.setActionCommand("ConsulterOffreStage");
			
			btn3 = new JButton("Valider une offre");
			btn3.setActionCommand("ConsulterOffreStageValide");
			
		}
		
		btn1.setBounds(350, 230, 200, 80);
		btn1.setForeground(new Color(255, 255, 255));
		btn1.setBackground(new Color(41, 46, 69));
		btn1.setBorder(new LineBorder(new Color(41, 46, 69)));
		btn1.addActionListener(c);
	
		
		
		btn3.setBounds(350, 470, 200, 80);
		btn3.setForeground(new Color(255, 255, 255));
		btn3.setBackground(new Color(41, 46, 69));
		btn3.setBorder(new LineBorder(new Color(41, 46, 69)));
		btn3.addActionListener(c);
		
		panelPrincipal.add(btn1);
		
		panelPrincipal.add(btn3);
		
		return panelPrincipal;
	}
	
}
