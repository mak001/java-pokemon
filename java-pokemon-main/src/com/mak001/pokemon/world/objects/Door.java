package com.mak001.pokemon.world.objects;

public class Door {

	private final int posX, posY, newPosX, newPosY;
	private final String world;

	public Door(int posX, int posY, String worldName, int newPosX, int newPosY) {
		this.posX = posX;
		this.posY = posY;
		this.world = worldName;
		this.newPosX = newPosX;
		this.newPosY = newPosY;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public int getNewPosX() {
		return newPosX;
	}

	public int getNewPosY() {
		return newPosY;
	}

	public String getWorld() {
		return world;
	}

}
