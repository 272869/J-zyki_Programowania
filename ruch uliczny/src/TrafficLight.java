import javax.swing.*;
import java.awt.*;

public class TrafficLight extends JPanel {
    public boolean isGreenLight = true;
    public TrafficLight(){}
    public boolean getIsGreenLight() { return isGreenLight; }
    public void setIsGreenLight(boolean greenLight) {
        isGreenLight = greenLight;
        repaint();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int radius = 20;
        g.setColor(Color.BLACK);
        g.drawOval(400, 50, 2 * radius, 2 * radius);
        if (isGreenLight) g.setColor(Color.GREEN);
        else g.setColor(Color.RED);
        g.fillOval(400, 50, 2 * radius, 2 * radius);
    }
}
