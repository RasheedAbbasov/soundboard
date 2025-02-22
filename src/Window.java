import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;


public class Window extends JFrame{

    private Scanner scnr;
    private static String filename = "button.txt";
    private String soundName;
    private JPanel contentPane = new JPanel();
    private ImageIcon Icon = new ImageIcon("soundboard.png");
    private JLabel label = new JLabel("Test");
    private JButton soundButton1 = new JButton("sound1");
    private JButton soundButton2 = new JButton("sound2");
    private JButton importButton = new JButton("Import");
    private JButton deleteButton = new JButton("Delete");
    private JButton editButton = new JButton("Edit");



    /**
     * Constructor
     */
    public Window() {

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

        //Creates Center and North sub panels
        JPanel panelcenter = new JPanel();
        JPanel panelnorth = new JPanel();


        //Sets the size of the buttons
        soundButton1.setPreferredSize(new Dimension(200,50));
        soundButton2.setPreferredSize(new Dimension(200, 50));
        importButton.setPreferredSize(new Dimension(100,50));
        deleteButton.setPreferredSize(new Dimension(100,50));
        editButton.setPreferredSize(new Dimension(100, 50));


        // makeButtonRound(soundButton1);
        // makeButtonRound(Button2);
        // makeButtonRound(soundButton2);
        
        
        //Sets the size and the color of the panels
        panelcenter.setPreferredSize(new Dimension(500,500));
        panelcenter.setBackground(Color.LIGHT_GRAY);
        panelnorth.setPreferredSize(new Dimension(500, 100));
        panelnorth.setBackground(Color.BLUE);

        //Sets a new grid layout for sub panels
        panelcenter.setLayout(new FlowLayout());
        panelnorth.setLayout(new FlowLayout());
        

        //Adds elements to the sub panels
        panelcenter.add(soundButton1);
        panelcenter.add(soundButton2);
        panelnorth.add(importButton);
        panelnorth.add(deleteButton);
        panelnorth.add(editButton);

;

        
        //adds the subpanels to the main panel
        contentPane.add(panelnorth, BorderLayout.NORTH);
        contentPane.add(panelcenter, FlowLayout.CENTER);
    }

    private JButton makeButton(String name) {

        return new JButton("name");

    }

    private ArrayList<String> readData() {
        ArrayList<String> buttonName = new ArrayList<>();
        return buttonName;
    }

    private void makeButtonRound(JButton button) {
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorder(new RoundedBorder(30)); //Set rounded corners with 30 px radius
        
    }

    class RoundedBorder implements Border {
        
        private int radius;


        public RoundedBorder (int radius) {
            this.radius = radius;
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 5, this.radius + 5, this.radius + 5, this.radius + 5);
        }

        @Override
        public boolean isBorderOpaque() {
            return true;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
             //Background Color
            g.fillRoundRect(x, y, width -1, height - 1, radius, radius);
        }
        
    }


    //Sets the user set a custom name for a button
    public void setSoundName(String name) {
        this.soundName = name;
    }

}
