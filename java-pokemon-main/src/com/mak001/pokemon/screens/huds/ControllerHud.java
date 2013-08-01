package com.mak001.pokemon.screens.huds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mak001.pokemon.screens.GameScreen;

public class ControllerHud extends AbstractHud {

	private Controller controller;

	private NinePatch patch;
	private TextureAtlas atlas;
	private GameScreen screen;
	private BitmapFont black;

	private String status = " disconnected";

	public ControllerHud(Controller controller, GameScreen screen, int cycles,
			boolean connected) {
		super(screen.getBatch(), cycles);
		this.controller = controller;
		this.screen = screen;

		atlas = new TextureAtlas(Gdx.files.internal("data/ui/frame.pack"));
		patch = atlas.createPatch("frame");

		black = new BitmapFont(Gdx.files.internal("data/fonts/black_font.fnt"),
				Gdx.files.internal("data/fonts/black_font_0.png"), false, true);
		black.setScale(1 / screen.getScale());

		if (connected)
			status = " connected";
		// TODO Auto-generated constructor stub
	}

	@Override
	public void dispose() {
		atlas.dispose();
		black.dispose();
	}

	@Override
	public void render(float delta) {
		float height = 30;

		float y = screen.renderer.camera.viewportHeight - height;

		patch.draw(batch, 0, y, screen.renderer.camera.viewportWidth, height);
		black.draw(batch, getName(controller) + status, 10, y + 20);
	}

	private String getName(Controller c) {
		if (c.getName().toLowerCase().contains("xbox")
				&& c.getName().contains("360")) {
			return "XBox 360 controller";
		}
		return c.getName();
	}
}
