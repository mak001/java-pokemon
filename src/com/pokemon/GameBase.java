package com.pokemon;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import com.game.engine.Game;
import com.game.engine.GameApp;
import com.pokemon.Commands.Keys;
import com.pokemon.loader.GameProperties;
import com.pokemon.loader.Settings;
import com.pokemon.room.Coordinate;
import com.pokemon.room.Room;
import com.pokemon.room.characters.NPC;
import com.pokemon.room.characters.Player;
import com.pokemon.room.objects.Wall;
import com.pokemon.root.GeneralCharacter;
import com.pokemon.root.GeneralObject;

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
		title = "pokemon";
		width = 180;
		w = width;
		h = height;
		height = 265;
		renderDelay = 100;
		over = false;
		commands = new Commands();
		player = new Player();

		ConcurrentHashMap<Coordinate, GeneralObject> objects = new ConcurrentHashMap<Coordinate, GeneralObject>() {
			private static final long serialVersionUID = 1L;
			{

				put(new Coordinate(0, 0),
						new Wall(Toolkit.getDefaultToolkit().getImage(
								getClass().getResource(
										"/images/room/sailor.png"))));
			}
		};
		ConcurrentHashMap<Coordinate, GeneralCharacter> npcs = new ConcurrentHashMap<Coordinate, GeneralCharacter>() {
			private static final long serialVersionUID = 1L;
			{
				put(new Coordinate(0, 0), new NPC(NPC.Type.SAILOR,
						new String[] { "mmm", "ggg" },
						new ArrayList<Coordinate>() {
							private static final long serialVersionUID = 1L;
							{
								add(new Coordinate(0, 0));
								add(new Coordinate(0, 3));
								add(new Coordinate(5, 3));
								add(new Coordinate(5, 0));
							}
						}));

			}
		};
		room = new Room(objects, npcs);
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
