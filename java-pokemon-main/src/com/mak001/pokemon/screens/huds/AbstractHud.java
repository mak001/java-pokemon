package com.mak001.pokemon.screens.huds;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public abstract class AbstractHud implements Disposable {

	protected SpriteBatch batch;
	protected int cycles = -1;
	public int currentCycles = 0;

	public AbstractHud(SpriteBatch spriteBatch) {
		this(spriteBatch, -1);
	}

	/**
	 * 
	 * @param spriteBatch
	 * @param cycles
	 *            - number of times the render() method is called before
	 *            disposing (1000 is a little longer than a second)
	 */
	public AbstractHud(SpriteBatch spriteBatch, int cycles) {
		this.batch = spriteBatch;
		this.cycles = cycles;
	}

	public int getNeededCycles() {
		return cycles;
	}

	public abstract void render(float delta);
}
