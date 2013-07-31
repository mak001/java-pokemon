package com.mak001.pokemon.control.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;

public class Control {

	// D-pad buttons
	public int move_up;
	public int move_down;
	public int move_left;
	public int move_right;

	// Game-boy specific buttons
	public int start_button;
	public int select_button;
	public int a_button;
	public int b_button;

	// Top buttons
	public int right_button;
	public int left_button;

	public enum Input {
		KEYBOARD, TOUCH_AND_ACCELEROMETER, CONTROLLER
	}

	public Control() {
	}

	public Control(int m_up, int m_down, int m_left, int m_right,
			int start_but, int select_but, int a_but, int b_but, int r_button,
			int l_button) {
		move_up = m_up;
		move_down = m_down;
		move_left = m_left;
		move_right = m_right;

		// Game-boy specific buttons
		start_button = start_but;
		select_button = select_but;
		a_button = a_but;
		b_button = b_but;
		right_button = r_button;
		left_button = l_button;
	}

	public void save() {
		Json json = new Json();
		json.toJson(this, Gdx.files.external("Desktop/controls.json"));
	}

}
