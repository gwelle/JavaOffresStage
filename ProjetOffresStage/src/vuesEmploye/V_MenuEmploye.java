package vuesEmploye;
import controleurs.Controleur;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
public class V_MenuEmploye{

	public V_MenuEmploye(){	
	}
	
	JPanel panelMenu; 
	JButton btnCreer;
	JButton btnSaisir;
	JButton btnConsulter;
	JButton btnDeconnexion;
	JButton btnQuitter;
	
	public JPanel menu(Controleur c){
		
		panelMenu = new JPanel();
		panelMenu.setBounds(0, 0, 253, 1000);
		panelMenu.setBackground(new Color(27, 30, 46));
		panelMenu.setLayout(null);
		Font style1 = new Font("Arial",Font.BOLD,12);
		
		JLabel lblNom = new JLabel("Nom :");
		lblNom.setBounds(47, 50, 165, 29);
		lblNom.setForeground(new Color(156, 180, 202));
		panelMenu.add(lblNom);
		lblNom.setFont(style1);
		
		JLabel lblPrenom = new JLabel("Prénom :");
		lblPrenom.setBounds(47, 75, 137, 29);
		lblPrenom.setForeground(new Color(156, 180, 202));
		panelMenu.add(lblPrenom);
		lblPrenom.setFont(style1);
		
		//Informations dans labels
		ArrayList infosUser = new ArrayList();
		infosUser = c.afficheInfosU(c.getPseudo());
		lblNom.setText("Nom : " + infosUser.get(0).toString());
		lblPrenom.setText("Prénom : " + infosUser.get(1).toString());
		
		btnCreer = new JButton("Créer une entreprise");
		btnCreer.setForeground(new Color(255, 255, 255));
		btnCreer.setBackground(new Color(41, 46, 69));
		btnCreer.setBorder(new LineBorder(new Color(41, 46, 69)));
		
		btnSaisir = new JButton("Saisir une offre");
		btnSaisir.setBounds(47, 235, 148, 64);
		btnSaisir.setForeground(new Color(255, 255, 255));
		btnSaisir.setBackground(new Color(41, 46, 69));
		btnSaisir.setBorder(new LineBorder(new Color(41, 46, 69)));
		
		btnConsulter = new JButton("Consulter");
		btnConsulter.setBounds(47, 335, 148, 64);
		btnConsulter.setForeground(new Color(255, 255, 255));
		btnConsulter.setBackground(new Color(41, 46, 69));
		btnConsulter.setBorder(new LineBorder(new Color(41, 46, 69)));
		
		btnDeconnexion = new JButton("Deconnexion");
		btnDeconnexion.setForeground(new Color(255, 255, 255));
		btnDeconnexion.setBackground(new Color(41, 46, 69));
		btnDeconnexion.setBorder(new LineBorder(new Color(41, 46, 69)));
		
		btnQuitter = new JButton("Quitter");
		btnQuitter.setForeground(new Color(255, 255, 255));
		btnQuitter.setBackground(new Color(41, 46, 69));
		btnQuitter.setBorder(new LineBorder(new Color(41, 46, 69)));
		
		if(c.getUneEntreprise() != false){
			
			btnCreer.setBounds(47, 135, 148, 64);
			btnCreer.setText("Modifier l'entreprise");
			
			btnCreer.setActionCommand("ConsulterEntreprise");
			
			btnDeconnexion.setBounds(47, 435, 148, 64);
			
			btnQuitter.setBounds(47, 535, 148, 64);
			panelMenu.add(btnSaisir);
			panelMenu.add(btnConsulter);
			
		}else{
			
			btnCreer.setBounds(47, 200, 148, 64);
		
			btnCreer.setActionCommand("Creer");
			
			btnDeconnexion.setBounds(47, 350, 148, 64);
			
			btnQuitter.setBounds(47, 500, 148, 64);
			
		}
		panelMenu.add(btnCreer);
		panelMenu.add(btnQuitter);
		panelMenu.add(btnDeconnexion);

		//ActionListener
		btnCreer.addActionListener(c);
		
		btnSaisir.addActionListener(c);
		btnSaisir.setActionCommand("Saisir");
		
		btnConsulter.addActionListener(c);
		btnConsulter.setActionCommand("ConsulterEmploye");
	
		btnDeconnexion.addActionListener(c);
		btnDeconnexion.setActionCommand("Deconnexion");
		
		btnQuitter.addActionListener(c);
		btnQuitter.setActionCommand("Quitter");
		
		return panelMenu;
	}
	
}
