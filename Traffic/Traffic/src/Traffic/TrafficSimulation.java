package Traffic;

import javax.swing.*;

public class TrafficSimulation {
    View view;
    private final int TIME_STEP = 1000;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(TrafficSimulation::new);
    }
    public TrafficSimulation(){
        view = new View();
        view.setVisible(true);
        startSimulation();
    }
    private void startSimulation() {
        // Wątek do kontrolowania logiki czasowej
        Thread simulationThread = new Thread(() -> {
            while (true) {
                long start_time = System.currentTimeMillis();
                view.trafficS.updateTraffic(); // Aktualizacja stanu symulacji

                // Aktualizacja widoku
                SwingUtilities.invokeLater(view::updateView);

                long elapsed_time = System.currentTimeMillis() - start_time;
                long sleep_time = TIME_STEP - elapsed_time;
                if (sleep_time < 0) sleep_time = 0;
                try {
                    Thread.sleep(sleep_time);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        simulationThread.start(); // Uruchomienie wątku symulacji
    }
}