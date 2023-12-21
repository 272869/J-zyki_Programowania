package Traffic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TrafficSimulation {
    public static void main(String[] args) {
        Intersection intersection = new Intersection();
        TrafficSignal trafficSignal = new TrafficSignal(intersection);
        Car[] cars = new Car[10];
        Thread[] carThreads = new Thread[10];

        ExecutorService executor = Executors.newFixedThreadPool(12);

        // Uruchomienie sygnalizatora
        executor.execute(trafficSignal);

        // Uruchomienie samochodów
        for (int i = 0; i < 10; i++) {
            cars[i] = new Car();
            carThreads[i] = new Thread(cars[i]);
            executor.execute(carThreads[i]);
        }

        // Po pewnym czasie zatrzymaj symulację
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Zatrzymaj sygnalizator
        trafficSignal.stop();

        // Zatrzymaj samochody
        for (int i = 0; i < 10; i++) {
            carThreads[i].interrupt();
        }

        executor.shutdown();
    }
}