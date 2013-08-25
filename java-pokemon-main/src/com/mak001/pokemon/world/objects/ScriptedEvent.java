package com.mak001.pokemon.world.objects;

import com.badlogic.gdx.math.Rectangle;
import com.mak001.pokemon.PokeGame;

public abstract class ScriptedEvent {

	private final Rectangle bounds;

	public ScriptedEvent(Rectangle bounds) {
		this.bounds = scaleBounds(bounds);
	}

	private Rectangle scaleBounds(Rectangle bounds) {
		return new Rectangle(bounds.x / PokeGame.TILE_DIMENSION, bounds.y
				/ PokeGame.TILE_DIMENSION, bounds.width
				/ PokeGame.TILE_DIMENSION, bounds.height
				/ PokeGame.TILE_DIMENSION);
	}

	private boolean running = false;

	public abstract boolean shouldTrigger();

	public boolean isRunning() {
		return running;
	}

	public void run() {
		if (running == false)
			running = true;
	}

	public Rectangle getBounds() {
		return bounds;
	}

}
