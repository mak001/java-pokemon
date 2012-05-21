package com.pokemon.room;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map.Entry;

import com.pokemon.GameBase;
import com.pokemon.root.GeneralCharacter;
import com.pokemon.root.GeneralObject;
import com.pokemon.root.GeneralObject.Collision;

public class Room implements Serializable {
	private static final long serialVersionUID = 1L;

	private HashMap<Coordinate, GeneralObject> map = new HashMap<Coordinate, GeneralObject>();
	private HashMap<Coordinate, GeneralCharacter> npcs = new HashMap<Coordinate, GeneralCharacter>();

	/**
	 * 
	 * Creates a room
	 * 
	 * @param objects
	 *            - predefined walls, doors, cutables, pushables and surfables
	 * @param npcs
	 *            - The npcs that normally appear in the room
	 */
	public Room(HashMap<Coordinate, GeneralObject> objects,
			HashMap<Coordinate, GeneralCharacter> npcs) {
		map = objects;
		for (int i = 0; i < map.size() - 1; i++) {
			Coordinate c = (Coordinate) map.keySet().toArray()[i];
			GeneralObject o = (GeneralObject) map.values().toArray()[i];
			o.setCoordinate(c);
		}

		this.npcs = npcs;
		for (int i = 0; i < npcs.size() - 1; i++) {
			Coordinate c = (Coordinate) npcs.keySet().toArray()[i];
			GeneralCharacter o = (GeneralCharacter) npcs.values().toArray()[i];
			o.setCoordinate(c);
		}
	}

	/**
	 * Moves a GeneralObject to a specified coordinate
	 * 
	 * @param coord
	 *            - the coordinate to move to
	 * @param o
	 *            - The GeneralObject to move
	 */
	public void setTile(Coordinate coord, GeneralObject o) {
		map.put(coord, o);
	}

	/**
	 * Moves a GeneralCharacter to a specified coordinate
	 * 
	 * @param coord
	 *            - the coordinate to move to
	 * @param c
	 *            - The GeneralCharacter to move
	 */
	public void setTile(Coordinate coord, GeneralCharacter c) {
		npcs.put(coord, c);
	}

	public void moveObjectTo(Coordinate start, Coordinate end, GeneralObject o) {
		map.put(start, null);
		map.put(end, o);
	}

	public void moveCharacterTo(Coordinate start, Coordinate end,
			GeneralCharacter c) {
		npcs.put(start, null);
		npcs.put(end, c);
	}

	/**
	 * Checks the collision type of an object at a specific place in the room.
	 * </br> NPCs of any sort will always return a wall collision. Don't want to
	 * walk though them!
	 * 
	 * @param coord
	 *            - The Coordinate to check
	 * @return The collision type
	 */
	public Collision getCollision(Coordinate coord) {
		if (getCharacterAt(coord) != null)
			return GeneralObject.Collision.WALL;
		return getObjectAt(coord).getCollision();
	}

	/**
	 * Checks the collision type of an object at a specific place in the room.
	 * </br> NPCs of any sort will always return a wall collision. Don't want to
	 * walk though them!
	 * 
	 * @param x
	 *            - The x of the coordinate to check
	 * @param y
	 *            - The y of the coordinate to check
	 * 
	 * @return The collision type
	 */
	public Collision getCollision(int x, int y) {
		if (getCharacterAt(x, y) != null)
			return GeneralObject.Collision.WALL;
		return getObjectAt(x, y).getCollision();
	}

	/**
	 * Gets the GeneralObject at a given coordinate
	 * 
	 * @param c
	 *            - The coordinate to check
	 * @return The GeneralObject that was at the coordinate, null if there was
	 *         no object.
	 */
	public GeneralObject getObjectAt(Coordinate c) {
		for (Entry<Coordinate, GeneralObject> entry : map.entrySet()) {
			if (entry.getKey().isSame(c)) {
				return entry.getValue();
			}
		}
		return null;
	}

	/**
	 * Gets the GeneralCharacter at a given coordinate
	 * 
	 * @param c
	 *            - The coordinate to check
	 * @return The GeneralCharacter that was at the coordinate, null if there
	 *         was no character.
	 */
	public GeneralCharacter getCharacterAt(Coordinate c) {
		for (Entry<Coordinate, GeneralCharacter> entry : npcs.entrySet()) {
			if (entry.getKey().isSame(c)) {
				return entry.getValue();
			}
		}
		return null;
	}

	/**
	 * Gets the GeneralObject at a given coordinate
	 * 
	 * @param x
	 *            - The x of the coordinate to check
	 * @param y
	 *            - The y of the coordinate to check
	 * 
	 * @return The GeneralObject that was at the coordinate, null if there was
	 *         no object.
	 */
	public GeneralObject getObjectAt(int x, int y) {
		for (Entry<Coordinate, GeneralObject> entry : map.entrySet()) {
			if (entry.getKey().isSame(x, y)) {
				return entry.getValue();
			}
		}
		return null;
	}

	/**
	 * Gets the GeneralCharacter at a given coordinate
	 * 
	 * @param x
	 *            - The x of the coordinate to check
	 * @param y
	 *            - The y of the coordinate to check
	 * 
	 * @return The GeneralCharacter that was at the coordinate, null if there
	 *         was no character.
	 */
	public GeneralCharacter getCharacterAt(int x, int y) {
		for (Entry<Coordinate, GeneralCharacter> entry : npcs.entrySet()) {
			if (entry.getKey().isSame(x, y)) {
				return entry.getValue();
			}
		}
		return null;
	}

	public void draw(Graphics g) {
		// TODO - change to make it like character drawing? (mainly for pushable
		// and cuttable objects)
		for (Entry<Coordinate, GeneralObject> entry : map.entrySet()) {
			g.drawImage(entry.getValue().getImage(), entry.getKey()
					.getOffsetX(), entry.getKey().getOffsetY(), null);
		}

		for (Entry<Coordinate, GeneralCharacter> entry : npcs.entrySet()) {
			entry.getValue().draw(g);
		}

		GameBase.getPlayer().draw(g);
	}
}