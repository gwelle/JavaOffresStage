package editeurs;
import controleurs.Controleur;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

//CTRL + SHIFT + O pour g�n�rer les imports
public class ButtonEditor extends DefaultCellEditor {

	private JButton button;
	private boolean isPushed;
	private ButtonListener bListener ;
	
	public ButtonEditor(JCheckBox checkBox, Controleur c, int i) {
		    super(checkBox);
		    button = new JButton();
		    button.setOpaque(true);
		    button.addActionListener(c);
		    if(i == 1){
		    	button.setActionCommand("ValiderModifierEtudiant");
		    }else if(i == 2){
		    	button.setActionCommand("ValiderSupprimerEtudiant");
		    }else if(i == 3){
		    	button.setActionCommand("ValiderModifierEmploye");
		    }else if(i == 4){
		    	button.setActionCommand("ValiderSupprimerEmploye");
		    }else if(i == 5){
		    	button.setActionCommand("ValiderOffre");
		    }else if(i == 6){
		    	button.setActionCommand("ModifierUneOffre");
		    }else if(i == 7){
		    	button.setActionCommand("ValiderSupprimerOffre");
		    }else if(i == 10){
		    	button.setActionCommand("ValiderModifierDomaine");
		    }else if(i == 11){
		    	button.setActionCommand("ValiderSupprimerDomaine");
		    }
		    else if(i == 12){
		    	button.setActionCommand("ValiderBoutonConsulter");
		    }
		    
		    else if(i == 13){
		    	button.setActionCommand("ValiderBoutonPostuler");
		    }
		    
		    bListener =  new ButtonListener();
		    
		}
	

  public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) { 
    //On affecte le num�ro de ligne au listener
    bListener.setRow(row);
    //Idem pour le num�ro de colonne
    bListener.setColumn(column);
    //On passe aussi le tableau en param�tre pour des actions potentielles
    bListener.setTable(table);

    //On r�affecte le libell� au bouton
    button.setText( (value == null) ? "Modifier" : value.toString() );
  
    //On renvoie le bouton
    return button;
  }
  
  public int getRow(JTable table, Controleur c){
  	
  	return table.getSelectedRow();
  }
  

   //Notre listener pour le bouton
  class ButtonListener implements ActionListener{

	  private int column, row;
	    private JTable table;
	    private int nbre = 0;
	    private JButton button;
	
	    public void setColumn(int col){this.column = col;}
	    public void setRow(int row){this.row = row;}
	    public void setTable(JTable table){this.table = table;}
	
	    public JButton getButton(){return this.button;}
	   
	  
	    public void actionPerformed(ActionEvent event) {
	    	
	    	//System.out.println("Test" + ((JButton)event.getSource()).getText());
	    	//System.out.println(table.getSelectedRow());
	    	
		    //On affecte un nouveau libell� � une celulle de la ligne
		    //((AbstractTableModel)table.getModel()).setValueAt("" + (++nbre), this.row, (this.column -1));   
		    //Permet de dire � notre tableau qu'une valeur a chang� � l'emplacement d�termin� par les valeurs pass�es en param�tres
		    //((AbstractTableModel)table.getModel()).fireTableCellUpdated(this.row, this.column - 1);
		    this.button = ((JButton)event.getSource());
		    
	    }
  }
}