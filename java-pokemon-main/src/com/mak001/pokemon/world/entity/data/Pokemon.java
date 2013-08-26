package com.mak001.pokemon.world.entity.data;

import java.util.ArrayList;
import java.util.Random;

public class Pokemon {

	private int hp;
	private ArrayList<Move> moves;

	public Pokemon(ArrayList<Move> moves, int hp) {

	}

	public int getHP() {
		return hp;
	}

	public Move getRandom() {
		ArrayList<Move> temp = new ArrayList<Move>();
		for (Move m : moves) {
			if (m.getPP() > 0) {
				temp.add(m);
			}
		}
		if (temp.size() == 0)
			return null;
		Random rand = new Random();
		return temp.get(rand.nextInt(temp.size()));
	}

}
