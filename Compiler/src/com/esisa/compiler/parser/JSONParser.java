package com.esisa.compiler.parser;

public class JSONParser extends Parser {

	public JSONParser() {
	}

	public boolean axiom() {
		return object();
	}
	
	private boolean object() { // Object -> { Field } 
		if(next("{")) {
			if (fields()) {
				return next("}");
			}
		}
		return false;
	}
	
	private boolean fields() {
		if (field()) {
			return nextFields();
		}
		return true;
	}
	
	private boolean nextFields() {
		if (next(",")) {
			if (field()) {
				return nextFields();
			}
			return false;
		}
		return true;
	}

	private boolean field() { // key : value
		if (next("String")) {
			if (next(":")) {
				if (next("String")) return true;
				if (next("Number")) return true;
				if (next("boolean")) return true;
			}
		}
		return false;
	}
}
