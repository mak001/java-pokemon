package com.mak001.pokemon.world.entity;

public class Interaction {

	private String text;
	private Interaction yes, no;

	public Interaction(String string) {
		this(string, null);
	}

	public Interaction(String string, Interaction next) {
		this(string, next, null);
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