import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Road extends JPanel implements ActionListener {
    Image image;
    TrafficLight trafficLight = new TrafficLight();
    Graphics2D device, buffer;
    private int delay = 70, lightChangeDelay = 5000;
    private Timer timer, lightTimer;
    private List<Pedestrian> PedestrianList;
    private List<Car> CarList;
    Pedestrian pedestrian;
    Car car;
    private double distance;
    public Road() {
        setBackground(Color.WHITE);
        setBounds(10, 10, 500, 300);
        setLayout(null);
        timer = new Timer(delay, this);
        //lightTimer = new Timer(lightChangeDelay, e -> changeLights());
        //lightTimer.start();
        PedestrianList = new ArrayList<>();
        CarList = new ArrayList<>();
    }
    void changeLights() {
        trafficLight.isGreenLight = !trafficLight.isGreenLight; // zmiana koloru świateł na przeciwny
        System.out.println("Light green: "+ trafficLight.getIsGreenLight());
    }
    public void initialize() {
        int width = getWidth();
        int height = getHeight();
        image = createImage(width, height);
        buffer = (Graphics2D) image.getGraphics();
        buffer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        device = (Graphics2D) getGraphics();
        device.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }
    void addSam() {
        car = new Car(buffer, delay, getWidth(), getHeight(), 0);
        CarList.add(car);
        timer.addActionListener(car);
        new Thread(car).start(); //Wątek dla samochodów
        car = new Car(buffer, delay, getWidth(), getHeight(), 1);
        CarList.add(car);
        timer.addActionListener(car);
        new Thread(car).start(); //Wątek dla samochodów
    }
    void addPiesi() {
        pedestrian = new Pedestrian(buffer, delay, getWidth(), getHeight(),0);
        PedestrianList.add(pedestrian);
        timer.addActionListener(pedestrian);
        new Thread(pedestrian).start(); //Wątek dla piesi
        pedestrian = new Pedestrian(buffer, delay, getWidth(), getHeight(),1);
        PedestrianList.add(pedestrian);
        timer.addActionListener(pedestrian);
        new Thread(pedestrian).start(); //Wątek dla piesi
    }
    public void moveAllAgents() {
        int minCarDistance = 80; // Minimalny dystans między samochodami
        int minPedestrianDistance = 30; // Minimalny dystans między pieszymi
        int collisionDistance =60 ; // Minimalny dystans między samochodami a pieszymi
        // Kontrola kolizji między pieszymi
        for (Pedestrian currentPedestrian : PedestrianList) {
            for (Pedestrian otherPedestrian : PedestrianList) {
                if (currentPedestrian != otherPedestrian) {
                    double distance = calculateDistance(currentPedestrian.getBounds(), otherPedestrian.getBounds());
                    if (distance < minPedestrianDistance) {
                        currentPedestrian.turn();
                        break;
                    }
                }
            }
        }
        /*if (trafficLight.isGreenLight) {
            // Jeśli są zielone światła, ruch samochodów
            for (Car car : CarList) {
                car.keepMoving();
            }
            for (Pedestrian pedestrian : PedestrianList) {
                if((pedestrian.getDirection()==0 && pedestrian.getBounds().y==100) || (pedestrian.getDirection()==1 && pedestrian.getBounds().y==180)) pedestrian.stay();
            }
        } else {
            // Jeśli są czerwone światła, ruch pieszych
            for (Car car : CarList) {
                if((car.getDirection()==0 && car.getBounds().x == 180) || (car.getDirection()==1 && car.getBounds().x==240)) car.stay();
            }
            for (Pedestrian pedestrian : PedestrianList) {
                pedestrian.keepMoving();
            }
        }*/
        // Kontrola kolizji między samochodami
        for (Car currentCar : CarList) {
            for (Car otherCar : CarList) {
                if (currentCar != otherCar) {
                    distance = calculateDistance(currentCar.getBounds(), otherCar.getBounds());
                    if(currentCar.getDirection() == otherCar.getDirection()){
                        if (distance < minCarDistance) {
                            if (!currentCar.isStopped() ) {
                                if((currentCar.getBounds().x < otherCar.getBounds().x) && currentCar.getDirection() == 0) currentCar.stay();
                                else if((currentCar.getBounds().x < otherCar.getBounds().x) && currentCar.getDirection() == 1) otherCar.stay();
                            }
                        } else {
                            otherCar.keepMoving();
                        }
                    }
                }
            }
        }
        // Kontrola kolizji między samochodami a pieszymi
        for (Car currentCar : CarList) {
            for (Pedestrian currentPedestrian : PedestrianList) {
                double distance = calculateDistance(currentCar.getBounds(), currentPedestrian.getBounds());
                if (distance < collisionDistance) {
                    double CarCenterX = currentCar.getBounds().getCenterX();
                    double CarCenterY = currentCar.getBounds().getCenterY();
                    double piesiCenterX = currentPedestrian.getBounds().getCenterX();
                    double piesiCenterY = currentPedestrian.getBounds().getCenterY();

                    boolean intersectX = Math.abs(CarCenterX - piesiCenterX) < (currentCar.getBounds().getWidth() + currentPedestrian.getBounds().getWidth()) / 2 + 5;
                    boolean intersectY = Math.abs(CarCenterY - piesiCenterY) < (currentCar.getBounds().getHeight() + currentPedestrian.getBounds().getHeight()) / 2 + 5;
                    if(intersectY && intersectX){
                        currentPedestrian.stay();
                    }
                    else{
                        currentPedestrian.keepMoving();
                        if((currentCar.getDirection()==0 && (currentCar.getBounds().getCenterX() < currentPedestrian.getBounds().getCenterX())) || currentCar.getDirection()==1 && (currentCar.getBounds().getCenterX() > currentPedestrian.getBounds().getCenterX())) {
                            currentCar.stay();
                        }
                        else{
                            currentCar.keepMoving();
                        }
                    }
                }
            }
        }
    }
    private double calculateDistance(Rectangle rect1, Rectangle rect2) {
        double centerX1 = rect1.getCenterX();
        double centerY1 = rect1.getCenterY();
        double centerX2 = rect2.getCenterX();
        double centerY2 = rect2.getCenterY();
        return Math.sqrt(Math.pow(centerX2 - centerX1, 2) + Math.pow(centerY2 - centerY1, 2));
    }
    void animate() {
        if (timer.isRunning()) timer.stop();
        else timer.start();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        device.drawImage(image, 0, 0, null);
        buffer.clearRect(0, 0, getWidth(), getHeight());
        buffer.clearRect(0, 0, getWidth(), getHeight());
        moveAllAgents();
    }
}

