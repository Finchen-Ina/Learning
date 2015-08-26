package datumUndZeit;

import java.util.Calendar;
import java.util.Date;

public class datum {
	
	
	public static void main(String[] args) {
		String s = String.format("Du erbst %.2f Euro!", 476578.09876);
		System.out.println(s);
		
		
		String d = String.format("%tc", new Date());
		System.out.println(d);
		
		// stattische Methode der Klasse Calendar aufrufen
		Calendar cal = Calendar.getInstance();
		int jahr = 2016;
		cal.set(jahr,1,6);
		System.out.println("Ich hab im Jahr " + jahr + " Geburtstag am " + cal.getTime());
	}
	
}
