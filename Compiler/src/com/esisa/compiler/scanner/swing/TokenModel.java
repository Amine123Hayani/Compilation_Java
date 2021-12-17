package com.esisa.compiler.scanner.swing;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import com.esisa.compiler.scanner.Token;

public class TokenModel extends DefaultTableModel{
	static final long serialVersionUID = 1L;
	private Vector<Token> tokens;
	
	public TokenModel() {
		super(new String[] {"Lexème", "Unité Lexicale"}, 0);
		tokens = new Vector<Token>();
	}
	
	public void add(Token token) {
		tokens.add(token);
		addRow(new String[] { token.getValue(), token.getType() });
	}
	
	public void setTokens(Vector<Token> tokens) {
		setRowCount(0);
		for (Token token : tokens) {
			add(token);
		}
	}
	
	public Token getToken(int index) {
		return tokens.get(index);
	}
	
	public Vector<Token> getTokens() {
		return tokens;
	}
	
	public void clear() {
		setRowCount(0);
		tokens.clear();
	}
	
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
