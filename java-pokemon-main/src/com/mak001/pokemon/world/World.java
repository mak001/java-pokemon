package com.mak001.pokemon.world;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mak001.pokemon.GlobalVars;
import com.mak001.pokemon.PokeGame;
import com.mak001.pokemon.screens.GameScreen;
import com.mak001.pokemon.world.entity.Direction;
import com.mak001.pokemon.world.entity.Entity;
import com.mak001.pokemon.world.entity.NPC;
import com.mak001.pokemon.world.entity.Player;

public class World {

	public GameScreen screen;
	private Player player;
	private ArrayList<NPC> npcs;
	private ArrayList<ScriptedEvent> events;
	private TiledMap map;

	private TiledMapTileLayer collision;

	private Music music;
	private String map_name;
	private int mapHeight;
	private int mapWidth;

	private Sound doorOpen;

	public World(String name, GameScreen screen, TiledMap map, Music music,
			Vector2 playerPos) {
		this.screen = screen;
		this.map_name = name;
		this.map = map;
		mapHeight = map.getProperties().get("height", Integer.class);
		mapWidth = map.getProperties().get("width", Integer.class);

		collision = (TiledMapTileLayer) map.getLayers().get("collision");

		this.music = music;
		music.setLooping(true);
		music.setVolume(GlobalVars.music_sound_level);
		music.play();

		doorOpen = Gdx.audio.newSound(Gdx.files.internal(PokeGame.SOUND_EFFECTS
				+ "/world/dooropen.mp3"));

		player = new Player(Direction.UP, (int) playerPos.x, mapHeight
				- (int) playerPos.y, this);

		npcs = new ArrayList<NPC>();
		events = new ArrayList<ScriptedEvent>();
	}

	public Player getPlayer() {
		return player;
	}

	public void update() {
		music.setVolume(GlobalVars.music_sound_level);
		player.update();
		for (Entity npc : npcs) {
			npc.update();
		}
		for (ScriptedEvent se : events) {
			if (!se.isRunning() && se.shouldTrigger())
				se.run();
		}
	}

	public void dispose() {
		player.dispose();
		for (Entity npc : npcs) {
			npc.dispose();
		}
		map.dispose();
		music.stop();
		music.dispose();
		doorOpen.dispose();
	}

	public ArrayList<NPC> getNPCs() {
		return npcs;
	}

	public boolean addNPC(NPC npc) {
		return npcs.add(npc);
	}

	public boolean addEvent(ScriptedEvent se) {
		return events.add(se);
	}

	public TiledMap getMap() {
		return map;
	}

	public TiledMapTileLayer getCollision() {
		return collision;
	}

	public void handleDoor(int x, int y) {
		for (MapObject object : map.getLayers().get("doors").getObjects()) {
			if (object instanceof RectangleMapObject) {
				Rectangle bounds = ((RectangleMapObject) object).getRectangle();
				if ((int) bounds.getX() / 16 == x
						&& (int) bounds.getY() / 16 == y) {
					String world = object.getProperties().get("world",
							String.class);
					int new_x = x;
					int new_y = y;

					try {
						new_x = Integer.parseInt(object.getProperties().get(
								"new_x", String.class));
						new_y = Integer.parseInt(object.getProperties().get(
								"new_y", String.class));
					} catch (NumberFormatException e) {
					}
					doorOpen.play(GlobalVars.effects_sound_level);
					if (world.equals(map_name)) {
						player.setPosition(new_x, mapHeight - new_y);
					} else {
						World w = new WorldLoader(screen, world, new_x, new_y)
								.getWorld();
						screen.setWorld(w);
						PokeGame.handler.setWorld(w);
						// System.out.println("other worlds not handled yet");
						// TODO - test
					}
				}
			}
		}
	}

	public int getHeight() {
		return mapHeight;
	}

	public int getWidth() {
		return mapWidth;
	}
}
