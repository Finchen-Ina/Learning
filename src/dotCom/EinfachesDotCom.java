package dotCom;

public class EinfachesDotCom {
	public String pruefDich (String stringTipp) {
		int [] zellorte;
		int anzahlTreffer;
		
		public void setZellorte(int[] orte) {
			zellorte = orte;
		}
		
		public String pruefDich(String stringTipp) {
			//String in int umwandeln
			int tipp = Integer.parseInt(stringTipp);
			//Variable für das Ergebnis erstellen, default Wert "vorbei"
			String ergebnis = "vorbei";
			//Für jede Zelle in Zellort wiederholen
			for (int zelle : zellorte) {
				//Tipp mit Array vergleichen
				if (tipp == zelle) {
					ergebnis = "Treffer";
					anzahlTreffer++;
					break;
				}
			}
			//prüfen ob versenkt wurde
			if (anzahlTreffer == zellorte.length) {
				ergebnis = "Versenkt";
			}
			//Benutzer das Ergebnis zeigen
			System.out.println(ergebnis);
			//Ergebnis geht an die aufrufende Methde zurück
			return ergebnis;
		}		
	}
}
