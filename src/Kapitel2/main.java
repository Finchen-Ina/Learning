package Kapitel2;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Neuer Hund
		//Hund h = new Hund();
		//h.groesse = 35;
		//h.bellen();
		
		// Hund Objekt erzeugen
		//Hund hund1 = new Hund();
		//hund1.bellen();
		//hund1.name = "Bert";
		
		//Hund Array erzeugen
		Hund[] meineHunde = new Hund[3];
		//Hund Array befüllen
		meineHunde[0] = new Hund();
		meineHunde[0].groesse = 70;
		meineHunde[0].name = "Finchen";
		meineHunde[0].bellen();
		
		meineHunde[1] = new Hund();
		meineHunde[0].groesse = 8;
		meineHunde[1].name = "Bella";
		meineHunde[1].bellen();
		
		meineHunde[2] = new Hund();
		meineHunde[0].groesse = 35;
		meineHunde[2].name = "Mira";
		meineHunde[2].bellen();
		
		//System.out.print("Der Name des letzten Hundes ist: ");
		//System.out.println(meineHunde[2].name);
		
		/*
		int x = 0;
		while (x < meineHunde.length) {
			meineHunde[x].bellen();
			x = x+1;
		}*/
		
		/*		
		Hund fido = new Hund(); // Neuer Hund
		fido.name = "Fido"; // Neuem Hund n Namen geben
		fido.bellen();
		fido.katzeJagen();
		fido.essen();
		
		//Neuer Film
		Film eins = new Film();
		eins.titel = "Von der Krise verweht";
		eins.genre = "Tragisch";
		eins.bewertung = -2;
		
		Film zwei = new Film();
		zwei.titel = "Vier Deadlines und ein Todesfall";
		zwei.genre = "Komödie";
		zwei.bewertung = 5;
		zwei.vorführen();
		
		Film drei = new Film();
		drei.titel = "Byte Club";
		drei.genre = "Tragisch, aber letzendlich aufbauernd";
		drei.bewertung = 127;
		*/
	}

}
