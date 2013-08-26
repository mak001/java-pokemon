package com.mak001.pokemon.world.entity.data;

public enum Speed {
	STILL(1), WALKING(1), RUNNING(2), SURFING(2);
	private final float speed;

	Speed(float speed) {
		this.speed = speed;
	}

	// Used for updates
	public float getSpeed() {
		return speed;
	}
}