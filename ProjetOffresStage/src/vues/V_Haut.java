package vues;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class V_Haut {

	public V_Haut(){
	}
	
	private JLabel info;
	
	public JPanel panelHaut(){
		
		JPanel panel_haut = new JPanel();
		panel_haut.setBackground(new Color(41, 46, 69));
		panel_haut.setBounds(252, 0, 1000, 106);
		panel_haut.setLayout(null);
		
		info = new JLabel("");
		info.setBounds(20, 70, 350, 20);
		info.setForeground(Color.white);
		panel_haut.add(info);
	
		return panel_haut;
	}
	
	public JLabel getInfo(){
		return info;
	}
	
}
