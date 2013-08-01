package com.mak001.pokemon.world;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mak001.pokemon.world.entity.Direction;

public abstract class Locatable {

	protected float width;
	protected float height;

	protected Rectangle bounds;

	protected Vector2 position;

	protected World world;

	public Locatable(float x, float y, World world) {
		this(new Vector2(x, y), world);
	}

	public Locatable(Vector2 location, World world) {
		position = location;
		this.width = 1;
		this.height = 1;
		bounds = new Rectangle(position.x, position.y, width, height);
		this.world = world;
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

	public World getWorld() {
		return world;
	}

	public Direction getDirection(Locatable other) {
		return getDirection(other.getPosition());
	}

	public Direction getDirection(Vector2 pos) {
		if (position.x == pos.x) {
			if (position.y < pos.y) {
				return Direction.UP;
			} else {
				return Direction.DOWN;
			}
		} else if (position.y == pos.y) {
			if (position.x < pos.x) {
				return Direction.RIGHT;
			} else {
				return Direction.LEFT;
			}
		}
		return Direction.UP;
	}
}
