package com.mak001.pokemon.world;

import com.badlogic.gdx.math.Rectangle;

public abstract class ScriptedEvent {

	private final Rectangle bounds;

	public ScriptedEvent(Rectangle bounds) {
		this.bounds = bounds;
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
