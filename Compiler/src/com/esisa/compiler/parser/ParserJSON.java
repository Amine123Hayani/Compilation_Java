package com.esisa.compiler.parser;

public class ParserJSON extends Parser {

	public ParserJSON() {
	}

	public boolean axiom() {
		return Object();
	}

	private boolean Object() {
		if (next("{")) {
			if (fields()) {
				return next("}");
			}
		}
		return false;
	}

	private boolean fields() {
		if (field()) {
			return nextFields();
		}
		return true;
	}

	private boolean field() {
		if (next("String")) {
			if (next(":")) {
				return element();
			}
		}
		return false;
	}

	private boolean nextFields() {
		if (next(",")) {
			if (field()) {
				return nextFields();
			}
		}
		return true;
	}

	private boolean Array() {
		if (next("[")) {
			if (elements()) {
				return (next("]"));
			}
		}
		return false;
	}

	private boolean elements() {
		if (element()) {
			return next();
		}
		return true;
	}

	private boolean next() {
		if (next(",")) {
			if (element()) {
				return next();
			}
		}
		return true;
	}

	private boolean element() {
		if (Array()) return true;
		if (Object()) return true;
		if (next("Number")) return true;
		if (next("String")) return true;
		if (next("boolean")) return true;
		return false;
	}

}
