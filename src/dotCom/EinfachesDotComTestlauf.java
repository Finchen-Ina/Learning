package dotCom;

public class EinfachesDotComTestlauf {

	public static void main ( String[] args) {
		int anzahlTipps = 0;
		SpielHelfer helfer = new SpielHelfer();
		DotCom dasDotCom = new DotCom();
		int zufallsZahl = (int) (Math.random() * 5);
		int [] orte = {zufallsZahl, zufallsZahl+1, zufallsZahl+2};
		//dasDotCom.setZellorte(orte);		
		boolean lebt = true;
		while (lebt == true) {
			String tipp = helfer.getBenutzereingabe("Geben Sie eine Zahl ein");
			String ergebnis = dasDotCom.pruefDich(tipp);
			anzahlTipps++;
			if (ergebnis.equals("Versenkt")) {
				lebt = false;
				System.out.println("Sie haben " + anzahlTipps + " Versuche benötigt");
			}
		}
				
	}
}
