import java.awt.*;
import java.io.File;
import java.util.*;
import javax.swing.*;

public class Window extends JFrame {

    private Scanner scnr;
    private static String filename = "button.txt";
    private String soundName;
    private JPanel contentPane = new JPanel();
    private JPanel panelCenter = new JPanel();
    private ImageIcon Icon = new ImageIcon("soundboard.png");
    private final JButton importButton = new JButton("Import");
    private final JButton deleteButton = new JButton("Delete");
    private final JButton editButton = new JButton("Edit");
    private SoundManager soundManager;

    /**
     * Constructor
     */
    public Window() {


        //Initializing sound manager
        soundManager = new SoundManager();

        //Sets the properties 
        this.setTitle("Rasho's SoundBoard");
        this.setSize(500,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(Icon.getImage());

        //Sets the frame to full screen
        // this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        //Hides the title when set to full screen
        // this.setUndecorated(true);

        //Set the custom layout
        customLayout();

        //Loading the saved sounds
        loadSavedSounds();

        importButton.addActionListener(e -> addNewSound());
        deleteButton.addActionListener(e -> removeSelectedSound());
        

        //Set the contentPane to the frame
        this.setContentPane(contentPane);

        //Makes the frame visible (Always set at the end)
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

        ;

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
     * Takes in the name and the location of the sound and stores it in a hashmap
     * and adds it to the center panel
     * 
     * 
     * @param soundName the name of the sounds that is going to be added
     * @param filePath  the location of the sounds
     */
    private void addSoundButton(String soundName, String filePath) {
        JButton soundButton = new JButton(soundName);
        soundButton.setPreferredSize(new Dimension(150, 50));
        panelCenter.add(soundButton);
        panelCenter.revalidate();
        panelCenter.repaint();
    }


    /**
     * Adds a new sound
     */
    private void addNewSound() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(this);

        if(returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String soundName = selectedFile.getName();
            String filePath = selectedFile.getAbsolutePath();


            //Saves it to the properties 
            soundManager.addSound(soundName, filePath);


            //Adds a button to the ui
            addSoundButton(soundName, filePath); 
        }
    }




    private  void removeSelectedSound() {
        Component[] components = panelCenter.getComponents();

        if(components.length > 0) {
            JButton buttonToRemove  = (JButton) components[components.length - 1];
            panelCenter.remove(buttonToRemove);
            panelCenter.revalidate();
            panelCenter.repaint();



            //Removes from properties 
            soundManager.removeSound(buttonToRemove.getText());
        }

    }
        
    }

    // private void makeButtonRound(JButton button) {
    // button.setFocusPainted(false);
    // button.setContentAreaFilled(false);
    // button.setBorder(new RoundedBorder(30)); //Set rounded corners with 30 px
    // radius

    // }

    // class RoundedBorder implements Border {

    // private int radius;

    // public RoundedBorder (int radius) {
    // this.radius = radius;
    // }

    // @Override
    // public Insets getBorderInsets(Component c) {
    // return new Insets(this.radius + 5, this.radius + 5, this.radius + 5,
    // this.radius + 5);
    // }

    // @Override
    // public boolean isBorderOpaque() {
    // return true;
    // }

    // @Override
    // public void paintBorder(Component c, Graphics g, int x, int y, int width, int
    // height) {
    // //Background Color
    // g.fillRoundRect(x, y, width -1, height - 1, radius, radius);
    // }

    // }

    // //Sets the user set a custom name for a button
    // public void setSoundName(String name) {
    // this.soundName = name;
    // }


