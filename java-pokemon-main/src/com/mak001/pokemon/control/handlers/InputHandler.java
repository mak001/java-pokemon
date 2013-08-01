package com.mak001.pokemon.control.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;
import com.mak001.pokemon.PokeGame;
import com.mak001.pokemon.control.AccelerometerListener;
import com.mak001.pokemon.control.Control.GameBoyButton;
import com.mak001.pokemon.control.Control.Input;
import com.mak001.pokemon.control.controllers.XperiaGamepad;
import com.mak001.pokemon.screens.huds.ControllerConnectedHud;
import com.mak001.pokemon.world.World;
import com.mak001.pokemon.world.entity.Direction;

public class InputHandler implements InputProcessor, ControllerListener,
		AccelerometerListener {

	private World world;

	public InputHandler() {
		Controllers.addListener(this);
	}

	public void setWorld(World world) {
		this.world = world;

		// need this or it won't process events (even if set somewhere else)
		Gdx.input.setInputProcessor(this);
		Controllers.removeListener(this);
		Controllers.addListener(this);
	}

	// ------------------------------//
	// ------Keyboard + Mouse--------//
	// ------------------------------//

	@Override
	public boolean keyDown(int keycode) {
		if (world != null) {
			if (keycode == PokeGame.controls.getValue(GameBoyButton.D_PAD_UP,
					Input.KEYBOARD).getID()) {
				if (!world.screen.isPaused())
					world.getPlayer().setMovement(true, Direction.UP);
			} else if (keycode == PokeGame.controls.getValue(
					GameBoyButton.D_PAD_DOWN, Input.KEYBOARD).getID()) {
				if (!world.screen.isPaused())
					world.getPlayer().setMovement(true, Direction.DOWN);
			} else if (keycode == PokeGame.controls.getValue(
					GameBoyButton.D_PAD_LEFT, Input.KEYBOARD).getID()) {
				if (!world.screen.isPaused())
					world.getPlayer().setMovement(true, Direction.LEFT);
			} else if (keycode == PokeGame.controls.getValue(
					GameBoyButton.D_PAD_RIGHT, Input.KEYBOARD).getID()) {
				if (!world.screen.isPaused())
					world.getPlayer().setMovement(true, Direction.RIGHT);
			} else if (keycode == PokeGame.controls.getValue(
					GameBoyButton.START_BUTTON, Input.KEYBOARD).getID()) {
				world.screen.setPaused();
			}
			// TODO Auto-generated method stub
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (world != null) {
			if (keycode == PokeGame.controls.getValue(GameBoyButton.D_PAD_UP,
					Input.KEYBOARD).getID()) {
				world.getPlayer().setMovement(false, Direction.UP);
			} else if (keycode == PokeGame.controls.getValue(
					GameBoyButton.D_PAD_DOWN, Input.KEYBOARD).getID()) {
				world.getPlayer().setMovement(false, Direction.DOWN);
			} else if (keycode == PokeGame.controls.getValue(
					GameBoyButton.D_PAD_LEFT, Input.KEYBOARD).getID()) {
				world.getPlayer().setMovement(false, Direction.LEFT);
			} else if (keycode == PokeGame.controls.getValue(
					GameBoyButton.D_PAD_RIGHT, Input.KEYBOARD).getID()) {
				world.getPlayer().setMovement(false, Direction.RIGHT);
			}
		}
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	// ------------------------------//
	// ---------Touch pads-----------//
	// ------------------------------//

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public void accelerometerXChanged(float x) {
		// TODO Auto-generated method stub

	}

	@Override
	public void accelerometerYChanged(float y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void accelerometerZChanged(float z) {
		// TODO Auto-generated method stub

	}

	// ------------------------------//
	// ---------Controllers----------//
	// ------------------------------//

	public int indexOf(Controller controller) {
		return Controllers.getControllers().indexOf(controller, true);
	}

	public Input getControllerType(Controller controller) {
		if (controller.getName().toLowerCase().contains("xbox")
				&& controller.getName().contains("360")) {
			return Input.XBOX_CONTROLLER;
		} else if (controller.getName().equalsIgnoreCase(XperiaGamepad.ID)) {
			return Input.XPERIA_GAMEPAD;
		}
		return null;
	}

	@Override
	public void connected(Controller controller) {
		world.screen.addHud(new ControllerConnectedHud(controller,
				world.screen, 500));
	}

	@Override
	public void disconnected(Controller controller) {
	}

	@Override
	public boolean buttonDown(Controller controller, int buttonIndex) {
		if (getControllerType(controller).equals(Input.XBOX_CONTROLLER)) {
			world.screen.addHud(new ControllerConnectedHud(controller,
					world.screen, 500));
		}
		return true;
	}

	@Override
	public boolean buttonUp(Controller controller, int buttonIndex) {
		if (getControllerType(controller).equals(Input.XBOX_CONTROLLER)) {

		}
		return false;
	}

	@Override
	public boolean axisMoved(Controller controller, int axisIndex, float value) {
		return false;
	}

	@Override
	public boolean povMoved(Controller controller, int povIndex,
			PovDirection value) {
		if (getControllerType(controller).equals(Input.XBOX_CONTROLLER)) {
			// povIndex not needed for xbox controller

			if (world != null) {
				if (value == PokeGame.controls.getValue(GameBoyButton.D_PAD_UP,
						Input.XBOX_CONTROLLER).getDirection()) {
					if (!world.screen.isPaused())
						world.getPlayer().setMovement(true, Direction.UP);
				} else if (value.equals(PokeGame.controls.getValue(
						GameBoyButton.D_PAD_DOWN, Input.XBOX_CONTROLLER)
						.getDirection())) {
					if (!world.screen.isPaused())
						world.getPlayer().setMovement(true, Direction.DOWN);
				} else if (value.equals(PokeGame.controls.getValue(
						GameBoyButton.D_PAD_LEFT, Input.XBOX_CONTROLLER)
						.getDirection())) {
					if (!world.screen.isPaused())
						world.getPlayer().setMovement(true, Direction.LEFT);
				} else if (value.equals(PokeGame.controls.getValue(
						GameBoyButton.D_PAD_RIGHT, Input.XBOX_CONTROLLER)
						.getDirection())) {
					if (!world.screen.isPaused())
						world.getPlayer().setMovement(true, Direction.RIGHT);
				} else if (value.equals(PokeGame.controls.getValue(
						GameBoyButton.START_BUTTON, Input.XBOX_CONTROLLER)
						.getDirection())) {
					world.screen.setPaused();
				} else if (value.equals(PovDirection.center)) {
					world.getPlayer().setMovement(false, Direction.UP);
					world.getPlayer().setMovement(false, Direction.DOWN);
					world.getPlayer().setMovement(false, Direction.LEFT);
					world.getPlayer().setMovement(false, Direction.RIGHT);
				}
				// TODO Auto-generated method stub
			}
		}
		return false;
	}

	@Override
	public boolean xSliderMoved(Controller controller, int sliderIndex,
			boolean value) {
		return false;
	}

	@Override
	public boolean ySliderMoved(Controller controller, int sliderIndex,
			boolean value) {
		return false;
	}

	@Override
	public boolean accelerometerMoved(Controller arg0, int arg1, Vector3 arg2) {
		return false;
	}

}
