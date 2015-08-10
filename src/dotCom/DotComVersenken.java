package dotCom;

import java.util.*;

public class DotComVersenken {
	private SpielHelfer helfer = new SpielHelfer();
	private ArrayList<DotCom> dotComListe = new ArrayList<DotCom>();
	private int anzahlVersuche = 0;
	
	private void spielEinrichten() {
		DotCom eins = new DotCom();
		eins.setName("Haustiere.com");
		DotCom zwei = new DotCom();
		zwei.setName("Immonet.de");
		DotCom drei = new DotCom();
		drei.setName = new DotCom("FragMich.com");
		dotComListe.add(eins);
		dotComListe.add(zwei);
		dotComListe.add(drei);
		
		System.out.println("Ihre Aufgabe ist es, drei Dot-Coms zu versenken.");
		System.out.println("Haustiere.com, Immonet.de, FragMich.com");
		System.out.println("Versuchen Sie, sie mit so wenig Versuchen wie möglich zu erledigen!");
		
		for (DotCom aktuellesDotCom : dotComListe) {
			ArrayList<String> neuerOrt = helfer.platziereDotCom(3);
			aktuellesDotCom.setZellorte(neuerOrt);
		}
	}
	
	private void beginneSpiel() {
		while (!dotComListe.isEmpty()) {
			String rateversuch = helfer.getBenutzereingabe("Geben Sie einen Rateversuch ein");
			pruefeRateversuch(rateversuch);
		}
		beendeSpiel();
	}
	
	private void pruefeRateversuch(String rateversuch) {
		anzahlVersuche++;
		String ergebnis = "Vorbei";
		
		for (DotCom aktuellesDotCom : dotComListe) {
			ergebnis = aktuellesDotCom.pruefDich(rateversuch);
			if (ergebnis.equals("Treffer")) {
				break;
			}
			if (ergebnis.equals("Versenkt")) {
				dotComListe.remove(aktuellesDotCom);
				break;
			}
		}
		System.out.println(ergebnis);
	}
	
	private void beendeSpiel() {
		System.out.println("Alle Dot-Coms sind versenkt! Ihre Aktien sind wertlos.");
		if (anzahlVersuche <= 18) {
			System.out.println("Sie haben nur " + anzahlVersuche + "Versuche benötigt. Sie erhalten en KinoWelt-Preis!");
		} 
		else {
			System.out.println("Hat ja lang genug gedauert. " + anzahlVersuche + "Versuche");
			System.out.println("Warum sind Sie nicht ausgestiegen, bevor der Kurs in den Keller fiel?");
		}
		
		public static void main(String[] args) {
			DotComVersenken spiel = new DotComVersenken();
			spiel.spielEinrichten();
			spiel.beginneSpiel();
		}
		
		
	}
}



