package com.mak001.pokemon.world.entity.data;

import java.util.ArrayList;
import java.util.Random;

public class BattleData {

	private ArrayList<Pokemon> pokemon;
	private ArrayList<Item> items;
	private int cash;

	public BattleData(ArrayList<Pokemon> pokemon, ArrayList<Item> items,
			int cash) {
		this.pokemon = pokemon;
		this.items = items;
		this.cash = cash;
	}

	public ArrayList<Pokemon> getPokemon() {
		return pokemon;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public int getCash() {
		return cash;
	}

	public Pokemon getRandom() {
		ArrayList<Pokemon> temp = new ArrayList<Pokemon>();
		for (Pokemon p : pokemon) {
			if (p.getHP() > 0) {
				temp.add(p);
			}
		}
		if (temp.size() == 0)
			return null;
		Random rand = new Random();
		return temp.get(rand.nextInt(temp.size()));
	}

}
