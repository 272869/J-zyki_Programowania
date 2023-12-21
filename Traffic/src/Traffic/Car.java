package Traffic;
enum direction {right, left, up, down}
public class Car implements Runnable {
    static direction direction;
    private int x,y,speed=40,width=40,height=40;

    public Car(int x, int y, direction direction) {
        this.direction= direction;
        this.x=x;
        this.y=y;
    }
    private void move(){
        if(direction== Car.direction.right){
            x+=speed;
        }else if (direction== Car.direction.left) {
            x-=speed;
        }else if (direction== Car.direction.up) {
            y-=speed;
        }else {
            y+=speed;
        }
    }
    @Override
    public void run() {
        while (true) {
            try {
                move();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
