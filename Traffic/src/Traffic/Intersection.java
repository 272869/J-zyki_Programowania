package Traffic;
import java.util.ArrayList;
public class Intersection {
    private ArrayList cars = new ArrayList<>();
    int xLeft,xRight,xUp,xDown;
    int yLeft,yRight,yUp, yDown;
    private final Object lock = new Object();
    public Intersection(int xRight, int yRight, int xLeft,int yLeft, int xUp,int yUp, int xDown, int yDown){
        this.xRight=xRight;
        this.xLeft=xLeft;
        this.xUp=xUp;
        this.xDown=xDown;
        this.yRight=yRight;
        this.yLeft=yLeft;
        this.yUp=yUp;
        this.yDown=yDown;
    }
    public void enterIntersection(String carName) {
        synchronized (lock) {
            System.out.println(carName + " enters the intersection.");
        }
    }
    public void addCar(int x, int y, direction direction){
        Car car = new Car(x,y,direction);
        cars.add(car);
    }
    public void exitIntersection(String carName) {
        synchronized (lock) {
            System.out.println(carName + " exits the intersection.");
        }
    }
}
