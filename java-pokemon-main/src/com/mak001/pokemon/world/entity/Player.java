package com.mak001.pokemon.world.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mak001.pokemon.PokeGame;
import com.mak001.pokemon.world.Collision;
import com.mak001.pokemon.world.World;

public class Player extends Entity {

	private boolean shouldMove;
	private boolean shouldMove_2;
	private Direction temp = null;

	private World world;

	public Player(Direction direction, int x, int y, World world) {
		this(direction, new Vector2(x, y), world);
	}

	public Player(Direction direction, Vector2 position, World world) {
		super(direction, position, new Texture(
				"data/textures/entities/player.png"), "player");
		this.world = world;
		// TODO Auto-generated constructor stub
	}

	public void updateDirection(Direction d) {
		if (!d.equals(direction)) {
			if (!isMoving()) {
				direction = d;
			}
		}
	}

	@Override
	public void update() {
		if (temp != null && !isMoving()) {
			setDirection(temp);
			temp = null;
		}
		if (!shouldMove_2 && !isMoving())
			shouldMove = false;

		if (shouldMove) {
			switch (getDirection()) {
			case DOWN:
				float tmpD = position.y - (1f / PokeGame.TILE_DIMENSION);
				if (!isMoving()) {
					if (!isBlocked(position.x, position.y - 1, Direction.DOWN)) {
						position.y = tmpD;
					}
				} else {
					position.y = tmpD;
				}
				break;

			case LEFT:
				float tmpL = position.x - (1f / PokeGame.TILE_DIMENSION);
				if (!isMoving()) {
					if (!isBlocked(position.x - 1, position.y, Direction.LEFT)) {
						position.x = tmpL;
					}
				} else {
					position.x = tmpL;
				}
				break;

			case RIGHT:
				float tmpR = position.x + (1f / PokeGame.TILE_DIMENSION);
				if (!isMoving()) {
					if (!isBlocked(position.x + 1, position.y, Direction.RIGHT)) {
						position.x = tmpR;
					}
				} else {
					position.x = tmpR;
				}
				break;

			case UP:
				float tmpU = position.y + (1f / PokeGame.TILE_DIMENSION);
				if (!isMoving()) {
					if (!isBlocked(position.x, position.y + 1, Direction.UP)) {
						position.y = tmpU;
					}
				} else {
					position.y = tmpU;
				}
				break;
			}
			if (isDoor(position.x, position.y)) {
				world.handleDoor((int) position.x, (int) position.y);
			}
		}
	}

	public void setMovement(boolean movement, Direction direction) {
		// TODO Auto-generated method stub
		if (direction.equals(getDirection()))
			shouldMove_2 = movement;

		if (!isMoving())
			shouldMove_2 = movement;

		if (movement) {
			if (!isMoving()) {
				setDirection(direction);
			} else {
				temp = direction;
			}
			shouldMove = true;
		}
	}

	private boolean isDoor(int i) {
		return i == Collision.DOOR.getType();
	}

	private boolean isDoor(float x, float y) {
		return isDoor(getCollision((int) x, (int) y));
	}

	private boolean isBlocked(int i, Direction d) {
		if (d.equals(Direction.DOWN))
			return i == Collision.WALL.getType();
		return i == Collision.WALL.getType() || i == Collision.CLIFF.getType();
	}

	private boolean isBlocked(float x, float f, Direction d) {
		return isBlocked(getCollision((int) x, (int) f), d);
	}

	private int getCollision(int x, int y) {
		if (world.getCollision().getCell(x, y) == null) {
			return Collision.NONE.getType();
		} else {
			TextureRegion r = world.getCollision().getCell(x, y).getTile()
					.getTextureRegion();

			if (Collision.WALL.equals(r)) {
				return Collision.WALL.getType();

			} else if (Collision.DOOR.equals(r)) {
				return Collision.DOOR.getType();

			} else if (Collision.CLIFF.equals(r)) {
				return Collision.CLIFF.getType();

			} else {
				return Collision.NONE.getType();
			}
		}
	}

	public void setPosition(int x, int y) {
		position.x = x;
		position.y = y;
	}
}
