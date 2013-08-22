package com.mak001.pokemon.screens.huds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.mak001.pokemon.screens.GameScreen;

public class TouchpadHud extends AbstractHud {

	private ShapeRenderer shape;
	private int gWidth = Gdx.graphics.getWidth();
	private int gHeight = Gdx.graphics.getHeight();

	private Rectangle d_pad_y_down;
	private Rectangle d_pad_y_up;
	private Rectangle d_pad_x_left;
	private Rectangle d_pad_right;

	private Polygon d_pad;

	private Rectangle r_button;
	private Rectangle l_button;

	private Circle a_button;
	private Circle b_button;

	public TouchpadHud(GameScreen screen) {
		super(screen);
		shape = new ShapeRenderer();
		generateButtons();
	}

	private void generateButtons() {
		int d_pad_offset;

		// TODO Auto-generated method stub

	}

	public void setBatch(SpriteBatch batch) {
		this.batch = batch;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		shape.dispose();
	}

	@Override
	public void render(float delta) {
		shape.begin(ShapeType.Filled);

		// TODO Auto-generated method stub
		shape.end();
	}

}
