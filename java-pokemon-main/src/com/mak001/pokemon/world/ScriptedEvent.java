package com.mak001.pokemon.world;

public abstract class ScriptedEvent {

	private boolean running = false;

	public abstract boolean shouldTrigger();

	public boolean isRunning() {
		return running;
	}

	public void run() {
		if (running == false)
			running = true;
	}

}
