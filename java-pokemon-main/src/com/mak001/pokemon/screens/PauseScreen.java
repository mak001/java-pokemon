package com.mak001.pokemon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Disposable;
import com.mak001.pokemon.GlobalVars;

public class PauseScreen implements Disposable {

	private SpriteBatch batch;
	private GameScreen screen;
	private NinePatch patch;
	public String[] options;
	private TextureAtlas atlas;
	private BitmapFont black;
	private List list;
	private Skin skin;

	public PauseScreen(GameScreen gameScreen) {
		this.batch = gameScreen.getBatch();
		this.screen = gameScreen;

		if (!GlobalVars.hasPokedex) {
			options = new String[] { "Bag", GlobalVars.playerName, "Map",
					"Options", "Exit" };
		} else {
			options = new String[] { "Bag", GlobalVars.playerName, "Map",
					"Pokedex", "Options", "Exit" };
		}

		atlas = new TextureAtlas(Gdx.files.internal("data/ui/frame.pack"));
		patch = atlas.createPatch("frame");

		black = new BitmapFont(Gdx.files.internal("data/fonts/black_font.fnt"),
				false);

	//	skin = new Skin(Gdx.files.internal("data/ui/skin.json"));
	//	list = new List(options, skin);
	}

	public void render() {

		screen.renderer.camera.setToOrtho(false, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		screen.renderer.camera.update();
		batch.setProjectionMatrix(screen.renderer.camera.combined);

		/*----Start rendering here----*/

		float width = 70 * screen.getScale();
		float height = ((15 * options.length) + 10) * screen.getScale();
		// float height = ((15 * 6) + 10) * screen.getScale();
		float x = screen.getWidth() - width;
		float y = screen.getHeight() - height;

		patch.draw(batch, x, y, width, height);
	//	list.setBounds(x, y, width, height);

	//	list.draw(batch, 0);
	}

	@Override
	public void dispose() {
		atlas.dispose();
		black.dispose();
	//	skin.dispose();
		// TODO Auto-generated method stub
	}

}
