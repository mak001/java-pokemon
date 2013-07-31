package com.mak001.pokemon.control.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;
import com.mak001.pokemon.PokeGame;
import com.mak001.pokemon.control.AccelerometerListener;
import com.mak001.pokemon.control.Control.GameBoyButton;
import com.mak001.pokemon.control.Control.Input;
import com.mak001.pokemon.world.World;
import com.mak001.pokemon.world.entity.Direction;

public class InputHandler implements InputProcessor, ControllerListener,
		AccelerometerListener {

	private World world;

	public InputHandler() {
	}

	public void setWorld(World world) {
		this.world = world;

		// need this or it won't process events (even if set somewhere else)
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public boolean keyDown(int keycode) {
		if (world != null) {
			if (keycode == PokeGame.controls.getValue(GameBoyButton.D_PAD_UP,
					Input.KEYBOARD)) {
				if (!world.screen.isPaused())
					world.getPlayer().setMovement(true, Direction.UP);
			} else if (keycode == PokeGame.controls.getValue(
					GameBoyButton.D_PAD_DOWN, Input.KEYBOARD)) {
				if (!world.screen.isPaused())
					world.getPlayer().setMovement(true, Direction.DOWN);
			} else if (keycode == PokeGame.controls.getValue(
					GameBoyButton.D_PAD_LEFT, Input.KEYBOARD)) {
				if (!world.screen.isPaused())
					world.getPlayer().setMovement(true, Direction.LEFT);
			} else if (keycode == PokeGame.controls.getValue(
					GameBoyButton.D_PAD_RIGHT, Input.KEYBOARD)) {
				if (!world.screen.isPaused())
					world.getPlayer().setMovement(true, Direction.RIGHT);
			} else if (keycode == PokeGame.controls.getValue(
					GameBoyButton.START_BUTTON, Input.KEYBOARD)) {
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
					Input.KEYBOARD)) {
				world.getPlayer().setMovement(false, Direction.UP);
			} else if (keycode == PokeGame.controls.getValue(
					GameBoyButton.D_PAD_DOWN, Input.KEYBOARD)) {
				world.getPlayer().setMovement(false, Direction.DOWN);
			} else if (keycode == PokeGame.controls.getValue(
					GameBoyButton.D_PAD_LEFT, Input.KEYBOARD)) {
				world.getPlayer().setMovement(false, Direction.LEFT);
			} else if (keycode == PokeGame.controls.getValue(
					GameBoyButton.D_PAD_RIGHT, Input.KEYBOARD)) {
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
		// TODO Auto-generated method stub
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

	@Override
	public boolean accelerometerMoved(Controller arg0, int arg1, Vector3 arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean axisMoved(Controller arg0, int arg1, float arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean buttonDown(Controller arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean buttonUp(Controller arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void connected(Controller c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void disconnected(Controller arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean povMoved(Controller arg0, int arg1, PovDirection arg2) {
		// TODO Auto-generated method stub

		return false;
	}

	@Override
	public boolean xSliderMoved(Controller arg0, int arg1, boolean arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean ySliderMoved(Controller arg0, int arg1, boolean arg2) {
		// TODO Auto-generated method stub
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

}
