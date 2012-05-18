package com.pokemon;

import java.awt.Image;

public class Base {

	public enum Collision {
		YES, NO
	}

	private Image i;
	private Collision c;
	
	protected int movable_X = 20;
	protected int movable_Y = 20;

	public Image getImage() {
		return i;
	}

	public void setImage(Image i) {
		this.i = i;
	}

	public int getX() {
		return movable_X;
	}

	public void setX(int x) {
		this.movable_X = x;
	}

	public int getY() {
		return movable_Y;
	}

	public void setY(int y) {
		this.movable_Y = y;
	}

	public void setCollision(Collision c) {
		this.c = c;
	}

	public Collision getCollision() {
		return c;
	}

}
