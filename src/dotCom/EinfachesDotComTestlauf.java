package dotCom;

public class EinfachesDotComTestlauf {

	public static void main ( String[] args) {
		EinfachesDotCom dotCom = new EinfachesDotCom();
		int [] orte = {2,3,4};
		dotCom.setZellorte(orte);		
		String tipp = "2";
		String ergebnis = dotCom.pruefDich(tipp);
		
		
	}
}
