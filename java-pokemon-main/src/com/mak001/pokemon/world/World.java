package com.mak001.pokemon.world;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.mak001.pokemon.GlobalVars;
import com.mak001.pokemon.PokeGame;
import com.mak001.pokemon.screens.GameScreen;
import com.mak001.pokemon.world.entity.Entity;
import com.mak001.pokemon.world.entity.Player;
import com.mak001.pokemon.world.entity.data.Direction;
import com.mak001.pokemon.world.objects.Collidable;
import com.mak001.pokemon.world.objects.Door;
import com.mak001.pokemon.world.objects.ScriptedEvent;

public class World {

	public GameScreen screen;
	private Player player;
	private ArrayList<Entity> entities;
	private ArrayList<ScriptedEvent> events;
	private ArrayList<Door> doors;
	private ArrayList<Collidable> collisions;
	private TiledMap map;

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

		this.music = music;
		music.setLooping(true);
		music.setVolume(GlobalVars.music_sound_level);
		music.play();

		doorOpen = Gdx.audio.newSound(Gdx.files.internal(PokeGame.SOUND_EFFECTS
				+ "/world/dooropen.mp3"));

		player = new Player(Direction.UP, (int) playerPos.x, mapHeight
				- (int) playerPos.y, this);

		entities = new ArrayList<Entity>();
		entities.add(player);

		events = new ArrayList<ScriptedEvent>();
		doors = new ArrayList<Door>();
		collisions = new ArrayList<Collidable>();
	}

	public Player getPlayer() {
		return player;
	}

	public void update() {
		music.setVolume(GlobalVars.music_sound_level);
		// TODO - can change speeds by updating multiple times
		for (Entity npc : entities) {
			npc.update();
		}
		for (ScriptedEvent se : events) {
			if (!se.isRunning() && se.shouldTrigger())
				se.run();
		}
	}

	public void dispose() {
		player.dispose();
		for (Entity npc : entities) {
			npc.dispose();
		}
		map.dispose();
		music.stop();
		music.dispose();
		doorOpen.dispose();
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public boolean addEntity(Entity entity) {
		return entities.add(entity);
	}

	public boolean addEvent(ScriptedEvent se) {
		return events.add(se);
	}

	public boolean addDoor(Door door) {
		return doors.add(door);
	}

	public boolean addCollidable(Collidable collidable) {
		return collisions.add(collidable);
	}

	public ArrayList<Collidable> getCollision() {
		return collisions;
	}

	public TiledMap getMap() {
		return map;
	}

	public void handleDoor(int x, int y) {
		for (Door d : doors) {
			if (d.getBounds().x == x && d.getBounds().y == y) {
				doorOpen.play(GlobalVars.effects_sound_level);
				if (d.getWorld().equals(map_name)) {
					player.setPosition(d.getNewPosX(),
							mapHeight - d.getNewPosY());
					return;
				} else {
					World w = new WorldLoader(screen, d.getWorld(),
							d.getNewPosX(), d.getNewPosY()).getWorld();
					screen.setWorld(w);
					PokeGame.handler.setWorld(w);
					return;
				}
			}
		}
	}

	public void handleEvent(int x, int y) {
		for (ScriptedEvent e : events) {
			if (e.getBounds().contains(x, y)) {
				System.out.println("Event " + e.getClass().getSimpleName()
						+ " was triggered");
				// TODO
				return;
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