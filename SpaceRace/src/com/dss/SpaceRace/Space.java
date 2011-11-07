package com.dss.SpaceRace;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class Space extends JPanel {
	
	private long lastMoveTime;
	private Ship shipOne;
	private Ship shipTwo;
	private List<Diamond> activeDiamonds;
	
	public Space() {
		shipOne = new Ship(300,225,false);
		shipTwo = new Ship(250,270,true);
		activeDiamonds = generateDiamonds();
		addKeyListener(shipOne.getInputControl());
		addKeyListener(shipTwo.getInputControl());
		lastMoveTime = System.currentTimeMillis();
	}

	public void moveAll() {
		long elapsedTime = System.currentTimeMillis()-lastMoveTime;
		shipOne.move(elapsedTime);	
		shipTwo.move(elapsedTime);	
		lastMoveTime = System.currentTimeMillis();
	}
	
	public void detectCollisions() {
		eatAnyDiamonds(shipOne);
		eatAnyDiamonds(shipTwo);
	}

	public boolean detectGameOver() {
		if (activeDiamonds.size() == 0) {
			return true;
		}
		return false;	
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g); //background color
		shipOne.paintComponent(g);
		shipTwo.paintComponent(g);
		for (Diamond d : activeDiamonds) {
				d.paintComponent(g);
		}
		g.setColor(Color.WHITE);
		g.drawString(String.valueOf(shipOne.getScore()), 20, 10);
		g.drawString(String.valueOf(shipTwo.getScore()), App.MAX_WIDTH-10,10);
		requestFocusInWindow();
	}
	
	private void eatAnyDiamonds(Ship ship) {
		List<Diamond> inactivated = new ArrayList<Diamond>();
		for (Diamond d : activeDiamonds) {
			if (ship.getRect().contains(d.getRect())) {
				ship.eat(d);
				inactivated.add(d);
			}
		}
		activeDiamonds.removeAll(inactivated);
	}
	
	private List<Diamond> generateDiamonds() {
		List<Diamond> diamonds = new ArrayList<Diamond>();
		for (int i=0;i<App.NUMBER_OF_DIAMONDS;i++) {
			double x = Math.random() * App.MAX_WIDTH;
			double y = Math.random() * App.MAX_HEIGHT;
			Diamond d = new Diamond(x,y);
			diamonds.add(d);
		}
		return diamonds;
	}



}
