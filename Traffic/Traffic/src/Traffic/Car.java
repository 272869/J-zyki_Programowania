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
            x+=speed;
        }else if (direction== Car.direction.up) {
            y+=speed;
        }else {
            y+=speed;
        }
    }
    @Override
    public void run() {
        move();
        //while true queue.isred try queue.wait, w swiatlach notifyall czy zmienilo sie swiatlo
        //while swiatlo zielone jedz
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public static direction getDirection() {
        return direction;
    }
}
