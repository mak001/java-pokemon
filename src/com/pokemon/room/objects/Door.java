package com.pokemon.room.objects;

import java.awt.Image;

import com.pokemon.room.Coordinate;
import com.pokemon.room.Room;
import com.pokemon.root.GeneralObject;

public class Door extends GeneralObject {

	private Room WARP_ROOM;
	private Coordinate WARP_COORD;

	public Door(Image image, Coordinate WARP_COORD, Room WARP_ROOM) {
		this.collision = Collision.DOOR;
		this.WARP_COORD = WARP_COORD;
		this.WARP_ROOM = WARP_ROOM;
		this.image = image;
	}

	public Coordinate getCoordWarp() {
		return WARP_COORD;
	}

	public Room getRoomWarp() {
		return WARP_ROOM;
	}

}
