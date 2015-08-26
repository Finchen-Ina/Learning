package Lernkarten;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class QuizKartenAutor {
	
	private JTextArea frage;
	private JTextArea antwort;
	private ArrayList<QuizKarte> kartenListe;
	private JFrame frame;
	
	public static void main (String[] args) {
		QuizKartenAutor editor = new QuizKartenAutor();
		editor.los();
	}
	
	public void los() {
		//GUI erstellen
		frame = new JFrame("Quizkarten-Autor");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainPanel = new JPanel();
		Font bigFont = new Font("sanserif", Font.BOLD, 24);
		frage = new JTextArea(6,20);
		frage.setLineWrap(true);
		frage.setWrapStyleWord(true);
		frage.setFont(bigFont);
		
		JScrollPane fScroller = new JScrollPane(frage);
		fScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		fScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		antwort = new JTextArea(6,20);
		antwort.setLineWrap(true);
		antwort.setWrapStyleWord(true);
		antwort.setFont(bigFont);
		
		JScrollPane aScroller = new JScrollPane(antwort);
		aScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		aScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JButton naechsteKarteButton = new JButton("Nächste Karte");
		
		kartenListe = new ArrayList<QuizKarte>();
		JLabel fLabel = new JLabel("Die Frage lautet: ");
		JLabel aLabel = new JLabel("Die Antwort lautet: ");
		
		mainPanel.add(fLabel);
		mainPanel.add(fScroller);
		mainPanel.add(aLabel);
		mainPanel.add(aScroller);
		mainPanel.add(naechsteKarteButton);
		naechsteKarteButton.addActionListener(new NaechsteKarteListener());
		JMenuBar menueLeiste = new JMenuBar();
		JMenu menueDatei = new JMenu("Datei");
		JMenuItem menuePunktNeu = new JMenuItem("Neu");
		JMenuItem menuePunktSpeichern = new JMenuItem("Speichern");
		menuePunktNeu.addActionListener(new MenueNeuListener());
		
		menuePunktSpeichern.addActionListener(new MenueSpeichernListener());
		menueDatei.add(menuePunktNeu);
		menueDatei.add(menuePunktSpeichern);
		menueLeiste.add(menueDatei);
		frame.setJMenuBar(menueLeiste);
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		frame.setSize(500,600);
		frame.setVisible(true);		
	}
	
	public class NaechsteKarteListener implements ActionListener {
		public void actionPerformed (ActionEvent ev) {
			QuizKarte karte = new QuizKarte(frage.getText(), antwort.getText());
			kartenListe.add(karte);
			karteAbraeumen();
		}
	}
	
	public class MenueSpeichernListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			QuizKarte karte = new QuizKarte(frage.getText(), antwort.getText());
			kartenListe.add(karte);
			JFileChooser dateiWahl = new JFileChooser();
			dateiWahl.showSaveDialog(frame);
			dateiSpeichern(dateiWahl.getSelectedFile());
		}
	}
	
	public class MenueNeuListener implements ActionListener {
		public void actionPerformed (ActionEvent ev) {
			kartenListe.clear();
			karteAbraeumen();
		}
	}
	
	private void karteAbraeumen() {
		frage.setText("");
		antwort.setText("");
		frage.requestFocus();
	}
	
	private void dateiSpeichern(File datei) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(datei));
			for (QuizKarte karte:kartenListe) {
				writer.write(karte.getFrage() + "/");
				writer.write(karte.getAntwort() + "\n");				
			}
			writer.close();
		}
		catch (IOException ex) {
			System.out.println("konnte die Kartenliste nicht schreiben");
			ex.printStackTrace();
		}
	}
}
