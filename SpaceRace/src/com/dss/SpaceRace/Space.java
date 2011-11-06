package com.dss.SpaceRace;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Space extends JPanel {
	
	private long lastPaintTime;
	private Ship spaceship;
	public Space() {
		spaceship = new Ship();
		addKeyListener(spaceship);
		
		lastPaintTime = System.currentTimeMillis();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g); //background color
		spaceship.move(System.currentTimeMillis()-lastPaintTime);
		spaceship.paintComponent(g);
		lastPaintTime = System.currentTimeMillis();
		requestFocusInWindow();
	}
}
