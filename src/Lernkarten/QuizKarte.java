package Lernkarten;

public class QuizKarte {
	private String frage;
	private String antwort;
	

	public QuizKarte(String frage, String antwort) {
		// TODO Auto-generated constructor stub
		this.frage = frage;
		this.antwort = antwort;
	}

	
	public void setFrage(String f) {
		frage = f;
	}
	
	public String getFrage() {
		// TODO Auto-generated method stub
		return frage;
	}
	
	public void setAntwort(String a) {
		antwort = a;
	}

	public String getAntwort() {
		// TODO Auto-generated method stub
		return antwort;
	}

}
