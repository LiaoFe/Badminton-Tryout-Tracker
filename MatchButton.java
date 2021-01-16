import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * This is a button that appears when a player is searched for a match. This button tells which players are selected for a match
 * 
 * @author Felix Liao
 *
 * @version 13 March 26, 2020
 * 
 */
public class MatchButton extends JButton implements ActionListener

{
    private boolean isSelected = false;
    protected static int numSelected;
    /**
     * Constructor for objects of class MatchButtons
     */
    public MatchButton(String name)
    {
        this.setText(name);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == this)
        {
            isSelected=  true;
            this.setBackground(Color.GREEN);
        }
    }

    /**
     * Deselects this button by setting isSelected to false
     * 
     */
    public void deselect()
    {
        numSelected --;
        isSelected = false;
        this.setBackground(null);
    }

    /**
     * Selects this button by setting isSelected to true
     * 
     */
    public void select()
    {
        numSelected++;
        isSelected=  true;
        this.setBackground(Color.GREEN);
    }
}
