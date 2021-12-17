package com.esisa.compiler.scanner;

public class Error {
	private static int counter = 0;
	
	private int index;
	private ErrorType type;
	private int position;
	private String symbol;
	private String message;

	public Error() {
		counter++;
		index = counter;
	}

	public Error(ErrorType type, int position, String symbol, String message) {
		counter++;
		index = counter;
		this.type = type;
		this.position = position;
		this.symbol = symbol;
		this.message = message;
	}
	
	public static int getErrorCount() {
		return counter;
	}
	
	public static void reset() {
		counter = 0;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public ErrorType getType() {
		return type;
	}

	public void setType(ErrorType type) {
		this.type = type;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String toString() {
		return type.getValue() + "(" + index + ") à la position " + position + ". " + message + " [" + symbol + "].";
	}
	
	
}
