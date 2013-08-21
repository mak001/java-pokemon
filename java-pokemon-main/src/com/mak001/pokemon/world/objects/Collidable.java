package com.mak001.pokemon.world.objects;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Polygon;
import com.mak001.pokemon.PokeGame;
import com.mak001.pokemon.world.Locatable;
import com.mak001.pokemon.world.World;

public class Collidable extends Locatable {

	public static final int NONE = -1;
	public static final int SOLID = 1;
	public static final int CLIFF = 2;

	private final Sound stepSound;
	private final int flag;
	private final Polygon polygon;

	public Collidable(Polygon p, World world, int flag) {
		this(p, world, flag, null);
	}

	public Collidable(Polygon po, World world, int flag, Sound stepSound) {
		super(po.getX() / PokeGame.TILE_DIMENSION, po.getY()
				/ PokeGame.TILE_DIMENSION, world);
		this.stepSound = stepSound;
		this.flag = flag;
		this.polygon = correctBounds(po);
	}

	private Polygon correctBounds(Polygon po) {
		float[] vert = new float[po.getVertices().length];
		float x = po.getX() / PokeGame.TILE_DIMENSION;
		float y = po.getY() / PokeGame.TILE_DIMENSION;
		for (int i = 0; i < vert.length; i += 2) {
			vert[i] = x + (po.getVertices()[i] / PokeGame.TILE_DIMENSION);
			vert[i + 1] = y
					+ (po.getVertices()[i + 1] / PokeGame.TILE_DIMENSION);
		}
		return new Polygon(vert);
	}

	public Sound getStepSound() {
		return stepSound;
	}

	public int getCollision() {
		return flag;
	}

	public Polygon getPolygon() {
		return polygon;
	}

}
