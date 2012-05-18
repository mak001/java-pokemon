package com.pokemon.room;

import java.awt.Image;

import com.pokemon.root.GeneralObject;

public class Tile extends GeneralObject {

	private Image texture;

	public Tile(Image i, Coordinate coord) {
		this.collision = Collision.NO;
		this.coordinate = coord;
		texture = i;
	}

	public Image getTexture() {
		return texture;
	}

}
