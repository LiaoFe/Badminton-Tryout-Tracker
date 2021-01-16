import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.*;
/**
 * This is the image panel that appears when clicking a PlayerButton. The user will see a picture of the player if they upload one, or the default picture that is set.
 * 
 * @author Felix Liao
 *
 * @version 13 March 26, 2020
 * 
 */
class ImagePanel extends JPanel{

    private Image image;
    private Image image2;
    private BufferedImage scaledImage;
    private JFileChooser fc = new JFileChooser();

    /**
     * The constructor for objects in class ImagePanel
     * 
     * @param fileName
     */
    public ImagePanel(String fileName) {
        this.setSize(280,400);
        try {      
            image2 = ImageIO.read(new File(fileName));
            image = image2.getScaledInstance(this.getWidth(),this.getHeight(),Image.SCALE_SMOOTH);
        } catch (IOException ex) {
            // handle exception...
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

    /**
     * Sets the image of this object by reading a file
     * 
     * @param file the file with the image being opened
     */
    protected String changeImage(File file)
    {
        try {                

            scaledImage= ImageIO.read(file);

            image = scaledImage.getScaledInstance(this.getWidth(),this.getHeight(),Image.SCALE_SMOOTH);
            return file.getAbsolutePath();
        } catch (IOException ex) {
            // handle exception...
        }   
        return "";
    }

}