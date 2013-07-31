package com.mak001.pokemon.world.entity;

public enum Speed {
	STILL(0), WALKING(1), RUNNING(2);
	private final float speed;

	Speed(float speed) {
		this.speed = speed;
	}

	public float getSpeed() {
		return speed;
	}
}