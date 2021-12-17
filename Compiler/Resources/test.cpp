package com.esisa.compiler.scanner.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.JToolBar;

import com.esisa.compiler.scanner.Error;
import com.esisa.compiler.scanner.InputTape;
import com.esisa.compiler.scanner.Scanner;
import com.esisa.compiler.scanner.Token;


public class ScannerPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	private Scanner scanner;
	private String file_source = ""; 
	private JFileChooser jfc; 
	private File selectedFile = null;

	private JTextPane source;
	private TokenTable table;
	private DefaultListModel<Error> errorModel;
	private JList<Error> list;
	private JLabel result;

	public ScannerPanel(Scanner scanner) {
		this.scanner = scanner;
		setLayout(new BorderLayout());

		source = new JTextPane();
		source.setFont(new Font("Consolas", Font.PLAIN, 14));
		source.setForeground(Color.BLACK);
		source.setMargin(new Insets(5, 5, 5, 5));

		table = new TokenTable();

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.add(new JScrollPane(source));
		splitPane.add(new JScrollPane(table));
		add("Center", splitPane);
		splitPane.setDividerLocation(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width/2);

		result = new JLabel();
		result.setForeground(Color.BLUE);
		result.setOpaque(true);
		result.setFont(new Font("Cursive", Font.BOLD, 12));

		errorModel = new DefaultListModel<>();
		list = new JList<Error>(errorModel);
		list.setFont(new Font("Consolas", Font.PLAIN, 13));
		list.setForeground(Color.RED);

		JPanel p1 = new JPanel(new BorderLayout());
		p1.add("North", result);
		p1.add("Center", new  JScrollPane(list));
		add("South", p1);

		jfc = new JFileChooser("D:\\ESISA\\L3\\Compilation\\WorkSpace\\Scanner\\Resources");

		createToolbar();
	}

	public void createToolbar() {
		JToolBar toolbar = new JToolBar();
		add("North", toolbar);
		String[][] list = {
				{"Nouveau", "New.png"},
				{"Ouvrir", "Open.png"},
				{"Enregister", "Save.png"},
				{"Enregister sous", "Saveas.png"},
				{"Analyse Lexicale", "Scan.png"},
				{"-"},
				{"Copier", "Copy.png"},
				{"Couper", "Cut.png"},
				{"Coller", "Paste.png"}
		};
		for (int i = 0; i < list.length; i++) {
			if ("-".equals(list[i][0]))
				toolbar.addSeparator();
			else {
				ImageIcon icon = new ImageIcon("Resources/icons/" + list[i][1]);
				JButton btn = new JButton(icon);
				btn.setToolTipText(list[i][0]);
				btn.addActionListener(this);
				toolbar.add(btn);
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			JButton Btn = (JButton)e.getSource();
			if (Btn.getToolTipText().equals("Analyse Lexicale")) {
				scan();
			}else if(Btn.getToolTipText().equals("Enregister sous")) {
				saveas();
			}else if(Btn.getToolTipText().equals("Enregister")) {
				save();
				saveTokens("Resources/Tokens");
			}else if(Btn.getToolTipText().equals("Ouvrir")) {
				openFile();
			}else if(Btn.getToolTipText().equals("Nouveau")) {
				newFile();
			}else if(Btn.getToolTipText().equals("Copier")) {
				source.copy();
			}else if(Btn.getToolTipText().equals("Coller")) {
				source.paste();
			}else if(Btn.getToolTipText().equals("Couper")) {
				source.cut();
			}
		}
	}

	private void scan() {
		InputTape it = new InputTape(source.getText());
		scanner.scan(it);
		Vector<Token> tokens = scanner.getTokens();
		table.setTokens(tokens);
		Vector<Error> errors = scanner.getErrors();
		errorModel.clear();
		if(errors.size() > 0) {
			result.setText("Nombre d'erreurs lexicales : " + Error.getErrorCount());
			for (Error error : errors) {
				errorModel.addElement(error);
			}
		}else {
			result.setText("Aucune Erreur Lexicale");
		}

	}

	private void saveas() {
		int returnValue = jfc.showSaveDialog(this);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			selectedFile = jfc.getSelectedFile();
			file_source = selectedFile.getAbsolutePath();
			save();
		}
	}

	private void save() {
		if (!file_source.equals("")) {
			try {
				PrintWriter out = new PrintWriter(file_source);
				out.write(source.getText());
				out.close();
			} catch (Exception e) {
				System.out.println("Erreur : " + e.getMessage());
			}
		}else saveas();
	}

	private void openFile() {
		if (!source.getText().equals(""))
			save();
		int returnValue = jfc.showOpenDialog(this);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			selectedFile = jfc.getSelectedFile();
			file_source = selectedFile.getAbsolutePath();
			open();
		}
	}

	private void open() {
		try {
			FileReader File = new FileReader(file_source);
			BufferedReader in = new BufferedReader(File);
			String str;
			while((str = in.readLine()) != null){
				source.setText(source.getText() + str + "\n");
			}
			in.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void newFile() {
		source.setText("");
		errorModel.clear();
		result.setText("");
	}

	public void saveTokens(String path) {
		if (!path.endsWith("/")) path = path + "/";
		Vector<Token> tokens = table.getTokens();
		Hashtable<String, Vector<Token>> h = new Hashtable<>();
		//Classification des tokens
		for (Token token : tokens) {
			Vector<Token> htokens = h.get(token.getType());
			if (htokens == null) {
				htokens = new Vector<Token>();
				h.put(token.getType(), htokens);				
			}
			htokens.add(token);
		}
		
		//Nettoyage du répertoire
		File folder = new File(path);
		File f[] = folder.listFiles();
		for (File file : f) {
			file.delete();
		}
		
		//Enregistrement
		for (String key : h.keySet()) {
			try {
				PrintWriter out = new PrintWriter(path + key + ".txt");
				Vector<Token> htokens = h.get(key);
				for (Token token : htokens) {
					out.println(token.getValue());
				}
				out.close();
			} catch (Exception e) {}
		}
	}
}
