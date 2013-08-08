package com.mak001.pokemon.screens.huds;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.mak001.pokemon.screens.GameScreen;

public abstract class AbstractHud implements Disposable {

	protected SpriteBatch batch;
	protected GameScreen screen;
	protected int cycles = -1;
	public int currentCycles = 0;

	public AbstractHud(GameScreen gameScreen) {
		this(gameScreen, -1);
	}

	/**
	 * 
	 * @param spriteBatch
	 * @param cycles
	 *            - number of times the render() method is called before
	 *            disposing (1000 is a little longer than a second)
	 */
	public AbstractHud(GameScreen gameScreen, int cycles) {
		this.screen = gameScreen;
		this.batch = gameScreen.getBatch();
		this.cycles = cycles;
	}

	public int getNeededCycles() {
		return cycles;
	}

	public abstract void render(float delta);
}
