package com.pokemon;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.game.engine.Game;
import com.game.engine.GameApp;
import com.pokemon.Commands.Keys;
import com.pokemon.root.Player;

public class GameBase extends Game {

	private static Commands commands;
	private Player player;

	private static int w;
	private static int h;

	public static void main(String[] args) {
		GameApp.start(new GameBase());
	}

	public GameBase() {
		title = "PackMan";
		width = 180;
		w = width;
		h = height;
		height = 265;
		renderDelay = 100;
		over = false;
		commands = new Commands();
	}

	public static int width() {
		return w;
	}

	public static int height() {
		return h;
	}

	public static Commands getCommands() {
		return commands;
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent k) {
		if (k.getKeyCode() == commands.getCurrentConfiguration().get(Keys.A)) {
			player.interact();
		}
	}

	@Override
	public void keyReleased(KeyEvent k) {

	}

	@Override
	public void keyTyped(KeyEvent k) {

	}

}
