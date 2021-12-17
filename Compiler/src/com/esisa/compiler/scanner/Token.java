package com.esisa.compiler.scanner;

public class Token {
	private String value; // Lexème
	private String type; // Unité lexicale

	public Token() {
	}

	public Token(String value, String type) {
		super();
		this.value = value;
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String toString() {
		return "[" + value + " : " + type + "]";
	}
	
	public boolean equals(String token) {
		if(token.equals(value)) return true;
		if(token.equals(type)) return true;
		return false;
	}
}
