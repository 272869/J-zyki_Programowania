import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Simulation {
    private final View view;
    private Timer simulationTimer;
    private static final int SIMULATION_DELAY = 1000;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Simulation());
    }
    public Simulation(){
        Traffic traffic = new Traffic();
        view = new View();
        startSimulation();
    }
    private void startSimulation() {
        simulationTimer = new Timer(SIMULATION_DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateView();
            }
        });
        simulationTimer.start();
    }
    private void updateView() { //update widoku
        view.update_view();
    }
}
