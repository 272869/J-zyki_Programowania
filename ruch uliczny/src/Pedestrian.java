

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class Pedestrian implements Runnable, ActionListener {
    protected Graphics2D buffer;
    protected Area area;
    Road road = new Road();
    protected Shape shape;
    protected AffineTransform aft;
    private int dx, dy;
    private final int delay, width, height;
    private final Color color;
    private final Point[] startingPositions;
    private final int predkosc=2;
    int direction;
    private static int numer = 0;
    private static int znak = 0;
    private boolean stopped = false;
    public boolean isStopped() {
        return stopped;
    }
    protected static final Random rand = new Random();
    public Pedestrian(Graphics2D buf,int del, int w ,int h,int dir){
        delay = del;
        buffer = buf;
        width = w;
        height = h;
        direction = dir;
        if(direction>1) direction=1;
        startingPositions = new Point[]{
                new Point(200, 10),
                new Point(220, 290),
        };
        if(direction==0) dy=predkosc;
        else dy=-predkosc;
        color = Color.RED;
        shape = new Ellipse2D.Float(0,0,10,10);
        aft = new AffineTransform();
        area = new Area(shape);
    }
    @Override
    public void run() {
        Point initialPosition = startingPositions[direction];
        aft.translate(initialPosition.getX(), initialPosition.getY());
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
    public void stay(){
        stopped = true;
    }
    public void keepMoving(){
        stopped = false;
    }
    public void turn() {
        if (numer++ % 2 == 0) {
            if(znak++ % 2 == 0) {
                dx = 0;
                dy = predkosc;
            }
            else {
                dx = 0;
                dy = -predkosc;
            }
        }
    }
    public Rectangle getBounds() {
        return area.getBounds();
    }
    /*public Shape nextFrame(){
        if(!stopped) {
            area = new Area(area);
            aft = new AffineTransform();
            Rectangle bounds = area.getBounds();
            int cx = bounds.x + bounds.width / 2;
            int cy = bounds.y + bounds.height / 2;
            if (cx < -7 || cx > width + 7 || cy < -7 || cy > height + 7) {
                stay();
            }
            aft.translate(dx, dy);
            area.transform(aft);
        }
        return area;
    }*/
    public Shape nextFrame(){
        if(!stopped) {
            area = new Area(area);
            aft = new AffineTransform();
            Rectangle bounds = area.getBounds();
            int centerX = bounds.x + bounds.width / 2;
            int centerY = bounds.y + bounds.height / 2;
            // Zatrzymaj pieszych idących z góry na y = 100, gdy światła dla nich są czerwone
            if (direction == 0 && centerY >= 100 && !road.getIsGreenLight()) {
                stay();
            }
            // Zatrzymaj pieszych idących z dołu na y = 180, gdy światła dla nich są czerwone
            if (direction == 1 && centerY <= 180 && !road.getIsGreenLight()) {
                stay();
            }
            if (!stopped) {
                if (centerX < -7 || centerX > width + 7 || centerY < -7 || centerY > height + 7) {
                    stay();
                }
                aft.translate(dx, dy);
                area.transform(aft);
            }
        }
        return area;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        buffer.setColor(color);
        buffer.fill(shape);
        buffer.draw(shape);
    }
}

