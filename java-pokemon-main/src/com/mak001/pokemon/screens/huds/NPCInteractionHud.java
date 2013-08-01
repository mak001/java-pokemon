package com.mak001.pokemon.screens.huds;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mak001.pokemon.world.entity.Interaction;

public class NPCInteractionHud extends AbstractHud {

	private Interaction current;

	public NPCInteractionHud(SpriteBatch spriteBatch, Interaction interaction) {
		super(spriteBatch);
		current = interaction;
	}

	public void yesOption() {
		if (current.getYesInteraction() != null) {
			current = current.getYesInteraction();
		} else {
			cycles = 1;
		}
	}

	public void noOption() {
		if (current.getNoInteraction() != null) {
			current = current.getNoInteraction();
		} else if (current.getYesInteraction() != null) {
			current = current.getYesInteraction();
		} else {
			cycles = 1;
		}
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		if (current == null)
			cycles = 1;
		// TODO Auto-generated method stub

	}

}
