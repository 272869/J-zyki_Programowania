package Lista3.zad3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
public class PanelWithClickCounter extends JFrame implements MouseListener {
    private Figure figure;
    private HashMap<Figure, Long> clickTimes;
    JPanel mainPanel;
    public PanelWithClickCounter() {
        figure = new Figure(50, 50);
        mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D graphics2D = (Graphics2D) g;
                figure.draw(graphics2D);
            }
        };
        add(mainPanel, BorderLayout.CENTER);
        setSize(400,400);
        mainPanel.addMouseListener(this);
        clickTimes = new HashMap<>();
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        int clickX = e.getX();
        int clickY = e.getY();
        boolean figureClicked = figure.isClicked(clickX, clickY);
        if (figureClicked) {
            Long lastClickTime = clickTimes.get(figure);
            long currentTime = System.currentTimeMillis();

            if (lastClickTime != null) {
                long timeDiff = currentTime - lastClickTime;
                int newX = figure.x + (int) (Math.random() * 50); // Losowe przesunięcie w osi X
                int newY = figure.y + (int) (Math.random() * 50); // Losowe przesunięcie w osi Y
                figure.moveTo(newX, newY);
                repaint();
                System.out.println("Czas od poprzedniego kliknięcia: " + timeDiff + " ms");
            }
            clickTimes.put(figure, currentTime);
        }
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
