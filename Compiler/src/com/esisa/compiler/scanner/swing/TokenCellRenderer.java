package com.esisa.compiler.scanner.swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.Hashtable;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class TokenCellRenderer implements TableCellRenderer{
	private static final Hashtable<String, CellProperties> properties = new Hashtable<String, CellProperties>();
	
	static {
		properties.put("Identificateur", new CellProperties(Color.RED, Color.BLACK));
		properties.put("Modifier", new CellProperties(Color.LIGHT_GRAY));
		properties.put("Op�rateur Arithm�tique", new CellProperties(Color.BLACK, CellProperties.CENTER, new Font("Arial", Font.ITALIC, 10)));
		properties.put("Op�rateur D'Assignation", new CellProperties(Color.BLACK));
		properties.put("Charact�re", new CellProperties(Color.DARK_GRAY));
		properties.put("Cha�ne de Caract�res", new CellProperties(Color.RED));
		properties.put("Type", new CellProperties(Color.RED));
		properties.put("Keyword", new CellProperties(Color.RED));
		properties.put("Op�rateur Logique", new CellProperties(Color.BLACK));
		properties.put("Nombre", new CellProperties(Color.GRAY, CellProperties.CENTER, new Font("Courier New", Font.BOLD, 16)));
		properties.put("Op�rateur Relationnel", new CellProperties(Color.BLACK));
		properties.put("S�parateur", new CellProperties(Color.BLACK));
		properties.put("Op�rateurs Unaires", new CellProperties(Color.BLACK));
	}
	
	public TokenCellRenderer() {
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		JLabel l1 = new JLabel("" + value);
		TokenTable t = (TokenTable)table;
		CellProperties props = properties.get(t.getToken(row).getType());
		if (props != null) {
			l1.setForeground(props.getTextColor());
			l1.setOpaque(true);
			l1.setBackground(props.getBackColor());
			l1.setFont(props.getFont());
			l1.setHorizontalAlignment(props.getAlign());
		}
		return l1;

	}

}
