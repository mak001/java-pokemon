package com.pokemon.room;

import java.awt.Image;

import com.pokemon.root.GeneralObject;

public class Wall extends GeneralObject {

	public Wall(Image image, Coordinate coordinate) {
		// TODO Auto-generated constructor stub
		this.coordinate = coordinate;
		this.collision = Collision.WALL;
	}

}
