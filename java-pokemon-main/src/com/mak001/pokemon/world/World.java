package com.mak001.pokemon.world;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
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
	private TiledMap map;
	private TiledMapTileLayer collision;
	private Music music;
	private String map_name;
	private int mapHeight;

	private Sound doorOpen;

	public World(GameScreen screen, Vector2 player_pos, String map_name) {
		this(screen, player_pos, new ArrayList<NPC>(), map_name);
	}

	public World(GameScreen screen, int player_x, int player_y, String map_name) {
		this(screen, player_x, player_y, new ArrayList<NPC>(), map_name);
	}

	public World(GameScreen screen, Vector2 player_pos, ArrayList<NPC> npcs,
			String map_name) {
		this(screen, (int) player_pos.x, (int) player_pos.y,
				new ArrayList<NPC>(), map_name);
	}

	public World(GameScreen screen, int player_x, int player_y,
			ArrayList<NPC> npcs, String map_name) {
		this.screen = screen;
		this.npcs = new ArrayList<NPC>() {
			private static final long serialVersionUID = 1L;
			{
				add(new NPC(Direction.UP, new ArrayList<Vector2>() {
					private static final long serialVersionUID = 1L;
					{
						add(new Vector2(10, 10));
						add(new Vector2(12, 10));
						add(new Vector2(12, 12));
						add(new Vector2(10, 12));
					}
				}, World.this, "player", "player"));
			}
		};
		this.map_name = map_name;

		doorOpen = Gdx.audio.newSound(Gdx.files.internal(PokeGame.SOUND_EFFECTS
				+ "/world/dooropen.mp3"));

		map = new TmxMapLoader().load("data/maps/" + map_name + "/map.tmx");
		mapHeight = map.getProperties().get("height", Integer.class);
		System.out.println(mapHeight);

		player = new Player(Direction.UP, player_x, mapHeight - player_y, this);

		collision = (TiledMapTileLayer) map.getLayers().get("collision");

		music = Gdx.audio.newMusic(Gdx.files.internal("data/maps/" + map_name
				+ "/music.mp3"));

		music.setLooping(true);
		music.setVolume(GlobalVars.music_sound_level);
		music.play();
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
					if (world.equals(map_name)) {
						doorOpen.play(GlobalVars.effects_sound_level);
						player.setPosition(new_x, mapHeight - new_y);
					} else {
						doorOpen.play(GlobalVars.effects_sound_level);
						World w = new World(screen, new_x, new_y, world);
						screen.setWorld(w);
						PokeGame.handler.setWorld(w);
						// System.out.println("other worlds not handled yet");
						// TODO - test
					}
				}
			}
		}
		// TODO Auto-generated method stub

	}
}
