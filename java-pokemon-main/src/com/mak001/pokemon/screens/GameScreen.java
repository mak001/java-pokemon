package com.mak001.pokemon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mak001.pokemon.PokeGame;
import com.mak001.pokemon.world.World;
import com.mak001.pokemon.world.WorldRenderer;

public class GameScreen extends AbstractScreen {

	public WorldRenderer renderer;
	private boolean paused;
	private PauseScreen pauseScreen;
	private SpriteBatch batch;
	private int width;
	private int height;

	private float scale = 1f;

	public GameScreen(PokeGame game) {
		super(game);
		batch = new SpriteBatch();
		renderer = new WorldRenderer(
				new World(this, new Vector2(6, 7), "test"), this);
		pauseScreen = new PauseScreen(this);
	}

	public void setWorld(World world) {
		this.renderer.dispose();
		this.renderer = new WorldRenderer(world, this);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		batch.begin();

		renderer.render();
		if (!paused) {
			renderer.update();
		} else {
			pauseScreen.render();
		}
		// TODO - pause screen
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		this.width = width;
		this.height = height;
		renderer.resize(width, height);
	}

	@Override
	public void dispose() {
		super.dispose();
		renderer.dispose();
		pauseScreen.dispose();
		batch.dispose();
	}

	public void pause() {
		paused = true;
	}

	public void unpause() {
		paused = false;
	}

	public void setPaused() {
		paused = !paused;
	}

	public boolean isPaused() {
		return paused;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
