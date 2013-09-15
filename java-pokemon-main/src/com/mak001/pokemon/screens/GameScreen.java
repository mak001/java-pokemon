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

	public static final int PAUSE_HUD = 0, NPC_HUD = 1, CONTROLLER_HUD = 2,
			OTHER = 3;

	public WorldRenderer renderer;
	public World world;

	private boolean paused = false;

	private OrganizedMap<Integer, AbstractHud> huds;
	private PauseHud pauseHUD;
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
		pauseHUD = new PauseHud(this);
	}

	public void setWorld(World world) {
		if (renderer != null)
			this.renderer.dispose();
		this.renderer = new WorldRenderer(world, this);
		PokeGame.handler.setWorld(world);
		this.world = world;
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

		renderer.camera.setToOrtho(false, Gdx.graphics.getWidth() / getScale(),
				Gdx.graphics.getHeight() / getScale());
		renderer.camera.update();
		batch.setProjectionMatrix(renderer.camera.combined);

		for (AbstractHud hud : huds.values()) {
			if (hud != null) {
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
			if (hud != null)
				hud.dispose();
		}
		huds.clear();
		batch.dispose();
	}

	public void addHud(AbstractHud hud) {
		addHud(hud, OTHER);
	}

	public void addHud(AbstractHud hud, int key) {
		huds.put(key, hud);
	}

	public void removeHud(AbstractHud hud) {
		removeHud(huds.getByValue(hud));
		hud.dispose();
	}

	public void removeHud(int key) {
		if (huds.get(key) != null && key != PAUSE_HUD)
			huds.get(key).dispose();
		huds.put(key, null);
	}

	public void pause() {
		huds.put(PAUSE_HUD, pauseHUD);
		paused = true;
	}

	public void unpause() {
		paused = false;
	}

	public void setPaused(boolean pause) {
		paused = pause;
		if (paused) {
			huds.put(PAUSE_HUD, pauseHUD);
		} else {
			removeHud(PAUSE_HUD);
		}
	}

	public void setPaused() {
		setPaused(!paused);
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

	public OrganizedMap<Integer, AbstractHud> getHuds() {
		return huds;
	}
}
