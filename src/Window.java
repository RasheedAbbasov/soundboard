import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import javax.sound.sampled.*;
import javax.swing.*;

public class Window extends JFrame {

    private JPanel contentPane = new JPanel();
    private JPanel panelCenter = new JPanel();
    private ImageIcon Icon = new ImageIcon("soundboard.png");
    private final JButton importButton = new JButton("Import");
    private final JButton deleteButton = new JButton("Delete");
    private final JButton editButton = new JButton("Edit");
    private boolean deleteMode;
    private SoundManager soundManager;

    /**
     * Constructor
     */
    public Window() {

        // Initializing sound manager
        soundManager = new SoundManager();

        // Sets the properties
        this.setTitle("Rasho's SoundBoard");
        this.setSize(500, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(Icon.getImage());
        this.setResizable(false);

        // Sets the frame to full screen
        // this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Hides the title when set to full screen
        // this.setUndecorated(true);

        // Set the custom layout
        customLayout();

        // Loading the saved sounds
        loadSavedSounds();

        importButton.addActionListener(e -> addNewSound());
        deleteButton.addActionListener(e ->  {
            deleteMode = true;
            System.out.println(deleteMode);
            if(deleteMode) {
                removeSelectedSound();
            }
            System.out.println(deleteMode);


            


            
        });

        // Set the contentPane to the frame
        this.setContentPane(contentPane);

        // Makes the frame visible (Always set at the end)
        this.setVisible(true);
    }

    /**
     * Sets the layout of the soundboard
     */
    private void customLayout() {
        this.contentPane.setLayout(new FlowLayout());

        // Creates Center and North sub panels
        JPanel panelnorth = new JPanel();

        // Sets the size of the buttons
        importButton.setPreferredSize(new Dimension(100, 50));
        deleteButton.setPreferredSize(new Dimension(100, 50));
        editButton.setPreferredSize(new Dimension(100, 50));

        // makeButtonRound(soundButton1);
        // makeButtonRound(Button2);
        // makeButtonRound(soundButton2);

        // Sets the size and the color of the panels
        panelCenter.setPreferredSize(new Dimension(500, 500));
        panelCenter.setBackground(Color.LIGHT_GRAY);
        panelnorth.setPreferredSize(new Dimension(500, 100));
        panelnorth.setBackground(Color.BLUE);

        // Sets a new grid layout for sub panels
        panelCenter.setLayout(new FlowLayout());
        panelnorth.setLayout(new FlowLayout());

        // Adds elements to the sub panels
        panelnorth.add(importButton);
        panelnorth.add(deleteButton);
        panelnorth.add(editButton);

        // adds the subpanels to the main panel
        contentPane.add(panelnorth, BorderLayout.NORTH);
        contentPane.add(panelCenter, FlowLayout.CENTER);
    }

    /**
     * Loads the saved sounds from properties and adds them as buttons
     */
    private void loadSavedSounds() {
        Map<String, String> sounds = soundManager.getAllSounds();
        for (String soundName : sounds.keySet()) {
            addSoundButton(soundName, sounds.get(soundName));
        }
    }

    /**
     * Takes in the name and location of the file and sets it to the button
     * and adds it to the center panel
     * 
     * 
     * @param soundName the name of the sounds that is going to be added
     * @param filePath  the location of the sounds
     */
    private void addSoundButton(String soundName, String filePath) {

        JButton soundButton = new JButton(soundName);
        soundButton.setPreferredSize(new Dimension(150, 50));
        String fileType = getFileType(filePath);

        if (fileType.equalsIgnoreCase("audio/wav")) {

                soundButton.addActionListener(e -> {    
                    if(!deleteMode) {
                        playSound(filePath);
                    }
                });
                panelCenter.add(soundButton);
                panelCenter.revalidate();
                panelCenter.repaint();
        }
    }

    /**
     * Implemets a method to play the audio file
     * Added in a actionListener
     * 
     * @param filePath Location of the file
     */
    private void playSound(String filePath) {

        File soundFile = new File(filePath);
        if(!deleteMode) {
            try {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
    
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
    
                clip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Gets the type of file
     * 
     * @param filePath the path of the file
     * @return retuns a string on the type of file
     */
    private String getFileType(String filePath) {
        Path fileLocation = Paths.get(filePath);
        String fileType = "";

        try {
            fileType = Files.probeContentType(fileLocation);
            System.out.println("File type: " + fileType); // e.g., "text/plain", "image/jpeg"
            return fileType;
        } catch (IOException e) {
            System.err.println("Error probing file type: " + e.getMessage());
        }
        return "";

    }

    /**
     * Opens file explored and lets the user choose a song and adds it
     * Also checks if the file type is a .wav file since that is the only one
     * supported
     */
    private void addNewSound() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(this);
        String fileType = "";

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            if (getFileType(selectedFile.toString()).equalsIgnoreCase("audio/wav")) {
                String soundName = selectedFile.getName();
                String filePath = selectedFile.getAbsolutePath();

                // Saves it to the properties
                soundManager.addSound(soundName, filePath);

                // Adds a button to the ui
                addSoundButton(soundName, filePath);
            }
        }
    }


    
    /**
     * Implements the delete action
     */
    private void removeSelectedSound() {
    if (!deleteMode) return;

    for (Component component : panelCenter.getComponents()) {
        if (component instanceof JButton soundButton) {
            // Remove existing ActionListeners
            for (ActionListener al : soundButton.getActionListeners()) {
                soundButton.removeActionListener(al);
            }

            // Set new ActionListener to delete the button
            soundButton.addActionListener(e -> {
                panelCenter.remove(soundButton);
                panelCenter.revalidate();
                panelCenter.repaint();

                soundManager.removeSound(soundButton.getText());
                deleteButton.setText("Delete");
                deleteMode = false;
            });
        }
    }
        




    }

}