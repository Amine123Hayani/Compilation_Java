package com.esisa.compiler.scanner.dfa;

import com.esisa.compiler.scanner.DFA;

public class Blank extends DFA{

	public Blank() {
		super("Blank", 2, 1);
		add(0, 1, " \t\r\n");
		add(1, 1, " \t\r\n");
	}

}
