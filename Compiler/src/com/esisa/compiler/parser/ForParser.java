package com.esisa.compiler.parser;

import com.esisa.compiler.parser.Parser;

/*
 * Q1 Grammaire
 * 
 * A	-> for ( B ) C
 * B	-> I O R
 * I	-> id = N; | ;
 * O	-> id oprel N; | ;
 * R	-> X | eps
 * X	-> id opUna
 * N	-> id | nb
 * C	-> { L } | ;
 * L	-> A L | eps
 */
public class ForParser extends Parser {

	public ForParser() {
		getScanner().addReservedWordList("Boucle", "for");
	}

	// A -> for ( B ) C
	public boolean axiom() {
		if (next("for")) {
			if (next("(")) {
				if (B()) {
					if (next(")")) {
						return C();
					}
				}
			}
		}
		return false;
	}

	// B -> I O R
	private boolean B() {
		if (I()) {
			if (O()) {
				return R();
			}
		}
		return true;
	}

	// I -> id = N; | ;
	private boolean I() {
		if (next("Identificateur")) {
			if (next("=")) {
				if (N()) {
					return next(";");
				}
			}
		}
		return next(";");
	}

	// O -> id oprel N; | ;
	private boolean O() {
		if (next("Identificateur")) {
			if (next("Opérateur Relationnel")) {
				if (N()) {
					return next(";");
				}
			}
		}
		return next(";");
	}

	// R -> X | Y | eps
	private boolean R() {
		if (X()) {
			return true;
		}
		return true;
	}

	// X -> id++
	private boolean X() {
		if (next("Identificateur")) {
			return next("Opérateurs Unaires");
		}
		return false;
	}

	// N -> id | nb
	private boolean N() {
		if (next("Identificateur")) {
			return true;
		}
		return next("Number");
	}

	// C -> { L } | ;
	private boolean C() {
		if (next("{")) {
			if (L()) {
				return next("}");
			}
		}
		return next(";");
	}

	// L -> A L | eps
	private boolean L() {
		if (axiom()) {
			return L();
		}
		return true;
	}
}
