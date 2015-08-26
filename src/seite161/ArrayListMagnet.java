package seite161;

import java.util.*;

public class ArrayListMagnet {
	

	public static void main (String[] args) {
		ArrayList<String> a = new ArrayList<String>();
		
		a.add(0,"null");
		a.add(1,"eins");
		a.add(2,"zwei");	
		a.add(3, "drei");
		printAL(a);
		
		if (a.contains("drei")) {
			a.add("vier");
		}
		
		a.remove(2);		
		printAL(a);
		
		
		if (a.indexOf("vier") != 4) {
			a.add(4, "4.2");
		}
		printAL(a);
		
		if (a.contains("zwei")) {
			a.add("2.2");
		}	
		printAL(a);		
	}
	
	public static void printAL(ArrayList<String> al) {
		for (String element : al) {	
			System.out.print(element + " ");
		}
		System.out.println(" ");
		
	}
	
	
	
}
