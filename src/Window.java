import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;


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
        
        
        //Sets the size of the panels 
        panelcenter.setPreferredSize(new Dimension(300,100));
        panelnorth.setPreferredSize(new Dimension(300, 100));
        paneleast.setPreferredSize(new Dimension(300, 100));
        panelwest.setPreferredSize(new Dimension(300, 100));
        panelsouth.setPreferredSize(new Dimension(300 ,100));

        panelcenter.setLayout(new GridLayout(2,2,2,10));
        

        //Adds elements to the sub pnales
        panelcenter.add(this.soundButton1);
        panelcenter.add(this.soundButton2);
        panelnorth.add(this.Button2);

        
        //adds the subpanels to the main panel
        contentPane.add(panelnorth, BorderLayout.NORTH);
        contentPane.add(panelcenter, BorderLayout.CENTER);
    }



    //Sets the user set a custom name for a button
    public void setSoundName(String name) {
        this.soundName = name;
    }

}
