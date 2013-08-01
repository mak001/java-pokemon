package com.mak001.pokemon;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.Peripheral;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.utils.Json;
import com.mak001.pokemon.control.Control;
import com.mak001.pokemon.control.Control.GameBoyButton;
import com.mak001.pokemon.control.Control.Input;
import com.mak001.pokemon.control.controllers.XBoxController;
import com.mak001.pokemon.control.handlers.InputHandler;
import com.mak001.pokemon.screens.SplashScreen;

public class PokeGame extends Game {

	public static int TILE_DIMENSION = 16;
	public static int CHAR_HEIGHT = 32;
	public final static String SOUND_EFFECTS = "data/sound/effects/";
	public final static String MUSIC = "data/sound/music/";

	public static boolean no_external_controller;

	public static InputHandler handler;

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
		no_external_controller = !Gdx.input
				.isPeripheralAvailable(Peripheral.HardwareKeyboard)
				&& Controllers.getControllers().size == 0;

		controls = new Control();
		controls.updateButton(GameBoyButton.A_BUTTON, Input.KEYBOARD,
				Keys.SPACE);
		controls.updateButton(GameBoyButton.B_BUTTON, Input.KEYBOARD, Keys.B);
		controls.updateButton(GameBoyButton.D_PAD_UP, Input.KEYBOARD, Keys.W);
		controls.updateButton(GameBoyButton.D_PAD_LEFT, Input.KEYBOARD, Keys.A);
		controls.updateButton(GameBoyButton.D_PAD_RIGHT, Input.KEYBOARD, Keys.D);
		controls.updateButton(GameBoyButton.D_PAD_DOWN, Input.KEYBOARD, Keys.S);
		controls.updateButton(GameBoyButton.RIGHT_BUTTON, Input.KEYBOARD,
				Keys.E);
		controls.updateButton(GameBoyButton.LEFT_BUTTON, Input.KEYBOARD, Keys.Q);
		controls.updateButton(GameBoyButton.START_BUTTON, Input.KEYBOARD,
				Keys.ENTER);
		controls.updateButton(GameBoyButton.SELECT_BUTTON, Input.KEYBOARD,
				Keys.APOSTROPHE);

		controls.updateButton(GameBoyButton.A_BUTTON, Input.XBOX_CONTROLLER,
				XBoxController.BUTTON_A);
		controls.updateButton(GameBoyButton.B_BUTTON, Input.XBOX_CONTROLLER,
				XBoxController.BUTTON_X);
		controls.updateButton(GameBoyButton.D_PAD_UP, Input.XBOX_CONTROLLER,
				XBoxController.BUTTON_DPAD_UP);
		controls.updateButton(GameBoyButton.D_PAD_LEFT, Input.XBOX_CONTROLLER,
				XBoxController.BUTTON_DPAD_LEFT);
		controls.updateButton(GameBoyButton.D_PAD_RIGHT, Input.XBOX_CONTROLLER,
				XBoxController.BUTTON_DPAD_RIGHT);
		controls.updateButton(GameBoyButton.D_PAD_DOWN, Input.XBOX_CONTROLLER,
				XBoxController.BUTTON_DPAD_DOWN);
		controls.updateButton(GameBoyButton.RIGHT_BUTTON,
				Input.XBOX_CONTROLLER, XBoxController.BUTTON_RB);
		controls.updateButton(GameBoyButton.LEFT_BUTTON, Input.XBOX_CONTROLLER,
				XBoxController.BUTTON_LB);
		controls.updateButton(GameBoyButton.START_BUTTON,
				Input.XBOX_CONTROLLER, XBoxController.BUTTON_START);
		controls.updateButton(GameBoyButton.SELECT_BUTTON,
				Input.XBOX_CONTROLLER, XBoxController.BUTTON_BACK);

		// controls.save();

		// Json json = new Json();
		// controls = json.fromJson(Control.class,
		// Gdx.files.external("Desktop/controls.json"));

		setScreen(getSplashScreen());

		handler = new InputHandler();

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

		// Only because there is no built in accelerometer listener
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
