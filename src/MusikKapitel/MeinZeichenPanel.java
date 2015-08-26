package MusikKapitel;

import java.awt.*;

import javax.sound.midi.ControllerEventListener;
import javax.sound.midi.ShortMessage;
import javax.swing.*;

import java.awt.event.*;


public class MeinZeichenPanel extends JPanel implements ControllerEventListener {
	boolean msg = false;
	
	public void controlChange (ShortMessage event) {
		msg = true;
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		
		if (msg) {
			Graphics2D g2d = (Graphics2D) g;		
			
			int rot = (int) (Math.random() * 250);
			int gruen = (int) (Math.random() * 250);
			int blau = (int) (Math.random() * 250);
			g.setColor(new Color(rot,gruen,blau));
			
			int hoehe = (int) ((Math.random() * 120) + 10);
			int breite = (int) ((Math.random() * 120) + 10);
			int x = (int) ((Math.random() * 40) + 10);
			int y = (int) ((Math.random() * 40) + 10);
			g.fillRect(x, y, breite, hoehe);;
			msg = false;
			
		}
	}
}


       


       