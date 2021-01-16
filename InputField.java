import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
/**
 * This class is used in the class GamePanel as a way to input fields. 
 * 
 * @author Felix Liao
 *
 * @version 13 March 26, 2020
 * 
 */
public class InputField extends JPanel
{
    private JLabel text;
    private JTextField textField;
    /**
     * Creates an object that has a JLabel and a JTextField
     * 
     * @param textLabel the name of this InputField
     * 
     */
    public InputField(String textLabel)
    {
        //mainPanel = new JPanel(new FlowLayout());
        this.setLayout(new GridLayout(1,2));
        text = new JLabel(textLabel);
        textField = new JTextField();
        this.add(text);
        this.add(textField);
        this.setVisible(true);

    }

    /**
     * Sets the text in this object's textField to an empty string
     */
    
    protected void resetTextField()
    {
        textField.setText("");
    }

     /**
     * Gets the text in this object's textField to the parameter
     * 
     *@return the text in this object's textField
     */
    
    protected String getTextField()
    {
        return textField.getText();
    }
}
