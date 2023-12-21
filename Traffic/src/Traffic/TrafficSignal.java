package Traffic;

public class TrafficSignal implements Runnable {
    private final Intersection intersection;

    public TrafficSignal(Intersection intersection) {
        this.intersection = intersection;
    }
    public void changeLight(){
        
    }

    @Override
    public void run() {
        while () {
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
