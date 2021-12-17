package com.esisa.compiler.scanner.swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

import com.esisa.compiler.scanner.InputTape;
import com.esisa.compiler.scanner.Scanner;
import com.esisa.compiler.scanner.Token;

public class Analyser extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private JTextPane source;
	private Scanner scanner;
	private DefaultTableModel table;

	public Analyser() {
		setLayout(new BorderLayout());
		setTitle("Analyser");
		
		scanner = new Scanner();
		scanner.setIgnoredTokens("Blank");
		
		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		split.setDividerLocation(500);
		source = new JTextPane();
		split.add(source);
		
		table = new DefaultTableModel(new String[] {"Value", "Type"}, 0);
		JTable t = new JTable(table);
		split.add(new JScrollPane(t));
		
		getContentPane().add("Center", split);
		
		JButton b1 = new JButton("Open file");
		b1.addActionListener(this);
		JButton b2 = new JButton("Anaylse");
		b2.addActionListener(this);
		
		JPanel panel = new JPanel();
		panel.add(b1);
		panel.add(b2);
		getContentPane().add("South", panel);

		setLocation(250, 40);
		setSize(1000, 800);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	
	
	public static void main(String[] args) {
		new Analyser();
	}

	public void actionPerformed(ActionEvent e) {
		JButton src = (JButton)e.getSource();
		if (src.getText().equals("Open file")) {
			JFileChooser jfc = new JFileChooser("Resources");
			int returnValue = jfc.showSaveDialog(this);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				try {
					FileReader file = new FileReader(jfc.getSelectedFile());
					BufferedReader in = new BufferedReader(file);
					String str;
					while((str = in.readLine()) != null){
						source.setText(source.getText() + str + "\n");
					}
					in.close();
				} catch (Exception e1) {}
				
			}
		}else if (src.getText().equals("Anaylse")) {
			InputTape it = new InputTape(source.getText());
			scanner.scan(it);
			Vector<Token> tokens = scanner.getTokens();
			for (Token token : tokens) {
				table.addRow(new String[] { token.getValue(), token.getType() });
			}
		}
		
	}
}
