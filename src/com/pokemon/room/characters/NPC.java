package com.pokemon.room.characters;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import com.pokemon.GameBase;
import com.pokemon.battle.Pokemon;
import com.pokemon.room.Coordinate;
import com.pokemon.root.Animation;
import com.pokemon.root.GeneralCharacter;

public class NPC extends GeneralCharacter {
	private static final long serialVersionUID = 1L;

	private String name;
	private ArrayList<Pokemon> pokemon;
	private String[] text;

	private ArrayList<Coordinate> path = new ArrayList<Coordinate>();
	private int current_coord = -1;
	private Type type;

	private Animation animation;

	private int ticks = 0;
	private int delay = 20;

	public enum Type {
		PROF_OAK, POLICE, SAILOR, CAPTAIN, ROCKET_MALE, ROCKET_FEMALE,
		SWIMMER_MALE, SWIMMER_FEMALE, BIKER, BUG_CATCHER, CAMPER_MALE,
		CAMPER_FEMALE, HIKER, SCIENTIST, CHEF, TRAINER_MALE, TRAINER_FEMALE
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
		if (path.size() > 2) {
			current_coord = 0;
			setCoordinate(path.get(0));
		}
		setImage(type);
		animation = new Animation(getImage());
	}

	private void setImage(Type type) {
		switch (type) {
		case POLICE:
			setImage(image("police", false));
			break;
		case SAILOR:
			setImage(image("sailor", false));
			break;

		}
	}

	public Image image(String name, boolean battle) {
		if (battle)
			return Toolkit.getDefaultToolkit().getImage(
					getClass().getResource(
							"/images/battle/trainers/" + name + ".png"));
		return Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/images/room/" + name + ".png"));
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

	public Type getType() {
		return type;
	}

	public String[] getSpeech() {
		return text;
	}

	public ArrayList<Pokemon> getPokemon() {
		return pokemon;
	}

	public Coordinate getNextInPath() {
		if (current_coord + 1 == path.size()) {
			return path.get(0);
		} else {
			return path.get(current_coord + 1);
		}
	}

	@Override
	public void draw(Graphics2D g) {

		if (ticks == delay) {

			if (current_coord != -1) { // -1 being that it doesn't have a path

				coordinate = path.get(current_coord);

				GameBase.getRoom().moveCharacterTo(path.get(current_coord),
						this);

				current_coord++;

				if (current_coord == path.size()) {
					current_coord = 0;
				}

				ticks = 0;
			}
		}
		g.drawImage(image, coordinate.getOffsetX(), getDrawY(), null);
		ticks++;
		// TODO Auto-generated method stub

	}

	public int getDrawY() {
		return getCoordinate().getOffsetY() - 9;
	}

}
