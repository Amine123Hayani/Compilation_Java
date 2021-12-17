package com.esisa.compiler.scanner.dfa;

import com.esisa.compiler.scanner.DFA;

public class LogicalOperator extends DFA{

	public LogicalOperator() {
		super("Opérateur Logique", 6, 2, 4, 5);
		add(0, 1, '&');
		add(1, 2, '&');
		add(0, 3, '|');
		add(3, 4, '|');
		add(0, 5, '!');
	}

}
