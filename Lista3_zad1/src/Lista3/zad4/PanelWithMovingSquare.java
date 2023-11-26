package Lista3.zad4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class PanelWithMovingSquare extends JFrame implements KeyListener {
    private int squareX = 50;
    private int squareY = 50;
    private final int squareSize = 50;
    JPanel mainPanel;

    public PanelWithMovingSquare() {
        setSize(400,400);
        mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.blue);
                g.fillRect(squareX, squareY, squareSize, squareSize);
            }
        };
        add(mainPanel, BorderLayout.CENTER);
        setBackground(Color.white);
        addKeyListener(this);
        setFocusable(true);
        requestFocus();
    }

    private void moveSquare(int dx, int dy) {
        squareX += dx;
        squareY += dy;
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                moveSquare(0, -10);
                break;
            case KeyEvent.VK_DOWN:
                moveSquare(0, 10);
                break;
            case KeyEvent.VK_LEFT:
                moveSquare(-10, 0);
                break;
            case KeyEvent.VK_RIGHT:
                moveSquare(10, 0);
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
