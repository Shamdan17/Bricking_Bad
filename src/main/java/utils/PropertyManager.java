package utils;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {

    private static PropertyManager instance;
    private Properties properties;
    final static Logger logger = Logger.getLogger(PropertyManager.class);


    private PropertyManager() {

    }

    public static PropertyManager getInstance() {
        if (instance == null) {
            synchronized (PropertyManager.class) {
                instance = new PropertyManager();
            }
        }
        return instance;
    }

    public void saveProperties() {

        /*OutputStream outputStream = new FileOutputStream()

        try (OutputStream output = new FileOutputStream("path/to/config.properties")) {

            Properties prop = new Properties();

            // set the properties value
            prop.setProperty("db.url", "localhost");
            prop.setProperty("db.user", "mkyong");
            prop.setProperty("db.password", "password");

            // save properties to project root folder
            prop.store(output, null);

            System.out.println(prop);

        } catch (IOException io) {
            io.printStackTrace();
        }*/
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

    public void printProperties() {
        properties.forEach((key, value) -> logger.debug("Key : " + key + ", Value : " + value));
    }
}
