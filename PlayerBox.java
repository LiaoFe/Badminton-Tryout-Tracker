import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Scanner; // Import the Scanner class to read text files

/**
 * A class that contains the display panel for the boys and girls added to the tryout
 *
 * @author Felix Liao
 * @version 13 March 26, 2020
 */
public class PlayerBox extends JPanel
{
    private  LinkedList<PlayerButton> playerList;
    // private  LinkedList<JButton> playerButtonList = new LinkedList<JButton>();
    private BinaryTree tree;
    private JLabel label;
    protected  JPanel contentPanel;
    private PlayerButton button;
    private int playerCount = 0;
    private String name;
    private JScrollPane content;
    private JPanel mainPanel;
    private int size = 620;

    private File file;
    /**
     * 
     * Constructor for objects of class GirlBox
     * 
     * @param name the name of this object which is a male or female box
     */
    public PlayerBox(String name)
    {
        file = new File(name + ".txt");
        playerList = new LinkedList<PlayerButton>();
        tree = new BinaryTree();
        contentPanel = new JPanel(new FlowLayout());

        mainPanel = new JPanel();

        contentPanel.setPreferredSize(new Dimension(330,size));

        content = new JScrollPane(contentPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        content.setWheelScrollingEnabled(true);
        content.setPreferredSize(new Dimension(350, size+10));

        mainPanel.add(content);
        this.add(new JLabel(name),BorderLayout.NORTH);
        this.add(mainPanel);
        contentPanel.revalidate();
        contentPanel.repaint();
        content.revalidate();
        content.repaint();
        this.revalidate();
        this.repaint();
        this.name = name;

        //        this.add(contentPanel,BorderLayout.CENTER);
        this.setVisible(true);
    }

    /**
     * 
     * Constructor for objects of class GirlBox with a fileName. Used when loading saved files,
     * 
     * @param name the name of this object which is a male or female box
     */
    public PlayerBox(String name, String fileName)
    {

        playerList = new LinkedList<PlayerButton>();
        tree = new BinaryTree();
        playerCount=0;
        contentPanel = new JPanel(new GridLayout(5,1));
        this.setLayout(new BorderLayout());
        content = new JScrollPane(contentPanel);
        content.setWheelScrollingEnabled(true);
        content.getViewport().setBackground(Color.WHITE);
        content.setViewportView(contentPanel);
        this.add(content);

        this.name = name;
        //      contentPanel.setSize(400,400);
        this.add(new JLabel(name),BorderLayout.NORTH);
        //        this.add(contentPanel,BorderLayout.CENTER);
        this.setVisible(true);
    }

    /**
     * Adds a player to the playerList of this object.
     * 
     * @param isGirl the gender of the player
     * 
     * @param name the name of the player
     * 
     * @param age the age of the player
     * 
     * @param exp the experience of the player
     * 
     * @param clubName the clubName of the player
     */
    protected  void createPlayer(boolean isGirl,String name, int age,  int exp,String clubName){
        button = new PlayerButton(new Player(isGirl, name, age, exp,clubName),playerCount);
        playerList.add(button);
        contentPanel.add(playerList.get(playerCount));
        contentPanel.setPreferredSize(new Dimension(330,size));
        content.setViewportView(contentPanel);
        if (playerCount > 11)
            size = size + 40;

        contentPanel.revalidate();
        contentPanel.repaint();
        content.revalidate();
        content.repaint();
        this.revalidate();
        this.repaint();
        contentPanel.setVisible(true);
        content.setWheelScrollingEnabled(true);

        playerCount++;
    }

    /**
     * Gets the PlayerButton object from this objects playerList ad the index of the parameter
     * 
     * @param i the index of the object from this objects playerList
     * 
     * @return the PlayerButton at index i
     * 
     */
    protected PlayerButton getButton(int i)
    {
        PlayerButton button = playerList.get(i);

        return button;
    }

    /**
     * Gets the playerList of this object.
     * 
     * @return the playerList of this object
     * 
     */
    private LinkedList<PlayerButton> getList()
    {
        return playerList;
    }

     /**
     * Repaints the contents of this objects contentPanel after being sorted
     * 
     */
    protected void reSort()
    {

        contentPanel.removeAll();
        contentPanel = new JPanel(new FlowLayout());
        for (int i = 0; i < playerList.size(); i++)
        {
            contentPanel.add(getList().get(i));
            contentPanel.setPreferredSize(new Dimension(330,size));
            content.setViewportView(contentPanel);
        }

        contentPanel.revalidate();
        contentPanel.repaint();
        contentPanel.setVisible(true);

        contentPanel.setVisible(true);
    }

     /**
     * Sorts the playerList by the the PlayerButton's Player object's wins
     * 
     */
    protected void sortWins()
    {
        tree = new BinaryTree();
        if (playerList!=null)
        {
            for (int i = 0; i < playerList.size(); i++)
            {
                tree.addWins(getList().get(i));
            }
            tree.traverseInOrder(tree.getRoot());

            playerList.clear();
            LinkedList<PlayerButton> temp = new LinkedList<PlayerButton>();
            temp = tree.getSorted();
            for (int i = 0; i < temp.size(); i++)
            {
                playerList.add(temp.get(i));
            }
            reSort();
        }
    }

    /**
     * Sorts the playerList by the the PlayerButton's Player object's experience
     * 
     */
    protected void sortExp()
    {
        tree = new BinaryTree();
        if (playerList!=null)
        {
            for (int i = 0; i < playerList.size(); i++)
            {
                tree.addExp(getList().get(i));
            }
            tree.traverseInOrder(tree.getRoot());

            playerList.clear();
            LinkedList<PlayerButton> temp = new LinkedList<PlayerButton>();
            temp = tree.getSorted();
            for (int i = 0; i < temp.size(); i++)
            {
                playerList.add(temp.get(i));
            }
            reSort();
        }

    }

    /**
     * Sorts the playerList by the the PlayerButton's Player object's points
     * 
     */
    protected void sortPoints()
    {
        tree = new BinaryTree();
        if (playerList!=null)
        {
            for (int i = 0; i < playerList.size(); i++)
            {
                tree.addPoints(getList().get(i));
            }
            tree.traverseInOrder(tree.getRoot());

            playerList.clear();
            LinkedList<PlayerButton> temp = new LinkedList<PlayerButton>();
            temp = tree.getSorted();
            for (int i = 0; i < temp.size(); i++)
            {
                playerList.add(temp.get(i));
            }
            reSort();
        }

    }

    /**
     * Loads the saved files. Repaints the contentPanel 
     * 
     */
    protected void load()
    {
        String name;
        boolean isGirl;
        int age;
        int experience;
        int wins =0;
        int losses = 0;
        int points=0;
        String clubName;
        String fileName;
        String imageFile;
        try
        {
            FileReader reader;reader = new FileReader(file);

            Scanner scanner = new Scanner(file);
            while(!scanner.nextLine().equals(""))
            {
                isGirl = Boolean.parseBoolean(scanner.nextLine());
                name = scanner.nextLine();
                age = Integer.parseInt(scanner.nextLine());
                experience = Integer.parseInt(scanner.nextLine());
                wins = Integer.parseInt(scanner.nextLine());
                losses = Integer.parseInt(scanner.nextLine());
                points = Integer.parseInt(scanner.nextLine());
                clubName = scanner.nextLine();
                fileName = scanner.nextLine();
                PlayerButton button = new PlayerButton(new Player(isGirl, name, age, experience, wins ,losses, points,clubName),playerCount);
                button.setFile(fileName);
                button.setImage(scanner.nextLine());
                contentPanel.add(button);
                playerList.add(button);
                contentPanel.setPreferredSize(new Dimension(330,size));
                content.setViewportView(contentPanel);
                if (playerCount > 11)
                    size = size + 40;
                playerCount++;
            }
            reader.close();

            contentPanel.revalidate();
            contentPanel.repaint();
            this.revalidate();
            this.repaint();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }

    /**
     * Saves each Player object's information into a textFile that can be loaded
     * 
     */
    protected void save()
    {
        try
        {
            FileWriter writer = new FileWriter(file);

            for (int i = 0; i < playerList.size(); i++)
            {
                writer.write("start"+System.getProperty("line.separator"));

                writer.write(playerList.get(i).getPlayer().getGender()+System.getProperty("line.separator"));
                writer.write(playerList.get(i).getPlayer().getName() + System.getProperty("line.separator"));
                writer.write(playerList.get(i).getPlayer().getAge()+System.getProperty("line.separator"));
                writer.write(playerList.get(i).getPlayer().getExperience()+System.getProperty("line.separator"));
                writer.write(playerList.get(i).getPlayer().getWins()+System.getProperty("line.separator"));
                writer.write(playerList.get(i).getPlayer().getLosses()+System.getProperty("line.separator"));
                writer.write(playerList.get(i).getPlayer().getPoints()+System.getProperty("line.separator"));
                writer.write(playerList.get(i).getPlayer().getClubName()+System.getProperty("line.separator"));
                writer.write((playerList.get(i).textAreaFile.getName()+System.getProperty("line.separator")));
                writer.write((playerList.get(i).imageFile+System.getProperty("line.separator")));


            }
            writer.write(""+System.getProperty("line.separator"));
            writer.close();
        }
        catch (Exception e)
        {
        }
    }

    /**
     * Remove a PlayerButton object for playerList 
     * 
     * @param player the PlayerButton object that will be deleted in this objects playerList
     * 
     */
    protected void removePlayer(PlayerButton player)
    {

        playerList.remove(player);
        contentPanel.remove(player);
        contentPanel.revalidate();
        contentPanel.repaint();
        playerCount--;
    }

    /**
     * Returns this objects playerList
     * 
     * @return the playerList stored in this object
     * 
     */
    protected LinkedList getPlayerList()
    {
        return playerList;
    }

    /**
     * Returns this name of the Player object stored in this objects playerList
     * 
     * @param i the index of the playerList 
     * 
     * @return the name of player stored in the PlayerButton stored in the playerList at index i
     * 
     */
    protected String getPlayersName(int i)
    {
        return playerList.get(i).getPlayerName();
    }

    /**
     * Returns the Player object stored in PlayerButton in this objects playerList at index i
     * 
     * @param i the index of the playerList 
     * 
     * @return the number of players in this object
     * 
     */
    protected Player getPlayer(int i)
    {
        return playerList.get(i).getPlayer();
    }

     /**
     * Returns the playerCount of this object 
     * 
     * @return the number of players in this object
     * 
     */
    protected int getPlayerCount()
    {
        return playerCount;
    }

     /**
     * Changes the statistics of the players after a singles match
     * 
     * @param winner the winner of the match
     * 
     * @param points1 the points the winner gains
     * 
     * @param loser the loser of the match
     * 
     * @param points2 the points of the loser gain
     * 
     */
    protected void updatePlayers(Player winner, int points1,  Player loser, int points2)
    {
        int i =0;
        for (int j = 0; j < playerList.size(); j++)
        {
            if (playerList.get(j).getPlayer().equals(winner))
            {          
                playerList.get(j).getPlayer().addWin();
                playerList.get(j).getPlayer().addPoints(points1);

            }
            else if (playerList.get(j).getPlayer().equals(loser))
            {

                playerList.get(j).getPlayer().addLoss();
                playerList.get(j).getPlayer().addPoints(points2);

            }
        }

    }

    /**
     * Changes the statistics of the players after a doubles match
     * 
     * @param winner1 the winner of the match
     * 
     * @param winner2 the second winner of the match
     * 
     * @param points1 the points the winner gains
     * 
     * @param loser the loser of the match
     * 
     * @param loser2 the second loser of the match
     * 
     * @param points2 the points of the losers gain
     * 
     */
    protected void updatePlayers(Player winner1, Player winner2,int points1, Player loser1, Player loser2, int points2)
    {
        int i =0;
        for (int j = 0; j < playerList.size(); j++)
        {
            if (playerList.get(j).getPlayer().equals(winner1) || (playerList.get(j).getPlayer().equals(winner2)))
            {          
                playerList.get(j).getPlayer().addWin();
                playerList.get(j).getPlayer().addPoints(points1);    

            }
            else if (playerList.get(j).getPlayer().equals((loser1)) || playerList.get(j).getPlayer().equals((loser2)))
            {
                playerList.get(j).getPlayer().addLoss();
                playerList.get(j).getPlayer().addPoints(points2);

            }
        }

    }

}
