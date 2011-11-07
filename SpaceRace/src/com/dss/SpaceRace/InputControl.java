package com.dss.SpaceRace;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputControl implements KeyListener {

	private static final double POWER = 5;
	
	private boolean useArrowKeys;
	private boolean upKeyDown = false;
	private boolean downKeyDown = false;
	private boolean leftKeyDown = false;
	private boolean rightKeyDown = false;

	public InputControl(boolean useArrowKeys) {
		this.useArrowKeys = useArrowKeys;
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(useArrowKeys && e.getKeyCode()==e.VK_DOWN) {
			downKeyDown = true;
		}
		if(useArrowKeys && e.getKeyCode()==e.VK_UP) {
			upKeyDown = true;
		}
		if(useArrowKeys && e.getKeyCode()==e.VK_RIGHT) {
			rightKeyDown = true;
		}
		if(useArrowKeys && e.getKeyCode()==e.VK_LEFT) {
			leftKeyDown = true;
		}
		if(!useArrowKeys && e.getKeyCode()==e.VK_S) {
			downKeyDown = true;
		}
		if(!useArrowKeys && e.getKeyCode()==e.VK_W) {
			upKeyDown = true;
		}
		if(!useArrowKeys && e.getKeyCode()==e.VK_D) {
			rightKeyDown = true;
		}
		if(!useArrowKeys && e.getKeyCode()==e.VK_A) {
			leftKeyDown = true;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if(useArrowKeys && e.getKeyCode()==e.VK_DOWN) {
			downKeyDown = false;
		}
		if(useArrowKeys && e.getKeyCode()==e.VK_UP) {
			upKeyDown = false;
		}
		if(useArrowKeys && e.getKeyCode()==e.VK_RIGHT) {
			rightKeyDown = false;
		}
		if(useArrowKeys && e.getKeyCode()==e.VK_LEFT) {
			leftKeyDown = false;
		}
		if(!useArrowKeys && e.getKeyCode()==e.VK_S) {
			downKeyDown = false;
		}
		if(!useArrowKeys && e.getKeyCode()==e.VK_W) {
			upKeyDown = false;
		}
		if(!useArrowKeys && e.getKeyCode()==e.VK_D) {
			rightKeyDown = false;
		}
		if(!useArrowKeys && e.getKeyCode()==e.VK_A) {
			leftKeyDown = false;
		}
	}
	
	public double xPower() {
		if (leftKeyDown) {
			return -POWER;
		}
		if (rightKeyDown) {
			return POWER;
		}
		return 0;
	}
	
	public double yPower() {
		if (upKeyDown) {
			return -POWER;
		}
		if (downKeyDown) {
			return POWER;
		}
		return 0;
	}

}
