package com.mak001.pokemon.world.objects;

import com.badlogic.gdx.math.Rectangle;
import com.mak001.pokemon.PokeGame;
import com.mak001.pokemon.world.World;

public abstract class ScriptedEvent {

	private final Rectangle bounds;
	private final World world;
	private boolean running = false;

	public ScriptedEvent(Rectangle bounds, World world) {
		this.bounds = scaleBounds(bounds);
		this.world = world;
	}

	private Rectangle scaleBounds(Rectangle bounds) {
		return new Rectangle(bounds.x / PokeGame.TILE_DIMENSION, bounds.y
				/ PokeGame.TILE_DIMENSION, bounds.width
				/ PokeGame.TILE_DIMENSION, bounds.height
				/ PokeGame.TILE_DIMENSION);
	}

	public abstract boolean shouldTrigger();

	public boolean isRunning() {
		return running;
	}

	public abstract void run();

	public Rectangle getBounds() {
		return bounds;
	}

	public World getWorld() {
		return world;
	}

	public void setRunning(boolean b) {
		running = b;
	}

}
