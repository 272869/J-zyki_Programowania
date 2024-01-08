import javax.swing.*;
import java.awt.*;

class RectangleThread extends Thread {
    private JPanel panel;
    private int x1, y1, x2, y2;
    private int velocity = 5; // Szybkość przesuwania prostokąta
    private boolean movingRight = true;

    public RectangleThread(JPanel panel, int x1, int y1, int x2, int y2) {
        this.panel = panel;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    private void moveRectangle() {
        if (movingRight) {
            x1 += velocity;
            x2 += velocity;
            if (x1 >= panel.getWidth()) { // Jeśli dotknie prawej krawędzi
                x1 = -Math.abs(x2 - x1);
                x2 = 0;
            }
        }
    }
    public void run() {
        while (true) {
            try {
                Thread.sleep(50); // zmień czas oczekiwania na potrzeby animacji
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
            moveRectangle();
            panel.repaint();
        }
    }

    public void draw(Graphics g) {
        g.drawRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));
    }
}