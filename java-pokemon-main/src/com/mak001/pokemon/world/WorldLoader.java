package com.mak001.pokemon.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.mak001.pokemon.screens.GameScreen;

public class WorldLoader {

	private final World world;

	public WorldLoader(GameScreen screen, String worldName) {
		this(screen, worldName, new Vector2(0, 0));
	}

	public WorldLoader(GameScreen screen, String worldName, int playerX,
			int playerY) {
		this(screen, worldName, new Vector2(playerX, playerY));
	}

	public WorldLoader(GameScreen screen, String worldName, Vector2 playerPos) {

		TiledMap map = getMap(worldName);

		world = new World(worldName, screen, map, getMusic(map.getProperties()
				.get("music", String.class), worldName), playerPos);
		// TODO - load npcs and events
		loadNPCs(worldName);
	}

	private void loadNPCs(String worldName) {
		// TODO Auto-generated method stub

	}

	private Music getMusic(String musicName, String worldName) {
		if (musicName != null) {
			String path = "";
			if (musicName.contains(".")) {
				path = "data/sound/music/" + musicName;
			} else {
				path = "data/sound/music/" + musicName + ".mp3";
			}
			return Gdx.audio.newMusic(Gdx.files.internal(path));
		} else {
			FileHandle dir = Gdx.files.internal("data/sound/music/");
			if (worldName.contains(".")) {
				worldName = worldName.replace(
						worldName.substring(worldName.lastIndexOf(".")), "");
			}
			for (int i = 0; i < dir.list().length; i++) {
				if (dir.list()[i].name().contains(worldName)) {
					return Gdx.audio.newMusic(Gdx.files
							.internal("data/sound/music/"
									+ dir.list()[i].name()));
				}
			}
			return Gdx.audio.newMusic(Gdx.files
					.internal("data/sound/music/pokecenter.mp3"));
		}
	}

	private TiledMap getMap(String worldName) {
		if (worldName.contains(".")) {
			return new TmxMapLoader().load("data/maps/" + worldName);
		} else {
			return new TmxMapLoader().load("data/maps/" + worldName + ".tmx");
		}
	}

	public World getWorld() {
		return world;
	}

}
