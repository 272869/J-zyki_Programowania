import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class RectangleDrawingApp extends JFrame {
    private JPanel drawingPanel;
    private List<RectangleThread> rectangles = new ArrayList<RectangleThread>();
    private int startX, startY, endX, endY;

    public RectangleDrawingApp() {
        setTitle("Rysowanie prostokątów");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (RectangleThread rectangleThread : rectangles) {
                    rectangleThread.draw(g);
                }
            }
        };

        drawingPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startX = e.getX();
                startY = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                endX = e.getX();
                endY = e.getY();

                RectangleThread newRectangleThread = new RectangleThread(drawingPanel, startX, startY, endX, endY);
                newRectangleThread.start();
                rectangles.add(newRectangleThread);
            }
        });

        add(drawingPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RectangleDrawingApp::new);
    }
}