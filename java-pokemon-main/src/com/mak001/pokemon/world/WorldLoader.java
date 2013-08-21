package com.mak001.pokemon.world;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
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
import com.mak001.pokemon.screens.GameScreen;
import com.mak001.pokemon.world.entity.Direction;
import com.mak001.pokemon.world.entity.NPC;
import com.mak001.pokemon.world.objects.Door;

public class WorldLoader {

	private World world;

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
		loadObjects(worldName, map);
	}

	private void loadObjects(String worldName, TiledMap map) {
		MapObjects objs = map.getLayers().get("doors").getObjects();
		for (int i = 0; i < objs.getCount(); i++) {
			MapObject o = objs.get(i);
			String type = o.getProperties().get("type", String.class);

			if (type.equals("NPC")) {
				loadNPC(o, objs);
			} else if (type.equals("Door")) {
				loadDoor(o);
			} else if (type.equals("Event")) {

			}
		}
		// TODO Auto-generated method stub
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
			world.addNPC(new NPC(direction, path, world, null, gName, name));
		} else {
			world.addNPC(new NPC(direction, new Vector2(o.getProperties().get(
					"x", Integer.class), o.getProperties().get("y",
					Integer.class)), world, null, gName, name));

		}
		// TODO load interactions
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
			float x = ((PolylineMapObject) o).getPolyline().getX() / 16;
			float y = ((PolylineMapObject) o).getPolyline().getY() / 16;
			for (int i = 0; i < vert.length - 2; i += 2) {
				path.add(new Vector2((vert[i] / 16) + x, (vert[i + 1] / 16) + y));
			}
		} else if (o instanceof PolygonMapObject) {
			float[] vert = ((PolygonMapObject) o).getPolygon().getVertices();
			float x = ((PolygonMapObject) o).getPolygon().getX() / 16;
			float y = ((PolygonMapObject) o).getPolygon().getY() / 16;
			for (int i = 0; i < vert.length - 2; i += 2) {
				path.add(new Vector2((vert[i] / 16) + x, (vert[i + 1] / 16) + y));
			}
		} else if (o instanceof RectangleMapObject) {
			Rectangle rect = ((RectangleMapObject) o).getRectangle();
			float x = rect.x / 16;
			float y = rect.y / 16;
			float width = rect.width / 16;
			float height = rect.height / 16;
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
			int new_x = (int) r.x / 16;
			int new_y = (int) r.y / 16;

			try {
				new_x = Integer.parseInt(o.getProperties().get("new_x",
						String.class));
				new_y = Integer.parseInt(o.getProperties().get("new_y",
						String.class));
			} catch (NumberFormatException e) {
			}
			world.addDoor(new Door((int) r.x, (int) r.y, worldN, new_x, new_y));
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

	public World getWorld() {
		return world;
	}

}
