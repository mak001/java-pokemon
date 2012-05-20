package com.pokemon.room.characters;

import java.awt.Graphics;
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

	public NPC(String[] text, ArrayList<Coordinate> path) {
		this("", null, text, path);
	}

	public NPC(String name, String[] text, ArrayList<Coordinate> path) {
		this(name, null, text, path);
	}

	public NPC(String name, ArrayList<Pokemon> pokemon, String[] text,
			ArrayList<Coordinate> path) {
		this.name = name;
		this.pokemon = pokemon;
		this.text = text;
		this.path = path;
		if (this.path != null) {
			current_coord = 0;
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
		}
		// TODO Auto-generated method stub

	}

}
