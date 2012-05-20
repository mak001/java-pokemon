package com.pokemon.room.objects;

import java.awt.Image;

import com.pokemon.root.GeneralObject;

public class Wall extends GeneralObject {

	public Wall(Image image) {
		this.collision = Collision.WALL;
		this.image = image;
	}

}
