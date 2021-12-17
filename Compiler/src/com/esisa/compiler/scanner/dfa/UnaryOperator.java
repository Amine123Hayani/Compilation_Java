package com.esisa.compiler.scanner.dfa;

import com.esisa.compiler.scanner.DFA;

public class UnaryOperator extends DFA{

	public UnaryOperator() {
		super("Opérateurs Unaires", 5, 2, 4);
		add(0, 1, '+');
		add(1, 2, '+');
		add(0, 3, '-');
		add(3, 4, '-');
	}

}
