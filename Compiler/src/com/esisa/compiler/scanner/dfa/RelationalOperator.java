package com.esisa.compiler.scanner.dfa;

import com.esisa.compiler.scanner.DFA;

public class RelationalOperator extends DFA{

	public RelationalOperator() {
		super("Opérateur Relationnel", 4, 1, 2);
		add(0, 1, "<>");
		add(0, 3, "=!");
		add(1, 2, '=');
		add(3, 2, '=');
		
	}

}
