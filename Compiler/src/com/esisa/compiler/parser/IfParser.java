package com.esisa.compiler.parser;

/*
 * Q1 : Grammaire
 * A 	->	if ( C ) Inst Else
 * C	->	Op oprel Op
 * Op	-> 	id | nb
 * Inst ->	{ L } | I | ;
 * L	-> 	I L | eps
 * I	->	id = E ;
 * Else	-> else Inst | eps
 * E	-> 	T E'
 * E'	->	+ T E' | - T E' | eps
 * T	-> 	F T'
 * T'	-> 	* F T' | / F T' | eps
 * F	->	id | nb | ( E )
 * 
 * VT = { if, else, oprel, {, }, ;, =, opar, (, ), id, nb }
 */

/*
 * Q2 : le Parser
 */
public class IfParser extends Parser {

	public IfParser() {
		getScanner().addReservedWordList("choice", "if", "else");
	}

	// A 	->	if ( C ) Inst Else
	public boolean axiom() {
		if (next("if")) {
			if (next("(")) {
				if (C()) {
					if (next(")")) {
						if (Inst()) {
							return Else();
						}
					}
				}
			}
		}
		return false;
	}
	
	// C	->	Op oprel Op
	private boolean C() {
		if (Op()) {
			if (next("Opérateur Relationnel")) {
				return Op();
			}
		}
		return false;
	}
	
	//Inst ->	{ L } | I | ;
	private boolean Inst() {
		if(next("{")) {
			if (L()) {
				return next("}");
			}
		}
		if (I()) {
			return true;
		}
		return next(";");
	}
	
	//I	->	id = E ;
	private boolean I() {
		if (next("Identificateur")) {
			if (next("=")) {
				if (E()) {
					return next(";");
				}
			}
		}
		return false;
	}

	//E	-> 	T E'
	private boolean E() {
		if (T()) {
			return EP();
		}
		return false;
	}

	//T	-> 	F T'
	private boolean T() {
		if (F()) {
			return TP();
		}
		return false;
	}
	
	//T'	-> 	* F T' | / F T' | eps
	private boolean TP() {
		if (next("*")) {
			if (F()) {
				return TP();
			}
		}
		if (next("/")) {
			if (F()) {
				return TP();
			}
		}
		return true;
	}

	//F	->	id | nb | ( E )
	private boolean F() {
		if (next("Identificateur")) {
			return true;
		}
		if (next("Number")) {
			return true;
		}
		if (next("(")) {
			if (E()) {
				return next(")");
			}
		}
		return false;
	}

	//E'	->	+ T E' | - T E' | eps
	private boolean EP() {
		if (next("+")) {
			if (T()) {
				return EP();
			}
		}
		if (next("-")) {
			if (T()) {
				return EP();
			}
		}
		return true;
	}

	// L	-> 	I L | eps
	private boolean L() {
		if (I()) {
			return L();
		}
		return true;
	}

	//Else	-> else Inst | eps
	private boolean Else() {
		if (next("else")) {
			return Inst();
		}
		return true;
	}
	
	// Op	-> 	id | nb
	private boolean Op() {
		if (next("Identificateur")) return true;
		if (next("Number")) return true;
		return false;
	}

}
