import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Queue; 
/**
 * This is the box that appears when a match is about to be entered
 * 
 * @author Felix Liao
 *
 * @version 13 March 26, 2020
 * 
 */
public class MatchBox extends OptionBoxes implements ActionListener
{
    private final int WIDTH = 500;
    private final int HEIGHT = 700;
    private JOptionPane selectFormat;
    private JButton singlesButton;
    private JButton doublesButton;

    private JPanel secondPanel;
    private JPanel labelPanel;
    private JPanel optionPanel;

    private boolean isDoubles;
    private int playerCountBoys;
    private int playerCountGirls;

    private MatchButton[] playerButtonsBoys = new MatchButton[GamePanel.playerBox2.getPlayerCount()];
    private MatchButton[] playerButtonsGirls = new MatchButton[GamePanel.playerBox1.getPlayerCount()];
    private LinkedList playerList = GamePanel.playerBox1.getPlayerList();
    private JLabel teamLabel1 = new JLabel("Team 1");
    private JLabel teamLabel2 = new JLabel("Team 2");
    private JPanel teamPanel1;
    private JPanel teamPanel2;
    private int teamSize;

    private MatchButton[] selectedPlayers = new MatchButton[2];
    private int queueMax = 0;
    private ScoreBox scoreBox;

    private LinkedList<Player> players = new LinkedList<Player>();
    protected LinkedList<Player> teamPlayers1 = new LinkedList<Player>();
    protected LinkedList<Player> teamPlayers2 = new LinkedList<Player>();

    private int listCount=0;
    private String Player;
    private SearchPlayer teamSearch1;
    private SearchPlayer teamSearch2;

    /**
     * The constructors of objects in class MatchBox
     * 
     * @param name the format of the match (singles or doubles)
     * 
     * @param num the size of each team of this match
     * 
     */
    public MatchBox(String name, int num){
        super();
        teamSize = num;
        // initialize 
        dialog = this.createDialog(name + " match");

        //remove existing content
        this.removeAll();
        dialog.setSize(WIDTH, HEIGHT);

        //all my elements
        addPanels();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        dialog.setLocation(dim.width/2-WIDTH/2, dim.height/2-HEIGHT/2);

        // show content
        dialog.show();
    } // end of constructor PlayerInputFrame()

    /**
     * Adds content to this object
     * 
     */
    private void addPanels()
    {
        playerCountGirls = GamePanel.playerBox1.getPlayerCount();
        playerCountBoys = GamePanel.playerBox2.getPlayerCount();

        mainPanel = new JPanel(new BorderLayout());
        secondPanel = new JPanel (new GridLayout(1,2));
        labelPanel = new JPanel(new GridLayout(1,2));
        teamPanel1 = new JPanel();
        teamPanel1.setLayout(new FlowLayout());
        teamPanel2 = new JPanel ();
        teamPanel2.setLayout(new FlowLayout());
        optionPanel = new JPanel (new GridLayout(1,2));

        labelPanel.add(teamLabel1);
        labelPanel.add(teamLabel2);

        teamSearch1 = new SearchPlayer(teamSize);
        teamSearch2 = new SearchPlayer(teamSize);

        teamPanel1.add(teamSearch1);
        teamPanel2.add(teamSearch2);

        confirmButton = new JButton("Confirm");
        exitButton = new JButton ("Exit");

        optionPanel.add(confirmButton);
        optionPanel.add(exitButton);

        confirmButton.addActionListener(this);
        exitButton.addActionListener(this);

        secondPanel.add(teamPanel1);
        secondPanel.add(teamPanel2);

        mainPanel.add(labelPanel, BorderLayout.NORTH);
        mainPanel.add(secondPanel, BorderLayout.CENTER);
        mainPanel.add(optionPanel, BorderLayout.SOUTH);
        this.add(mainPanel);

        // add panels to frames

        mainPanel.revalidate();
        mainPanel.repaint();
        this.validate();
        this.setVisible(true);
    }

    /**
     * Changes the label of in this object
     * 
     * @param name1 the name of the first team
     * @param name2 the name of the second team
     */
    public void changeLabel(String name1, String name2)
    {
        teamLabel1.setText(name1);
        teamLabel2.setText(name2);
    }

    @Override
    public void actionPerformed (ActionEvent e)
    {

        if (e.getSource() == confirmButton)
        {

            int playerCount1 = 0;
            teamPlayers1.clear();
            teamPlayers2.clear();

            for (int i = 0; i < teamSearch1.getList().size(); i++) {

                if (teamSearch1.getList().get(i).getSelected())
                {

                    teamPlayers1.add(teamSearch1.getList().get(i).getPlayer());

                    playerCount1++;
                }
            }
            int playerCount2 = 0;
            for (int i = 0; i < teamSearch2.getList().size(); i++) {

                if (teamSearch2.getList().get(i).getSelected())
                {
                    teamPlayers2.add(teamSearch2.getList().get(i).getPlayer());

                    playerCount2++;
                }
            }

            if (playerCount2 + playerCount1 != teamSize * 2)
            {
                JOptionPane.showMessageDialog(null, "Please select " + teamSize + " player per team.", "Incorrect number of players", JOptionPane.INFORMATION_MESSAGE);
            }
            else if (teamSize ==2)
            {
                if (teamPlayers1.contains(teamPlayers2.get(0))||teamPlayers1.contains(teamPlayers2.get(1)) || teamPlayers1.get(0).equals(teamPlayers1.get(1)) || teamPlayers2.get(0).equals(teamPlayers1.get(1)))
                {
                    JOptionPane.showMessageDialog(null, "Same player selected on both teams", "Warning", JOptionPane.INFORMATION_MESSAGE);

                }
                else
                {

                    scoreBox =  new ScoreBox(teamPlayers1.get(0),teamPlayers1.get(1),teamPlayers2.get(0),teamPlayers2.get(1));
                    dialog.dispose();
                }
            }
            else if (teamSize == 1)
            {
                if (teamPlayers1.contains(teamPlayers2.get(0)))
                {
                    JOptionPane.showMessageDialog(null, "Same player selected on both teams", "Warning", JOptionPane.INFORMATION_MESSAGE);

                }
                else 
                {

                    scoreBox =  new ScoreBox(teamPlayers1.get(0),teamPlayers2.get(0));
                    dialog.dispose();
                }
            }

        }
        else if (e.getSource() == exitButton)
        {
            players = new LinkedList<Player>();
            dialog.dispose();
        }
    }
    /**
     * A private class in MatchBox which appears when a match will happen. This object allows the user to serach for players to select for a match.
     * 
     * @author Felix Liao
     *
     * @version 13 March 26, 2020
     * 
     */
    private class SearchPlayer extends JPanel implements ActionListener
    {
        // instance variables - replace the example below with your own
        private JTextField searchBar;
        private JButton searchButton;
        private JPanel searchPanel;
        private JPanel resultPanel;
        private int maxTeamSize;
        private LinkedList<SearchButton> searchButtons = new LinkedList<SearchButton>(); 
        private String search;
        private int index1, index2, box1,box2;
        /**
         * Constructor for objects of class SearchPlayer
         */
        public SearchPlayer(int num)
        {
            this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
            searchPanel = new JPanel();
            searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
            searchButton = new JButton("Search");
            searchButton.addActionListener(this);
            searchBar = new JTextField();
            searchBar.setSize(150,30);
            resultPanel=new JPanel();
            resultPanel.setLayout(new GridLayout(14,1));
            resultPanel.setSize(200,700);

            searchPanel.add(searchBar);
            searchPanel.add(searchButton);
            searchPanel.setPreferredSize(new Dimension(200,30));
            this.add(searchPanel);
            this.add(resultPanel);
            this.revalidate();
            this.repaint();
        }

        /** 
         * Returns this objects list of players that were searched
         * 
         * @return the list of players that were searched and appear on this object
         */
        protected LinkedList<SearchButton> getList()
        {
            return searchButtons;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {

            for(int i = 0; i <playerCountBoys; i++)
            {
                search = searchBar.getText();
                if (search.equalsIgnoreCase(GamePanel.playerBox2.getPlayersName(i)) || GamePanel.playerBox2.getPlayersName(i).contains(search))
                {
                    boolean exists = false;
                    if (!searchButtons.isEmpty())
                    {
                        for(int j = 0; j <searchButtons.size(); j++)
                        {
                            if (searchButtons.get(j).getPlayer().equals(GamePanel.playerBox2.getPlayer(i)))
                            {
                                exists = true;
                            }
                        }
                    }
                    if (!exists)
                    {
                        searchButtons.add(new SearchButton(GamePanel.playerBox2.getPlayersName(i),GamePanel.playerBox2.getPlayer(i)));
                        players.add(GamePanel.playerBox2.getPlayer(i));
                        searchButtons.getLast().setPreferredSize(new Dimension (100,40));

                        resultPanel.add(searchButtons.getLast());                   
                        listCount++;
                    }
                }
            }

            for(int i = 0; i < playerCountGirls; i++)
            {
                search = searchBar.getText();
                if (search.equalsIgnoreCase(GamePanel.playerBox1.getPlayersName(i))||GamePanel.playerBox1.getPlayersName(i).contains(search))
                {
                    boolean exists = false;
                    if (!searchButtons.isEmpty())
                    {
                        for(int j = 0; j <searchButtons.size(); j++)
                        {
                            if (searchButtons.get(j).getPlayer().equals(GamePanel.playerBox1.getPlayer(i)))
                            {
                                exists = true;
                            }
                        }
                    }
                    if (!exists)
                    {
                        searchButtons.add(new SearchButton(GamePanel.playerBox1.getPlayersName(i),GamePanel.playerBox1.getPlayer(i)));
                        players.add(GamePanel.playerBox1.getPlayer(i));
                        searchButtons.getLast().setPreferredSize(new Dimension (100,40));
                        resultPanel.add(searchButtons.getLast());
                        listCount++;
                    }
                }
            }
            resultPanel.revalidate();
            resultPanel.repaint();
        }

    }

    /**
     * This is an object that appears when a match is complete and the user will input the match results so that players will have updated statistics
     * 
     * @author Felix Liao
     *
     * @version 13 March 26, 2020
     * 
     */
    public class ScoreBox extends OptionBoxes implements ActionListener

    {

        private JPanel labelPanel;
        private JPanel textPanel;
        private JTextField field1;
        private JTextField field2;
        private JPanel buttonPanel;

        private int totalPlayers;
        private JLabel label1, label2;

        //team 1
        private Player player1, player2;
        //team 2
        private Player player3, player4;
        //for singles
        private Player winner1,loser1;
        //for doubles NOTES: winners on same team
        private Player winner2,loser2;

        /**
         * Constructor for objects of class MatchButtons for singles matchs
         * 
         * @param player1 the first player
         * 
         * @param player2 the second player
         */

        public ScoreBox(Player player1, Player player2)
        {
            super();
            dialog = this.createDialog("Match Results");
            this.removeAll();
            this.player1 = player1;
            this.player2 = player2;
            totalPlayers=2;
            //all my elements
            addPanels();
            dialog.setSize(400,120);

            // show content
            dialog.show();
        }

        /**
         * Constructor for objects of class MatchButtons for doubles matchs
         * 
         * @param player1 the first player of team 1
         * 
         * @param player2 the second player of  team 1
         * 
         * @param player3 the first player of team 2
         * 
         * @param player4 the second player of  team 2
         */
        public ScoreBox(Player player1, Player player2, Player player3, Player Player4)
        {
            dialog = this.createDialog("Match Results");
            this.removeAll();
            this.player1 = player1;
            this.player2 = player2;
            this.player3 = player3;
            this.player4 = player4;

            totalPlayers=4;
            //all my elements
            addPanels();
            dialog.setSize(400,120);

            // show content
            dialog.show();

        }

        /**
         * Adds content to this object
         */
        private void addPanels()
        {
            mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
            labelPanel = new JPanel();
            labelPanel.setLayout(new GridLayout(1,totalPlayers));
            textPanel = new JPanel();
            textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.X_AXIS));
            buttonPanel = new JPanel ();
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
            confirmButton = new JButton("Confirm");
            exitButton = new JButton("Exit");
            confirmButton.addActionListener(this);
            exitButton.addActionListener(this);

            field1 = new JTextField("");
            field1.setPreferredSize(new Dimension(50,30));
            field2 = new JTextField("");
            field2.setPreferredSize(new Dimension(50,30));

            label1 = new JLabel();
            label2 = new JLabel();
            if (totalPlayers ==2)
            {
                label1.setText(teamPlayers1.get(0).getName());
                label2.setText(teamPlayers2.get(0).getName());
            }
            else
            {
                label1.setText(teamPlayers1.get(0).getName()+ " & " + teamPlayers1.get(1).getName());

                label2.setText(teamPlayers2.get(0).getName()+ " & " + teamPlayers2.get(1).getName());

            }
            labelPanel.add(label1);
            labelPanel.add(label2);

            textPanel.add(field1);
            textPanel.add(field2);
            textPanel.setPreferredSize(new Dimension(100,30));

            buttonPanel.add(confirmButton);
            buttonPanel.add(exitButton);

            mainPanel.add(labelPanel);
            mainPanel.add(textPanel);
            mainPanel.add(buttonPanel);
            this.add(mainPanel);
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == exitButton)
            {
                // reset the array of players being searhced by reassignment
                //players = new Player[4];
                dialog.dispose();
            }
            else if (e.getSource() == confirmButton)
            { 
                int p1 = 1;
                int p2=1;
                //for singles
                try
                {
                    p1 = Integer.parseInt(field1.getText());
                    p2 = Integer.parseInt(field2.getText());

                    //there are no draws in racket sports
                    if (p1 >=0 && p2 >=0)
                    {
                        if(!(p1==0 && p2==0))
                        {
                            if(p1>=p2)
                            {
                                if (totalPlayers ==2)
                                {

                                    winner1 = teamPlayers1.get(0);
                                    loser1 = teamPlayers2.get(0);

                                    GamePanel.playerBox1.updatePlayers(winner1, p1, loser1, p2);
                                    GamePanel.playerBox2.updatePlayers(winner1, p1, loser1, p2);
                                }
                                else if ( totalPlayers == 4)
                                {
                                    winner1 = teamPlayers1.get(0);
                                    winner2 = teamPlayers1.get(1);
                                    loser1= teamPlayers2.get(0);
                                    loser2 = teamPlayers2.get(1);

                                    GamePanel.playerBox1.updatePlayers(winner1, winner2, p1, loser1,loser2, p2);
                                    GamePanel.playerBox2.updatePlayers(winner1, winner2, p1, loser1,loser2, p2);
                                }
                            }

                            if (p1 < p2)
                            {
                                if (totalPlayers ==2)
                                {

                                    winner1 = teamPlayers2.get(0);
                                    loser1 = teamPlayers1.get(0);

                                    GamePanel.playerBox1.updatePlayers(winner1, p2, loser1, p1);
                                    GamePanel.playerBox2.updatePlayers(winner1, p2, loser1, p1);
                                }
                                else if ( totalPlayers == 4)
                                {

                                    winner1 = teamPlayers2.get(0);
                                    winner2 = teamPlayers2.get(1);
                                    loser1= teamPlayers1.get(0);
                                    loser2 = teamPlayers1.get(1);

                                    GamePanel.playerBox1.updatePlayers(winner1, winner2, p2, loser1,loser2, p1);
                                    GamePanel.playerBox2.updatePlayers(winner1, winner2, p2, loser1,loser2, p1);
                                }
                            }
                        }
                        dialog.dispose();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Please input valid points only.", "Information", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null, "Please input numbers.", "Information", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        }
    }

}
