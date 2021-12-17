package com.esisa.compiler.scanner.dfa;

import com.esisa.compiler.scanner.DFA;

public class AssignmentOperator extends DFA{

	public AssignmentOperator() {
		super("Opérateur D'Assignation", 8, 1, 2);
		add(0, 1, '=');
		add(0, 3, '+');
		add(0, 4, '-');
		add(0, 5, '*');
		add(0, 6, '/');
		add(0, 7, '%');
		add(3, 2, '=');
		add(4, 2, '=');
		add(5, 2, '=');
		add(6, 2, '=');
		add(7, 2, '=');
	}

}
