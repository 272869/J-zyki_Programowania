package Lista3.zad1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DrawingAreaWithTextField extends JFrame  {
    private JPanel drawingPanel;
    private JTextField textField;

    public DrawingAreaWithTextField(){
        setTitle("Drawing Area with Text Field");
        /*drawingPanel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Graphics g = getGraphics();
                g.setColor(Color.BLACK);
                g.fillOval(e.getX(),e.getY(),5,5);
            }
            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });*/
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400,400);

        textField = new JTextField();
        textField.setBounds(0,0,400,30);
        add(textField);

        drawingPanel = new JPanel();
        drawingPanel.setBounds(0,30,400,370);
        drawingPanel.setBackground(Color.pink);
        drawingPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                textField.setText("Co-ordinates: "+x+", "+y);
            }
        });
        add(drawingPanel);
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText("");
            }
        });

    }

}
