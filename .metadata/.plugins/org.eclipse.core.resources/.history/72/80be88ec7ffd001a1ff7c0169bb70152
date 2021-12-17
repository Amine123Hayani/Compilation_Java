package com.esisa.compiler.scanner;

import java.util.Vector;

import com.esisa.compiler.scanner.dfa.ArithmeticOperator;
import com.esisa.compiler.scanner.dfa.AssignmentOperator;
import com.esisa.compiler.scanner.dfa.Blank;
import com.esisa.compiler.scanner.dfa.Comment;
import com.esisa.compiler.scanner.dfa.Identifier;
import com.esisa.compiler.scanner.dfa.LitteralString;
import com.esisa.compiler.scanner.dfa.LogicalOperator;
import com.esisa.compiler.scanner.dfa.RelationalOperator;
import com.esisa.compiler.scanner.dfa.Separator;
import com.esisa.compiler.scanner.dfa.UnaryOperator;
import com.esisa.compiler.scanner.dfa.Character;
import com.esisa.compiler.scanner.dfa.Number;

public class Scanner {
	public static final Token EOF = new Token("$", "$");

	private Vector<DFA> list;
	private InputTape inputTape;
	private Vector<Token> tokens; //Table des symboles
	private Vector<Error> errors;
	private Vector<String> ignored;
	private Vector<ReservedWordList> reservedWordLists;

	public Scanner() {
		list = new Vector<>();
		ignored = new Vector<String>();
		reservedWordLists = new Vector<ReservedWordList>();
		
		add(new Blank());
		add(new Comment());
		add(new Identifier());
		add(new Number());
		add(new Blank());
		add(new Separator());
		add(new LitteralString());
		add(new UnaryOperator());
		add(new RelationalOperator());
		add(new AssignmentOperator());
		add(new ArithmeticOperator());
		add(new LogicalOperator());
		add(new Character());

		//addReversedWordList("Type", "byte", "short", "int", "long", "float", "double", "char", "boolean");
		//addReversedWordList("Modifier", "private", "public", "protected", "static", "final", "synchronized");
		//addReservedWordList("Keyword", "package", "class", "interface", "enum", "import",
				//"void", "new", "instanceof", "return", "if", "else", "while", "do", "break", "for", "null", "switch", "case");
		
		addReservedWordList("boolean", "true", "false");
		setIgnoredTokens("Blank");
	}

	public void setIgnoredTokens(String ...tokens) {
		for (String token : tokens) {
			ignored.add(token);
		}
	}

	private boolean toIgnore(Token token) {
		for (String ign : ignored) {
			if(ign.equals(token.getType()))
				return true;
		}
		return false;
	}

	public void addReservedWordList(ReservedWordList list) {
		reservedWordLists.add(list);
	}

	public void addReservedWordList(String name, String ...words) {
		addReservedWordList(new ReservedWordList(name, words));
	}

	public Vector<Token> getTokens() {
		return tokens;
	}

	public Vector<Error> getErrors() {
		return errors;
	}

	public void scan(InputTape inputTape) {
		this.inputTape = inputTape;
		tokens = new Vector<>();
		errors = new Vector<>();
		Error.reset();
		Token token = next();
		while(token != EOF) {
			if (token != null)
				if (!toIgnore(token)) tokens.add(token);
			token = next();
		}
	}

	public void add(DFA a) {
		list.add(a);
	}

	private boolean checkReservedWord(Token token) {
		if (!token.getType().equals("Identificateur")) 
			return false;
		for (ReservedWordList list : reservedWordLists) {
			if (list.contains(token.getValue())) {
				token.setType(list.getName());
				return true;
			}				
		}
		return false;
	}

	public Token next() {
		if (inputTape.eof())
			return EOF;
		for (DFA dfa : list) {
			Token token = dfa.extract(inputTape);
			if(token != null) {
				checkReservedWord(token);
				return token;
			}
		}
		//System.out.println("Erreur Lexicale à la position " + inputTape.getReadHead() + ". Symbole inconnu [" + inputTape.current() + "]");
		errors.add(new Error(ErrorType.LEXICAL_ERROR, inputTape.getReadHead(), "" + inputTape.current(), "Symbole inconnu"));
		inputTape.next();
		return null;
	}
}
