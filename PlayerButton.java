    
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.nio.file.*; 
/**
 * This class stores a player as well as a textfile which stores the player's description writted by the user if it is saved. It also has a class called PlayerPopup which will show the players statstics.
 *
 * @author Felix Liao
 * @version 13 March 26, 2020
 */
public class PlayerButton extends JButton implements ActionListener
{
    // instance variables - replace the example below with your own
    private JButton proxy;
    private Player player;
    private PlayerPopup playerPopup;
    protected String imageFile= new String("playerIcon.PNG");
    private String notesFile = "";
    protected File textAreaFile = new File(notesFile);
    private int listNum;

    /**
     * Constructor for objects of class PlayerButton
     */
    public PlayerButton(Player player, int num)
    {
        
        listNum = num;
        if (player.getName().length() >= 20)
        {
            this.setText(player.getName().substring(0,20));
        }
        else
        {
            this.setText(player.getName());
        }
        this.player = player;
        this.addActionListener(this);
        this.setPreferredSize(new Dimension (300,40));
    }

    @Override
    public void actionPerformed (ActionEvent e){
        if (e.getSource() == this)
        {
            playerPopup = new PlayerPopup();
        }
    }

    /**
     * Gets the PlayerButon of this object
     * 
     * @return this object 
     */
    protected PlayerButton getPlayerButton()
    {
        return this;
    }

    /**
     * Gets the name  of this object's Player object
     * 
     * @return the name of this object's Player object
     */
    protected String getPlayerName()
    {
        return this.player.getName();
    }

    /**
     * Gets the player of this object
     * 
     * @return the player stored in this object 
     */
    protected Player getPlayer()
    {
        return this.player;
    }

    /**
     * Gets the listNum  of this object
     * 
     * @return the index of this object when it is stored in playerList in the object GamePanel
     */
    private int getListNum()
    {
        return listNum;
    }

    /**
     * Adds  1 to the listNum
     */
    private void plus()
    {
        listNum++;
    }

    /**
     * Deletes the file this object stores when this object is deleted
     */
    private void deleteFile()
    {
        textAreaFile.delete();
        textAreaFile = new File("");
    }

    /**
     * Sets the tex tfile this object stores 
     * 
     * @param name the name of the text file
     */
    protected void setFile(String name)
    {
        textAreaFile = new File(name);
    }

    /**
     * Sets the image file this object stores 
     * 
     * @param name the name of the image file
     */
    protected void setImage(String name)
    {
        imageFile = name;
    }
    /**
     * This class is a private class in the class PlayerButton which is a popup when the button is selected to display the statistics of the Player object stored in the PlayerButton object
     * 
     * @author Felix Liao
     *
     * @version 13 March 26, 2020
     * 
     */
    private class PlayerPopup extends OptionBoxes implements ActionListener
    {
        private final int WIDTH = 600;
        private final int HEIGHT = 500;

        private JPanel infoPanel;
        private ImagePanel imagePanel;
        private JPanel firstPanel;
        private JPanel secondPanel;
        private JPanel notesPanel;
        private JPanel buttonPanel;

        private JButton removeButton;
        private JButton uploadButton;
        private JButton saveButton;
        private String fileName = imageFile;
        private JTextArea textArea = new JTextArea(8,25);

        private BufferedWriter writer;
        private File file;

        /**
         * The constructor for objects in class PlayerPopup
         * 
         */
        public PlayerPopup()        
        {
            super();
            dialog = this.createDialog("Player Information");

            this.removeAll();
            addPanels();
            dialog.show();

        }

        /**
         * Adds panels to the object
         * 
         */
        private void addPanels()
        {
            mainPanel = new JPanel(new GridLayout(1,2));
            firstPanel = new JPanel(new BorderLayout());
            secondPanel = new JPanel(new GridLayout(2,1));
            notesPanel = new JPanel(new FlowLayout());
            infoPanel = new JPanel(new GridLayout(4,2));
            imagePanel = new ImagePanel(imageFile);
            buttonPanel = new JPanel(new GridLayout(1,2));
            infoPanel.add(new JLabel ("Name: " + player.getName()));
            if (player.getGender())
                infoPanel.add(new JLabel ("Gender: Female"));
            else
                infoPanel.add(new JLabel ("Gender: Male"));
            infoPanel.add(new JLabel ("Age: " + player.getAge()));
            infoPanel.add(new JLabel ("Years of Experience: " + player.getExperience()));
            infoPanel.add(new JLabel ("Wins: " + player.getWins()));
            infoPanel.add(new JLabel ("Losses: " + player.getLosses()));
            infoPanel.add(new JLabel ("Points: " + player.getPoints()));
            infoPanel.add(new JLabel ("Club: " + player.getClubName()));
            notesPanel.add(new JLabel ("Notes "));
            try{
                FileReader reader = new FileReader(textAreaFile.getName());
                BufferedReader br = new BufferedReader(reader);
                textArea.read(br,null);

            }
            catch (Exception e)
            {
            }

            notesPanel.add(textArea);
            textArea.setEditable(true);
            removeButton = new JButton("Remove");
            saveButton = new JButton("Save");
            exitButton = new JButton("Exit");
            uploadButton = new JButton("Upload");

            buttonPanel.add(saveButton);
            buttonPanel.add(removeButton);
            buttonPanel.add(exitButton);

            uploadButton.addActionListener(this);
            saveButton.addActionListener(this);
            exitButton.addActionListener(this);
            removeButton.addActionListener(this);

            dialog.setSize(WIDTH, HEIGHT);

            firstPanel.add(imagePanel,BorderLayout.CENTER);
            firstPanel.add(uploadButton,BorderLayout.SOUTH);

            mainPanel.add(firstPanel);
            secondPanel.add(infoPanel);
            notesPanel.add(buttonPanel);
            secondPanel.add(notesPanel);

            mainPanel.add(secondPanel);

            this.add(mainPanel);

            mainPanel.revalidate();
            mainPanel.repaint();
        }

        /**
         * Deletes the file that this object stores
         * 
         */
        private void deleteTextFile()
        {
            file.delete();
        }

        @Override
        public void actionPerformed (ActionEvent e){
            ArrayList<String> playerInfo;
            String fileName = imageFile;
            JFileChooser fc = new JFileChooser();
            String filePath = notesFile;

            try
            {
                if (e.getSource() == exitButton)
                {
                    dialog.setVisible(false);
                }
                else if (e.getSource() == saveButton)
                {
                    writer = new BufferedWriter(new FileWriter(listNum + getPlayerButton().player.getGenderString() + ".txt"));
                    textAreaFile = new File(listNum + getPlayerButton().player.getGenderString() + ".txt");

                    textAreaFile.setExecutable(true); 
                    textAreaFile.setReadable(true); 
                    textAreaFile.setWritable(true);
                    setFile(textAreaFile.getName());
                    writer.append(textArea.getText());
                    writer.close();
                }
                else if (e.getSource() == uploadButton)
                {
                    File file = null;
                    String path;
                    int returnVal = fc.showOpenDialog(this);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        file= fc.getSelectedFile();
                    }

                    path = file.getAbsolutePath();
                    imageFile = imagePanel.changeImage(new File (path));

                    mainPanel.revalidate();
                    mainPanel.repaint();
                }
                else if (e.getSource() == removeButton)
                {
                    //File file;
                    // file = new File("" + getPlayerButton().getListNum() + getPlayerButton().player.getGender() +".txt");

                    deleteFile();

                    //Files.deleteIfExists(Paths.get(("C:\\Users\\felix\\Downloads\\GUI PROJECt\\Progressing\\IAV3\\" + getPlayerButton().getListNum() + getPlayerButton().player.getGenderString()+".txt" ))); 

                    if (getPlayerButton().player.getGender())
                    {
                        GamePanel.playerBox1.removePlayer(getPlayerButton());

                    }
                    else
                    {
                        GamePanel.playerBox2.removePlayer(getPlayerButton());
                    }   

                    dialog.dispose();
                    mainPanel.revalidate();
                    mainPanel.repaint();
                }
            }
            catch (Exception exception)
            {
                System.err.format("Exception occurred trying to read '%s'.", "playerInfo");

            }
        }
    }
}