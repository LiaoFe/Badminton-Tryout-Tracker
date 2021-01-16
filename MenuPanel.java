import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
/**
 * The menu panel to greet the user.
 * 
 * @author Felix Liao
 *
 * @version 13 March 26, 2020
 * 
 */
class MenuPanel  extends JPanel implements ActionListener{
    private JButton  goGame;
    private JLabel introduction;
    private ImagePanel image;
    /**
     * The default constructor of this class.
     * 
     */
    public MenuPanel(){  // constructor
        goGame=new JButton("Start Tryout!");
        goGame.addActionListener(this);
        image = new ImagePanel("");
        image.setSize(1200,740);
        image.changeImage(new File("intro.PNG"));
        this.setLayout(new BorderLayout());
        this.add(image,BorderLayout.CENTER);
        this.add(goGame, BorderLayout.SOUTH);     
        //this.add(new ImagePanel(), BorderLayout.CENTER);

        //    this.add(buttonLayout,BorderLayout.CENTER);
    }      

    @Override
    
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==goGame){
            TournamentSoftware.getLay().next(TournamentSoftware.getCont());

        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);       
    }//end of paint   

}// end of MyMenuPanel class

