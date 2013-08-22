package com.mak001.pokemon.world;

import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mak001.pokemon.PokeGame;
import com.mak001.pokemon.screens.GameScreen;
import com.mak001.pokemon.world.entity.Direction;
import com.mak001.pokemon.world.entity.NPC;
import com.mak001.pokemon.world.objects.Collidable;
import com.mak001.pokemon.world.objects.Door;

public class WorldLoader {

	private World world;
	private Class<?> c;
	private ClassLoader loader;
	private InteractionLoader interactionLoader;

	public WorldLoader(GameScreen screen, String worldName) {
		this(screen, worldName, new Vector2(0, 0));
	}

	public WorldLoader(GameScreen screen, String worldName, int playerX,
			int playerY) {
		this(screen, worldName, new Vector2(playerX, playerY));
	}

	public WorldLoader(GameScreen screen, String worldName, Vector2 playerPos) {
		interactionLoader = new InteractionLoader(worldName);
		TiledMap map = getMap(worldName);

		world = new World(worldName, screen, map, getMusic(map.getProperties()
				.get("music", String.class), worldName), playerPos);
		loadObjects(worldName, map);
	}

	private void loadObjects(String worldName, TiledMap map) {
		MapObjects objs = map.getLayers().get("objects").getObjects();
		for (int i = 0; i < objs.getCount(); i++) {
			MapObject o = objs.get(i);
			String type = o.getProperties().get("type", String.class);

			if (type.equals("NPC")) {
				loadNPC(o, objs);
			} else if (type.equals("Door")) {
				loadDoor(o);
			} else if (type.equals("Event")) {
				loadEvent(o);
			} else if (type.equals("Collision")) {
				loadColision(o);
			}
		}
	}

	private void loadColision(MapObject o) {
		if (o instanceof PolygonMapObject) {
			int flag = getPropInt(o, "flag");
			Sound sound;
			world.addCollidable(new Collidable(((PolygonMapObject) o)
					.getPolygon(), world, flag));
		}
		// TODO Auto-generated method stub
	}

	private void loadEvent(MapObject o) {
		if (o instanceof RectangleMapObject) {
			String clazzName = o.getProperties().get("class", String.class);
			try {

				loader = new URLClassLoader(new URL[] { Gdx.files.internal("")
						.file().toURI().toURL() });
				c = loader.loadClass("data.maps.events." + clazzName);

				Constructor<?>[] cs = c.getConstructors();
				Object invoke = cs[0].newInstance(((RectangleMapObject) o)
						.getRectangle());
				ScriptedEvent se = (ScriptedEvent) (invoke);
				world.addEvent(se);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void loadNPC(MapObject o, MapObjects objs) {
		MapObject _path = objs.get(o.getProperties().get("path", String.class));
		Direction direction = getDirection(o.getProperties().get("direction",
				String.class));
		String name = o.getProperties().get("real_name", String.class);
		String gName = o.getProperties().get("generic_name", String.class);

		if (_path != null) {
			ArrayList<Vector2> path;
			path = toPath(_path);
			world.addNPC(new NPC(direction, path, world, interactionLoader
					.loadInteractions(gName, name), gName, name));
		} else {
			world.addNPC(new NPC(direction, new Vector2(getPropInt(o, "x"),
					getPropInt(o, "y")), world, interactionLoader
					.loadInteractions(gName, name), gName, name));

		}
	}

	private Direction getDirection(String string) {
		if (string == null) {
			return Direction.UP;
		} else {
			if (string.equalsIgnoreCase("down")) {
				return Direction.DOWN;
			} else if (string.equalsIgnoreCase("left")) {
				return Direction.LEFT;
			} else if (string.equalsIgnoreCase("right")) {
				return Direction.RIGHT;
			} else {
				return Direction.UP;
			}
		}
	}

	private ArrayList<Vector2> toPath(MapObject o) {
		ArrayList<Vector2> path = new ArrayList<Vector2>();
		if (o instanceof PolylineMapObject) {
			float[] vert = ((PolylineMapObject) o).getPolyline().getVertices();
			float x = ((PolylineMapObject) o).getPolyline().getX()
					/ PokeGame.TILE_DIMENSION;
			float y = ((PolylineMapObject) o).getPolyline().getY()
					/ PokeGame.TILE_DIMENSION;
			for (int i = 0; i < vert.length - 2; i += 2) {
				path.add(new Vector2((vert[i] / PokeGame.TILE_DIMENSION) + x,
						(vert[i + 1] / PokeGame.TILE_DIMENSION) + y));
			}
		} else if (o instanceof PolygonMapObject) {
			float[] vert = ((PolygonMapObject) o).getPolygon().getVertices();
			float x = ((PolygonMapObject) o).getPolygon().getX()
					/ PokeGame.TILE_DIMENSION;
			float y = ((PolygonMapObject) o).getPolygon().getY()
					/ PokeGame.TILE_DIMENSION;
			for (int i = 0; i < vert.length - 2; i += 2) {
				path.add(new Vector2((vert[i] / PokeGame.TILE_DIMENSION) + x,
						(vert[i + 1] / PokeGame.TILE_DIMENSION) + y));
			}
		} else if (o instanceof RectangleMapObject) {
			Rectangle rect = ((RectangleMapObject) o).getRectangle();
			float x = rect.x / PokeGame.TILE_DIMENSION;
			float y = rect.y / PokeGame.TILE_DIMENSION;
			float width = rect.width / PokeGame.TILE_DIMENSION;
			float height = rect.height / PokeGame.TILE_DIMENSION;
			path.add(new Vector2(x, y));
			path.add(new Vector2(x + width, y));
			path.add(new Vector2(x + width, y + height));
			path.add(new Vector2(x, y + height));
		}
		return path;
	}

	private void loadDoor(MapObject o) {
		if (o instanceof RectangleMapObject) {
			Rectangle r = ((RectangleMapObject) o).getRectangle();
			String worldN = o.getProperties().get("world", String.class);

			int new_x = getPropInt(o, "new_x");
			int new_y = getPropInt(o, "new_y");
			if (new_x == -1)
				new_x = (int) r.x / PokeGame.TILE_DIMENSION;
			if (new_y == -1)
				new_y = (int) r.y / PokeGame.TILE_DIMENSION;
			world.addDoor(new Door(r, worldN, new_x, new_y));
		}
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

	private int getPropInt(MapObject o, String prop) {
		try {
			return Integer.parseInt(o.getProperties().get(prop, String.class));
		} catch (Exception e) {
			return -1;
		}
	}

	public World getWorld() {
		return world;
	}

}
