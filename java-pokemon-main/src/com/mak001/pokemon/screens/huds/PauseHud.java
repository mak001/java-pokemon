package com.mak001.pokemon.screens.huds;

import com.mak001.pokemon.GlobalVars;
import com.mak001.pokemon.screens.GameScreen;
import com.mak001.pokemon.screens.menus.PartialScreenMenu;
import com.mak001.pokemon.world.WorldRenderer;

public class PauseHud extends AbstractHud {

	private PartialScreenMenu<String> menu;

	public PauseHud(GameScreen gameScreen) {
		super(gameScreen);

		String[] options = new String[] { "Bag", GlobalVars.playerName, "Map",
				"Pokedex", "Options", "Exit" };

		if (!GlobalVars.hasPokedex) {
			options = new String[] { "Bag", GlobalVars.playerName, "Map",
					"Options", "Exit" };
		}

		float width = 90;
		float height = (15 * (options.length + 1)) + 10;

		float x = WorldRenderer.VIRTUAL_WIDTH - width;
		float y = WorldRenderer.VIRTUAL_HEIGHT - height;

		menu = new PartialScreenMenu<String>(5, 5, width, screen, batch,
				PartialScreenMenu.Anchor.TOP_RIGHT, options);
	}

	@Override
	public void render(float delat) {
		menu.render();
	}

	@Override
	public void dispose() {
		menu.dispose();
	}

	public void moveSelectionUp() {
		menu.selectPrevious();
	}

	public void moveSelectionDown() {
		menu.selectNext();
	}

	public void selectSelection() {
		menu.getSelected();
		// TODO Auto-generated method stub

	}

	public void cancel() {
		// TODO Auto-generated method stub

	}

}
