import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {

    private static int WIDTH = 300;
    private static int HEIGHT = SimulationPanel.HEIGHT;

    public ControlPanel() throws HeadlessException {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        for(CircleComponent circle: SimulationPanel.circles){
            add(new ControlElement(circle));
        }
    }


}
