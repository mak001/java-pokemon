package com.pokemon.room.objects;

import java.awt.Image;

import com.pokemon.root.GeneralObject;

public class Tile extends GeneralObject {

	/**
	 * initializes a tile
	 * 
	 * 
	 * @param image
	 *            - The image that the tile should have
	 */
	public Tile(Image image) {
		this.collision = Collision.NO;
		this.image = image;
	}
}
