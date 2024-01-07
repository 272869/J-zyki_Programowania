package pl.kamilagronska;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Point extends Item {
    private final int w = 20,h=20;
    public boolean isTaken = false;


    public Point(int delay, Graphics2D buf,Player player) {
        super(delay, buf,player);
        x=random.nextInt(600);
        color = Color.CYAN;
        shape = new Ellipse2D.Float(x,y,w,h);
        transform = new AffineTransform();
        area = new Area(shape);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        buf.setColor(color);
        buf.fill(shape);
    }

    @Override
    public void run() {//dodać poruszanie sie w dół tak jak przeszkody
        while (true) {
            // przygotowanie nastepnego kadru
            shape = nextFrame();
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
            }
        }

    }
    public Shape nextFrame(){
        if (AnimationPanel.isIsRunning() && x<600 && y<600) {
            dy = random.nextInt(10);
            y +=dy;
            transform = new AffineTransform();
            area = new Area(area);
            transform.translate(dx, dy);

            if (shape.intersects((int)player.getPx(),(int)player.getPy(),player.getW(),player.getH())&&isTaken==false){
                isTaken = true;
                player.score++;
                color = Color.BLUE;
                buf.fill(shape); //zmienić sposób znikania punktów z planszy
                AnimationApp.jLabel.setText("Score: " + player.score);
            }
            area.transform(transform);
        }
        return area;
    }



}
