package com.mak001.pokemon.control;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;

import com.mak001.pokemon.PokeGame;
import com.mak001.pokemon.world.World;
import com.mak001.pokemon.world.entity.Direction;

public class InputHandler implements InputProcessor, ControllerListener,
		AccelerometerListener {

	private World world;

	public InputHandler(World world) {
		this.world = world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == PokeGame.controls.move_up) {
			if (!world.screen.isPaused())
				world.getPlayer().setMovement(true, Direction.UP);
		} else if (keycode == PokeGame.controls.move_down) {
			if (!world.screen.isPaused())
				world.getPlayer().setMovement(true, Direction.DOWN);
		} else if (keycode == PokeGame.controls.move_left) {
			if (!world.screen.isPaused())
				world.getPlayer().setMovement(true, Direction.LEFT);
		} else if (keycode == PokeGame.controls.move_right) {
			if (!world.screen.isPaused())
				world.getPlayer().setMovement(true, Direction.RIGHT);
		} else if (keycode == PokeGame.controls.start_button) {
			world.screen.setPaused();
		}
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch (keycode) {
		case Keys.W:
			world.getPlayer().setMovement(false, Direction.UP);
			break;
		case Keys.S:
			world.getPlayer().setMovement(false, Direction.DOWN);
			break;
		case Keys.A:
			world.getPlayer().setMovement(false, Direction.LEFT);
			break;
		case Keys.D:
			world.getPlayer().setMovement(false, Direction.RIGHT);
			break;
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
