package com.dss.SpaceRace;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

public class App {
	
	JFrame frame;
	
	public App() {
		frame = new JFrame();
        frame = new JFrame("Space Race");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,550);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.getContentPane().setLayout(null);
        frame.getContentPane().setPreferredSize(new Dimension(600,550));
	}

    public static void main(String[] args) {
        App app = new App();
        app.start();
    }

	private void start() {
		frame.pack();
		frame.setVisible(true);
		
	}
    
}
