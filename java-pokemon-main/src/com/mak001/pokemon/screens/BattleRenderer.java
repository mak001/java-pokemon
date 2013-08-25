package com.mak001.pokemon.screens;

import com.badlogic.gdx.utils.Disposable;
import com.mak001.pokemon.world.entity.Entity;

public class BattleRenderer implements Disposable {

	private boolean didReset = false;
	
	public void setBattle(Entity player, Entity enemy) {
		// TODO Auto-generated method stub

	}

	public void render() {

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		didReset = true;
	}

	public boolean didReset() {
		return didReset;
	}

}
