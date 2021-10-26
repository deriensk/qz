package fr.epita.quiz.services.conf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Configuration {

    private static Configuration configurationInstance;

    private final Properties properties;

    public static Configuration getInstance(){
        if (configurationInstance == null){
            try{
                configurationInstance = new Configuration();
            } catch (Exception e){
                //TODO improve exceptional handling
                e.printStackTrace();
            }

        }
        return configurationInstance;
    }

    private  Configuration() throws IOException {
        this.properties = new Properties();
        properties.load(new FileInputStream(new File("conf.properties")));
    }

    public String getConfValue(String entry) {
        // to be added later
        return entry;

    }

}
