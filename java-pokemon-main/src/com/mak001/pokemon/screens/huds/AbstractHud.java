package com.mak001.pokemon.screens.huds;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.mak001.pokemon.screens.GameScreen;

public abstract class AbstractHud implements Disposable {

	protected SpriteBatch batch;
	protected GameScreen screen;

	public AbstractHud(GameScreen gameScreen) {
		this.batch = gameScreen.getBatch();
		this.screen = gameScreen;
	}

	public abstract void render();
}
