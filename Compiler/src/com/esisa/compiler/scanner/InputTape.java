package com.esisa.compiler.scanner;

import java.io.File;
import java.io.FileReader;

public class InputTape {
	private String buffer;
	private int readHead = 0;
	private int start = 0;
	
	public InputTape(String buffer) {
		this.buffer = buffer;
	}
	
	public InputTape(File file) {
		try {
			FileReader in = new FileReader(file);
			char t[] = new char[(int)file.length()];
			in.read(t);
			buffer = new String(t);
			in.close();
		} catch (Exception e) {
			System.out.println("Erreur : " + e.getMessage());
		}
	}
	
	public void mark() {
		start = readHead;
	}
	
	public void reset() {
		readHead = start;
	}
	
	public char next() {
		readHead++;
		if (eof()) return 0;	
		return buffer.charAt(readHead);
	}
	
	public char current() {
		return buffer.charAt(readHead);
	}
	
	public boolean eof() {
		return (readHead >= buffer.length());
	}
	
	public String getToken() {
		return buffer.substring(start, readHead);
	}
	
	public int getReadHead() {
		return readHead;
	}
}
