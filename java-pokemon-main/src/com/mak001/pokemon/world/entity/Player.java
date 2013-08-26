package com.mak001.pokemon.world.entity;

import com.badlogic.gdx.math.Vector2;
import com.mak001.pokemon.PokeGame;
import com.mak001.pokemon.world.World;
import com.mak001.pokemon.world.entity.data.Direction;

public class Player extends Entity {

	private boolean shouldMove;
	private boolean shouldMove_2;
	private Direction temp = null;

	public Player(Direction direction, int x, int y, World world) {
		this(direction, new Vector2(x, y), world);
	}

	public Player(Direction direction, Vector2 position, World world) {
		super(direction, position, "player", world);
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
		for (int i = 0; i < getSpeed().getSpeed(); i++) {
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
						if (!isBlocked(position.x, position.y - 1,
								Direction.DOWN)) {
							position.y = tmpD;
						}
					} else {
						position.y = tmpD;
					}
					break;

				case LEFT:
					float tmpL = position.x - (1f / PokeGame.TILE_DIMENSION);
					if (!isMoving()) {
						if (!isBlocked(position.x - 1, position.y,
								Direction.LEFT)) {
							position.x = tmpL;
						}
					} else {
						position.x = tmpL;
					}
					break;

				case RIGHT:
					float tmpR = position.x + (1f / PokeGame.TILE_DIMENSION);
					if (!isMoving()) {
						if (!isBlocked(position.x + 1, position.y,
								Direction.RIGHT)) {
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
				world.handleDoor((int) position.x, (int) position.y);
				world.handleEvent((int) position.x, (int) position.y);
			}
		}
		updateBounds();
	}

	public void setMovement(boolean movement, Direction direction) {
		if (!talking) {
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
	}

	public void setPosition(int x, int y) {
		position.x = x;
		position.y = y;
		updateBounds();
	}

	@Override
	public void setTalking(boolean talking) {
		this.talking = talking;
	}
}
