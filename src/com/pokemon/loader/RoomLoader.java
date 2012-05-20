package com.pokemon.loader;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map.Entry;

import com.pokemon.room.Room;

public class RoomLoader {

	HashMap<String, Room> rooms = new HashMap<String, Room>();

	public RoomLoader() {
		String filePath = Settings.userHome + Settings.fileSeparator + "Maps"
				+ Settings.fileSeparator;

		File[] files = getMaps(filePath);
		for (File file : files) {
			try {
				// Open file to read from, in this case a map file
				FileInputStream mapFile = new FileInputStream(file);

				// Create an ObjectInputStream to get objects from map file.
				ObjectInputStream map = new ObjectInputStream(mapFile);

				// Now we do the restore.
				// readObject() returns a generic Object, we cast those back
				// into their original class type.
				// For primitive types, use the corresponding reference class.

				rooms.put(file.getName(), (Room) map.readObject());

				// Close the file.
				mapFile.close();
				map.close(); // This also closes saveFile.
			} catch (Exception exc) {
				exc.printStackTrace(); // If there was an error, print the info.
			}
		}
	}

	public Room getRoom(String name) {
		return rooms.get(name);
	}

	public String getRoomName(Room r) {
		for (Entry<String, Room> entry : rooms.entrySet()) {
			if (r.equals(entry.getValue())) {
				return entry.getKey();
			}
		}
		return "No room found.";
	}

	public File[] getMaps(String path) {
		File dir = new File(path);

		// This filter does not returns directories
		FileFilter fileFilter = new FileFilter() {
			public boolean accept(File file) {
				return !file.isDirectory();
			}
		};

		// The list of files can also be retrieved as File objects
		return dir.listFiles(fileFilter);
	}

}
