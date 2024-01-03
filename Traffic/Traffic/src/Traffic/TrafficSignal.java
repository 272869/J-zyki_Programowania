package Traffic;

import javax.swing.*;
import java.awt.*;

public class TrafficSignal extends JPanel implements Runnable {
    private final Intersection intersection;

    public TrafficSignal(Intersection intersection) {
        this.intersection = intersection;
    }
    public void changeLight(){
        
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Rysowanie koła
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int radius = 100;
        g.setColor(Color.RED);
        g.fillOval(centerX - radius, centerY - radius, 2 * radius, 2 * radius);
    }
    @Override
    public void run() {
        while(true){
            // Logika zmiany świateł
            try {
                Thread.sleep(5000); // Symulacja zmiany świateł co 5 sekund
                System.out.println("Traffic signal changes");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
