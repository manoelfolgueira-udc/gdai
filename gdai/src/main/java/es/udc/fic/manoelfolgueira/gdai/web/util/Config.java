package es.udc.fic.manoelfolgueira.gdai.web.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
	
	public static final String ADMINISTRATORS_GROUP_NAME = "ADMINISTRATORS_GROUP_NAME";
	private static final String configFilePath = "src/main/resources/es/udc/fic/manoelfolgueira/gdai/model/config.properties";

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

				input = new FileInputStream(Config.configFilePath);
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