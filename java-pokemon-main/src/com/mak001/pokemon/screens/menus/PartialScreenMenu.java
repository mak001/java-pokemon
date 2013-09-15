package com.mak001.pokemon.screens.menus;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mak001.pokemon.GlobalVars;
import com.mak001.pokemon.screens.GameScreen;
import com.mak001.pokemon.world.WorldRenderer;

public class PartialScreenMenu<O extends Object> extends AbstractMenu {

	private float width;
	private float height;
	private float x;
	private float y;
	private ArrayList<O> options = new ArrayList<O>();
	private int index = 0;

	private NinePatch patch;
	private TextureAtlas atlas;
	private BitmapFont black;
	private Anchor anchor;

	public enum Anchor {
		TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT, CENTER, ABSOLUTE;
	}

	public PartialScreenMenu(float x, float y, float width, GameScreen screen,
			SpriteBatch batch, O[] options) {
		this(x, y, width, screen, batch, options, GlobalVars.defaultFrame);
	}

	public PartialScreenMenu(float x, float y, float width, GameScreen screen,
			SpriteBatch batch, Anchor anchor, O[] options) {
		this(x, y, width, screen, batch, anchor, options,
				GlobalVars.defaultFrame);
	}

	public PartialScreenMenu(float x, float y, float width, GameScreen screen,
			SpriteBatch batch, O[] options, String framePatch) {
		this(x, y, width, screen, batch, Anchor.ABSOLUTE, options,
				GlobalVars.defaultFrame);
	}

	public PartialScreenMenu(float x, float y, float width, GameScreen screen,
			SpriteBatch batch, Anchor anchor, O[] options, String framePatch) {
		super(screen, batch);
		this.width = width;
		this.anchor = anchor;
		this.x = x;
		this.y = y;
		for (O option : options) {
			this.options.add(option);
		}

		atlas = new TextureAtlas(Gdx.files.internal("data/ui/frame.pack"));
		patch = atlas.createPatch("frame");

		black = new BitmapFont(Gdx.files.internal("data/fonts/black_font.fnt"),
				false);

		black.getRegion().getTexture()
				.setFilter(TextureFilter.Linear, TextureFilter.Linear);
	}

	public ArrayList<O> getOptions() {
		return options;
	}

	public void addOption(O option) {
		options.add(option);
		x += 15;
	}

	public void addOption(int index, O option) {
		options.add(index, option);
		x += 15;
	}

	public O getSelected() {
		return options.get(index);
	}

	public int getSelectedIndex() {
		return index;
	}

	public int setSelected(int index) {
		if (index < 0) {
			index = options.size() - 1;
		} else if (index <= options.size()) {
			index = 0;
		}
		this.index = index;
		return index;
	}

	public void selectNext() {
		setSelected(index + 1);
	}

	public void selectPrevious() {
		setSelected(index - 1);
	}

	public void render() {
		height = (15 * (options.size() + 1)) + 10;
		patch.draw(batch, getRealX(), getRealY(), width, height);

		for (int i = 0; i < options.size(); i++) {
			black.draw(batch, options.get(i).toString(), getRealX() + 15,
					getY(getRealY(), height, i));
		}

		// text is 10 tall, 5 apart, 10 from top
		// arrow is 5 wide, 10 tall

		// TODO render arrow
	}

	private float getY(float y, float height, int index) {
		return ((y + height) - getLineTotal(index)) - 10;
	}

	private float getLineTotal(int i) {
		return (10 * i) + getSpacing(i);
	}

	private float getSpacing(int index) {
		return (5 * index);
	}

	private float getRealY() {
		switch (anchor) {
		case BOTTOM_LEFT:
			return y;
		case BOTTOM_RIGHT:
			return y;
		case TOP_LEFT:
			return (WorldRenderer.VIRTUAL_HEIGHT - y) - height;
		case TOP_RIGHT:
			return (WorldRenderer.VIRTUAL_HEIGHT - y) - height;
		case CENTER:
			return (WorldRenderer.VIRTUAL_HEIGHT / 2) - (height / 2);
		default:
			return y;
		}
	}

	private float getRealX() {
		switch (anchor) {
		case BOTTOM_LEFT:
			return x;
		case BOTTOM_RIGHT:
			return (WorldRenderer.VIRTUAL_WIDTH - x) - width;
		case TOP_LEFT:
			return x;
		case TOP_RIGHT:
			return (WorldRenderer.VIRTUAL_WIDTH - x) - width;
		case CENTER:
			return (WorldRenderer.VIRTUAL_WIDTH / 2) - (width / 2);
		default:
			return x;
		}
	}

	@Override
	public void dispose() {
		atlas.dispose();
		black.dispose();
		// TODO Auto-generated method stub

	}

}
