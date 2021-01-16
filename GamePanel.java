import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.*;
/**
 * This class is the main panel of the program. PlayerButtons are displayed here and the action buttons are added here. This is the program's main interface panel.
 * 
 * @author Felix Liao
 *
 * @version 13 March 26, 2020
 * 
 */
class GamePanel  extends JPanel {
    private JButton  goMenu;
    private JButton confirmButton;
    private JLabel sortLabel;

    private JPanel optionPanel;

    // D for doubles and S for singles
    protected static int girls;
    protected static int boys;

    private boolean loaded;
    private boolean saved;
    protected OptionPanel optionBox = new OptionPanel();
    private int numPlayers;
    protected static PlayerBox playerBox1 = new PlayerBox("Female");
    protected static PlayerBox playerBox2 = new PlayerBox("Male");
    /**
     * Creates an object GamePanel. This is the default constructor
     */
    public GamePanel(){  // constructor
        optionPanel = new JPanel();
        this.setLayout(new GridLayout(1,3));

        this.setSize(1200,800);

        this.add(playerBox1);
        this.add(playerBox2);

        this.add(optionBox);

        optionBox.setPreferredSize(new Dimension(300,300));
    }      

    /**
     * Sets the total number of girls in this class
     * 
     * @param num the number of girls in this class
     * 
     */
    protected static void setGirls(int num){
        girls = num;
    }

    /**
     * Sets the total number of boys in this class
     * 
     * @param num the number of boys in this class
     * 
     */
    protected static void setBoys(int num){
        boys = num;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setFont(new Font("Arial", Font.BOLD+Font.ITALIC, 18));  

    }//end of paint 

    /**
     * This is a private class for the class GamePanel. This is the action panel where the user interacts with the interface.
     * 
     * @author Felix Liao
     *
     * @version 13 March 26, 2020
     * 
     */
    private class OptionPanel extends JPanel implements ActionListener
    {
        // instance variables - replace the example below with your own
        private JPanel mainPanel;
        private JPanel secondPanel;
        private JButton addPlayerButton;
        private JButton updateButton;
        private JButton exit;
        private AddPlayerFrame popup;
        private OptionBoxes updateBox;
        private JPanel sortPanel;
        private JButton points, yearsOfExp, rating, wins;
        private JButton saveButton;
        private JButton loadButton;
        private JButton exitButton;
        /**
         * Constructor for objects of class OptionBox
         */
        public OptionPanel()
        {
            this.setLayout(new FlowLayout());
            mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
            secondPanel = new JPanel(new GridLayout(6,1));

            addPlayerButton = new JButton("Add Player");
            updateButton = new JButton("Update");
            saveButton = new JButton("Save");
            loadButton = new JButton("Load Saved File");
            exitButton = new JButton("Exit");

            addPlayerButton.setPreferredSize(new Dimension(300, 30));
            updateButton.setPreferredSize(new Dimension(300, 30));
            saveButton.setPreferredSize(new Dimension(300, 30));
            loadButton.setPreferredSize(new Dimension(300, 30));
            exitButton.setPreferredSize(new Dimension(300, 30));
            sortPanel = new JPanel(new GridLayout(5,1));

            sortLabel = new JLabel("Sort by: ");
            points = new JButton("Points");
            wins = new JButton("Wins");
            yearsOfExp = new JButton("Experience");

            points.addActionListener(this);
            wins.addActionListener(this);
            yearsOfExp.addActionListener(this);

            addPlayerButton.addActionListener(this);
            updateButton.addActionListener(this);
            saveButton.addActionListener(this);
            loadButton.addActionListener(this);
            exitButton.addActionListener(this);

            secondPanel.add(new JLabel(""));
            secondPanel.add(addPlayerButton);
            secondPanel.add(updateButton);
            secondPanel.add(saveButton);
            secondPanel.add(loadButton);
            secondPanel.add(exitButton);

            sortPanel.add(sortLabel);
            sortPanel.add(points);
            sortPanel.add(wins);
            sortPanel.add(yearsOfExp);

            mainPanel.add(secondPanel);
            mainPanel.add(sortPanel);

            this.add(mainPanel);
            this.setPreferredSize(new Dimension(300,300));
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == addPlayerButton)
            {
                popup = new AddPlayerFrame();
            }
            else if (e.getSource() == updateButton)
            {
                updateBox = new UpdateBox();
            }
            else if (e.getSource() == points)
            {
                playerBox1.sortPoints();
                playerBox2.sortPoints();
            }
            else if (e.getSource() == wins)
            {
                playerBox1.sortWins();
                playerBox2.sortWins();
            }
            else if (e.getSource() == yearsOfExp)
            {
                playerBox1.sortExp();
                playerBox2.sortExp();
            }
            else if (e.getSource() == saveButton)
            {
                saved=true;
                playerBox1.save();
                playerBox2.save();
                loadButton.setText("File Sucessfully Saved.");
            }
            else if (e.getSource() == loadButton)
            {
                if(!loaded && !saved)
                {
                    loaded = true;
                    playerBox1.load();
                    playerBox2.load();
                    loadButton.setText("File Sucessfully Loaded.");
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Files load upon new session or if not already loaded", "Information", JOptionPane.INFORMATION_MESSAGE);
                }

            }
            else if (e.getSource() == exitButton)
            {
                if (!saved)
                {
                    int input = JOptionPane.showConfirmDialog(null, "Session not saved. Exit?");
                    if(input == 0)
                        System.exit(0);

                }
                else
                {
                    System.exit(0);
                }
            }
        }
        /**
         * This is a private class in the class OptionBox. When adding a player, this option box appears and the user enters a new players information to be stored
         * 
         * @author Felix Liao
         *
         * @version 13 March 26, 2020
         * 
         */
        private class AddPlayerFrame extends OptionBoxes implements ActionListener
        {
            protected InputField[] inputFields = new InputField[4];
            protected String[] fieldLabels = {"Name: ", "Age: ", "Years of Experience: ", "Club Name: "};
            private JPanel actionPanel;
            private JLabel introduction;
            protected Player player;
            private JButton selectBoy;
            private JButton selectGirl;
            private JPanel selectGenderPanel;
            private JPanel genderButtonPanel= new JPanel(new GridLayout(1,2));
            private JLabel genderLabel = new JLabel("Gender: ");
            private boolean isGirl;

            /**
             * Constructor for objects of class AddPlayerFrame
             */
            public AddPlayerFrame(){
                super();
                // initialize 
                dialog = this.createDialog("Tryout Information");

                //remove existing content
                this.removeAll();

                //all my elements
                addPanels();

                // show content
                dialog.show();
            } // end of constructor PlayerInputFrame()

            /**
             * Creates the elements in the object
             */
            private void addPanels()
            {

                //initialize panels and set layouts
                mainPanel = new JPanel(new GridLayout (6,1));
                actionPanel = new JPanel(new GridLayout(2,1));
                selectGenderPanel = new JPanel(new GridLayout(1,3));
                introduction = new JLabel("Enter Player Information");
                // initialize buttons
                confirmButton = new JButton("Confirm");
                exitButton = new JButton("Cancel");
                selectBoy = new JButton("Male");
                selectGirl = new JButton("Female");
                selectGirl.addActionListener(this);
                selectBoy.addActionListener(this);

                genderButtonPanel.add(selectGirl);
                genderButtonPanel.add(selectBoy);

                exitButton.addActionListener(this);
                confirmButton.addActionListener(this);
                selectBoy.addActionListener(this);
                selectGirl.addActionListener(this);

                selectGenderPanel.add(genderLabel);
                selectGenderPanel.add(genderButtonPanel);
                mainPanel.add(introduction);
                mainPanel.add(selectGenderPanel);

                for (int i = 0; i < fieldLabels.length; i = i + 1)
                {
                    inputFields[i] = new InputField(fieldLabels[i]); 
                    mainPanel.add(inputFields[i]);
                } // end of for loop

                // add elements to panels
                actionPanel.add(confirmButton);
                actionPanel.add(exitButton);
                exitButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
                confirmButton.setAlignmentX(JButton.CENTER_ALIGNMENT);

                // add panels to frames
                this.add(mainPanel);
                this.add(actionPanel);
                dialog.setSize(400, 300);
                this.validate();
                this.setVisible(true);
            }

            @Override
            public void actionPerformed (ActionEvent e)
            {
                if (e.getSource() == selectBoy)
                {
                    selectBoy.setBackground(Color.GREEN);
                    selectGirl.setBackground(null);
                    isGirl = false;
                }
                else if (e.getSource() == selectGirl)
                {
                    selectGirl.setBackground(Color.GREEN);
                    selectBoy.setBackground(null);
                    isGirl = true;
                }
                if (e.getSource() == confirmButton)
                {
                    try
                    {
                        if  (Integer.parseInt(inputFields[1].getTextField())<Integer.parseInt(inputFields[2].getTextField()))
                        {
                            JOptionPane.showMessageDialog(null, "Experience can not be greater than age.", "Information", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else if(Integer.parseInt(inputFields[2].getTextField()) > 0 && inputFields[3].getTextField().equals(""))
                        {
                            JOptionPane.showMessageDialog(null, "If player is experienced, please input the club they lasted trained at.", "Information", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else if (Integer.parseInt(inputFields[1].getTextField()) <=0 ||Integer.parseInt(inputFields[2].getTextField()) <0)
                        {
                            JOptionPane.showMessageDialog(null, "Please input positive integers only.", "Information", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else if (isGirl)
                        {
                            if(inputFields[3].getTextField().equals(""))
                            {
                                playerBox1.createPlayer(isGirl,inputFields[0].getTextField(),Integer.parseInt(inputFields[1].getTextField()),Integer.parseInt(inputFields[2].getTextField()),"none");
                            }
                            else
                            {
                                playerBox1.createPlayer(isGirl,inputFields[0].getTextField(),Integer.parseInt(inputFields[1].getTextField()),Integer.parseInt(inputFields[2].getTextField()),inputFields[3].getTextField());
                            }
                            dialog.dispose();
                        }
                        else
                        {
                            if(inputFields[3].getTextField().equals(""))
                                playerBox2.createPlayer(isGirl,inputFields[0].getTextField(),Integer.parseInt(inputFields[1].getTextField()),Integer.parseInt(inputFields[2].getTextField()),"none");
                            else
                                playerBox2.createPlayer(isGirl,inputFields[0].getTextField(),Integer.parseInt(inputFields[1].getTextField()),Integer.parseInt(inputFields[2].getTextField()),inputFields[3].getTextField());
                            dialog.dispose();
                        }

                    }
                    catch (Exception ex)
                    {
                        JOptionPane.showMessageDialog(null, "Please input valid values", "Information", JOptionPane.INFORMATION_MESSAGE);
                    }

                }
                else if(e.getSource() == exitButton)
                {
                    dialog.dispose();
                }

            }
        }
    }
}// end of MyGamePanel class