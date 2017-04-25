package jTable;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;


public class ModelJTable extends AbstractTableModel{
	private Object[][] data;
	private String[] title;
	private String compte;
	private String action;
	/**
	 * Constructeur
	 * 
	 * @param data
	 * @param title
	 */
	public ModelJTable(Object[][] data, String[] title, String compte, String action){
		this.data = data;
		this.title = title;
		this.compte = compte;
		this.action = action;
	}


	/**
	 * Retourne le titre de la colonne a l'indice specifie
	 * 
	 * @param col
	 */
	public String getColumnName(int col) {
		return this.title[col];
	}

	/**
	 * Retourne le nombre de colonnes
	 * 
	 * @param col
	 */
	public int getColumnCount() {
		return this.title.length;
	}

	/**
	 * Retourne le nombre de lignes
	 * 
	 * @param col
	 * @return this.data.length
	 */
	public int getRowCount() {
		return this.data.length;
	}

	/**
	 * Retourne la valeur a l'emplacement specifie
	 * 	
	 * @param row
	 * @param col
	 * @return this.data[row][col]
	 */
	public Object getValueAt(int row, int col) {
	    return this.data[row][col];
	}

	/**
	 * Definit la valeur a l'emplacement specifie
	 * 			   
	 * @param value
	 * @param row
	 * @param col
	 */	
	public void setValueAt(Object value, int row, int col) {
		//On interdit la modification sur certaines colonnes 
		if(!this.getColumnName(col).equals("Modifier") && !this.getColumnName(col).equals("Supprimer")
				&& !this.getColumnName(col).equals("Consulter") && !this.getColumnName(col).equals("Postuler"))
			this.data[row][col] = value;
	}
			    		    
	public void centerTable(JTable table) {
		DefaultTableCellRenderer custom = new DefaultTableCellRenderer(); 
		 custom.setHorizontalAlignment(JLabel.CENTER); // centre les donnees du tableau
		 for (int i=0 ; i < table.getColumnCount() ; i++) // centre chaque cellule du tableau
			 table.getColumnModel().getColumn(i).setCellRenderer(custom); 
	}
			    
	/**
	 * Autorise a modifier les champs noms, prenoms, login et mdp
	 * 			   
	 * @param row
	 * @param col
	 */
	 public boolean  isCellEditable(int row, int col) { 
		boolean b = false;
	 	if(this.compte == "admin"){
	 		
	 		if(col >= 0 && col < 6){
	 			 b = true;
			 }
	 		
	 		else {
				 b =  false;
			 }
	 		 
	 	}
	 	
	 	else if(compte == "employe"){
	 		
	 		if(this.action != "Modifier"){
	 			
	 			if(col == 7){
	 				b = true;
	 			}
	 		}
	 		
	 		else{
		 		
	 			if(col > 5){
		 			b =  true;
		 		}
	 		}
	 	}
	 		
	 		else if(compte == "etudiant"){
		 		
		 		if(col == 6 || col == 7){
		 			
		 			b = true ;
		 		}
		 		
		 		else{
		 			
		 			b = false ;
		 		}
	 		}
	 		
	 	return b;
	 }
	 
	 
	 /**
	  * Retourne la classe de la donnee de la colonne
	  * 			   
	  * @param col
	  * @return this.data[0][col].getClass()
	  */
	 public Class getColumnClass(int col){
		 //On retourne le type de la cellule a la colonne demandee
		 //On choisit donc la premiere ligne
		 return this.data[0][col].getClass();
	 }
	 
	
	 
}