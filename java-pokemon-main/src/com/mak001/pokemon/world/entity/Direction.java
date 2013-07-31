package com.mak001.pokemon.world.entity;

public enum Direction {
	LEFT("LEFT", 270), RIGHT("RIGHT", 90), UP("UP", 0), DOWN("DOWN", 180);

	private final int angle;
	private final String name;

	Direction(String name, int angle) {
		this.name = name;
		this.angle = angle;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return name;
	}

	public int getAngle() {
		return angle;
	}
}