package Lista3.zad2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class DrawingPanel extends JFrame implements MouseMotionListener {
    public DrawingPanel(){

        setTitle("Drawing Area");
        addMouseMotionListener(this);
        setSize(400,400);
        setLayout(null);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Graphics g = getGraphics();
        g.setColor(Color.BLACK);
        g.fillOval(e.getX(),e.getY(),5,5);

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

}