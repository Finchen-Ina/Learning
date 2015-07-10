package Ratespiel;

public class Spieler {
	//Instanzvariablen
	int zahl = 0;
	
	public void raten() {
		zahl = (int) (Math.random() * 10);
		System.out.println("Ich tippe auf die Zahl: " + zahl);
	}	
}
