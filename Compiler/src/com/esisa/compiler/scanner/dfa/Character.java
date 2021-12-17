package com.esisa.compiler.scanner.dfa;

import com.esisa.compiler.scanner.DFA;

public class Character extends DFA {

	public Character() {
		super("Charact�re", 4, 3);
		add(0, 1, 39);
		add(1, 2, 65, 90);
		add(1, 2, 97, 122);
		add(2, 3, 39);
	}

}
