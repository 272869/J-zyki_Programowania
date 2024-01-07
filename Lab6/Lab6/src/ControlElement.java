import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class ControlElement extends JPanel {
    private CircleComponent component;
    private JSlider slider;
    public ControlElement(CircleComponent component) {
        this.setPreferredSize(new Dimension(200,20));
        this.setMaximumSize(new Dimension(200,50));
        this.component = component;

        slider = new JSlider(0, 20);
        double speed = component.getBaseSpeed()*10;
        slider.setValue((int) speed);
        slider.setPaintTrack(true);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(5);
        slider.setMinorTickSpacing(1);

        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                double newSpeed = (double) slider.getValue() /10;
                component.setBaseSpeed(newSpeed);
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(new ColorFill(component.getColor()));
        add(slider);
    }

    private class ColorFill extends JPanel{
        Color color;
        public ColorFill(Color color) {
            setPreferredSize(new Dimension(30,30));
            this.color = color;
        }
        public void paintComponent(Graphics g){
            g.setColor(color);
            g.fillRect(0,0,30,30);
        }
    }
}
