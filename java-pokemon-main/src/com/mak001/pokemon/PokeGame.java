package com.mak001.pokemon;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Peripheral;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.utils.Json;
import com.mak001.pokemon.control.InputHandler;
import com.mak001.pokemon.control.controllers.Control;
import com.mak001.pokemon.screens.GameScreen;
import com.mak001.pokemon.screens.SplashScreen;

public class PokeGame extends Game {

	private FPSLogger fpsLogger;

	public static int TILE_DIMENSION = 16;
	public static int CHAR_HEIGHT = 32;
	public final static String SOUND_EFFECTS = "data/sound/effects/";
	public final static String MUSIC = "data/sound/music/";

	public static InputHandler handler;
	public static GameScreen gameScreen;

	public static Control controls;

	private boolean has_a = false;
	private float a_x = 0;
	private float a_y = 0;
	private float a_z = 0;

	public SplashScreen getSplashScreen() {
		return new SplashScreen(this);
	}

	@Override
	public void create() {
		Json json = new Json();
		controls = json.fromJson(Control.class,
				Gdx.files.external("Desktop/controls.json"));
		setScreen(getSplashScreen());
		fpsLogger = new FPSLogger();

		gameScreen = new GameScreen(this);
		handler = new InputHandler(gameScreen.renderer.world);
		Gdx.input.setInputProcessor(handler);

		has_a = Gdx.input.isPeripheralAvailable(Peripheral.Accelerometer);
		if (has_a) {
			a_x = Gdx.input.getAccelerometerX();
			a_y = Gdx.input.getAccelerometerY();
			a_z = Gdx.input.getAccelerometerZ();
		}
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void render() {
		super.render();

		if (has_a) {
			if (a_x != Gdx.input.getAccelerometerX()) {
				handler.accelerometerXChanged(Gdx.input.getAccelerometerX());
				a_x = Gdx.input.getAccelerometerX();
			}
			if (a_y != Gdx.input.getAccelerometerY()) {
				handler.accelerometerYChanged(Gdx.input.getAccelerometerY());
				a_y = Gdx.input.getAccelerometerY();
			}
			if (a_z != Gdx.input.getAccelerometerZ()) {
				handler.accelerometerZChanged(Gdx.input.getAccelerometerZ());
				a_z = Gdx.input.getAccelerometerZ();
			}
		}

		// fpsLogger.log();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
}
