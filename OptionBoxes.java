import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * This is a degualt option pane that all my classes will use. They will all need a mainPanel, confirmButton and exitButton
 * 
 * @author Felix Liao
 *
 * @version 13 March 26, 2020
 * 
 */
public class OptionBoxes extends JOptionPane
{
    protected JDialog dialog;
    protected JPanel mainPanel;
    protected JButton confirmButton;
    protected JButton exitButton;

    /**
     * Constructor for objects of class OptionBoxes
     */
    public OptionBoxes()
    {
        dialog = this.createDialog("");
        this.setSize(400,400);
        
    }

}
