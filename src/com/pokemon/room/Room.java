package com.pokemon.room;

import java.util.HashMap;
import java.util.Map.Entry;

import com.pokemon.root.GeneralObject;
import com.pokemon.root.GeneralObject.Collision;

public class Room {

	HashMap<Coordinate, GeneralObject> map = new HashMap<Coordinate, GeneralObject>();

	/**
	 * Creates a square room
	 * 
	 * @param TILE_COUNT
	 *            - one side of the square
	 * @param objects
	 *            - predefined walls, doors, cutables, pushables and surfables
	 */
	public Room(int TILE_COUNT, HashMap<Coordinate, GeneralObject> objects) {
		this(TILE_COUNT, TILE_COUNT, objects);
	}

	/**
	 * 
	 * Creates a room with the given width and height
	 * 
	 * @param WIDTH_IN_TILES
	 *            - The width of the room in tiles
	 * @param HEIGHT_IN_TILES
	 *            - The height of the room in tiles
	 * @param objects
	 *            - predefined walls, doors, cutables, pushables and surfables
	 */
	public Room(int WIDTH_IN_TILES, int HEIGHT_IN_TILES,
			HashMap<Coordinate, GeneralObject> objects) {
		map = objects;
	}

	public void setTile(Coordinate coord, GeneralObject o) {
		map.put(coord, o);
	}

	public void moveObjectTo(Coordinate start, Coordinate end, GeneralObject o) {
		map.put(start, null);
		map.put(end, o);
	}

	public Collision getTileCollision(Coordinate coord) {
		return getObjectAt(coord).getCollision();
	}

	public Collision getTileCollision(int x, int y) {
		return getObjectAt(x, y).getCollision();
	}

	public GeneralObject getObjectAt(Coordinate c) {
		for (Entry<Coordinate, GeneralObject> entry : map.entrySet()) {
			if (entry.getKey().isSame(c)) {
				return entry.getValue();
			}
		}
		return null;
	}

	public GeneralObject getObjectAt(int x, int y) {
		for (Entry<Coordinate, GeneralObject> entry : map.entrySet()) {
			if (entry.getKey().isSame(x, y)) {
				return entry.getValue();
			}
		}
		return null;
	}

}
