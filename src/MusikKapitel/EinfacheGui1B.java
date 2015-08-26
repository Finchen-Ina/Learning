package MusikKapitel;

import java.awt.*;

import javax.swing.*;

public class EinfacheGui1B{
	/*
	JButton button;
	JFrame frame;
	JLabel label;
	
	public static void main(String[] args) {
		EinfacheGui1B gui = new EinfacheGui1B();
		gui.los();
	}
	
	public void los() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton labelButton = new JButton("Ändere Label");
		labelButton.addActionListener(new LabelListener());
		
		JButton colorButton = new JButton("Ändere Kreis");
		colorButton.addActionListener(new ColorListener());
		
		label = new JLabel("Ich bin ein Label");
		MeinZeichenPanel zeichenPanel = new MeinZeichenPanel();
		
		frame.getContentPane().add(BorderLayout.SOUTH, colorButton);
		frame.getContentPane().add(BorderLayout.CENTER, zeichenPanel);
		frame.getContentPane().add(BorderLayout.EAST, labelButton);
		frame.getContentPane().add(BorderLayout.WEST, label);
		
		frame.setSize(420,300);
		frame.setVisible(true);
	}
	
	class LabelListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			label.setText("Autsch!");
		}
	} // innere Klasse schließen
	
	class ColorListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			frame.repaint();
		}
	} // innere Klasse schließen
	
	public void actionPerformed(ActionEvent event) {
		frame.repaint();
	} */
	
	
	JFrame frame;
	JLabel label;
	
	int x = 70;
	int y = 70;
	
	public static void main (String[] args) {
		EinfacheGui1B gui = new EinfacheGui1B();
		gui.los();
	}
	
	public void los() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		
		MeinZeichenPanel zeichenPanel = new MeinZeichenPanel();
		
		frame.getContentPane().add(zeichenPanel);		
		frame.setSize(300,300);
		frame.setVisible(true);
		
		for (int i = 0; i < 130; i++) {
			x++;
			y++;
			zeichenPanel.repaint();
			try {
				Thread.sleep(50);			
			}
			catch (Exception e) {			
			}
		}
		
		
		
	}

	
	class MeinZeichenPanel extends JPanel {	    
	    public void paintComponent(Graphics g) {  
	       g.setColor(Color.green);
	       g.fillOval(x,y,40,40);
	    }

	}
}
