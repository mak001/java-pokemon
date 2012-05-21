package com.pokemon.room;

import java.awt.Rectangle;

import com.pokemon.root.GeneralCharacter;

public class Viewport {

	Rectangle rect;
	GeneralCharacter c;

	public Viewport(GeneralCharacter c) {
		this.c = c;
		rect = new Rectangle(c.getCoordinate().getOffsetX() - 100, c
				.getCoordinate().getOffsetY() - 200, 200, 400);
	}

	public void setView(GeneralCharacter c) {
		this.c = c;
		rect = new Rectangle(c.getCoordinate().getOffsetX() - 100, c
				.getCoordinate().getOffsetY() - 200, 200, 400);
	}

	public Rectangle getBounds() {
		return rect;
	}

}
