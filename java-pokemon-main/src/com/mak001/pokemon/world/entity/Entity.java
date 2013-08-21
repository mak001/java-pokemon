package com.mak001.pokemon.world.entity;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.mak001.pokemon.PokeGame;
import com.mak001.pokemon.world.Locatable;
import com.mak001.pokemon.world.World;
import com.mak001.pokemon.world.objects.Collidable;

public abstract class Entity extends Locatable implements Disposable {

	protected Direction direction;
	private boolean moved = false;
	private boolean moved_ = false;
	protected String generic_name;

	protected boolean talking = false;

	protected Vector2 positionFuture;
	protected Vector2 positionPast;

	public TextureAtlas atlas;
	private HashMap<String, AtlasRegion> regions;

	public Entity(Vector2 position, String generic_name, World world) {
		this(Direction.UP, position, generic_name, world);
	}

	public Entity(Direction direction, Vector2 position, String generic_name,
			World world) {
		super(position, world);
		this.direction = direction;
		this.generic_name = generic_name;

		regions = new HashMap<String, AtlasRegion>();
		atlas = new TextureAtlas("data/textures/entities/" + generic_name
				+ ".pack");

		regions.put("down", atlas.findRegion(generic_name + ".down"));
		regions.put("up", atlas.findRegion(generic_name + ".up"));
		regions.put("left", atlas.findRegion(generic_name + ".left"));

		AtlasRegion right = new AtlasRegion(atlas.findRegion(generic_name
				+ ".left"));
		right.flip(true, false);
		regions.put("right", right);

		regions.put("down.walking.1",
				atlas.findRegion(generic_name + ".down.walking"));
		regions.put("up.walking.1",
				atlas.findRegion(generic_name + ".up.walking"));
		regions.put("left.walking.1",
				atlas.findRegion(generic_name + ".left.walking.1"));

		AtlasRegion rightW1 = new AtlasRegion(atlas.findRegion(generic_name
				+ ".left.walking.1"));
		rightW1.flip(true, false);
		regions.put("right.walking.1", rightW1);

		regions.put("left.walking.2",
				atlas.findRegion(generic_name + ".left.walking.2"));

		AtlasRegion upW = new AtlasRegion(atlas.findRegion(generic_name
				+ ".up.walking"));
		upW.flip(true, false);
		regions.put("up.walking.2", upW);

		AtlasRegion downW = new AtlasRegion(atlas.findRegion(generic_name
				+ ".down.walking"));
		downW.flip(true, false);
		regions.put("down.walking.2", downW);

		AtlasRegion rightW2 = new AtlasRegion(atlas.findRegion(generic_name
				+ ".left.walking.2"));
		rightW2.flip(true, false);
		regions.put("right.walking.2", rightW2);
	}

	@Override
	public void dispose() {
		regions.clear();
		atlas.dispose();
	}

	public abstract void update();

	private boolean didUpdate = false;

	protected void updateBounds() {
		if (isMoving()) {
			if (!didUpdate)
				switch (direction) {
				case DOWN:
					positionPast = new Vector2(position.x, getNearest(
							position.y, true));
					positionFuture = new Vector2(position.x, getNearest(
							position.y, false));
					didUpdate = true;
					break;
				case LEFT:
					positionPast = new Vector2(getNearest(position.x, true),
							position.y);
					positionFuture = new Vector2(getNearest(position.x, false),
							position.y);
					didUpdate = true;
					break;
				case RIGHT:
					positionPast = new Vector2(getNearest(position.x, false),
							position.y);
					positionFuture = new Vector2(getNearest(position.x, true),
							position.y);
					didUpdate = true;
					break;
				case UP:
					positionPast = new Vector2(position.x, getNearest(
							position.y, false));
					positionFuture = new Vector2(position.x, getNearest(
							position.y, true));
					didUpdate = true;
					break;
				}
		} else {
			positionPast = null;
			positionFuture = null;
			didUpdate = false;
		}
	}

	private int getNearest(float f, boolean up) {
		if (up) {
			return (int) Math.ceil(f);
		} else {
			return (int) Math.floor(f);
		}
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public boolean isMoving() {
		return (!onTileX() || !onTileY()) || (!onTileX() && !onTileY());
	}

	private boolean isMoving_() {
		return (!onTileX_() || !onTileY_()) || (!onTileX_() && !onTileY_());
	}

	private boolean inRange(float i) {
		return i > 2 && i < 15;
	}

	private boolean onTileX_() {
		return !inRange((int) (position.x * PokeGame.TILE_DIMENSION)
				% PokeGame.TILE_DIMENSION);
	}

	private boolean onTileY_() {
		return !inRange((int) (position.y * PokeGame.TILE_DIMENSION)
				% PokeGame.TILE_DIMENSION);

	}

	protected boolean onTileX() {
		return position.x % 1 == 0;
	}

	protected boolean onTileY() {
		return position.y % 1 == 0;
	}

	public AtlasRegion getTextureRegion() {
		if (!isMoving())
			if (!moved_)
				moved_ = true;

		if (isMoving_()) {
			if (moved_) {
				moved = !moved;
				moved_ = false;
			}
			switch (direction) {
			case DOWN:
				if (moved)
					return regions.get("down.walking.2");
				return regions.get("down.walking.1");
			case LEFT:
				if (moved)
					return regions.get("left.walking.1");
				return regions.get("left.walking.2");
			case RIGHT:
				if (moved)
					return regions.get("right.walking.1");
				return regions.get("right.walking.2");
			case UP:
				if (moved)
					return regions.get("up.walking.2");
				return regions.get("up.walking.1");
			}
		} else {
			switch (direction) {
			case DOWN:
				return regions.get("down");
			case LEFT:
				return regions.get("left");
			case RIGHT:
				return regions.get("right");
			case UP:
				return regions.get("up");
			}
		}
		return regions.get("up");
	}

	protected boolean isBlocked(int i, Direction d) {
		if (d.equals(Direction.DOWN))
			return i == Collidable.SOLID;
		return i == Collidable.SOLID || i == Collidable.CLIFF;
	}

	protected boolean isBlocked(float x, float f, Direction d) {
		return isBlocked(getCollision((int) x, (int) f), d);
	}

	protected int getCollision(int x, int y) {// TODO
		for (Collidable c : world.getCollision()) {
			if (c.getPolygon().contains(x, y)) {
				return c.getCollision();
			}
		}

		for (NPC npc : world.getNPCs()) {
			if (!npc.equals(this)) {
				if (willCollide(npc, x, y)) {
					return Collidable.SOLID;
				}
			}
		}
		if (!(this instanceof Player)) {
			if (willCollide(world.getPlayer(), x, y))
				return Collidable.SOLID;
		}
		return Collidable.NONE;
	}

	private boolean willCollide(Entity e, float x, float y) {
		if (e.positionPast != null && e.positionFuture != null) {
			return willCollide(e.positionPast, x, y)
					|| willCollide(e.positionFuture, x, y);
		}
		return willCollide(e.position, x, y);
	}

	private boolean willCollide(Vector2 v, float x, float y) {
		return willCollide(v.x, v.y, x, y);
	}

	private boolean willCollide(float x, float y, float x2, float y2) {
		return x == x2 && y == y2;
	}

	public void render(SpriteBatch batch) {
		batch.draw(getTextureRegion(), position.x, position.y, 0, 0,
				PokeGame.TILE_DIMENSION, PokeGame.CHAR_HEIGHT,
				1f / PokeGame.TILE_DIMENSION, 1f / PokeGame.TILE_DIMENSION, 0);
	}

	public abstract void setTalking(boolean talking);

	public boolean isTalking() {
		return talking;
	}
}
