package com.pokemon.battle;

/**
 * All the moves of the pokemon
 *
 */
public enum Move {

	NULL("----", null, "No move. (default)", 0);

	private final TYPE type;
	private String effect;
	private String move_name;
	private double accuracy;

	Move(String move_name, TYPE type, String effect, double accuracy) {
		this.type = type;
		this.effect = effect;
		this.accuracy = accuracy;
		this.move_name = move_name;
	}

	public TYPE type() {
		return type;
	}

	public String effect() {
		return effect;
	}

	public String move_name() {
		return move_name;
	}

	public double accuracy() {
		return accuracy;
	}

	public enum TYPE {
		FIRE, Water
	}

}
