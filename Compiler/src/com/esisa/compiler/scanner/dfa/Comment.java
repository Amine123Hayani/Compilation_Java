package com.esisa.compiler.scanner.dfa;

import com.esisa.compiler.scanner.DFA;

public class Comment extends DFA{

	public Comment() {
		super("Commentaire", 6, 2, 5);
		add(0, 1, '/');
		add(1, 2, '/');
		add(2, 2, 32, 255);
		add(2, 2, "\t");
		add(1, 3, '*');
		add(3, 3, 32, 255);
		add(3, 3, "\n\t\r");
		add(4, 3, 32, 255);
		add(4, 3, "\n\t\r");
		add(3, 4, '*');
		add(4, 5, '/');
	}
}
