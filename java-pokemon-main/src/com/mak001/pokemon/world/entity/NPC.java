package com.mak001.pokemon.world.entity;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mak001.pokemon.PokeGame;
import com.mak001.pokemon.world.World;

public class NPC extends Entity {

	private ArrayList<Vector2> path;
	private Interaction interaction;

	private String name;
	private boolean talking = false;
	private Direction original_direction;
	private int nextPosition = 1;

	public NPC(Direction direction, Vector2 position, World world,
			String generic_name) {
		this(direction, position, world, generic_name, null);
	}

	public NPC(Direction direction, Vector2 position, World world,
			String generic_name, String name) {
		this(direction, position, world, null, generic_name, name);
	}

	public NPC(Direction direction, final Vector2 position, World world,
			Interaction interaction, String generic_name, String name) {
		this(direction, new ArrayList<Vector2>() {
			private static final long serialVersionUID = 1L;
			{
				add(position);
			}
		}, world, interaction, generic_name, name);
	}

	public NPC(Direction direction, ArrayList<Vector2> path, World world,
			final String interaction, String generic_name, String name) {
		this(direction, path, world, interaction == null ? null : interaction
				.equals("") ? null : new Interaction(interaction),
				generic_name, name);
	}

	public NPC(Direction direction, ArrayList<Vector2> path, World world,
			String generic_name, String name) {
		this(direction, path, world, "", generic_name, name);
	}

	public NPC(Direction direction, ArrayList<Vector2> path, World world,
			Interaction interaction, String generic_name, String name) {
		super(direction, path.get(0), generic_name, world);
		this.name = name;
		this.path = path;
		this.interaction = interaction;
		this.original_direction = direction;
	}

	public Interaction getFirstInteraction() {
		return interaction;
	}

	public void update() {
		if (talking) {
			if (!getDirection().equals(getDirection(world.getPlayer())))
				direction = getDirection(world.getPlayer());
		} else {
			if (path.size() > 1) {
				Vector2 pos = path.get(nextPosition);
				if (pos.x == position.x && pos.y == position.y) {
					System.out.println("Getting next pos...");
					nextPosition++;
					if (nextPosition == path.size())
						nextPosition = 0;
				}
				if (!isMoving()) {
					if (!getDirection().equals(
							getDirection(path.get(nextPosition))))
						direction = getDirection(path.get(nextPosition));

					Vector2 v = getNextPos();
					System.out.println("X: " + position.x + ", Y: "
							+ position.y);
					System.out.println("Next pos X: " + v.x + ", Y: " + v.y);
					System.out.println("Dextination X: " + pos.x + ", Y: "
							+ pos.y);

					if (!isBlocked(v.x, v.y, direction))
						move();
				} else {
					move();
				}
			} else {
				direction = original_direction;
			}
		}
	}

	private Vector2 getNextPos() {
		switch (direction) {
		case DOWN:
			return new Vector2(position.x, position.y - 1);
		case LEFT:
			return new Vector2(position.x - 1, position.y);
		case RIGHT:
			return new Vector2(position.x + 1, position.y);
		case UP:
			return new Vector2(position.x, position.y + 1);
		default:
			return null;

		}
	}

	private void move() {
		switch (getDirection()) { // TODO - move
		case DOWN:
			position.y -= (1f / PokeGame.TILE_DIMENSION);
			break;
		case LEFT:
			position.x -= (1f / PokeGame.TILE_DIMENSION);
			break;
		case RIGHT:
			position.x += (1f / PokeGame.TILE_DIMENSION);
			break;
		case UP:
			position.y += (1f / PokeGame.TILE_DIMENSION);
			break;
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		super.render(batch);
		update();
	}

	public String getName() {
		return name;
	}

	public String getFullName() {
		return generic_name + " " + name;
	}

	public void setTalking(boolean talking) {
		this.talking = talking;
	}

}
