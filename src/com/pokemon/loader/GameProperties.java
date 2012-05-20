package com.pokemon.loader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class GameProperties {

	private static Properties props = new Properties();

	public static void load() {
		try {
			props.load(new FileInputStream(Settings.userHome + Settings.fileSeparator + "Settings" + Settings.fileSeparator + "Settings.ini"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Properties getProperties() {
			return props;
	}

	public static String getProperty(final String KEY) {
			return props.getProperty(KEY);
	}
}
