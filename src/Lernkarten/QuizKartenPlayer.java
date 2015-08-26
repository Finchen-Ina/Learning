package Lernkarten;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.*;

public class QuizKartenPlayer {
	private JTextArea anzeige;
	private ArrayList<QuizKarte> kartenListe;
	private QuizKarte aktuelleKarte;
	private int aktuelleKarteIndex;
	private JFrame frame;
	private JButton naechsteKarteButton;
	private boolean istAntwortAngezeigt;
	
	public static void main (String[] args) {
		QuizKartenPlayer reader = new QuizKartenPlayer();
		reader.los();
	}
	
	public void los() {
		frame = new JFrame("Quizkarten-Player");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel hauptPanel = new JPanel();
		Font bigFont = new Font("sanserif", Font.BOLD,24);
		
		anzeige = new JTextArea(10,20);
		anzeige.setFont(bigFont);
		anzeige.setLineWrap(true);
		anzeige.setEditable(false);
		
		JScrollPane fScroller = new JScrollPane(anzeige);
		fScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		fScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		naechsteKarteButton = new JButton("Frage anzeigen");
		hauptPanel.add(fScroller);
		hauptPanel.add(naechsteKarteButton);
		naechsteKarteButton.addActionListener(new NaechsteKarteListener());
		
		JMenuBar menueleiste = new JMenuBar();
		JMenu menueDatei = new JMenu("Datei");
		JMenuItem menuePunktLaden = new JMenuItem("Kartensatz laden");
		menuePunktLaden.addActionListener(new MenueOeffnenListener());
		menueDatei.add(menuePunktLaden);
		menueleiste.add(menueDatei);
		frame.setJMenuBar(menueleiste);
		frame.getContentPane().add(BorderLayout.CENTER, hauptPanel);
		frame.setSize(500,600);
		frame.setVisible(true);
	}
	
	private class NaechsteKarteListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			if (istAntwortAngezeigt) {
				anzeige.setText(aktuelleKarte.getAntwort());
				naechsteKarteButton.setText("Nächste Karte");
				istAntwortAngezeigt = false;
			}
			else {
				if (aktuelleKarteIndex < kartenListe.size()) {
					naechsteKarteZeigen();
				}
				else {
					anzeige.setText("Das war die letzte Karte.");
					naechsteKarteButton.setEnabled(false);
				}
			}			
			
		}
	}
	
	private class MenueOeffnenListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			JFileChooser dateiOeffnen = new JFileChooser();
			dateiOeffnen.showOpenDialog(frame);
			dateiLaden(dateiOeffnen.getSelectedFile());
			
		}
	}
	
	private void dateiLaden(File datei) {
		kartenListe = new ArrayList<QuizKarte>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(datei));
			String zeile = null;
			while ((zeile = reader.readLine()) != null) {
				karteErstellen(zeile);
			}
			reader.close();
		}
		catch (Exception ex) {
			System.out.println("konnte Kartendatei nicht lesen");
			ex.printStackTrace();
		}
		naechsteKarteZeigen();
		
	}
	
	private void karteErstellen(String zuParsendeZeile) {
		String[] ergebnis = zuParsendeZeile.split("/");
		QuizKarte karte = new QuizKarte(ergebnis[0], ergebnis[1]);
		kartenListe.add(karte);
		System.out.println("Karte erstellt");
	}
	
	private void naechsteKarteZeigen() {
		aktuelleKarte = kartenListe.get(aktuelleKarteIndex);
		aktuelleKarteIndex++;
		anzeige.setText(aktuelleKarte.getFrage());
		naechsteKarteButton.setText("Antwort zeigen");
		istAntwortAngezeigt = true;
		
	}
}
