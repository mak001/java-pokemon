package com.pokemon.room.characters;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import com.pokemon.GameBase;
import com.pokemon.battle.Pokemon;
import com.pokemon.room.Coordinate;
import com.pokemon.root.GeneralCharacter;

public class NPC extends GeneralCharacter {
	private static final long serialVersionUID = 1L;

	private String name;
	private ArrayList<Pokemon> pokemon;
	private String[] text;

	private ArrayList<Coordinate> path = new ArrayList<Coordinate>();
	private int current_coord = -1;
	private Type type;

	public enum Type {
		PROF_OAK, POLICE, SAILOR, CAPTAIN, ROCKET_MALE, ROCKET_FEMALE,
		SWIMMER_MALE, SWIMMER_FEMALE, BIKER, BUG_CATCHER, CAMPER_MALE,
		CAMPER_FEMALE, HIKER, SCIENTIST, CHEF, TRAINER_MALE, TRAINER_FEMALE
	}

	public NPC(String[] text, ArrayList<Coordinate> path) {
		this("", null, null, text, path);
	}

	public NPC(Type type, String[] text, ArrayList<Coordinate> path) {
		this("", type, null, text, path);
	}

	public NPC(String name, Type type, ArrayList<Pokemon> pokemon,
			String[] text, ArrayList<Coordinate> path) {
		this.name = name;
		this.pokemon = pokemon;
		this.text = text;
		this.path = path;
		if (this.path != null) {
			current_coord = 0;
		}
		setImage();
	}

	private void setImage() {
		switch (type) {
		case POLICE:
			image = image("police", false);
			break;
		case SAILOR:
			image = image("sailor", false);
			break;

		}
	}

	public Image image(String name, boolean battle) {
		if (battle)
			return Toolkit.getDefaultToolkit().getImage(
					NPC.class.getResource("/images/battle/trainers/" + name
							+ ".png"));
		return Toolkit.getDefaultToolkit().getImage(
				NPC.class.getResource("/images/room/" + name + ".png"));
	}

	public int getSpeechLength() {
		return text.length;
	}

	public String getSpeech(int number) {
		return text[number];
	}

	public String getName() {
		return name;
	}

	public String[] getSpeech() {
		return text;
	}

	public ArrayList<Pokemon> getPokemon() {
		return pokemon;
	}

	@Override
	public void draw(Graphics g) {
		if (current_coord != -1) { // -1 being that it doesn't have a path
			GameBase.getRoom().setTile(path.get(current_coord + 1), this);
		} else {

		}
		// TODO Auto-generated method stub

	}

	public int getDrawY() {
		return getCoordinate().getY() + 4;
	}

}
