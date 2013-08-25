package com.mak001.pokemon.control.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mak001.pokemon.PokeGame;
import com.mak001.pokemon.control.AccelerometerListener;
import com.mak001.pokemon.control.Control.GameBoyButton;
import com.mak001.pokemon.control.Control.Input;
import com.mak001.pokemon.control.controllers.XperiaGamepad;
import com.mak001.pokemon.screens.GameScreen;
import com.mak001.pokemon.screens.huds.AbstractHud;
import com.mak001.pokemon.screens.huds.ControllerHud;
import com.mak001.pokemon.screens.huds.PauseHud;
import com.mak001.pokemon.world.World;
import com.mak001.pokemon.world.entity.Direction;
import com.mak001.pokemon.world.entity.Entity;
import com.mak001.pokemon.world.entity.Speed;

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
				dPadUp(true);
			} else if (keycode == PokeGame.controls.getValue(
					GameBoyButton.D_PAD_DOWN, Input.KEYBOARD).getID()) {
				dPadDown(true);
			} else if (keycode == PokeGame.controls.getValue(
					GameBoyButton.D_PAD_LEFT, Input.KEYBOARD).getID()) {
				dPadLeft(true);
			} else if (keycode == PokeGame.controls.getValue(
					GameBoyButton.D_PAD_RIGHT, Input.KEYBOARD).getID()) {
				dPadRight(true);
			} else if (keycode == PokeGame.controls.getValue(
					GameBoyButton.START_BUTTON, Input.KEYBOARD).getID()) {
				startButton();
			} else if (keycode == PokeGame.controls.getValue(
					GameBoyButton.SELECT_BUTTON, Input.KEYBOARD).getID()) {
				selectButton(true);
			} else if (keycode == PokeGame.controls.getValue(
					GameBoyButton.A_BUTTON, Input.KEYBOARD).getID()) {
				aButton(true);
			} else if (keycode == PokeGame.controls.getValue(
					GameBoyButton.B_BUTTON, Input.KEYBOARD).getID()) {
				bButton(true);
			} else if (keycode == PokeGame.controls.getValue(
					GameBoyButton.LEFT_BUTTON, Input.KEYBOARD).getID()) {
				leftButton(true);
			} else if (keycode == PokeGame.controls.getValue(
					GameBoyButton.RIGHT_BUTTON, Input.KEYBOARD).getID()) {
				rightButton(true);
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
				dPadUp(false);
			} else if (keycode == PokeGame.controls.getValue(
					GameBoyButton.D_PAD_DOWN, Input.KEYBOARD).getID()) {
				dPadDown(false);
			} else if (keycode == PokeGame.controls.getValue(
					GameBoyButton.D_PAD_LEFT, Input.KEYBOARD).getID()) {
				dPadLeft(false);
			} else if (keycode == PokeGame.controls.getValue(
					GameBoyButton.D_PAD_RIGHT, Input.KEYBOARD).getID()) {
				dPadRight(false);
			} else if (keycode == PokeGame.controls.getValue(
					GameBoyButton.SELECT_BUTTON, Input.KEYBOARD).getID()) {
				selectButton(false);
			} else if (keycode == PokeGame.controls.getValue(
					GameBoyButton.A_BUTTON, Input.KEYBOARD).getID()) {
				aButton(false);
			} else if (keycode == PokeGame.controls.getValue(
					GameBoyButton.B_BUTTON, Input.KEYBOARD).getID()) {
				bButton(false);
			} else if (keycode == PokeGame.controls.getValue(
					GameBoyButton.LEFT_BUTTON, Input.KEYBOARD).getID()) {
				leftButton(false);
			} else if (keycode == PokeGame.controls.getValue(
					GameBoyButton.RIGHT_BUTTON, Input.KEYBOARD).getID()) {
				rightButton(false);
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
		world.screen.addHud(new ControllerHud(controller, world.screen, 500,
				true));
	}

	@Override
	public void disconnected(Controller controller) {
		world.screen.addHud(new ControllerHud(controller, world.screen, 500,
				false));
	}

	@Override
	public boolean buttonDown(Controller controller, int buttonIndex) {
		if (getControllerType(controller).equals(Input.XBOX_CONTROLLER)) {

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
				if (value.equals(PokeGame.controls.getValue(
						GameBoyButton.D_PAD_UP, Input.XBOX_CONTROLLER)
						.getDirection())) {
					dPadUp(true);
				} else if (value.equals(PokeGame.controls.getValue(
						GameBoyButton.D_PAD_DOWN, Input.XBOX_CONTROLLER)
						.getDirection())) {
					dPadDown(true);
				} else if (value.equals(PokeGame.controls.getValue(
						GameBoyButton.D_PAD_LEFT, Input.XBOX_CONTROLLER)
						.getDirection())) {
					dPadLeft(true);
				} else if (value.equals(PokeGame.controls.getValue(
						GameBoyButton.D_PAD_RIGHT, Input.XBOX_CONTROLLER)
						.getDirection())) {
					dPadRight(true);
				} else if (value.equals(PovDirection.center)) {
					dPadUp(false);
					dPadDown(false);
					dPadLeft(false);
					dPadRight(false);
				}
				// TODO Auto-generated method stub
			}
		}
		return true;
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

	/*
	 * Performs the gameboy button equivalent
	 */

	private void dPadUp(boolean pressed) {
		if (!world.screen.isPaused()) {
			world.getPlayer().setMovement(pressed, Direction.UP);
		} else {
			AbstractHud hud = world.screen.getHuds().get(GameScreen.PAUSE_HUD);
			if (hud instanceof PauseHud) {
				((PauseHud) hud).moveSelectionUp();
			}
		}
	}

	private void dPadDown(boolean pressed) {
		if (!world.screen.isPaused()) {
			world.getPlayer().setMovement(pressed, Direction.DOWN);
		} else {
			AbstractHud hud = world.screen.getHuds().get(GameScreen.PAUSE_HUD);
			if (hud instanceof PauseHud) {
				((PauseHud) hud).moveSelectionDown();
			}
		}
	}

	private void dPadLeft(boolean pressed) {
		if (!world.screen.isPaused()) {
			world.getPlayer().setMovement(pressed, Direction.LEFT);
		} else {
			// TODO
		}
	}

	private void dPadRight(boolean pressed) {
		if (!world.screen.isPaused()) {
			world.getPlayer().setMovement(pressed, Direction.RIGHT);
		} else {
			// TODO
		}
	}

	private void aButton(boolean pressed) {
		if (!world.screen.isPaused()) {
			if (pressed) {
				Vector2 vec = world.getPlayer().getPosition(
						world.getPlayer().getDirection());
				for (Entity npc : world.getEntities()) {
					if (npc.getPosition().x == vec.x
							&& npc.getPosition().y == vec.y) {
						npc.setTalking(true);
					}
				}
			}
		} else {
			if (pressed) {
				AbstractHud hud = world.screen.getHuds().get(
						GameScreen.PAUSE_HUD);
				if (hud instanceof PauseHud) {
					((PauseHud) hud).selectSelection();
				}
			}
		}
	}

	private void bButton(boolean pressed) {

		if (world.screen.isPaused()) {// pause screen
			if (pressed) {
				AbstractHud hud = world.screen.getHuds().get(
						GameScreen.PAUSE_HUD);
				if (hud instanceof PauseHud) {
					((PauseHud) hud).cancel();
				}
			}
		} else if (world.getPlayer().isTalking()) {
			if (pressed) {
				Vector2 vec = world.getPlayer().getPosition(
						world.getPlayer().getDirection());
				for (Entity npc : world.getEntities()) {
					if (npc.getPosition().x == vec.x
							&& npc.getPosition().y == vec.y) {
						npc.setTalking(false);
					}
				}
			}
		} else {
			if (pressed) {
				world.getPlayer().setSpeed(Speed.RUNNING);
			} else {
				world.getPlayer().setSpeed(Speed.WALKING);
			}
		}

	}

	private void startButton() {
		world.screen.setPaused();
	}

	private void selectButton(boolean pressed) {

	}

	private void rightButton(boolean pressed) {

	}

	private void leftButton(boolean pressed) {

	}

}
