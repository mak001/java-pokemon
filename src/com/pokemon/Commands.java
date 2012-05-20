package com.pokemon;

import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.util.HashMap;

import com.pokemon.loader.Settings;

public class Commands {

	public Commands() {

		{// Settings for DEFAULT_1
			DEFAULT_1.put(Keys.UP, new Integer(KeyEvent.VK_W));
			DEFAULT_1.put(Keys.LEFT, new Integer(KeyEvent.VK_A));
			DEFAULT_1.put(Keys.DOWN, new Integer(KeyEvent.VK_S));
			DEFAULT_1.put(Keys.RIGHT, new Integer(KeyEvent.VK_D));

			DEFAULT_1.put(Keys.A, new Integer(KeyEvent.VK_SPACE));
			DEFAULT_1.put(Keys.B, new Integer(KeyEvent.VK_BACK_SPACE));

			DEFAULT_1.put(Keys.START, new Integer(KeyEvent.VK_SHIFT));
			DEFAULT_1.put(Keys.SELECT, new Integer(KeyEvent.VK_ENTER));
		}

		{// Settings for DEFAULT_2
			DEFAULT_2.put(Keys.UP, new Integer(KeyEvent.VK_RIGHT));
			DEFAULT_2.put(Keys.LEFT, new Integer(KeyEvent.VK_LEFT));
			DEFAULT_2.put(Keys.DOWN, new Integer(KeyEvent.VK_DOWN));
			DEFAULT_2.put(Keys.RIGHT, new Integer(KeyEvent.VK_RIGHT));

			DEFAULT_2.put(Keys.A, new Integer(KeyEvent.VK_SPACE));
			DEFAULT_2.put(Keys.B, new Integer(KeyEvent.VK_BACK_SPACE));

			DEFAULT_2.put(Keys.START, new Integer(KeyEvent.VK_SHIFT));
			DEFAULT_2.put(Keys.SELECT, new Integer(KeyEvent.VK_ENTER));
		}

		CURRENT = DEFAULT_1;
	}

	// A = talk/select
	// B = cancel
	public static enum Keys implements Serializable {
		UP, DOWN, LEFT, RIGHT, A, B, START, SELECT
	}

	public static enum Configuration implements Serializable {
		DEFAULT_1, DEFAULT_2, DEFAULT_3, CUSTOM
	}

	private HashMap<Keys, Integer> CURRENT;

	private HashMap<Keys, Integer> DEFAULT_1 = new HashMap<Keys, Integer>();
	private HashMap<Keys, Integer> DEFAULT_2 = new HashMap<Keys, Integer>();
	private HashMap<Keys, Integer> DEFAULT_3 = new HashMap<Keys, Integer>();
	private HashMap<Keys, Integer> CUSTOM = new HashMap<Keys, Integer>();

	public int getKey(Keys k) {
		return CURRENT.get(k).intValue();
	}

	public void setKey(Keys k, int v) {
		if (CURRENT.equals(DEFAULT_1) || CURRENT.equals(DEFAULT_2)
				|| CURRENT.equals(DEFAULT_3)) {
			// copies the current settings to a custom setting
			CUSTOM = CURRENT;
			// sets the current settings to the custom setting
			CURRENT = CUSTOM;
		}
		CURRENT.put(k, new Integer(v));
	}

	public void setPreDefined(Configuration c) {
		switch (c) {
		case DEFAULT_1:
			CURRENT = DEFAULT_1;
			Settings.put("COMMANDS", "DEFAULT_1");
			break;

		case DEFAULT_2:
			CURRENT = DEFAULT_2;
			Settings.put("COMANDS", "DEFAULT_2");
			break;

		case DEFAULT_3:
			CURRENT = DEFAULT_3;
			Settings.put("COMANDS", "DEFAULT_3");
			break;

		case CUSTOM:
			CURRENT = CUSTOM;
			Settings.put("COMANDS", CURRENT.toString());
			break;
		}
	}

	public HashMap<Keys, Integer> getCurrentConfiguration() {
		return CURRENT;
	}

}
