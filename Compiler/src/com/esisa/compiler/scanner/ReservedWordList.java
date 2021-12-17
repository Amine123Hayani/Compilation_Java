package com.esisa.compiler.scanner;

import java.util.Vector;

public class ReservedWordList {
	private String name;
	private Vector<String> words;

	public ReservedWordList(String name, String ...words) {
		this.name = name;
		this.words = new Vector<>();
		for (String str : words) {
			this.words.add(str);
		}
	}
	
	public void add(String word) {
		words.add(word);
	}
	
	public boolean contains(String token) {		
		return words.contains(token);
	}
	
	public String getName() {
		return name;
	}

}
