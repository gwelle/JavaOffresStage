package vuesEmploye;

import javax.swing.*;

import java.awt.*;

import javax.swing.border.LineBorder;

import controleurs.Controleur;
import entites.Entreprise;

// Création d’une classe Fenetre héritant de la classe JFrame

public class V_Creer extends JFrame {
	
	//private JPanel panelPrincipal;
	private JTextField jTFNom;
	private JTextField jTFAdresse;
	private JTextField jTFCP;
	private JTextField jTFVille;
	private JTextField jTFMail;
	private JTextField jTFTel;
	private JTextField jTFSecteur;
	
	private JLabel lblNom;
	private JLabel lblAdresse;
	private JLabel lblCP;
	private JLabel lblVille;
	private JLabel lblMail;
	private JLabel lblTel;
	private JLabel lblSecteur;
	
	private JButton btn1;
	private JButton btn2;
	

	public V_Creer() {
		
	}
	
	/**
	 * Panel contenant la vue de la création d'une entreprise
	 * @param controleur
	 * @return
	 */
	public JPanel creer(Controleur controleur, String action){
	
		//PanelPrincipal
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBackground(new Color(58, 65, 94));
		panelPrincipal.setBounds(252, 0, 850, 672);
		
		getContentPane().add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(null);
		
		//Création des JTF et lbl
		//JTF et lbl Nom
		lblNom = new JLabel("Nom de l'entreprise");
		Font style1 = new Font("Verdana",Font.BOLD,12);
		lblNom.setFont(style1);
		lblNom.setBounds(170, 129, 142, 22);
		lblNom.setForeground(Color.white);
		panelPrincipal.add(lblNom);
		
		jTFNom = new JTextField();
		//   g     h
		jTFNom.setBounds(400, 129, 142, 22);
		jTFNom.setBorder(new LineBorder(new Color(255,255,255)));
		panelPrincipal.add(jTFNom);
		jTFNom.setColumns(10);
		
		//JTF et lbl Adresse numéro et rue
		lblAdresse = new JLabel("Adresse");
		lblAdresse.setFont(style1);
		lblAdresse.setBounds(170, 179, 142, 22);
		lblAdresse.setForeground(Color.white);
		panelPrincipal.add(lblAdresse);
		
		jTFAdresse = new JTextField();
		jTFAdresse.setBounds(400, 179, 142, 22);
		jTFAdresse.setBorder(new LineBorder(new Color(255,255,255)));
		panelPrincipal.add(jTFAdresse);
		jTFAdresse.setColumns(10);
		
		//JTF et lbl Ville
		lblVille = new JLabel("Ville");
		lblVille.setFont(style1);
		lblVille.setBounds(170, 229, 142, 22);
		lblVille.setForeground(Color.white);
		panelPrincipal.add(lblVille);
				
		jTFVille = new JTextField();
		jTFVille.setBounds(400, 229, 142, 22);
		jTFVille.setBorder(new LineBorder(new Color(255,255,255)));
		panelPrincipal.add(jTFVille);
		jTFVille.setColumns(10);
	
		
		//JTF et lbl Code postal
		lblCP = new JLabel("Code postal");
		lblCP.setFont(style1);
		lblCP.setBounds(170, 279, 142, 22);
		lblCP.setForeground(Color.white);
		panelPrincipal.add(lblCP);
		
		jTFCP = new JTextField();
		jTFCP.setBounds(400, 279, 142, 22);
		jTFCP.setBorder(new LineBorder(new Color(255,255,255)));
		panelPrincipal.add(jTFCP);
		jTFCP.setColumns(10);
		
		//JTF et lbl Mail
		lblMail = new JLabel("Mail");
		lblMail.setFont(style1);
		lblMail.setBounds(170, 329, 142, 22);
		lblMail.setForeground(Color.white);
		panelPrincipal.add(lblMail);
		
		jTFMail = new JTextField();
		jTFMail.setBounds(400, 329, 142, 22);
		jTFMail.setBorder(new LineBorder(new Color(255,255,255)));
		panelPrincipal.add(jTFMail);
		jTFMail.setColumns(10);
		
		//JTF et lbl Téléphone
		lblTel = new JLabel("Téléphone");
		lblTel.setFont(style1);
		lblTel.setBounds(170, 379, 142, 22);
		lblTel.setForeground(Color.white);
		panelPrincipal.add(lblTel);
		
		jTFTel = new JTextField();
		jTFTel.setBounds(400, 379, 142, 22);
		jTFTel.setBorder(new LineBorder(new Color(255,255,255)));
		panelPrincipal.add(jTFTel);
		jTFTel.setColumns(10);
		
		//JTF et lbl Sécteur
		lblSecteur = new JLabel("Sécteur d'activité");
		lblSecteur.setFont(style1);
		lblSecteur.setBounds(170, 429, 142, 22);
		lblSecteur.setForeground(Color.white);
		panelPrincipal.add(lblSecteur);
		
		jTFSecteur = new JTextField();
		jTFSecteur.setBounds(400, 429, 142, 22);
		jTFSecteur.setBorder(new LineBorder(new Color(255,255,255)));
		panelPrincipal.add(jTFSecteur);
		jTFSecteur.setColumns(10);
		
		//Création boutons Valier et Annuler
		btn1 = new JButton("Valider");
		btn1.setBounds(175, 500, 188, 76);
		btn1.addActionListener(controleur);
		btn1.setActionCommand("ValiderCreer");
		btn1.setBackground(new Color(27, 30, 46));
		btn1.setForeground(new Color(210, 234, 252));
		panelPrincipal.add(btn1);
				
		btn2 = new JButton("Annuler");
		btn2.setBounds(400, 500, 188, 76);
		btn2.addActionListener(controleur);
		btn2.setActionCommand("AnnulerCreer");
		btn2.setBackground(new Color(27, 30, 46));
		btn2.setForeground(new Color(210, 234, 252));
		panelPrincipal.add(btn2);
		
		if(action == "modifier"){
			Entreprise uneEntreprise = controleur.getInfoEntreprise();
			this.getjTFNom().setText(uneEntreprise.getNomEntreprise());
			this.getjTFAdresse().setText(uneEntreprise.getAdresseEntreprise());
			this.getjTFCP().setText(uneEntreprise.getCodePostalEntreprise());
			this.getjTFMail().setText(uneEntreprise.getAdresseMailEntreprise());
			this.getjTFSecteur().setText(uneEntreprise.getSecteurActiviteEntreprise());
			this.getjTFTel().setText(uneEntreprise.getNumeroTelephoneEntreprise());
			this.getjTFVille().setText(uneEntreprise.getVilleEntreprise());
			
			btn1.setActionCommand("ModifierEntreprise");
			btn2.setActionCommand("AnnulerCreer");
			
		}
	
		return panelPrincipal;
	}
	
	/**
	 * @return the jTFNom
	 */
	public JTextField getjTFNom() {
		return jTFNom;
	}
	/**
	 * @return the jTFAdresse
	 */
	public JTextField getjTFAdresse() {
		return jTFAdresse;
	}
	/**
	 * @return the jTFCP
	 */
	public JTextField getjTFCP() {
		return jTFCP;
	}
	/**
	 * @return the jTFVille
	 */
	public JTextField getjTFVille() {
		return jTFVille;
	}
	/**
	 * @return the jTFMail
	 */
	public JTextField getjTFMail() {
		return jTFMail;
	}
	/**
	 * @return the jTFTel
	 */
	public JTextField getjTFTel() {
		return jTFTel;
	}
	/**
	 * @return the jTFSecteur
	 */
	public JTextField getjTFSecteur() {
		return jTFSecteur;
	}

}