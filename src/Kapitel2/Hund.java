package Kapitel2;

public class Hund {
	//Instanzvariablen
	private int groesse;
	String rasse;
	String name;
	
	public void bellen(int anzahlBeller) {
		while (anzahlBeller > 0 ) {
			if (groesse > 60) {
				System.out.println(name + " sagt Wuff! Wuff!");
			}
			else if (groesse > 14) {
				System.out.println(name + " sagt Wau! Wau!");
			}
			else {
				System.out.println(name + " sagt Jip! Jip!");
			}
			anzahlBeller = anzahlBeller -1;
		}		
	}
	
	public int getGroesse() {
		return groesse;
	}

	public void setGroesse(int groesse) {		
		if (groesse > 5) {
			this.groesse = groesse;
		}
	}

	public void essen() {
		
	}
	
	public void katzeJagen() {
		
	}	
	
}
