package com.mak001.pokemon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.mak001.pokemon.PokeGame;

public class MainMenu extends AbstractScreen {

	Stage stage;
	BitmapFont white;
	BitmapFont black;
	TextureAtlas atlas;
	Skin skin;
	TextButton button;
	Label heading;

	public MainMenu(PokeGame game) {
		super(game);
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		stage.act(delta);

		batch.begin();

		stage.draw();

		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		if (stage == null)
			stage = new Stage(width, height, true);
		stage.clear();
		Gdx.input.setInputProcessor(stage);
		TextButtonStyle style = new TextButtonStyle();
		style.up = skin.getDrawable("button.up");
		style.down = skin.getDrawable("button.down");
		style.font = white;

		if (button == null)
			button = new TextButton("Play", style);
		button.setWidth(400);
		button.setHeight(100);
		button.setX(Gdx.graphics.getWidth() / 2 - button.getWidth() / 2);
		button.setY(Gdx.graphics.getHeight() / 2 - button.getHeight() / 2);

		button.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				game.setScreen(new GameScreen(game));
			}
		});

		if (heading == null) {
			LabelStyle labelStyle = new LabelStyle(white, Color.WHITE);
			heading = new Label("test", labelStyle);
		}

		stage.addActor(button);
		stage.addActor(heading);
	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		atlas = new TextureAtlas("data/ui/button.pack");
		skin = new Skin();
		skin.addRegions(atlas);
		white = new BitmapFont(Gdx.files.internal("data/fonts/white_font.fnt"),
				false);
		black = new BitmapFont(Gdx.files.internal("data/fonts/black_font.fnt"),
				false);
	}

	@Override
	public void dispose() {
		skin.dispose();
		atlas.dispose();
		white.dispose();
		black.dispose();
		stage.dispose();
	}

}
