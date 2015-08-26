package MusikKapitel;

import javax.sound.midi.*;
import javax.swing.JFrame;

public class MiniMusikPlayer {
	static JFrame f = new JFrame("Mein erstes Musikvideo");
	static MeinZeichenPanel ml;

	public static void main(String[] args) {
		MiniMusikPlayer mini = new MiniMusikPlayer();
		mini.los();
	}
	
	public void guiErstellen() {
		ml = new MeinZeichenPanel();
		f.setContentPane(ml);
		f.setBounds(30,30,300,300);
		f.setVisible(true);
	}
	
	public void los() {
		guiErstellen();
		try {
			
			// einen Sequencer erzeugen (und öffnen),
			// eine Sequence und einen Track erzeugen
			
			Sequencer sequencer = MidiSystem.getSequencer();
			sequencer.open();
			sequencer.addControllerEventListener(ml, new int[] { 127 });
			Sequence seq = new Sequence(Sequence.PPQ, 4);
			Track track = seq.createTrack();
			
			// jetzt werden MidiEvents (die eine 
			// MidiMessage enthalten) erzeugt
			
			int r = 0;
			for (int i = 0; i < 60; i += 4) {
				r = (int) ((Math.random() * 50) + 1);
				track.add(eventErzeugen(144, 1, r, 100, i));
				track.add(eventErzeugen(176, 1, 127, 0, i));
				track.add(eventErzeugen(128, 1, r, 100, i + 2));
			} // Ende der for-Schleife
			
			// Hinzufügen der Events zum Track und der Sequence           
			// zum Sequencer, Setzen der Zeiten und Starten
			
			sequencer.setSequence(seq);
			sequencer.start();
			sequencer.setTempoInBPM(120);
			
			
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public MidiEvent eventErzeugen(int comd, int chan, int one, int two, int tick) {
		MidiEvent event = null;
		try {
			ShortMessage a = new ShortMessage();
			a.setMessage(comd, chan, one, two);
			event = new MidiEvent(a, tick);
			
		} catch (Exception e) { }
		return event;
	} // Methode eventErzeugen schließen
	
	public void controlChance(ShortMessage event) {
		System.out.println("la");
	}
	
	
}
