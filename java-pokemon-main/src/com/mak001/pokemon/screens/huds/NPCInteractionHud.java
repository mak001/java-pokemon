package com.mak001.pokemon.screens.huds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mak001.pokemon.screens.GameScreen;
import com.mak001.pokemon.world.entity.NPC;
import com.mak001.pokemon.world.entity.data.Interaction;

public class NPCInteractionHud extends AbstractHud {

	private Interaction current;
	private NPC npc;

	private NinePatch patch;
	private TextureAtlas atlas;
	private BitmapFont black;

	public NPCInteractionHud(GameScreen screen, Interaction interaction) {
		super(screen);
		current = interaction;
		atlas = new TextureAtlas(Gdx.files.internal("data/ui/frame.pack"));
		patch = atlas.createPatch("frame");

		black = new BitmapFont(Gdx.files.internal("data/fonts/black_font.fnt"),
				false);
		black.setScale(1 / screen.getScale());

		if (screen.world != null)
			if (screen.world.getPlayer() != null)
				screen.world.getPlayer().setTalking(true);
	}

	public NPCInteractionHud(GameScreen screen, NPC npc) {
		this(screen, npc.getFirstInteraction());
		this.npc = npc;
	}

	public Interaction getInteraction() {
		return current;
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
		} else {
			yesOption();
		}
	}

	@Override
	public void dispose() {
		atlas.dispose();
		black.dispose();
		if (npc != null) {
			npc.resetHud();
		}
		screen.world.getPlayer().setTalking(false);
	}

	@Override
	public void render(float delta) {
		float width = screen.renderer.camera.viewportWidth;
		float height = screen.renderer.camera.viewportHeight / 3;

		float x = 0;
		float y = 0;

		patch.draw(batch, x, y, width, height);

		if (current == null) {
			cycles = 1;
		} else {
			black.draw(batch, current.getText(), 10, height - 10);
		}

		// TODO Auto-generated method stub

	}

}
