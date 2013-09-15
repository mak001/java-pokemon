package com.mak001.pokemon.screens.menus;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.mak001.pokemon.screens.GameScreen;

public abstract class AbstractMenu implements Disposable{

	protected GameScreen screen;
	protected SpriteBatch batch;

	public AbstractMenu(GameScreen screen, SpriteBatch batch) {
		this.batch = batch;
		this.screen = screen;
	}

	public abstract void render();
}
