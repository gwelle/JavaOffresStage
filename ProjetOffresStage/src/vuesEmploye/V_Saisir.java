package vuesEmploye;
import com.toedter.calendar.*;
import controleurs.Controleur;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.TableModel;

public class V_Saisir extends JFrame{
	
	private JPanel panelPrincipal;
	
	private ArrayList lesDomaines;
	
	public JComboBox listeDomaine;
	
	private JTextField jTFEntreprise;
	private JTextField jTFLib;
	private JDateChooser uneDate;
	private JTextField jTFDuree;
	private JTextField jTFChemin;
	private JTextField jTFSecteur;
	
	private JTextArea jTADescriptif; 
	
	private JLabel lblNom;
	private JLabel lblDomaine;
	private JLabel lblLib;
	private JLabel lblDate;
	private JLabel lblDuree;
	private JLabel lblChemin;
	private JLabel lblDescriptif;
	
	//Constructeur
	public V_Saisir() {
	}
	
	public JPanel saisir(Controleur c, String action){
		
		//PanelPrincipal
		this.panelPrincipal = new JPanel();
		this.panelPrincipal.setBackground(new Color(58, 65, 94));
		this.panelPrincipal.setBounds(252, 0, 850, 672);
		this.getContentPane().add(this.panelPrincipal, BorderLayout.CENTER);
		this.panelPrincipal.setLayout(null);
		
		//Creation des JComboBox et lbl
		//JComboBox et lbl Nom
		lblNom = new JLabel("Nom de l'entreprise");
		Font style1 = new Font("Verdana",Font.BOLD,12);
		lblNom.setFont(style1);
		lblNom.setBounds(170, 129, 142, 22);
		lblNom.setForeground(Color.white);
		panelPrincipal.add(lblNom);
		
		jTFEntreprise = new JTextField();
		jTFEntreprise.setBounds(400, 129, 142, 22);
		jTFEntreprise.setBorder(new LineBorder(new Color(255,255,255)));
		jTFEntreprise.setText(c.getInfoEntreprise().getNomEntreprise());
		jTFEntreprise.setEditable(false);
		panelPrincipal.add(jTFEntreprise);
		jTFEntreprise.setColumns(10);
		
		
		//JTF et lbl Domaine d'activite
		lblDomaine = new JLabel("Domaine de l'offre");
		lblDomaine.setFont(style1);
		lblDomaine.setBounds(170, 179, 142, 22);
		lblDomaine.setForeground(Color.white);
		panelPrincipal.add(lblDomaine);
		
		this.listeDomaine = new JComboBox();
		
		this.lesDomaines = c.getListeDomaines();
		
		String unDomaine = null;
		int i = 0;
		for(i = 0; i < lesDomaines.size(); i++){
			unDomaine = lesDomaines.get(i).toString();
			listeDomaine.addItem(unDomaine);
		}
	
		listeDomaine.setSize(10,15);					
		listeDomaine.setBounds(400, 179, 142, 22);
		listeDomaine.addActionListener(c);
		listeDomaine.setBackground(new Color(255, 255, 255));
		panelPrincipal.add(listeDomaine);
		
		//Création des JTF et lbl
		//JTF et lbl Libelle de l'offre
		lblLib = new JLabel("Libelle de l'offre");
		lblLib.setFont(style1);
		lblLib.setBounds(170, 229, 142, 22);
		lblLib.setForeground(Color.white);
		panelPrincipal.add(lblLib);
				
		jTFLib = new JTextField();
		jTFLib.setBounds(400, 229, 142, 22);
		jTFLib.setBorder(new LineBorder(new Color(255,255,255)));
		panelPrincipal.add(jTFLib);
		jTFLib.setColumns(10);
				
		//JTF et lbl Date
		lblDate = new JLabel("Date de debut");
		lblDate.setFont(style1);
		lblDate.setBounds(170, 279, 122, 22);
		lblDate.setForeground(Color.white);
		panelPrincipal.add(lblDate);
		
		uneDate = new JDateChooser();
		uneDate.setBounds(400, 279, 142, 22);
		uneDate.setBorder(new LineBorder(new Color(255,255,255)));
		panelPrincipal.add(uneDate);

		//JTF et lbl Duree
		lblDuree = new JLabel("Duree");
		lblDuree.setFont(style1);
		lblDuree.setBounds(170, 329, 142, 22);
		lblDuree.setForeground(Color.white);
		panelPrincipal.add(lblDuree);
		
		jTFDuree = new JTextField();
								//329
		jTFDuree.setBounds(400, 329, 142, 22);
		jTFDuree.setBorder(new LineBorder(new Color(255,255,255)));
		panelPrincipal.add(jTFDuree);
		jTFDuree.setColumns(10);
			
		//JTF et lbl Chemin de stockage
		lblChemin = new JLabel("Chemin de stockage");
		lblChemin.setFont(style1);
		lblChemin.setBounds(170, 379, 142, 22);
		lblChemin.setForeground(Color.white);
		panelPrincipal.add(lblChemin);
		
		jTFChemin = new JTextField();
		jTFChemin.setBounds(400, 379, 142, 22);
		jTFChemin.setBorder(new LineBorder(new Color(255,255,255)));
		panelPrincipal.add(jTFChemin);
		jTFChemin.setColumns(10);
				
		//JTF et lbl Descriptif de l'offre
		lblDescriptif = new JLabel("Descriptif de l'offre");
		lblDescriptif.setFont(style1);
		lblDescriptif.setBounds(170, 429, 142, 22);
		lblDescriptif.setForeground(Color.white);
		panelPrincipal.add(lblDescriptif);
		
		jTADescriptif = new JTextArea();
		jTADescriptif.setBorder(new LineBorder(new Color(255,255,255)));
		jTADescriptif.setBounds(400, 429, 250, 100);
		jTADescriptif.setLineWrap(true);
		jTADescriptif.setWrapStyleWord(true); 
		JScrollPane scrollPaneArea = new JScrollPane(jTADescriptif, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneArea.setVisible(true);
		scrollPaneArea.setPreferredSize(new Dimension(250, 100));
		scrollPaneArea.setBounds(400, 429, 250, 100);

		panelPrincipal.add(scrollPaneArea);
		
		//Creation boutons Valier et Annuler
		JButton btnValiderSaisir = new JButton("Valider");
		btnValiderSaisir.setBounds(175, 550, 188, 76);
		btnValiderSaisir.addActionListener(c);
		btnValiderSaisir.setActionCommand("Valider"+action+"Offre");
		btnValiderSaisir.setBackground(new Color(27, 30, 46));
		btnValiderSaisir.setForeground(new Color(210, 234, 252));
		panelPrincipal.add(btnValiderSaisir);
				
		JButton btnAnnulerSaisir = new JButton("Annuler");
		btnAnnulerSaisir.setBounds(400, 550, 188, 76);
		btnAnnulerSaisir.addActionListener(c);
		btnAnnulerSaisir.setActionCommand("AnnulerSaisir");
		btnAnnulerSaisir.setBackground(new Color(27, 30, 46));
		btnAnnulerSaisir.setForeground(new Color(210, 234, 252));
		panelPrincipal.add(btnAnnulerSaisir);
				
		return panelPrincipal;
		
	}
	
	public JTextField getjTFEntreprise() {
		return jTFEntreprise;
	}
	
	public JTextField getjTFLib() {
		return jTFLib;
	}

	public JDateChooser getUneDate() {
		return uneDate;
	}

	public JTextField getjTFDuree() {
		return jTFDuree;
	}

	public JTextField getjTFChemin() {
		return jTFChemin;
	}

	public JTextField getjTFSecteur() {
		return jTFSecteur;
	}
	
	public JTextArea getjTADescriptif() {
		return jTADescriptif;
	}
	
	public JComboBox getListeDomaine(){
		return listeDomaine;
	}

}
