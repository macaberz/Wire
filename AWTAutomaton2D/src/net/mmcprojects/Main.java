package net.mmcprojects;

import javax.swing.JFrame;

public class Main {
	
	public static JFrame window;
	public static Automaton2D engine;

	public static void main(String[] args) {
		engine = new Automaton2D(1024,768,60);
		window = new JFrame("Automaton2D");
		window.setContentPane(engine);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
	}

}