package za.ac.cput.unihomeapp.gui;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author jadar
 */
public class CirclePanel extends JPanel{
    private Color color;
    
    public CirclePanel(Color color){
        this.color = color;
        setOpaque(false);//Make the panel transparent except the circle
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(color);
        g.fillOval(0,0, getWidth(), getHeight());//Draw a perfect circle
    }
}
