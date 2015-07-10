package Kapitel2;

public class Hund {
	//Instanzvariablen
	int groesse;
	String rasse;
	String name;
	
	public void bellen() {
		if (groesse > 60) {
			System.out.println(name + " sagt Wuff! Wuff!");
		}
		else if (groesse > 14) {
			System.out.println(name + " sagt Wau! Wau!");
		}
		else {
			System.out.println(name + " sagt Jip! Jip!");
		}
	}
	
	public void essen() {
		
	}
	
	public void katzeJagen() {
		
	}	
	
}
