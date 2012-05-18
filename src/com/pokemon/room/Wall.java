package com.pokemon.room;

import java.awt.Image;

import com.pokemon.root.GameObject;

public class Wall extends GameObject {

	public Wall(Image i, int x, int y) {
		setImage(i);
		setX(x);
		setY(y);
		this.setCollision(Collision.YES);
	}

}
