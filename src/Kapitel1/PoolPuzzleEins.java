package Kapitel1;

public class PoolPuzzleEins {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int x = 0;
		while ( x < 4 ) {
			System.out.print("da");
			if ( x < 1) { // also 0
				System.out.print(" "); //leer
			}
			System.out.print("s");
			if ( x > 1) {
				System.out.print(" kind"); // sagt
				x = x + 2;
			}
			if ( x == 1 ) {
				System.out.print("itzend"); //itzend
			}
			if ( x < 1 ) {
				System.out.print("agt"); //agt
			}
			System.out.println("");
			x = x + 1;
		}

	}

}
