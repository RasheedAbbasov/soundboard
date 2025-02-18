import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.*;
import javax.swing.border.*;


public class Window extends JFrame{


    private String soundName;
    private JPanel contentPane = new JPanel();
    private ImageIcon Icon = new ImageIcon("soundboard.png");
    private JLabel label = new JLabel("Test");
    private JButton soundButton1 = new JButton("1");
    private JButton soundButton2 = new JButton("2");
    private JButton Button2 = new JButton("Place Holder");



    /**
     * Constructor
     */
    public Window() {

        //Sets the properties 
        this.setTitle("Rasho's SoundBoard");
        this.setSize(1920,1080);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(Icon.getImage());

        //Sets the frame to full screen
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        //Hides the title when set to full screen
        this.setUndecorated(true);

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
        this.contentPane.setLayout(new BorderLayout());

        //Creates Center and North sub panels
        JPanel panelcenter = new JPanel();
        JPanel panelnorth = new JPanel();
        JPanel paneleast = new JPanel();
        JPanel panelwest = new JPanel();
        JPanel panelsouth = new JPanel();


        //Sets the size of the button
        soundButton1.setPreferredSize(new Dimension(200,50));
        Button2.setPreferredSize(new Dimension(200,50));

        makeButtonRound(soundButton1);
        makeButtonRound(Button2);
        makeButtonRound(soundButton2);
        
        
        //Sets the size of the panels 
        panelcenter.setPreferredSize(new Dimension(300,100));
        panelnorth.setPreferredSize(new Dimension(300, 100));
        paneleast.setPreferredSize(new Dimension(300, 100));
        panelwest.setPreferredSize(new Dimension(300, 100));
        panelsouth.setPreferredSize(new Dimension(300 ,100));

        //Sets a new grid layout for center panel
        panelcenter.setLayout(new GridLayout(2,2,2,10));
        

        //Adds elements to the sub panels
        panelcenter.add(this.soundButton1);
        panelcenter.add(this.soundButton2);
        panelnorth.add(this.Button2);

        
        //adds the subpanels to the main panel
        contentPane.add(panelnorth, BorderLayout.NORTH);
        contentPane.add(panelcenter, BorderLayout.CENTER);
    }

    private JButton makeButton(String name) {

        return new JButton("name");

    }

    private void makeButtonRound(JButton button) {
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorder(new RoundedBorder(30)); //Set rounded corners with 30 px radius
        button.setBackground(Color.CYAN); //Optional set background color
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
            g.setColor(Color.BLACK); //Background Color
            g.fillRoundRect(x, y, width -1, height - 1, radius, radius);
        }
        
    }


    //Sets the user set a custom name for a button
    public void setSoundName(String name) {
        this.soundName = name;
    }

}
