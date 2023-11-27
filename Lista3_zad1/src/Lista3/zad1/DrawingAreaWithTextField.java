package Lista3.zad1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DrawingAreaWithTextField extends JFrame implements MouseMotionListener,MouseListener  {
    private JPanel drawingPanel;
    private JTextField textField;

    public DrawingAreaWithTextField(){
        setTitle("Drawing Area with Text Field");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400,400);

        textField = new JTextField();
        textField.setBounds(0,0,400,30);
        add(textField);
        drawingPanel = new JPanel();
        drawingPanel.setBounds(0,30,400,370);
        drawingPanel.setBackground(Color.pink);
        drawingPanel.addMouseMotionListener(this);
        drawingPanel.addMouseListener(this);
        add(drawingPanel);
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText("");
            }
        });
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        Graphics g = getGraphics();
        g.setColor(Color.BLACK);
        g.fillOval(e.getX(),e.getY(),5,5);
    }
    @Override
    public void mouseMoved(MouseEvent e) {}
    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        textField.setText("Co-ordinates: "+x+", "+y);
    }
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}
