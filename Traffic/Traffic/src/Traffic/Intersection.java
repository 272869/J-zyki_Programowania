package Traffic;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Intersection {
    private ArrayList<Car> cars = new ArrayList<>();
    int xLeft,xRight,xUp,xDown; //punkty spawnowania na każdej drodze
    int yLeft,yRight,yUp, yDown;
    int leftLen, rightLen, upLen, downLen; //dlugosci drog
    enum trafficLight {VERTICAL, HORIZONTAL}
    static trafficLight trafficLight= Intersection.trafficLight.VERTICAL;
    public Intersection(int xRight, int yRight, int xLeft,int yLeft, int xUp,int yUp, int xDown, int yDown){
        this.xRight=xRight;
        this.xLeft=xLeft;
        this.xUp=xUp;
        this.xDown=xDown;
        this.yRight=yRight;
        this.yLeft=yLeft;
        this.yUp=yUp;
        this.yDown=yDown;
        leftLen=(xRight-xLeft)/2;
        rightLen=(xRight-xLeft)/2;
        upLen=(yDown-yUp)/2;
        downLen=(yDown-yUp)/2;
    }
    public int returnDirectionX(direction direction){ //zwraca x gdzie ma sie zespawnowac auto w zaleznosci od kierunku
        if(direction == Traffic.direction.right)return xRight;
        if(direction == Traffic.direction.left)return xLeft;
        if(direction == Traffic.direction.up)return xUp;
        else return xDown;
    }
    public int returnDirectionY(direction direction){
        if(direction == Traffic.direction.right)return yRight;
        if(direction == Traffic.direction.left)return yLeft;
        if(direction == Traffic.direction.up)return yUp;
        else return yDown;
    }
    public int returnDirectionLen(direction direction){
        if(direction == Traffic.direction.right)return rightLen;
        if(direction == Traffic.direction.left)return leftLen;
        if(direction == Traffic.direction.up)return upLen;
        else return downLen;
    }
    public void addCar(direction direction){
        int x = returnDirectionX(direction);
        int y = returnDirectionY(direction);
        Car car = new Car(x,y,direction);
        cars.add(car);
    }
    public void removeCar(Car car){
        cars.remove(car);
    }
    public void spawnCar(direction direction){
        if(!canSpawnCar(direction)) return;
        addCar(direction);
    }
    public boolean canSpawnCar(direction direction){
       int carNumber = countCarsBeforeIntersection(direction);
        if(carNumber < returnDirectionLen(direction)/40-1)return true;
        else return false;
    }
    public int countCarsBeforeIntersection(direction direction){
        int carNumber=0;
        for(Car car : cars){
            direction carDirection = car.getDirection();
            int carX = car.getX();
            int carY = car.getY();
            if(carDirection==direction && isCarBeforeIntersection(carX,carY,carDirection)) carNumber++;
        }
        return carNumber;
    }
    public int countCarsAfterIntersection(direction direction){
        int carNumber=0;
        for(Car car : cars){
            direction carDirection = car.getDirection();
            int carX = car.getX();
            int carY = car.getY();
            if(carDirection==direction && !isCarBeforeIntersection(carX,carY,carDirection)) carNumber++;
        }
        return carNumber;
    }
    public boolean isCarBeforeIntersection(int x, int y, direction direction){
        int interX=intersectionX(direction);
        int interY=intersectionY(direction);
        if(x<interX && y==interY) return true;
        if(y<interY && x==interX) return true;
        return false;
    }
    public int intersectionX(direction direction){
        if(direction== Traffic.direction.left) return xLeft+leftLen;
        if(direction== Traffic.direction.right) return xRight+rightLen;
        if(direction== Traffic.direction.up) return xUp;
        else return xDown;
    }
    public int intersectionY(direction direction){
        if(direction== Traffic.direction.left) return yLeft;
        if(direction== Traffic.direction.right) return yRight;
        if(direction== Traffic.direction.up) return yUp+upLen;
        else return yDown+downLen;
    }

    public void updateCars(){
        System.out.println("ilosc sam: "+cars.size());
        for(Car car : cars){
            direction direction= car.getDirection();
            int carsBefore = countCarsBeforeIntersection(direction);
            int carsAfter = countCarsAfterIntersection(direction);
            int x = car.getX();
            int y = car.getY();
            if(canCarDrive(x,y, direction)==true && ((isCarBeforeIntersection(x,y,direction) && carsBefore<returnDirectionLen(direction)/40-1) ||
                    (!isCarBeforeIntersection(x,y,direction) && carsAfter<returnDirectionLen(direction)/40-1))){
                car.run();
            }
            System.out.println("x: "+x+" y: "+y);
        }
    }
    public boolean canCarDrive(int x, int y, direction direction){
        if(trafficLight == Intersection.trafficLight.HORIZONTAL && (direction== Traffic.direction.left ||direction== Traffic.direction.right)) return true;
        if(trafficLight == Intersection.trafficLight.VERTICAL && (direction== Traffic.direction.up ||direction== Traffic.direction.down)) return true;
        return false;
    }
    public void changeLight() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // Zmiana świateł co 5 sekund
                if (trafficLight == trafficLight.VERTICAL) {
                    trafficLight = trafficLight.HORIZONTAL;
                    // Tutaj możesz dodać kod związany z obsługą poziomych świateł
                } else {
                    trafficLight = trafficLight.VERTICAL;
                    // Tutaj możesz dodać kod związany z obsługą pionowych świateł
                }
            }
        };
        // Uruchomienie zadania zmiany świateł co 5 sekund
        timer.scheduleAtFixedRate(task, 0, 5000); // 5000 milisekund = 5 sekund

    }

    public ArrayList<Car> getCars() {
        return cars;
    }
}
