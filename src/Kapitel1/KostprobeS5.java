package Kapitel1;

import Kapitel2.Hund;

public class KostprobeS5 {

	
	int groesse = 27;
	String name = "Fido";
	Hund meinHund = new Hund(name, groesse);
	x = groesse - 5; // x = 22 
	if (x < 15) meinHund.bellen(8); // Fido bellt nicht, wäre der Wert größer solle er 8x bellen
	
	while (x > 3) {
		meinHund.spielen(); // Fido will spielen, solange x größer als 3 ist
	}
	
	int[] zahlListe = {2,4,6,8}; //Zahlenliste als Array
	System.out.print("Hallo");
	System.out.print("Hund: " + name);
	String zahl = "8";
	int z = Integer.parseInt(zahl); // String 8 wird zum int 8 und in z geschrieben
	
	try {
		leseDatei("meineDatei.txt"); //Versuche Datei zu lesen
	}
	catch(FileNotFoundException ex) {
		System.out.print("Datei nicht gefunden."); //Wenn nicht geklappt, Fehlermeldung
	}
	
	
	
}
