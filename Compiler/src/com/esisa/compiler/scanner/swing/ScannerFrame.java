package com.esisa.compiler.scanner.swing;

import javax.swing.JFrame;

import com.esisa.compiler.scanner.Scanner;

public class ScannerFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private ScannerPanel ScannerPanel;
	
	public ScannerFrame() {
		Scanner scanner = new Scanner();
		scanner.setIgnoredTokens("Blank", "Commentaire");
		ScannerPanel = new ScannerPanel(scanner);
		setContentPane(ScannerPanel);
		config();
	}
	
	void config() {
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new ScannerFrame();
	}

}
