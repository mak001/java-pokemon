package com.pokemon.room;

public class Coordinate {

	private int x;
	private int y;

	// Tile width and height
	private int offset = 16;

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

	public int getCharOffset() {
		return getOffsetY() - 9;
	}

	/**
	 * @param coordinate
	 *            - The coordinate to check against
	 * @return True if the coordinates have the same x and y
	 */
	public boolean isSame(Coordinate coordinate) {
		return isSame(coordinate.getX(), coordinate.getY());
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

	public boolean isNextTo(Coordinate coordinate) {
		return isNextTo(coordinate.getX(), coordinate.getY());
	}

	private boolean isNextTo(int x, int y) {
		if (getX() == x) {
			if (Math.abs(getX() - x) == 1)
				return true;
			return false;
		} else if (getY() == y) {
			if (Math.abs(getX() - x) == 1)
				return true;
			return false;
		}
		// TODO Auto-generated method stub
		return false;
	}

	public enum Direction {
		UP, DOWN, LEFT, RIGHT
	}

	/**
	 * 
	 * (0,0) is in the top left
	 * 
	 * @param c
	 *            - The coordinate to compare to
	 * @return - The direction of the movement (null if the coordinates are not
	 *         lined up in any way)
	 */
	public Direction directionTo(Coordinate c) {
		if (c.getY() == getY()) {
			if (c.getX() > getX()) {
				return Direction.RIGHT;
			} else {
				return Direction.LEFT;

			}
		} else if (c.getX() == getX()) {
			if (c.getY() > getY()) {
				return Direction.DOWN;
			} else {
				return Direction.UP;
			}
		}
		System.out.println("Coords do not line up: (" + getX() + ", " + getY()
				+ ") ,  (" + c.getX() + ", " + c.getY() + ")");
		return null;
	}

	@Override
	public String toString() {
		return "(" + getX() + ", " + getY() + ")";
	}

}
