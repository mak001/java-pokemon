package com.mak001.pokemon.world;

import com.badlogic.gdx.math.Vector2;
import com.mak001.pokemon.world.entity.Direction;

public abstract class Locatable {

	protected Vector2 position;

	protected World world;

	public Locatable(float x, float y, World world) {
		this(new Vector2(x, y), world);
	}

	public Locatable(Vector2 location, World world) {
		this.world = world;
		position = location;
	}

	/**
	 * 
	 * @return The current position
	 */
	public Vector2 getPosition() {
		return position;
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
