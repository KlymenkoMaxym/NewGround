package Util;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesManager {

    private Properties property;

    private PropertiesManager() {

        InputStream stream = getClass().getClassLoader().getResourceAsStream("config.properties");
        property = new Properties();

        try {
            property.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static final PropertiesManager INSTANCE = new PropertiesManager();

    public static PropertiesManager getInstance() {
        return INSTANCE;
    }

    public String getResourceByName(String name) {
        return property.getProperty(name);
    }

}

