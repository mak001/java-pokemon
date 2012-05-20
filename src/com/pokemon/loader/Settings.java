package com.pokemon.loader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.pokemon.Commands.Keys;

public class Settings {

	private static File file;
	private static BufferedWriter writer;
	public static String lineSeparator, fileSeparator, userHome;

	private static HashMap<String, String> settings = new HashMap<String, String>();
	private static HashMap<String, HashMap<Keys, Integer>> commands = new HashMap<String, HashMap<Keys, Integer>>();

	public static boolean init() {
		lineSeparator = System.getProperty("line.separator");
		fileSeparator = System.getProperty("file.separator");
		userHome = System.getProperty("user.home") + fileSeparator + "Pokemon";

		if (!(file = new File(userHome)).exists()) {
			file.mkdirs();
		}

		if (!(file = new File(userHome + fileSeparator + "Rooms")).exists()) {
			file.mkdirs();
			updateRooms();
		}

		if (!(file = new File(userHome + fileSeparator + "Screenshots"))
				.exists()) {
			file.mkdirs();
		}

		if (!(file = new File(userHome + fileSeparator + "Settings")).exists()) {
			file.mkdirs();
		}

		if (!(file = new File(userHome + fileSeparator + "Settings"
				+ fileSeparator + "Settings.ini")).exists()) {
			put("COMMANDS", "DEFAULT_1");
			update();
		}

		if (!(file = new File(userHome + fileSeparator
				+ "Java-Pokemon GitHub.url")).exists()) {
			try {
				writer = new BufferedWriter(new FileWriter(file));
				writer.write("[{000214A0-0000-0000-C000-000000000046}]\n"
						+ "Prop3=19,2\n" + "[InternetShortcut]\n"
						+ "URL=https://github.com/mak001/java-pokemon\n"
						+ "IDList=\n" + "");
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	private static void updateRooms() {
		getZipFiles("");
	}

	public static String get(final String KEY) {
		return settings.get(KEY);
	}

	public static HashMap<Keys, Integer> getCommands(final String KEY) {
		return commands.get(KEY);
	}

	public static int getCommandKeys(final Keys KEY) {
		return commands.get("COMMANDS").get(KEY).intValue();
	}

	public static void put(final String KEY, final String VALUE) {
		settings.put(KEY, VALUE);
	}

	protected static void update() {
		try {
			writer = new BufferedWriter(new FileWriter(new File(userHome
					+ fileSeparator + "Settings" + fileSeparator
					+ "Settings.ini")));

			writer.write("KEYNAME=" + get("KEYNAME") + "\n");
			// TODO
			writer.close();
			GameProperties.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected static void updateCommands() {
		try {
			writer = new BufferedWriter(new FileWriter(new File(userHome
					+ fileSeparator + "Settings" + fileSeparator
					+ "Commands.ini")));
			writer.write("COMMANDS=" + getCommands("COMMANDS") + lineSeparator
					+ lineSeparator);
			// TODO
			writer.close();
			GameProperties.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void getZipFiles(String fileurl) {
		try {
			String destinationname = userHome + fileSeparator + "Rooms"
					+ fileSeparator;
			byte[] buf = new byte[1024];
			ZipInputStream zipinputstream = null;
			ZipEntry zipentry;
			zipinputstream = new ZipInputStream(new URL(fileurl).openStream());

			zipentry = zipinputstream.getNextEntry();
			while (zipentry != null) {
				// for each entry to be extracted
				String entryName = zipentry.getName();
				System.out.println("entryname " + entryName);
				int n;
				FileOutputStream fileoutputstream;
				File newFile = new File(entryName);
				String directory = newFile.getParent();

				if (directory == null) {
					if (newFile.isDirectory())
						break;
				}

				fileoutputstream = new FileOutputStream(destinationname
						+ entryName);

				while ((n = zipinputstream.read(buf, 0, 1024)) > -1)
					fileoutputstream.write(buf, 0, n);

				fileoutputstream.close();
				zipinputstream.closeEntry();
				zipentry = zipinputstream.getNextEntry();

			}// while
			zipinputstream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
