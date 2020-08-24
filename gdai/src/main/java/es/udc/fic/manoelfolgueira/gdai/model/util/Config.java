package es.udc.fic.manoelfolgueira.gdai.model.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * A bare class which handles some app general configuration via a properties
 * file
 */
public class Config {

	private static Config instance = null;

	private Properties properties = null;

	protected Config() {
		// Exists only to defeat instantiation.
	}

	public static Config getInstance() {
		if (instance == null) {
			instance = new Config();
			instance.properties = new Properties();
			InputStream input = null;

			try {

				input = new FileInputStream(ConfigPropertyKeys.CONFIG_FILE_PATH);
				instance.properties.load(input);
			} catch (IOException ex) {
				ex.printStackTrace();
			} finally {
				if (input != null) {
					try {
						input.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		}
		return instance;
	}

	public Properties getProperties() {
		return this.properties;
	}

}