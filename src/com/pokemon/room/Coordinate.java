package com.pokemon.room;

public class Coordinate {

	private int x;
	private int y;
	private int offset = 32;

	/**
	 * Initializes the coordinate.
	 * 
	 * @param x
	 *            - The x of the coordinate
	 * @param y
	 *            - The y of the coordinate
	 */
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return The x of the coordinate
	 */
	public int getX() {
		return x;
	}

	/**
	 * This method is for rendering only!
	 * 
	 * @return The offset x value of the coordinate
	 */
	public int getOffsetX() {
		return x * getOffset();
	}

	/**
	 * @return The y of the coordinate
	 */
	public int getY() {
		return y;
	}

	/**
	 * This method is for rendering only!
	 * 
	 * @return The offset y value of the coordinate
	 */
	public int getOffsetY() {
		return y * getOffset();
	}

	/**
	 * Returns how far apart the Coordinates are apart (Used for rendering)
	 * 
	 * @return - the offset of the points
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * @param coordinate
	 *            - The coordinate to check against
	 * @return True if the coordinates have the same x and y
	 */
	public boolean isSame(Coordinate coordinate) {
		return coordinate.getX() == getX() && coordinate.getY() == getY();
	}

	/**
	 * @param x
	 *            - The coordinate's x to check against
	 * @param y
	 *            - The coordinate's y to check against
	 * @return True if the coordinates have the same x and y
	 */
	public boolean isSame(int x, int y) {
		return x == getX() && y == getY();
	}

	@Override
	public String toString() {
		return "(" + getX() + ", " + getY() + ")";
	}

}
