package com.pokemon.room.characters;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.pokemon.battle.Pokemon;
import com.pokemon.room.Coordinate;
import com.pokemon.root.Animation;
import com.pokemon.root.Animation.AnimationType;
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
	private int stage;

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

	public BufferedImage image(String name, boolean battle) {
		try {
			if (battle)
				return ImageIO.read(getClass().getResourceAsStream(
						"/images/battle/trainers/" + name + ".png"));
			return ImageIO.read(getClass().getResourceAsStream(
					"/images/room/" + name + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
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
		if (current_coord < path.size() - 1) {
			return path.get(current_coord + 1);
		} else {
			return path.get(0);
		}
	}

	@Override
	public void draw(Graphics2D g) {
		AnimationType at = getAnimType(1, false);
		if (current_coord != -1) {
			if (ticks == delay) {

				at = getAnimType(1, false);
				animation.addTick();
				doCoord();
				ticks = 0;
			}
			ticks++;
		}
		animation.draw(g, at, coordinate);

		// TODO Auto-generated method stub

	}

	private void doCoord() {
		if (current_coord < path.size() - 1) {
			current_coord++;
		} else {
			current_coord = 0;
		}
		coordinate = path.get(current_coord);
		System.out.println("Coord: " + current_coord);

	}

	private AnimationType getAnimType(int stage, boolean b) {
		if (!b) {
			if (stage == 0) {
				switch (coordinate.directionTo(getNextInPath())) {
				case DOWN:
					return AnimationType.DOWN;
				case UP:
					return AnimationType.UP;
				case LEFT:
					return AnimationType.LEFT;
				case RIGHT:
					return AnimationType.RIGHT;
				default:
					return AnimationType.DOWN;
				}
			} else {
				switch (coordinate.directionTo(getNextInPath())) {
				case DOWN:
					return AnimationType.DOWN_WALKING;
				case UP:
					return AnimationType.UP_WALKING;
				case LEFT:
					return AnimationType.LEFT_WALKING;
				case RIGHT:
					return AnimationType.RIGHT_WALKING;
				default:
					return AnimationType.DOWN_WALKING;
				}
			}
		} else {
			return AnimationType.JUMPING;
		}
	}

}
