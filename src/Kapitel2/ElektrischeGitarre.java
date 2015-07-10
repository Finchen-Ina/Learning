package Kapitel2;

public class ElektrischeGitarre {
	// Instanzvariablen
	String marke;
	int anzahlTonabnehmer;
	boolean verwendetVonRockstar;
	
	public String getMarke() {
		return marke;
	}
	public void setMarke(String marke) {
		this.marke = marke;
	}
	public int getAnzahlTonabnehmer() {
		return anzahlTonabnehmer;
	}
	public void setAnzahlTonabnehmer(int anzahlTonabnehmer) {
		this.anzahlTonabnehmer = anzahlTonabnehmer;
	}
	public boolean isVerwendetVonRockstar() {
		return verwendetVonRockstar;
	}
	public void setVerwendetVonRockstar(boolean verwendetVonRockstar) { // verwendetVonRockstar => jaOderNein
		this.verwendetVonRockstar = verwendetVonRockstar;
	}
	
	
}
