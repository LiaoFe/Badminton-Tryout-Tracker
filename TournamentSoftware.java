import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
/**
 * This software is designed for organizing tryouts for any racket sports. Any sport requiring points and has a singles and doubles option can also use this tryout manager.
 * 
 * @author Felix Liao
 *
 * @version 13 March 26, 2020
 * 
 */
public class TournamentSoftware extends JFrame{
    private final int WIDTH = 1200;
    private final int HEIGHT = 800;
    private static CardLayout cardsL;
    protected static Container c;
    private MenuPanel  menuP;  
    private GamePanel gameP;   
    /**
     * Create the default constructor that will be created during main method
     * 
     */
    public TournamentSoftware(){    //constructor
        c=getContentPane();
        cardsL=new CardLayout();
        c.setLayout(cardsL);
        this.setSize(WIDTH, HEIGHT);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        menuP = new MenuPanel();
        gameP = new GamePanel();

        c.add("Menu", menuP);
        c.add("Game", gameP);
        this.setTitle("Tryout Manager");
    }

    /**
     * Create the tournament software object that launches the software
     * @param args
     * 
     */
    public static void main(String[] args) {
        TournamentSoftware layout = new TournamentSoftware();

        layout.setResizable(false);

        layout.setVisible(true);
        layout.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);  
    } //end of demo class

    /**
     * Gets the cardLayout of the object
     * 
     * @return  the cardLayout of the object
     */
    protected static CardLayout getLay()
    {
        return cardsL;
    }

    /**
     * Gets the containter of the object
     * 
     * @return the container of the object
     */
    protected static Container getCont()
    {
        return c;
    }

}

