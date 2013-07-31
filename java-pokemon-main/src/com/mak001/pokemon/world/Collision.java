package com.mak001.pokemon.world;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mak001.pokemon.PokeGame;

public enum Collision {
	NONE(-1, -1, 0), WALL(0, 0, 1), DOOR(16, 16, 2), CLIFF(16, 0, 3);
	// TODO - add more

	int type, x, y, width, height;

	Collision(int x, int y, int type) {
		this(x, y, PokeGame.TILE_DIMENSION, PokeGame.TILE_DIMENSION, type);
	}

	Collision(int x, int y, int width, int height, int type) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.type = type;
	}

	public boolean equals(TextureRegion region) {
		if (x == region.getRegionX() && y == region.getRegionY()
				&& width == region.getRegionWidth()
				&& height == region.getRegionHeight())
			return true;
		return false;
	}

	public int getType() {
		return type;
	}

}
