package com.pokemon.battle;

import java.util.ArrayList;

public class Pokemon {

	private ArrayList<Move> moves = new ArrayList<Move>();
	private int level;
	private int hp;

	public Pokemon(Move[] m) {
		for (int i = 0; i < 4; i++) {
			if (i > m.length) {
				moves.add(Move.NULL);
			} else {
				moves.add(m[i]);
			}
		}
	}

	public ArrayList<Move> getMoves() {
		return moves;
	}

	public void addMove(Move m) {

	}

	public void replaceMove(Move move_to_add, Move move_to_replace) {
		if (moves.contains(move_to_replace)) {
			for (Move m : moves) {
				if (m.equals(move_to_replace)) {
					int i = moves.indexOf(move_to_replace);
					moves.remove(i);
					moves.add(i, move_to_add);
				}
			}
		} else {
			System.out.println("Invalid move replacement: Tried to replace: "
					+ move_to_replace.move_name()
					+ " when it was not in moves ArrayList.");
		}
	}

}
