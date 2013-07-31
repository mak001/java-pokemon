package com.mak001.pokemon.world.entity;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.mak001.pokemon.PokeGame;
import com.mak001.pokemon.world.Locatable;

public abstract class Entity extends Locatable implements Disposable {

	protected Direction direction;
	protected Speed speed;
	private boolean moved = false;
	private boolean moved_ = false;
	protected String generic_name;

	private ArrayList<String> interactionText;

	public TextureAtlas atlas;
	private HashMap<String, AtlasRegion> regions;

	public Entity(Vector2 position, Texture texture, String generic_name) {
		this(Direction.UP, position, texture, generic_name);
	}

	public Entity(Direction direction, Vector2 position, Texture texture,
			String generic_name) {
		this(Speed.STILL, direction, position, texture, null, generic_name);
	}

	public Entity(Speed speed, Direction direction, Vector2 position,
			Texture texture, ArrayList<String> interactionText,
			String generic_name) {
		super(position);
		this.speed = speed;
		this.direction = direction;
		this.generic_name = generic_name;

		this.interactionText = interactionText;

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

	public ArrayList<String> getInteractions() {
		return interactionText;
	}

	public String getInteraction(int i) {
		return interactionText.get(i);
	}

	@Override
	public void dispose() {
		regions.clear();
		atlas.dispose();
	}

	public void update() {
		// TODO
	}

	public Speed getSpeed() {
		return speed;
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

	public void render(SpriteBatch batch) {
		batch.draw(getTextureRegion(), position.x, position.y, 0, 0,
				PokeGame.TILE_DIMENSION, PokeGame.CHAR_HEIGHT,
				1f / PokeGame.TILE_DIMENSION, 1f / PokeGame.TILE_DIMENSION, 0);
	}
}
