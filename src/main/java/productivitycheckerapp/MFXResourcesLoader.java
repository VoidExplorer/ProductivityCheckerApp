package productivitycheckerapp;

import java.io.InputStream;
import java.net.URL;

/**
 * Utility class which manages the access to this project's assets.
 * Helps keeping the assets files structure organized.
 */
public class MFXResourcesLoader {

	private MFXResourcesLoader() {
	}

	public static URL loadURL(String path) {
		return MFXResourcesLoader.class.getResource(path);
	}

	public static String load(String path) {
		return loadURL(path).toString();
	}

	public static InputStream loadStream(String name) {
		return MFXResourcesLoader.class.getResourceAsStream(name);
	}

}
