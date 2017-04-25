package vuesEtudiant;


	
import controleurs.Controleur;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class V_MenuEtudiant{

	public V_MenuEtudiant(){	
	}
		
	JPanel panelMenu; 
	
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
			
		JButton btnConsulter = new JButton("Consultation des offres");
						//		g	h	
		btnConsulter.setBounds(47, 150, 148, 64);
		btnConsulter.setForeground(new Color(255, 255, 255));
		btnConsulter.setBackground(new Color(41, 46, 69));
		btnConsulter.setBorder(new LineBorder(new Color(41, 46, 69)));
		panelMenu.add(btnConsulter);
			
		JButton btnDeconnexion = new JButton("Deconnexion");
		btnDeconnexion.setBounds(47, 300, 148, 64);
		btnDeconnexion.setForeground(new Color(255, 255, 255));
		btnDeconnexion.setBackground(new Color(41, 46, 69));
		btnDeconnexion.setBorder(new LineBorder(new Color(41, 46, 69)));
		panelMenu.add(btnDeconnexion);
			
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.setBounds(47, 450, 148, 64);
		btnQuitter.setForeground(new Color(255, 255, 255));
		btnQuitter.setBackground(new Color(41, 46, 69));
		btnQuitter.setBorder(new LineBorder(new Color(41, 46, 69)));
		panelMenu.add(btnQuitter);
			
		//ActionListener
		btnConsulter.addActionListener(c);
		btnConsulter.setActionCommand("EtudiantChercherConsulter");
		
		btnDeconnexion.addActionListener(c);
		btnDeconnexion.setActionCommand("Deconnexion");
			
		btnQuitter.addActionListener(c);
		btnQuitter.setActionCommand("Quitter");
			
		return panelMenu;
	}

}
