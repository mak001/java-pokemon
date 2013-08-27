package com.mak001.pokemon.world;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.mak001.pokemon.GlobalVars;
import com.mak001.pokemon.PokeGame;
import com.mak001.pokemon.screens.GameScreen;
import com.mak001.pokemon.world.entity.Entity;
import com.mak001.pokemon.world.objects.ScriptedEvent;

public class WorldRenderer implements Disposable {

	public World world;
	private SpriteBatch batch;
	public OrthographicCamera camera;
	public CameraCenter cameraCenter;

	private TiledMapTileLayer terrain;
	private TiledMapTileLayer below;
	private TiledMapTileLayer above;

	private static final int VIRTUAL_WIDTH = 240;
	private static final int VIRTUAL_HEIGHT = 160;
	private static final float ASPECT_RATIO = (float) VIRTUAL_WIDTH
			/ (float) VIRTUAL_HEIGHT;

	private GameScreen screen;

	private Rectangle viewport;
	private OrthogonalTiledMapRenderer renderer;

	public WorldRenderer(World world, GameScreen screen) {
		this.world = world;
		this.batch = screen.getBatch();
		this.screen = screen;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, VIRTUAL_WIDTH / PokeGame.TILE_DIMENSION,
				VIRTUAL_HEIGHT / PokeGame.TILE_DIMENSION);
		camera.update();

		renderer = new OrthogonalTiledMapRenderer(world.getMap(),
				(1f / PokeGame.TILE_DIMENSION), batch);

		cameraCenter = new CameraCenter(world);

		terrain = (TiledMapTileLayer) world.getMap().getLayers().get("terrain");
		below = (TiledMapTileLayer) world.getMap().getLayers().get("below");
		above = (TiledMapTileLayer) world.getMap().getLayers().get("above");
	}

	public void updateCamera() {
		Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
				(int) viewport.width, (int) viewport.height);

		camera.setToOrtho(false, VIRTUAL_WIDTH / PokeGame.TILE_DIMENSION,
				VIRTUAL_HEIGHT / PokeGame.TILE_DIMENSION);

		if (shouldUpdate()) {
			cameraCenter.update();
		}
		camera.position.x = cameraCenter.getX();
		camera.position.y = cameraCenter.getY();
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		renderer.setView(camera);
	}

	private boolean shouldUpdate() {
		for (ScriptedEvent se : world.getEvents()) {
			if (se.isRunning()) {
				se.run();
				return false;
			}
		}
		return true;
	}

	public void render() {
		if (GlobalVars.inBattle) {
			screen.battleRenderer.render();
		} else {
			if (!screen.battleRenderer.didReset()) {
				screen.battleRenderer.dispose();
			}
			updateCamera();

			renderer.renderTileLayer(terrain);
			renderer.renderTileLayer(below);

			ArrayList<ArrayList<Entity>> array = new ArrayList<ArrayList<Entity>>();
			for (int i = 0; i < world.getHeight(); i++) {
				array.add(new ArrayList<Entity>());

			}

			for (Entity npc : world.getEntities()) {
				array.get(world.getHeight() - (int) npc.getPosition().y).add(
						npc);
			}

			for (ArrayList<Entity> ae : array) {
				for (Entity e : ae) {
					e.render(batch);
				}
			}

			renderer.renderTileLayer(above);
		}
	}

	@Override
	public void dispose() {
		world.dispose();
		renderer.dispose();
	}

	public void resize(int width, int height) {
		float scale = 1f;
		float aspectRatio = (float) width / (float) height;
		Vector2 crop = new Vector2(0f, 0f);

		if (aspectRatio > ASPECT_RATIO) {
			scale = (float) height / (float) VIRTUAL_HEIGHT;
			crop.x = (width - VIRTUAL_WIDTH * scale) / 2f;
		} else if (aspectRatio < ASPECT_RATIO) {
			scale = (float) width / (float) VIRTUAL_WIDTH;
			crop.y = (height - VIRTUAL_HEIGHT * scale) / 2f;
		} else {
			scale = (float) width / (float) VIRTUAL_WIDTH;
		}
		float w = (float) VIRTUAL_WIDTH * scale;
		float h = (float) VIRTUAL_HEIGHT * scale;
		viewport = new Rectangle(crop.x, crop.y, w, h);
		screen.setScale(scale);
	}

	public void update() {
		world.update();
	}

}
