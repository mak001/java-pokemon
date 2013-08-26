package com.mak001.pokemon.world.entity.data;

public class Interaction {

	private String text;
	private Interaction yes, no;

	public Interaction(String string, Interaction... next) {
		this(string, next[0], next.length > 1 ? next[1] : null);
	}

	public Interaction(String string, Interaction yes, Interaction no) {
		this.yes = yes;
		this.no = no;
		this.text = string;
	}

	public Interaction getNoInteraction() {
		return no;
	}

	public Interaction getYesInteraction() {
		return yes;
	}

	public String getText() {
		return text;
	}

}