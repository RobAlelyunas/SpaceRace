package com.dss.SpaceRace;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class Space extends JPanel {
	
	public static final int MAX_WIDTH = 800;
	public static final int MAX_HEIGHT = 600;
	public static final int NUMBER_OF_DIAMONDS = 1;
	
	private long lastMoveTime;
	private Ship shipOne;
	private Ship shipTwo;
	private List<Diamond> activeDiamonds;
	private boolean shipsWithinCollision = false;	
	
	public Space() {
		shipOne = new Ship(false);
		shipTwo = new Ship(true);
		setBackground(Color.BLACK);		
		addKeyListener(shipOne.getInputControl());
		addKeyListener(shipTwo.getInputControl());
		setUpNewGame();
	}

	public void setUpNewGame() {
		shipOne.setX(300);
		shipOne.setY(225);
		shipTwo.setX(250);
		shipTwo.setY(270);
		shipOne.setUpNewGame();
		shipTwo.setUpNewGame();
		activeDiamonds = generateDiamonds();
		lastMoveTime = System.currentTimeMillis();
	}

	public void moveAll() {
		long elapsedTime = System.currentTimeMillis()-lastMoveTime;
		shipOne.move(elapsedTime);	
		shipTwo.move(elapsedTime);	
		lastMoveTime = System.currentTimeMillis();
	}
	
	public void detectCollisions() {
		collideShips(shipOne,shipTwo);
		if (!shipOne.isInCollision()) {
			eatAnyDiamonds(shipOne);
		}
		if (!shipTwo.isInCollision()) {
			eatAnyDiamonds(shipTwo);
		}
	}

	private void collideShips(Ship shipOne, Ship shipTwo) {
		if (shipOne.isOverShip(shipTwo)) {
			if (!shipOne.isInCollision()) {
				shipOne.bounce();
				shipOne.setInCollision(true);
			}
			if (!shipTwo.isInCollision()) {
				shipTwo.bounce();
				shipTwo.setInCollision(true);
			}
		}
		else {
			shipOne.setInCollision(false);
			shipTwo.setInCollision(false);
		}
	}

	public boolean detectGameOver() {
		if (activeDiamonds.size() == 0) {
			return true;
		}
		return false;	
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g); //background color
		for (Diamond d : activeDiamonds) {
			d.paintComponent(g);
	    }
		shipOne.paintComponent(g);
		shipTwo.paintComponent(g);
		g.setColor(Color.WHITE);
		g.drawString(String.valueOf(shipOne.getScore()), 20, 10);
		g.drawString(String.valueOf(shipTwo.getScore()), Space.MAX_WIDTH-10,10);
		requestFocusInWindow();
	}
	
	private void eatAnyDiamonds(Ship ship) {
		List<Diamond> inactivated = new ArrayList<Diamond>();
		for (Diamond d : activeDiamonds) {
			if (ship.isOverDiamond(d)) {
				ship.eat(d);
				inactivated.add(d);
			}
		}
		activeDiamonds.removeAll(inactivated);
	}
	
	private List<Diamond> generateDiamonds() {
		List<Diamond> diamonds = new ArrayList<Diamond>();
		for (int i=0;i<Space.NUMBER_OF_DIAMONDS;i++) {
			double x = Math.random() * Space.MAX_WIDTH;
			double y = Math.random() * Space.MAX_HEIGHT;
			Diamond d = new Diamond(x,y);
			diamonds.add(d);
		}
		return diamonds;
	}



}
