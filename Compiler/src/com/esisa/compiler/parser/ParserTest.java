package com.esisa.compiler.parser;

import java.io.File;
import java.util.Vector;

import com.esisa.compiler.scanner.InputTape;
import com.esisa.compiler.scanner.Error;

public class ParserTest {

	public ParserTest() {
		System.out.println("-------------------EXP01-------------------");
		exp01();
		System.out.println("-------------------EXP02-------------------");
		exp02();
		System.out.println("-------------------EXP03-------------------");
		exp03();
		System.out.println("-------------------EXP04-------------------");
		exp04();
	}

	private void exp01() {
		InputTape inputTape = new InputTape(new File("Resources/exp01.cpp"));
		Parser parser = new WhileParser();
		boolean result = parser.parse(inputTape);
		System.out.println(result);
		Vector<Error> errors = parser.getErrors();
		for (Error error : errors) {
			System.out.println(error);
		}
	}

	private void exp02() {
		InputTape inputTape = new InputTape(new File("Resources/exp02.cpp"));
		Parser parser = new WhileParser();
		boolean result = parser.parse(inputTape);
		System.out.println(result);
		Vector<Error> errors = parser.getErrors();
		for (Error error : errors) {
			System.out.println(error);
		}
	}

	private void exp03() {
		InputTape inputTape = new InputTape(new File("Resources/exp03.cpp"));
		Parser parser = new WhileParser();
		boolean result = parser.parse(inputTape);
		System.out.println(result);
		Vector<Error> errors = parser.getErrors();
		for (Error error : errors) {
			System.out.println(error);
		}
	}

	private void exp04() {
		InputTape inputTape = new InputTape(new File("Resources/exp04.cpp"));
		Parser parser = new WhileParser();
		boolean result = parser.parse(inputTape);
		System.out.println(result);
		Vector<Error> errors = parser.getErrors();
		for (Error error : errors) {
			System.out.println(error);
		}
	}

	public static void main(String[] args) {
		new ParserTest();
	}

}
