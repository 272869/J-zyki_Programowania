package pl.kamilagronska;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.util.Random;

public abstract class Item implements Runnable, ActionListener {

    protected Graphics2D buf;
    protected Area area;
    protected Shape shape;
    protected int delay;
    protected Color color;
    protected AffineTransform transform;
    protected int x,y;
    protected int dx=0,dy=0;
    Random random = new Random();
    protected Player player;

    public Item(int delay, Graphics2D buf,Player player) {
        this.delay = delay;
        this.buf = buf;
        this.player = player;

    }
    /*public Shape nextFrame(){
        if (AnimationPanel.isIsRunning()) {
            dy = random.nextInt(10);
            y +=dy;
            transform = new AffineTransform();
            area = new Area(area);
            transform.translate(dx, dy);
            area.transform(transform);
        }
        return area;
    }*/
}
