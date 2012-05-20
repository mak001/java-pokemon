package com.pokemon.root;

import java.awt.Graphics;
import java.awt.Image;
import java.io.Serializable;

import com.pokemon.room.Coordinate;

public abstract class GeneralCharacter implements Serializable {
	private static final long serialVersionUID = 1L;

	protected Coordinate coordinate;
	protected Image image;

	/**
	 * @return The current Coordinate of the character in the current room
	 */
	public Coordinate getCoordinate() {
		return coordinate;
	}

	/**
	 * Moves where the character is located in the room
	 * 
	 * @param coordinate
	 *            - The coordinate to move the character to
	 */
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	/**
	 * Returns the image to draw the object
	 * 
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * 
	 * Draws the npc/character
	 * 
	 * @param g
	 *            - Graphics
	 */
	public abstract void draw(Graphics g);

}
