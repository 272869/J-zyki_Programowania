import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class Samochod implements Runnable, ActionListener {
    protected Graphics2D buffer; // do rysowania obiektów
    protected Area area;
    protected Shape shape;
    private int direction;
    protected AffineTransform aft;
    private int dx, dy; //prędkości
    private final int predkosc_zero = 0;
    private final int predkosc_cztery = 4;
    private final int predkosc_minus_cztery = -4;
    private final int delay;
    private final int width;
    private final int height;
    private final Color color;
    private final Point[] startingPositions;
    private Point initialPosition;
    private boolean stopped = false;
    public boolean isStopped() {
        return stopped;
    }
    public Samochod(Graphics2D buf, int del, int w, int h, int dir){
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
        if(direction==0)dx=predkosc_cztery;
        else dx=predkosc_minus_cztery;
        dy = predkosc_zero;
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
    public void stay() {
        stopped = true;
    }
    public void keepMoving(){
        stopped = false;
    }
    public Rectangle getBounds() {
        return area.getBounds();
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
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        buffer.setColor(color);
        buffer.fill(shape);
        buffer.draw(shape);
    }

    public int getDirection() {
        return direction;
    }

}
