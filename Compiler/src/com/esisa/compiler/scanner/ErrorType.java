package com.esisa.compiler.scanner;

public enum ErrorType {
	LEXICAL_ERROR("Erreur Lexicale"),
	SYNTAXE_ERROR("Erreur Syntaxique"),
	SEMANTIC_ERROR("Erreur Semantique");
	
	private String value;
	
	private ErrorType(String value) {
		this.setValue(value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
