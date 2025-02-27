import java.io.*;
import java.util.*;

public class SoundManager {
    private static final String FILE_NAME = "sounds.properties";
    private Properties properties;

    public SoundManager() {
        properties = new Properties();
        loadProperties();

    }

    /**
     * Gets all the sounds from the property file
     * 
     * @return returns a hashmap with the name and the location as Strings
     */
    public Map<String, String> getAllSounds() {
        Map<String, String> sounds = new HashMap<>();
        for (String key : properties.stringPropertyNames()) {
            sounds.put(key, properties.getProperty(key));
        }

        return sounds;
    }

    /**
     * Loads the properties of
     */
    private void loadProperties() {
        try (FileInputStream fls = new FileInputStream(FILE_NAME)) {
            properties.load(fls);

        } catch (IOException e) {
            System.out.println("No sound file found");
        }
    }

    /**
     * Adds a sounds to the properties
     * 
     * @param soundName the name of the file
     * @param filePath  the location of the file
     */
    public void addSound(String soundName, String filePath) {
        properties.setProperty(soundName, filePath);
        saveProperties();

    }

    /**
     * removes the sound from the sounds.properties
     * 
     * @param soundName the name of the sound
     */
    public void removeSound(String soundName) {
        properties.remove(soundName);
        saveProperties();
    }

    /**
     * saves the properties used for after removing and adding
     */
    private void saveProperties() {
        try (FileOutputStream fos = new FileOutputStream(FILE_NAME)) {
            properties.store(fos, "Saved Sound");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
