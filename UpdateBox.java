import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * This software is designed for organizing tryouts for any racket sports. Any sport requiring points and has a singles and doubles option can also use this tryout manager.
 * 
 * @author Felix Liao
 *
 * @version 13 March 26, 2020
 * 
 */
public class UpdateBox extends OptionBoxes implements ActionListener
{
    private JOptionPane selectFormat;
    private JButton singlesButton;
    private JButton doublesButton;
    private JPanel buttonPanel;
    private boolean isDoubles;
    private OptionBoxes matchBox;

    /**
     * The constructors of object is the class UpdateBox
     * 
     */
    public UpdateBox(){
        super();
        // initialize 
        dialog = this.createDialog("Select Format");

        //remove existing content
        this.removeAll();

        //all my elements
        addPanels();
        this.setSize(800,1000);

        // show content
        dialog.show();
    } // end of constructor PlayerInputFrame()

    /**
     * Adds the content to this object 
     * 
     */
    private void addPanels()
    {
        singlesButton = new JButton ("Singles");
        doublesButton = new JButton ("Doubles");

        singlesButton.addActionListener(this);
        doublesButton.addActionListener(this);

        mainPanel = new JPanel(new BorderLayout());
        buttonPanel = new JPanel(new GridLayout(1,2));
        buttonPanel.add(singlesButton);
        buttonPanel.add(doublesButton);

        mainPanel.add(buttonPanel,BorderLayout.CENTER);

        this.add(mainPanel);

        // add panels to frames

        mainPanel.revalidate();
        mainPanel.repaint();
        this.validate();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed (ActionEvent e)
    {
        if (e.getSource() == singlesButton)
        {
            isDoubles = false;
            dialog.dispose();
            matchBox = new MatchBox("Singles",1);

        }
        else if (e.getSource() == doublesButton)
        {
            isDoubles = true;

            dialog.dispose();
            matchBox = new MatchBox("Doubles",2);

        }

    }
}