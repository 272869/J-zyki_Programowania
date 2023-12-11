import java.awt.*;

public class Car {
    private int position_x;
    public Car(int x){
        position_x=x;
    }
    public void move() {
        position_x++;
    }
    public int getX() {
        return position_x;
    }
}

