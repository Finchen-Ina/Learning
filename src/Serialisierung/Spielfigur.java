package Serialisierung;

import java.io.Serializable;

public class Spielfigur implements Serializable {
	int staerke;
	String typ;
	String[] waffen;
	
	public Spielfigur(int s, String t, String[] w) {
		staerke = s;
		typ = t;
		waffen = w;
	}
	
	public int getStaerke() {
		return staerke;
	}
	
	public String getTyp() {
		return typ;
	}
	
	public String getWaffen() {
		String waffenListe = "";
		for (int i = 0; i < waffen.length; i++) {
			waffenListe += waffen[i] + " ";
		}
		return waffenListe;
	}
}
