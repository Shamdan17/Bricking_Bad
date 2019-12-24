package utils;

import org.apache.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertyManager {

    private static PropertyManager instance;
    private Properties properties;
    final static Logger logger = Logger.getLogger(PropertyManager.class);


    private PropertyManager() {
        loadProperties();
    }

    public static PropertyManager getInstance() {
        if (instance == null) {
            synchronized (PropertyManager.class) {
                instance = new PropertyManager();
            }
        }
        return instance;
    }

    public void saveProperties() throws IOException {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String newGamePropertiesFile = rootPath + "game.properties";
        properties.store(new FileWriter(newGamePropertiesFile), "store to properties file");

    }

    public void updateProperty(String key, String value) {
        properties.setProperty(key, value);
    }

    public void loadProperties() {

        try (InputStream input = PropertyManager.class.getClassLoader().getResourceAsStream("game.properties")) {

            properties = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find game.properties");
                return;
            }
            // load a properties file
            properties.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getProperty(String key, String def) {
        return properties.getProperty(key, def);
    }

    public void printProperties() {
        properties.forEach((key, value) -> logger.debug("Key : " + key + ", Value : " + value));
    }
}
