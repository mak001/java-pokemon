package com.pokemon;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.game.engine.Game;
import com.game.engine.GameApp;
import com.pokemon.Commands.Keys;
import com.pokemon.loader.GameProperties;
import com.pokemon.loader.Settings;
import com.pokemon.room.Room;
import com.pokemon.room.characters.Player;

public class GameBase extends Game {

	private static Commands commands;
	private static Player player;
	private static Room room;

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
		Settings.init();
		GameProperties.load();
		switch (GameProperties.getProperty("COMMANDS")) {
		case "Default_1":
			commands.setPreDefined(Commands.Configuration.DEFAULT_1);
			break;
		case "Default_2":
			commands.setPreDefined(Commands.Configuration.DEFAULT_2);
			break;
		case "Default_3":
			commands.setPreDefined(Commands.Configuration.DEFAULT_3);
			break;
		default: // custom controls
			commands.setPreDefined(Commands.Configuration.CUSTOM);
			GameProperties.getProperty("COMMANDS");
			// TODO
			break;
		}
	}

	@Override
	public void update() {
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		room.draw(g);
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

	public static Player getPlayer() {
		return player;
	}

	public static Room getRoom() {
		return room;
	}

	public static void setRoom(Room r) {
		room = r;
	}

}