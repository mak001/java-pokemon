package com.mak001.pokemon.screens.huds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mak001.pokemon.GlobalVars;
import com.mak001.pokemon.screens.GameScreen;

public class PauseHud extends AbstractHud {

	private GameScreen screen;
	private NinePatch patch;
	public String[] options;
	private TextureAtlas atlas;
	private BitmapFont black;
	private List list;
	private Skin skin;

	public PauseHud(GameScreen gameScreen) {
		super(gameScreen);

		screen = gameScreen;

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

		// skin = new Skin(Gdx.files.internal("data/ui/skin.json"));
		// list = new List(options, skin);
	}

	@Override
	public void render(float delat) {
		/*----Start rendering here----*/

		float width = 70;
		float height = (15 * options.length) + 10;

		float x = (screen.renderer.camera.viewportWidth) - width;
		float y = (screen.renderer.camera.viewportHeight) - height;

		patch.draw(batch, x, y, width, height);
		// list.setBounds(x, y, width, height);

		// list.draw(batch, 0);
	}

	@Override
	public void dispose() {
		atlas.dispose();
		black.dispose();
		// skin.dispose();
		// TODO Auto-generated method stub
	}

	public void moveSelectionUp() {
		// TODO Auto-generated method stub

	}

	public void moveSelectionDown() {
		// TODO Auto-generated method stub

	}

	public void selectSelection() {
		// TODO Auto-generated method stub

	}

	public void cancel() {
		// TODO Auto-generated method stub

	}

}
