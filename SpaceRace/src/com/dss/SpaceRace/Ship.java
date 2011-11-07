package com.dss.SpaceRace;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Ship {
	private double x;
	private double y;
	private int w;
	private int h;
	private double dx; //per sec
	private double dy; //per sec
    private Image shipImageNoGlow;
    private Image shipImageGT;
    private Image shipImageGB;
    private Image shipImageGL;
    private Image shipImageGR;
    private Image shipImage;
    private List<Diamond> capturedDiamonds;
    private boolean useArrowKeys;
    private InputControl inputControl;
	
	
	public Ship(double x, double y, boolean useArrowKeys) {
		this.x = x;
		this.y = y;
		this.useArrowKeys = useArrowKeys;
		w = 30;
		h = 26;
		dx = 0;
		dy = 0;
		shipImageNoGlow = new ImageIcon(Ship.class.getResource("/images/ship.png")).getImage();
		shipImageGT = new ImageIcon(Ship.class.getResource("/images/shipGlowTop.png")).getImage();
		shipImageGB = new ImageIcon(Ship.class.getResource("/images/shipGlowBottom.png")).getImage();
		shipImageGL = new ImageIcon(Ship.class.getResource("/images/shipGlowLeft.png")).getImage();
		shipImageGR = new ImageIcon(Ship.class.getResource("/images/shipGlowRight.png")).getImage();
		shipImage = shipImageNoGlow;
		capturedDiamonds = new ArrayList<Diamond>();
		inputControl = new InputControl(useArrowKeys);
	}

	public void move(long elapsedTime) {
		double xPower = inputControl.xPower();
		double yPower = inputControl.yPower();
		dx = dx + xPower;
		dy = dy + yPower;
		x = x+(dx/1000*elapsedTime);
	    y = y+(dy/1000*elapsedTime);
	    if(x > App.MAX_WIDTH) {
	    	x = x-App.MAX_WIDTH;
	    }
	    if (x<0) {
	    	x = App.MAX_WIDTH+x;
	    }
	    if (y>App.MAX_HEIGHT) {
	    	y = y-App.MAX_HEIGHT;
	    }
	    if (y<0) {
	    	y = App.MAX_HEIGHT+y;
	    }
	    shipImage = poweredShipImage(xPower,yPower);
	}
	
	private Image poweredShipImage(double xPower, double yPower) {
		if(yPower > 0) {
			return shipImageGT;
		}
		else if(yPower < 0) {
			return shipImageGB;
		}
		else if(xPower > 0) {
			return shipImageGL;
		}
		else if(xPower < 0) {
			return shipImageGR;
		}
		else {
			return shipImageNoGlow;
		}
	}

	public void eat(Diamond d) {
		capturedDiamonds.add(d);
	}
		
	public void paintComponent(Graphics g) {
		g.drawImage(shipImage, (int)x, (int)y, w, h, null);
		boolean drawSecondShip = false;
		int x2 = (int)x;
		int y2 = (int)y;
		if ((x+w)>App.MAX_WIDTH) {
			drawSecondShip = true;
			x2 = x2 - App.MAX_WIDTH;
		}
		if ((y+h)>App.MAX_HEIGHT) {
			drawSecondShip = true;
			y2 = y2 - App.MAX_HEIGHT;
		}
		if (drawSecondShip) {
			g.drawImage(shipImage, x2, y2, w, h, null);
		}
	}

	public Rectangle getRect() {
		return new Rectangle((int)x,(int)y,w,h);
	}

	public int getScore() {
		return capturedDiamonds.size();
	}

	public KeyListener getInputControl() {
		return inputControl;
	}

	
}
