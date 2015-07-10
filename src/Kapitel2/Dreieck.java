package Kapitel2;

public class Dreieck {
	double flaeche;
	int hoehe;
	int breite;
	
	public static void main(String [] args) {		
		int x = 0;
		Dreieck[] dr = new Dreieck[4];
		while ( x < 4) {
			dr[x] = new Dreieck();
			dr[x].hoehe = (x + 1) * 2;
			dr[x].breite = x + 4;
			dr[x].setFlaeche();
			System.out.print("Dreieck " +x+", Fläche");
			System.out.println(" = " + dr[x].flaeche);
			x = x +1;
		}
		int y = x;
		x = 27;
		Dreieck d5 = dr[2];
		dr[2].flaeche = 343;
		System.out.print("y = "+y);
		System.out.println(", d5 Fläche = "+ d5.flaeche);		
	}
	void setFlaeche() {
		flaeche = (hoehe * breite) /2;
	}
}
