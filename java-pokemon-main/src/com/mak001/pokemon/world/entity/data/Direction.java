package com.mak001.pokemon.world.entity.data;

public enum Direction {
	LEFT("LEFT"), RIGHT("RIGHT"), UP("UP"), DOWN("DOWN");

	private final String name;

	Direction(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return name;
	}
}