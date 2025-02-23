import java.io.*;
import java.util.*;


public class SoundManager {
    private static final String FILE_NAME = "sounds.properties";
    private Properties properties;



    public SoundManager() {
        properties = new Properties();
        loadProperties();

    }

    public Map<String, String> getAllSounds() {
        Map<String, String> sounds = new HashMap<>();
        for (String key: properties.stringPropertyNames()) {
            sounds.put(key, properties.getProperty(key));
        }

        return sounds;
    }



    private void loadProperties() {
        try(FileInputStream fls = new FileInputStream(FILE_NAME)) {
            properties.load(fls);

        }catch (IOException e) {
            System.out.println("No sound file found");
        }
    }



    private void addSound(String soundName, String filePath) {
        properties.setProperty(soundName, filePath);
        saveProperties();
        
    }


    private void removeSound(String soundName) {
        properties.remove(soundName);
        saveProperties();
    }



    private void saveProperties() {
        try(FileOutputStream fos = new FileOutputStream(FILE_NAME)) {
            properties.store(fos, "Saved Sound");
        } catch (IOException e ) {
            e.printStackTrace();
        }
    }

}
