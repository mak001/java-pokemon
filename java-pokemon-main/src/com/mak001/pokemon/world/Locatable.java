package com.mak001.pokemon.world;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Locatable {

	protected float width;
	protected float height;

	protected Rectangle bounds;

	protected Vector2 position;

	public Locatable(float x, float y) {
		this(new Vector2(x, y));
	}

	public Locatable(Vector2 location) {
		position = location;
		this.width = 1;
		this.height = 1;
		bounds = new Rectangle(position.x, position.y, width, height);
	}

	/**
	 * 
	 * @return The current position
	 */
	public Vector2 getPosition() {
		return position;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public Rectangle getBounds() {
		return bounds;
	}

}
