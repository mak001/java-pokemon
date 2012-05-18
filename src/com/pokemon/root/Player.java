package com.pokemon.root;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.pokemon.Commands;
import com.pokemon.GameBase;

public class Player extends Movable {

	Commands c;

	public Player() {
		c = GameBase.getCommands();
	}

	public void move(KeyEvent k) {
		if (k.getKeyCode() == c.getKey(Commands.Keys.UP)) {
			// up
			moveUp();
		} else if (k.getKeyCode() == c.getKey(Commands.Keys.DOWN)) {
			// down
			moveDown();
		} else if (k.getKeyCode() == c.getKey(Commands.Keys.LEFT)) {
			// left
			moveLeft();
		} else if (k.getKeyCode() == c.getKey(Commands.Keys.RIGHT)) {
			// right
			moveRight();
		}
	}

	public void interact() {
		// TODO
	}

	public void draw(Graphics g) {

	}

}
