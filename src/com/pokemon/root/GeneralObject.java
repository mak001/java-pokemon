package com.pokemon.root;

import java.awt.Image;
import java.io.Serializable;

import com.pokemon.room.Coordinate;

public abstract class GeneralObject implements Serializable {
	private static final long serialVersionUID = 1L;

	protected Collision collision;
	protected Coordinate coordinate;
	protected Image image;

	/**
	 * An enum of all the collision types <br>
	 * <br>
	 * NO - The object is simply a texture on the ground<br>
	 * SURFABLE_WATER - water that you are able to surf on, but not walk on <br>
	 * GRASS - tall grass where you can run into wild pokemon <br>
	 * WALL - a solid wall <br>
	 * DOOR - A warp to another room <br>
	 * CUTABLE - Used for a tree that can be cut down <br>
	 * PUSHABLE - Used for a rock/ boulder that can be pushed
	 */
	public enum Collision {
		NO, SURFABLE_WATER, GRASS, WALL, DOOR, CUTABLE, PUSHABLE
	}

	/**
	 * @return The collision property of the object
	 */
	public Collision getCollision() {
		return collision;
	}

	/**
	 * @return The current Coordinate of the object in the current room
	 */
	public Coordinate getCoordinate() {
		return coordinate;
	}

	/**
	 * Moves where the object is located in the room
	 * 
	 * @param coordinate
	 *            - The coordinate to move the object to
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

}
