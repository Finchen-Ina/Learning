package Beatbox;

/*
 * Importbereich
 */
import java.awt.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.sound.midi.*;

import java.util.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;

public class Beatbox {	
	/*
	 * Instansvariablen
	 */
	JFrame derFrame;
	JPanel hauptPanel;
	@SuppressWarnings("rawtypes")
	JList eingangsListe;
	JTextField benutzerNachricht;
	ArrayList<JCheckBox> checkboxListe; 
	int nummerierung;
	Vector<String> listenVector = new Vector<String>();
	String benutzerName;
	ObjectOutputStream out;
	ObjectInputStream in;
	HashMap<String, boolean[]> andereSeqsMap = new HashMap<String, boolean[]>();
	Sequencer sequencer;
	Sequence sequence;
	Sequence meineSequence = null;
	Track track;
	
	String[] instrumentNamen = {"Bassdrum", "Hi-Hat, geschlossen", "Hi-Hat, offen",
	"Snaredrum", "Crashbecken", "Händeklatschen", "Hohes Tom-Tom", "Hohes Bongo", 
	"Maracas", "Trillerpfeife", "Tiefe Conga", "Kuhglocke", "Vibraslap", 
	"Tieferes Tom-Tom", "Hohes Agogo", "Hohe Conga, offen"};
	int[] instrumente = {35,42,46,38,49,39,50,60,70,72,64,56,58,47,67,63};
	
	
	public static void main (String[] args) {
		new Beatbox().inBetriebNehmen(args[0]); // args[0] ist der Benutzername
	}
	
	private void inBetriebNehmen(String name) {
		benutzerName = name;
		try {
			Socket sock = new Socket("127.0.0.1", 4242);
			out = new ObjectOutputStream(sock.getOutputStream());
			in = new ObjectInputStream(sock.getInputStream());
			Thread remote = new Thread(new RemoteReader());
			remote.start();
		}
		catch (Exception ex) {
			System.out.println("Keine Verbindung möglich - Sie müssen allein spielen.");
		}
		midiEinrichten();
		guiErstellen();
	}

	/*
	 * Gui erstellen
	 */
	public void guiErstellen() {
		derFrame = new JFrame("Carinas BeatBox");
		BorderLayout layout = new BorderLayout();
		JPanel hintergrund = new JPanel(layout);
		hintergrund.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		checkboxListe = new ArrayList<JCheckBox>();
		
		Box buttonBox = new Box(BoxLayout.Y_AXIS);
		JButton start = new JButton("Starten");
		start.addActionListener(new MeinStartListener());
		buttonBox.add(start);         
		
		JButton stopp = new JButton("Stoppen");
		stopp.addActionListener(new MeinStoppListener());
		buttonBox.add(stopp);
		
		JButton schneller = new JButton("Schneller");
		schneller.addActionListener(new MeinSchnellerListener());
		buttonBox.add(schneller);
		
		JButton langsamer = new JButton("Langsamer");
		langsamer.addActionListener(new MeinLangsamerListener());
		buttonBox.add(langsamer);
		
		JButton senden = new JButton("Senden");
		senden.addActionListener(new MeinSendenListener());
		buttonBox.add(senden);
		
		benutzerNachricht = new JTextField();
		buttonBox.add(benutzerNachricht);
		
		eingangsListe = new JList();
		eingangsListe.addListSelectionListener(new MeinListSelectionListener());
		eingangsListe.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane dieListe = new JScrollPane(eingangsListe);
		buttonBox.add(dieListe);
		eingangsListe.setListData(listenVector);
		
		Box namensBox = new Box(BoxLayout.Y_AXIS);
		for (int i = 0; i < 16; i++) {
			namensBox.add(new Label(instrumentNamen[i]));
		}
		
		hintergrund.add(BorderLayout.EAST, buttonBox);
		hintergrund.add(BorderLayout.WEST, namensBox);
		
		derFrame.getContentPane().add(hintergrund);		
		GridLayout raster = new GridLayout(16,16);
		raster.setVgap(1);
		raster.setHgap(2);
		hauptPanel = new JPanel(raster);
		hintergrund.add(BorderLayout.CENTER, hauptPanel);
		
		/*
		 * Schleife
		 * Kontrollkästchen zunächst alle auf 0 / leer 
		 * Kontrollkästchen dann der ArraList und dem GUi zufügen
		 */
		for (int i = 0; i < 256; i++) {                    
			JCheckBox c = new JCheckBox();
			c.setSelected(false);
			checkboxListe.add(c);
			hauptPanel.add(c);            
		} // Ende der Schleife
		
		derFrame.setBounds(50,50,300,300);
		derFrame.pack();
		derFrame.setVisible(true);
	} // Methode schließen
	
	
	public void midiEinrichten() {
		try {
			sequencer = MidiSystem.getSequencer();
			sequencer.open();
			sequence = new Sequence(Sequence.PPQ,4);
			track = sequence.createTrack();
			sequencer.setTempoInBPM(120);
			
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	} // Methode schließen
	
	/* 
	 * Track erstellen und starten
	 */
	public void trackErstellenUndStarten() {
		ArrayList<Integer> trackListe = null;				
		sequence.deleteTrack(track);
		track = sequence.createTrack();
		
		/*
		 * Für jede Musikinstrument-Reihe
		 */
		for (int i = 0; i < 16; i++) {
			trackListe = new ArrayList<Integer>();	
			for (int j = 0; j < 16; j++ ) {         
				JCheckBox jc = checkboxListe.get(j + (16*i));
				if ( jc.isSelected()) {					
					int taste = instrumente[i];   			
					trackListe.add(new Integer(taste));
				} else {
					trackListe.add(null);
				}                    
			} // Ende innere Schleife			
			tracksErzeugen(trackListe);			  
		} // Ende äußere Schleife
		
		track.add(eventErzeugen(192,9,1,0,16));     
		try {
			sequencer.setSequence(sequence); 
			// LOOP_CONTINUOUSLY: Dauerschleife
			sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);                   
			sequencer.start();
			sequencer.setTempoInBPM(120);
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	} // Methode trackErstellenUndStarten schließen
	
	
	/*
	 * Starten
	 */
	public class MeinStartListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			trackErstellenUndStarten();
		}
	} // innere Klasse schließen
	
	/*
	 * Stopen
	 */
	public class MeinStoppListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			sequencer.stop();
		}
	} // innere Klasse schließen
	
	/*
	 * Schneller
	 */
	public class MeinSchnellerListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			float tempoFactor = sequencer.getTempoFactor(); 
			sequencer.setTempoFactor((float)(tempoFactor * 1.03));
		}
	} // innere Klasse schließen
	
	/*
	 * Langsamer
	 */
	public class MeinLangsamerListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			float tempoFactor = sequencer.getTempoFactor();
			sequencer.setTempoFactor((float)(tempoFactor * .97));
		}
	} // innere Klasse schließen
	
	public class MeinSendenListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			boolean[] checkboxZustand = new boolean[256];
			for (int i = 0; i < 256; i++) {
				JCheckBox check = (JCheckBox) checkboxListe.get(i);
				if (check.isSelected()) {
					checkboxZustand[i] = true;
				}
			}
			try {
				out.writeObject(benutzerName + nummerierung++ + ": " + benutzerNachricht.getText());
				out.writeObject(checkboxZustand);
			}
			catch (Exception ex) {
				ex.printStackTrace();
				System.out.println("Tut mir Leid, Senden an Server nicht möglich.");
			}
			benutzerNachricht.setText("");
		}
	}
	
	public class MeinListSelectionListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent le) {
			if (!le.getValueIsAdjusting()) {
				String wahl = (String) eingangsListe.getSelectedValue();
				if (wahl != null) {
					boolean[] wahlZustand = (boolean[]) andereSeqsMap.get(wahl);
					sequenceWechseln(wahlZustand);
					sequencer.stop();
					trackErstellenUndStarten();
				}
			}
		}
	}
	
	public class RemoteReader implements Runnable {
		boolean[] checkboxZustand = null;
		String anzuzeigenderName = null;
		Object obj = null;
		public void run() {
			try {
				while ((obj=in.readObject()) != null) {
					System.out.println("Object vom Server erhalten");
					System.out.println(obj.getClass());
					String anzuzeigenderName = (String) obj;
					checkboxZustand = (boolean[]) in.readObject();
					andereSeqsMap.put(anzuzeigenderName,  checkboxZustand);
					listenVector.add(anzuzeigenderName);
					eingangsListe.setListData(listenVector);
				}
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public void sequenceWechseln(boolean[] checkboxZustand) {
		for (int i = 0; i < 256; i++) {
			JCheckBox check = (JCheckBox) checkboxListe.get(i);
			if (checkboxZustand[i]) {
				check.setSelected(true);
			}
			else {
				check.setSelected(false);;
			}
		}
	}
	
	/*
	 * Tracks erzeugen
	 */
	public void tracksErzeugen(ArrayList liste) {     
		Iterator it = liste.iterator();
		for (int i = 0; i < 16; i++) {
			Integer num = (Integer) it.next();
			if (num != null) {
				int numTaste = num.intValue();
				track.add(eventErzeugen(144,9,numTaste, 100, i));
				track.add(eventErzeugen(128,9,numTaste, 100, i+1));
			}
		}
	}
	
	public  MidiEvent eventErzeugen(int comd, int chan, int one, int two, int tick) {
		MidiEvent event = null;
		try {
			ShortMessage a = new ShortMessage();
			a.setMessage(comd, chan, one, two);
			event = new MidiEvent(a, tick);
			
		} catch(Exception e) {e.printStackTrace(); }
		return event;
	}
	
	/*
	public class MeinEinlesenListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			boolean[] checkboxZustand = null;
			try {
				FileInputStream fileIn = new FileInputStream(new File("Checkbox.ser"));
				ObjectInputStream is = new ObjectInputStream(fileIn);
				checkboxZustand = (boolean[]) is.readObject();
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
			for (int i = 0; i < 256; i++) {
				JCheckBox check = (JCheckBox) checkboxListe.get(i);
				if (checkboxZustand[i]) {
					check.setSelected(true);
				}
				else {
					check.setSelected(false);
				}
			}
			sequencer.stop();
			trackErstellenUndStarten();
		}
	}
	*/	
	
} // Klasse schließen

        
             
          
          
          
