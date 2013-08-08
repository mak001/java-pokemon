package com.mak001.pokemon.screens.huds;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.mak001.pokemon.screens.GameScreen;

public class TouchpadHud extends AbstractHud {

	private ShapeRenderer shape;
	private Rectangle r_button = new Rectangle(0, 0, 0, 0);
	private Rectangle l_button = new Rectangle(0, 0, 0, 0);

	private Rectangle d_pad_y = new Rectangle(0, 0, 0, 0);
	private Rectangle d_pad_x = new Rectangle(0, 0, 0, 0);
	private Circle a_button = new Circle();
	private Circle b_button = new Circle();

	public TouchpadHud(GameScreen screen) {
		super(screen);
		shape = new ShapeRenderer();
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
