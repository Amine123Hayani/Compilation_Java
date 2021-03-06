package com.esisa.compiler.parser;

import java.util.Vector;

import com.esisa.compiler.scanner.InputTape;
import com.esisa.compiler.scanner.Scanner;
import com.esisa.compiler.scanner.Token;
import com.esisa.compiler.scanner.Error;

public abstract class Parser {
	private Scanner scanner;
	private Vector<Token> tokens;
	private int index = 0;
	private Token token;
	public static final Token $ = new Token("$", "$");

	private Vector<Error> errors;

	public Parser() {
		scanner = new Scanner();
	}

	public boolean parse(InputTape inputTape) {
		scanner.scan(inputTape);
		tokens = scanner.getTokens();
		errors = scanner.getErrors();
		return axiom();
	}

	public boolean next(String terminal) {
		if (index < tokens.size()) {
			token = tokens.get(index);
			//System.out.println(token);
			if (token.equals(terminal)) {
				index++;
				return true;
			}else {
				return false;
			}
		}else {
			token = $;
			if (token.equals(terminal))
				return true;
			return false;
		}
	}
	
	public Vector<Error> getErrors() {
		return errors;
	}
	
	public Scanner getScanner() {
		return scanner;
	}
	
	abstract public boolean axiom();

}
