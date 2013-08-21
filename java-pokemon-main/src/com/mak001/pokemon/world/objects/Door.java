package com.mak001.pokemon.world.objects;

import com.badlogic.gdx.math.Rectangle;
import com.mak001.pokemon.PokeGame;

public class Door {

	private final int newPosX, newPosY;
	private final String world;
	private final Rectangle bounds;

	public Door(Rectangle bounds, String worldName, int newPosX, int newPosY) {
		this.bounds = scaleBounds(bounds);
		this.world = worldName;
		this.newPosX = newPosX;
		this.newPosY = newPosY;
	}

	private Rectangle scaleBounds(Rectangle bounds) {
		return new Rectangle(bounds.x / PokeGame.TILE_DIMENSION, bounds.y
				/ PokeGame.TILE_DIMENSION, bounds.width
				/ PokeGame.TILE_DIMENSION, bounds.height
				/ PokeGame.TILE_DIMENSION);
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public int getNewPosX() {
		return newPosX;
	}

	public int getNewPosY() {
		return newPosY;
	}

	public String getWorld() {
		return world;
	}

}
