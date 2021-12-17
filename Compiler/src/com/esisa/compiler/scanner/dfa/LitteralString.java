package com.esisa.compiler.scanner.dfa;

import com.esisa.compiler.scanner.DFA;

public class LitteralString extends DFA{

	public LitteralString() {
		super("String", 4, 2);
		add(0, 1, '"');
		add(1, 1, 0, 33);
		add(1, 1, 35, 91);
		add(1, 1, 93, 255);
		add(1, 1, "\n\t\r");
		add(1, 3, 92);
		add(3, 1, "ntr");
		add(3, 1, 92);
		add(1, 2, '"');
	}

}
