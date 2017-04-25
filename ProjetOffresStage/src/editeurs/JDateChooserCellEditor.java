package editeurs;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import com.toedter.calendar.JDateChooser;


public class JDateChooserCellEditor extends AbstractCellEditor implements TableCellEditor {

	private JDateChooser dateChooser;
	protected Object value;



	public JDateChooserCellEditor() {
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString( "dd/MMM/yyyy");
		dateChooser.setOpaque(true);
	}

	public Object getCellEditorValue() {
		return dateChooser.getDate();
	}

	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {


		dateChooser.setDate((Date)value);
		dateChooser.setDateFormatString("dd/MM/yyyy");
		if (isSelected) {
			dateChooser.setBackground(table.getSelectionBackground());
			dateChooser.setDateFormatString("dd/MM/yyyy");
		} else {
			dateChooser.setBackground(table.getBackground());

			dateChooser.setDateFormatString("dd/MM/yyyy");
		}
		dateChooser.setDateFormatString("dd/MM/yyyy");
		return dateChooser;
	}
}