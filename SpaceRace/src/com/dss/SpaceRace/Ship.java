package com.dss.SpaceRace;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Ship implements KeyListener {
	private static final double POWER = 5;
	private double x;
	private double y;
	private int w;
	private int h;
	private double dx; //per sec
	private double dy; //per sec
	
	
	public Ship() {
		
		x = 300;
		y = 225;
		w = 20;
		h = 20;
		dx = 0;
		dy = 0;
	}
	public int[] getXPoints() {
		int xint = (int)x;
		int[] xPoints = new int[4];
		xPoints[0] = xint+w/2;
		xPoints[1] = xint+w;
		xPoints[2] = xint;
		xPoints[3] = xint+w/2;
		
		return xPoints;
	}

	public int[] getYPoints() {
		int yint = (int)y;
		int[] yPoints = new int[4];
		yPoints[0] = yint;
		yPoints[1] = yint+h;
		yPoints[2] = yint+h;
		yPoints[3] = yint;
		return yPoints;
	}

	public int getNPoints() {
		
		return 4;
	}
	public void move(long elapsedTime) {
		x = x+(dx/1000*elapsedTime);
	    y = y+(dy/1000*elapsedTime);
	    if((x+w)>App.MAX_WIDTH) {
	    	x = x-App.MAX_WIDTH;
	    }
	    if (x<0) {
	    	x = App.MAX_WIDTH+x;
	    }
	    if ((y+h)>App.MAX_HEIGHT) {
	    	y = y-App.MAX_HEIGHT;
	    }
	    if (y<0) {
	    	y = App.MAX_HEIGHT+y;
	    }
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==e.VK_DOWN) {
			dy=dy+POWER;
		}
		if(e.getKeyCode()==e.VK_UP) {
			dy=dy-POWER;
		}
		if(e.getKeyCode()==e.VK_RIGHT) {
			dx=dx+POWER;
		}
		if(e.getKeyCode()==e.VK_LEFT) {
			dx=dx-POWER;
		}
		
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
