package com.mak001.pokemon.control;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;

public class Control {

	HashMap<GameBoyButton, HashMap<Input, Integer>> controls = new HashMap<GameBoyButton, HashMap<Input, Integer>>();

	public Control() {
		this(new HashMap<GameBoyButton, HashMap<Input, Integer>>());
	}

	public Control(HashMap<GameBoyButton, HashMap<Input, Integer>> controls) {
		this.controls = controls;
	}

	public void updateButton(GameBoyButton button, Input input, int value) {
		HashMap<Input, Integer> inputs = controls.get(button);
		if (inputs == null) {
			inputs = new HashMap<Input, Integer>();
			controls.put(button, inputs);
		}
		inputs.put(input, value);
	}

	public int getValue(GameBoyButton button, Input input) {
		HashMap<Input, Integer> inputs = controls.get(button);
		if (inputs.containsKey(input))
			return inputs.get(input);
		return -1;
	}

	public void save() {
		Json json = new Json();
		// TODO - change this
		json.toJson(controls, Gdx.files.external("Desktop/controls.json"));
	}

	public enum GameBoyButton {

		A_BUTTON(0, "A Button"), B_BUTTON(1, "B Button"), START_BUTTON(2,
				"Start Button"), SELECT_BUTTON(3, "Select Button"), D_PAD_UP(4,
				"D-Pad Up"), D_PAD_RIGHT(5, "D-Pad Right"), D_PAD_DOWN(6,
				"D-Pad Down"), D_PAD_LEFT(7, "D-Pad Left"), LEFT_BUTTON(8,
				"Left Button"), RIGHT_BUTTON(9, "Right Button"), POWER(10,
				"Power Button");

		private int id;
		private String name;

		GameBoyButton(int id, String name) {
			this.id = id;
			this.name = name;
		}

		public int getID() {// TODO - determine if needed
			return id;
		}

		public String toString() {
			return name;
		}
	}

	public enum Input {
		KEYBOARD("Keyboard"), TOUCH_AND_ACCELEROMETER("Touch pad"), CONTROLLER(
				"Controller");

		private String name;

		Input(String name) {
			this.name = name;
		}

		public String toString() {
			return name;
		}
	}
}
