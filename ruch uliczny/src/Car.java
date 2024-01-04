import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

public class Car implements Runnable, ActionListener {
    protected Graphics2D buffer; // do rysowania obiektów
    protected Area area;
    Road road = new Road();
    protected Shape shape;
    protected AffineTransform aft;
    private int dx, dy, direction; //prędkości
    private final int v0 = 0, v4=4, vMinus4 = -4, delay, width, height;
    private final Color color;
    private final Point[] startingPositions;
    private Point initialPosition;
    private boolean stopped = false;
    public boolean isStopped() {
        return stopped;
    }
    public Car(Graphics2D buf, int del, int w, int h, int dir){
        delay = del;
        buffer = buf;
        width = w;
        height = h;
        direction = dir;
        if(direction>1) direction=1;
        startingPositions = new Point[]{
                new Point(5, 120),
                new Point(490, 160),
        };
        if(direction==0)dx=v4;
        else dx = vMinus4;
        dy = v0;
        color = Color.BLACK;
        shape = new Rectangle2D.Float(0,0,30,20);
        aft = new AffineTransform();
        area = new Area(shape);
    }
    @Override
    public void run() {
        initialPosition = startingPositions[direction]; //ustalanie pozycji w zależności od kierunku
        aft.translate(initialPosition.getX(), initialPosition.getY()); //przesunięcie o wektor
        area.transform(aft);
        shape = area;
        while (true) {
            shape = nextFrame();
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
            }
        }
    }
    public Shape nextFrame() {
        if (!stopped) {
            area = new Area(area);
            aft = new AffineTransform();
            Rectangle bounds = area.getBounds();
            int carCenterX = bounds.x + bounds.width / 2;
            if (carCenterX > width + 15) stay();
            aft.translate(dx, dy);
            area.transform(aft);
        }
        return area;
    }
    /*public Shape nextFrame() {
        if (!stopped) {
            area = new Area(area);
            aft = new AffineTransform();
            Rectangle bounds = area.getBounds();
            int carCenterX = bounds.x + bounds.width / 2;
            if (direction == 0 && carCenterX >= 180 && !road.trafficLight.getIsGreenLight()) stay(); // Zatrzymaj samochody z lewej strony na x = 180, gdy światła dla nich są czerwone
            if (direction == 1 && carCenterX <= 240 && !road.trafficLight.getIsGreenLight()) stay(); // Zatrzymaj samochody z prawej strony na x = 240, gdy światła dla nich są czerwone
            if (!stopped) {
                if (carCenterX > width + 15) stay();
                aft.translate(dx, dy);
                area.transform(aft);
            }
        }
        return area;
    }*/
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        buffer.setColor(color);
        buffer.fill(shape);
        buffer.draw(shape);
    }
    public void stay() {
        stopped = true;
    }
    public void keepMoving(){
        stopped = false;
    }
    public int getDirection() {
        return direction;
    }
    public Rectangle getBounds() {
        return area.getBounds();
    }

}
