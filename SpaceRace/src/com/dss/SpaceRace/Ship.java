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
	private double x2 = 0; // x of reflected ship if it is crossing edge
	private double y2 = 0; // y of reflected ship if it is crossing edge
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
    private boolean inCollision;
	
	
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
		if (!isInCollision()) {
			double xPower = inputControl.xPower();
			double yPower = inputControl.yPower();
			dx = dx + xPower;
			dy = dy + yPower;
			shipImage = poweredShipImage(xPower,yPower);
		}
		x = x+(dx/1000*elapsedTime);
	    y = y+(dy/1000*elapsedTime);
	    if(x > Space.MAX_WIDTH) {
	    	x = x-Space.MAX_WIDTH;
	    }
	    if (x<0) {
	    	x = Space.MAX_WIDTH+x;
	    }
	    if (y>Space.MAX_HEIGHT) {
	    	y = y-Space.MAX_HEIGHT;
	    }
	    if (y<0) {
	    	y = Space.MAX_HEIGHT+y;
	    }
		if ((x+w)>Space.MAX_WIDTH) {
			x2 = x - Space.MAX_WIDTH;
		}
		else {
			x2 = 0;
		}
		if ((y+h)>Space.MAX_HEIGHT) {
			y2 = y - Space.MAX_HEIGHT;
		}
		else {
			y2 = 0;
		}
		if (x2 != 0 && y2 != y) y2 = y;
		if (y2 != 0 && x2 != x) x2 = x;
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
		if (x2 != 0 || y2 != 0) {
			g.drawImage(shipImage, (int)x2, (int)y2, w, h, null);
		}
	}

	public Rectangle getRect() {
		return new Rectangle((int)x,(int)y,w,h);
	}
	
	public Rectangle getRect2() {
		return new Rectangle((int)x2,(int)y2,w,h);
	}

	public int getScore() {
		return capturedDiamonds.size();
	}

	public KeyListener getInputControl() {
		return inputControl;
	}

	public boolean isOverDiamond(Diamond d) {
		boolean isOver = getRect().contains(d.getRect());
		if (!isOver && (x2 != 0 || y2 != 0)) {
			isOver = getRect2().contains(d.getRect());
		}
		return isOver;
	}

	public boolean isOverShip(Ship s) {
		boolean isOver = getRect().intersects(s.getRect());
		if (!isOver && (x2 != 0 || y2 != 0)) {
			isOver = getRect2().intersects(s.getRect());
		}
		return isOver;
	}

	public void bounce() {
		dx = -1.2 * dx;
		dy = -1.2 * dy;
	}

	public boolean isInCollision() {
		return inCollision;
	}

	public void setInCollision(boolean inCollision) {
		this.inCollision = inCollision;
	}

}
