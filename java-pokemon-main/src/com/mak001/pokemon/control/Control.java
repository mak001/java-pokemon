package com.mak001.pokemon.control;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.utils.Json;

public class Control {

	HashMap<GameBoyButton, HashMap<Input, InputValue>> controls;

	public Control() {
		this(new HashMap<GameBoyButton, HashMap<Input, InputValue>>());
	}

	public Control(HashMap<GameBoyButton, HashMap<Input, InputValue>> controls) {
		this.controls = controls;
	}

	public void updateButton(GameBoyButton button, Input input, int value) {
		updateButton(button, input, new InputValue(value));
	}

	public void updateButton(GameBoyButton button, Input input,
			PovDirection value) {
		updateButton(button, input, new InputValue(value));
	}

	public void updateButton(GameBoyButton button, Input input, InputValue value) {
		HashMap<Input, InputValue> inputs = controls.get(button);
		if (inputs == null) {
			inputs = new HashMap<Input, InputValue>();
			controls.put(button, inputs);
		}
		inputs.put(input, value);
	}

	public InputValue getValue(GameBoyButton button, Input input) {
		HashMap<Input, InputValue> inputs = controls.get(button);
		if (inputs.containsKey(input))
			return inputs.get(input);
		return null;
	}

	public void save() {
		Json json = new Json();
		// TODO - change this
		json.toJson(controls, Gdx.files.external("Desktop/controls.json"));
	}

	public enum GameBoyButton {

		A_BUTTON("A Button"), B_BUTTON("B Button"), START_BUTTON("Start Button"), SELECT_BUTTON(
				"Select Button"), D_PAD_UP("D-Pad Up"), D_PAD_RIGHT(
				"D-Pad Right"), D_PAD_DOWN("D-Pad Down"), D_PAD_LEFT(
				"D-Pad Left"), LEFT_BUTTON("Left Button"), RIGHT_BUTTON(
				"Right Button"), POWER("Power Button");

		private String name;

		GameBoyButton(String name) {
			this.name = name;
		}

		public String toString() {
			return name;
		}
	}

	public enum Input {
		KEYBOARD("Keyboard"), TOUCH_AND_ACCELEROMETER("Touch pad"), XBOX_CONTROLLER(
				"XBox Controller"), XPERIA_GAMEPAD("Xperia Gamepad");

		private String name;

		Input(String name) {
			this.name = name;
		}

		public String toString() {
			return name;
		}
	}

	public enum InputValueType {
		POVDirection, INT;
	}

	public class InputValue {

		private int id = -1;
		private PovDirection direction = null;
		private InputValueType type;

		public InputValue(int id) {
			this.id = id;
			this.type = InputValueType.INT;
		}

		public InputValue(PovDirection direction) {
			this.direction = direction;
			this.type = InputValueType.POVDirection;
		}

		public int getID() {
			return id;
		}

		public PovDirection getDirection() {
			return direction;
		}

		public InputValueType getType() {
			return type;
		}
	}
}
