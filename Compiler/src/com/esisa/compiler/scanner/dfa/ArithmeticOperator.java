package com.esisa.compiler.scanner.dfa;

import com.esisa.compiler.scanner.DFA;

public class ArithmeticOperator extends DFA{

	public ArithmeticOperator() {
		super("Op�rateur Arithm�tique", 2, 1);
		add(0, 1, "+-*/%");
	}

}
