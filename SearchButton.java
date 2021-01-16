import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * This class is a button that performs actions when players are searched.
 * 
 * @author Felix Liao
 *
 * @version 13 March 26, 2020
 * 
 */
public class SearchButton extends JButton implements ActionListener

{
    private boolean isSelected = false;
    protected static int numSelected;
    private String name;
    private Player player;
    /**
     * Constructor for objects of class MatchButtons
     */
    public SearchButton(String name, Player player)
    {
        this.player = player;
        this.name = name;
        this.setText(this.name);
        this.addActionListener(this);
    }

    /**
     * Gets the Player object of this object
     * 
     * @return the player object
     * 
     */
    protected Player getPlayer()
    {
        return this.player;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == this)
        {
            if (!isSelected){
                isSelected=  true;
                this.setBackground(Color.GREEN);
            }
            else{
                isSelected=  false;
                this.setBackground(null);
            }
        }
    }

    /**
     * Gets the name of this button
     * 
     * @return the name of this button
     */
    protected String getButtonName()
    {
        return name;
    }

    /**
     * Gets the status of the button to determine whether it is selected or not
     * 
     * @return the status of the button
     * 
     */
    protected boolean getSelected()
    {
        return isSelected;
    }
}
