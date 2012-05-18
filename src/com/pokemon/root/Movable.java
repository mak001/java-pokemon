package com.pokemon.root;

import com.pokemon.Base;
import com.pokemon.GameBase;

public abstract class Movable extends Base {

	protected boolean isMoving;

	public enum Direction {
		UP, DOWN, LEFT, RIGHT
	}

	private Direction current;

	public Direction getCurrentDirection() {
		return current;
	}

	public void setDirection(Direction d) {
		current = d;
	}

	protected void moveUp() {
		isMoving = true;
		setDirection(Direction.UP);
		if (movable_Y > 0) {
			movable_Y--;
		}
	}

	protected void moveDown() {
		isMoving = true;
		setDirection(Direction.DOWN);
		if (movable_Y <  GameBase.height() - GameBase.getGrid().getPieceSize()) {
			movable_Y++;
		}
	}

	protected void moveLeft() {
		isMoving = true;
		setDirection(Direction.LEFT);
		if (movable_X > 0) {
			movable_X--;
		}
	}

	protected void moveRight() {
		isMoving = true;
		setDirection(Direction.RIGHT);
		if (movable_X < GameBase.width() - GameBase.getGrid().getPieceSize()) {
			movable_X++;
		}
	}

}
