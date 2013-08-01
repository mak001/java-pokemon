package com.mak001.pokemon.screens.huds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mak001.pokemon.screens.GameScreen;

public class ControllerConnectedHud extends AbstractHud {

	private Controller controller;

	private NinePatch patch;
	private TextureAtlas atlas;
	private GameScreen screen;

	public ControllerConnectedHud(Controller controller, GameScreen screen, int cycles) {
		super(screen.getBatch(), cycles);
		this.controller = controller;
		this.screen = screen;
		
		atlas = new TextureAtlas(Gdx.files.internal("data/ui/frame.pack"));
		patch = atlas.createPatch("frame");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void dispose() {
		atlas.dispose();
	}

	@Override
	public void render(float delta) {
		float height = 30;

		float y = screen.renderer.camera.viewportHeight - height;

		patch.draw(batch, 0, y, screen.renderer.camera.viewportWidth, height);
	}
}
