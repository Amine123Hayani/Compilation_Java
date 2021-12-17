package com.esisa.compiler.scanner.swing;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import com.esisa.compiler.scanner.Token;

public class TokenTable extends JTable {
	private static final long serialVersionUID = 1L;
	private TokenModel model;
	
	public TokenTable(String ...titles) {
		model = new TokenModel();
		setModel(model);
		getTableHeader().setReorderingAllowed(false);
	}
	
	public void add(Token token) {
		model.add(token);
	}
	
	public void setTokens(Vector<Token> tokens) {
		model.setTokens(tokens);
	}
	
	public void clear() {
		model.clear();
	}
	
	public TableCellRenderer getCellRenderer(int row, int column) {
		return new TokenCellRenderer();
	}

	public Token getToken(int index) {
		return model.getToken(index);
	}

	public Vector<Token> getTokens() {
		return model.getTokens();
	}
	
	
}
