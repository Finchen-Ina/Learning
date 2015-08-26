package FramingK12;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Panel;

import javax.swing.*;

public class FrameGui {
	public static void main (String[] args) {
		Panel gui = new Panel();
		los();
	}
	public static void los() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton east = new JButton("Osten");
		JButton west = new JButton("Westen");
		JButton north = new JButton("Norden");
		JButton south = new JButton("Süden");
		JButton center = new JButton("Zentrum");
		Font bigFont = new Font("serif", Font.BOLD, 28);
		
		frame.getContentPane().add(BorderLayout.EAST, east);
		frame.getContentPane().add(BorderLayout.WEST, west);
		frame.getContentPane().add(BorderLayout.NORTH, north);
		frame.getContentPane().add(BorderLayout.SOUTH, south);
		frame.getContentPane().add(BorderLayout.CENTER, center);
		frame.setSize(300,300);
		frame.setVisible(true);
		
		JTextField field = new JTextField(20);
		JTextField field1 = new JTextField("Carina");
		System.out.println(field1.getText());
		field.setText("irgendwas");
		field.setText("");
		field.selectAll();
		field.requestFocus();
		
		JTextArea text = new JTextArea(10,20);
		
		
	}
}
