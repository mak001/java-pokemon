package com.mak001.pokemon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mak001.pokemon.PokeGame;
import com.mak001.pokemon.screens.huds.AbstractHud;
import com.mak001.pokemon.screens.huds.PauseHud;
import com.mak001.pokemon.utils.OrganizedMap;
import com.mak001.pokemon.world.World;
import com.mak001.pokemon.world.WorldLoader;
import com.mak001.pokemon.world.WorldRenderer;

public class GameScreen extends AbstractScreen {

	public WorldRenderer renderer;
	private boolean paused = false;
	public static final int PAUSE_HUD = 0;
	private OrganizedMap<Integer, AbstractHud> huds;
	private SpriteBatch batch;
	private int width;
	private int height;

	private float scale = 1f;
	public BattleRenderer battleRenderer;

	public GameScreen(PokeGame game) {
		super(game);
		batch = new SpriteBatch();
		setWorld(new WorldLoader(this, "test", 6, 7).getWorld());
		huds = new OrganizedMap<Integer, AbstractHud>();
		battleRenderer = new BattleRenderer();
	}

	public void setWorld(World world) {
		if (renderer != null)
			this.renderer.dispose();
		this.renderer = new WorldRenderer(world, this);
		PokeGame.handler.setWorld(world);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		AbstractHud[] toRemove = new AbstractHud[10];
		int currIndex = 0;

		batch.begin();

		renderer.render();
		if (!paused) {
			renderer.update();
		}

		if (huds.size() != 0) {
			renderer.camera.setToOrtho(false, Gdx.graphics.getWidth()
					/ getScale(), Gdx.graphics.getHeight() / getScale());
			renderer.camera.update();
			batch.setProjectionMatrix(renderer.camera.combined);

			for (AbstractHud hud : huds.values()) {
				hud.render(delta);
				if (hud.getNeededCycles() != -1) {
					hud.currentCycles++;
				}
				if (hud.currentCycles == hud.getNeededCycles()) {
					toRemove[currIndex] = hud;
					currIndex++;
				}
			}
		}
		batch.end();

		for (AbstractHud h : toRemove) {
			if (h != null)
				removeHud(h);
		}
	}

	@Override
	public void resize(int width, int height) {
		this.width = width;
		this.height = height;
		if (renderer != null)
			renderer.resize(width, height);
	}

	@Override
	public void dispose() {
		super.dispose();
		renderer.dispose();
		battleRenderer.dispose();
		for (AbstractHud hud : huds.values()) {
			hud.dispose();
		}
		huds.clear();
		batch.dispose();
	}

	public void addHud(AbstractHud hud) {
		if (huds.containsKey(PAUSE_HUD)) {
			huds.put(huds.keys().size(), hud);
		} else {
			// to avoid using space 0
			huds.put(huds.keys().size() + 1, hud);
		}
	}

	public void removeHud(AbstractHud hud) {
		hud.dispose();
		huds.removeByValue(hud);
	}

	public void pause() {
		paused = true;
	}

	public void unpause() {
		paused = false;
	}

	public void setPaused() {
		paused = !paused;
		if (paused) {
			huds.put(PAUSE_HUD, new PauseHud(this));
		} else {
			huds.remove(PAUSE_HUD);
		}
	}

	public boolean isPaused() {
		return paused;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
		System.out.println(scale);
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

	public OrganizedMap<Integer, AbstractHud> getHuds() {
		return huds;
	}
}
