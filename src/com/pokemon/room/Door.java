package com.pokemon.room;

import java.awt.Image;

import com.pokemon.root.GeneralObject;

public class Door extends GeneralObject {

	private Room WARP_ROOM;
	private Coordinate WARP_COORD;

	public Door(Image i, Coordinate co, Room r, Coordinate coord) {
		this.collision = Collision.DOOR;
		this.coordinate = coord;
		this.WARP_COORD = coord;
		this.WARP_ROOM = r;
	}

	public Coordinate getCoordWarp() {
		return WARP_COORD;
	}

	public Room getRoomWarp() {
		return WARP_ROOM;
	}

}
