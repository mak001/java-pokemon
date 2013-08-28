package com.mak001.pokemon.world;

import com.badlogic.gdx.math.Vector2;
import com.mak001.pokemon.PokeGame;

public class CameraCenter {

	private Vector2 position = new Vector2();
	private World world;

	public CameraCenter(World world) {
		if (world.getPlayer() == null) {
			setY(world.getHeight() / 2);
			setX(world.getWidth() / 2);
		} else {
			setPosition(world.getPlayer().getPosition());
		}
		this.world = world;
	}

	public CameraCenter(Locatable locatable) {
		setPosition(locatable.getPosition());
	}

	public CameraCenter(float x, float y) {
		setY(y);
		setX(x);
	}

	public void update() {
		update(world.getPlayer());
	}

	public void update(Locatable locatable) {
		setY(locatable.getPosition().y);
		setX(locatable.getPosition().x);
	}

	public void update(float x, float y) {
		setY(y);
		setX(x);
	}

	/**
	 * must be called instead of update();
	 * 
	 * @param locatable
	 *            - The Locatable object to follow
	 */
	public void moveTo(Locatable locatable) {
		moveTo(locatable.getPosition());
	}

	/**
	 * must be called instead of update();
	 * 
	 * @param vector
	 *            - The Vector2 to follow
	 */
	public void moveTo(Vector2 vector) {
		moveTo(vector.x, vector.y);
	}

	/**
	 * must be called instead of update();
	 * 
	 * @param x
	 *            - x position
	 * @param y
	 *            - y position
	 */
	public void moveTo(float x, float y) {
		moveTo(x, y, 2);
	}

	public void moveTo(float x, float y, int times) {
		System.out.println("Moving to " + x + ", " + y + "  :  X" + times);
		for (int i = 0; i < times; i++) {
			float tempx = position.x;
			float tempy = position.y;
			if (position.x > x) {
				tempx -= (1f / PokeGame.TILE_DIMENSION);
			} else if (position.x < x) {
				tempx += (1f / PokeGame.TILE_DIMENSION);
			}

			if (position.y > y) {
				tempy -= (1f / PokeGame.TILE_DIMENSION);
			} else if (position.y < y) {
				tempy += (1f / PokeGame.TILE_DIMENSION);
			}
			update(tempx, tempy);
		}
	}

	public void setY(float y) {
		position.y = y;
	}

	public void setX(float x) {
		position.x = x;
	}

	public void setPosition(Vector2 vec) {
		setX(vec.x);
		setY(vec.y);
	}

	public boolean atPosition(Locatable locatable) {
		return atPosition(locatable.getPosition());
	}

	public boolean atPosition(Vector2 vec) {
		return atPosition(vec.x, vec.y);
	}

	public boolean atPosition(float x, float y) {
		return x == position.x && y == position.y;
	}

	public float getX() {
		return position.x;
	}

	public float getY() {
		return position.y;
	}

}
