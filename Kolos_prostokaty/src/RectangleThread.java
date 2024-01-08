import javax.swing.*;
import java.awt.*;

class RectangleThread extends Thread {
    private JPanel panel;
    private int x1, y1, x2, y2;

    public RectangleThread(JPanel panel, int x1, int y1, int x2, int y2) {
        this.panel = panel;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(100); // zmień czas oczekiwania na potrzeby animacji
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
            panel.repaint();
        }
    }

    public void draw(Graphics g) {
        g.drawRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));
    }
}