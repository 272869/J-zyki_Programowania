package Lista3.zad5;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class MyFrame extends JFrame implements MouseListener, MouseWheelListener, KeyListener {
    private Square square;
    JPanel mainPanel;
    public MyFrame() {
        square = new Square(30, 40, Color.cyan);
        setSize(400, 400);
        mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D graphics2D = (Graphics2D) g;
                square.draw(graphics2D);
            }
        };
        mainPanel.setBackground(Color.gray);
        mainPanel.addMouseWheelListener(this);
        mainPanel.addKeyListener(this);
        mainPanel.addMouseListener(this);
        add(mainPanel, BorderLayout.CENTER);
    }
    @Override
    public void keyPressed(KeyEvent e) {
        square.setColor(Color.red);
        mainPanel.revalidate();
        mainPanel.repaint();
    }
    @Override
    public void keyReleased(KeyEvent e) {
        square.setColor(Color.cyan);
        mainPanel.revalidate();
        mainPanel.repaint();
    }
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int notches = e.getWheelRotation();
        if (notches < 0) {
            square.setW(square.getW() + 5);
            square.setH(square.getH() + 5);
            mainPanel.repaint();
        } else {
            square.setW(square.getW() - 5);
            square.setH(square.getH() - 5);
            mainPanel.repaint();
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {System.out.println("Naciśnięto przycisk myszy");}
    @Override
    public void mouseReleased(MouseEvent e) {System.out.println("Zwolniono przycisk myszy");}
    @Override
    public void mouseEntered(MouseEvent e) {System.out.println("Najechano kursorem na obszar rysowania");}
    @Override
    public void mouseExited(MouseEvent e) {System.out.println("Opuszczono obszar rysowania kursorem");}
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void mouseClicked(MouseEvent e) {}
}
