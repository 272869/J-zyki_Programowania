package Lista3.zad5;

import java.awt.*;

public class Square {
    private int w=40,h=40;
    private int x,y;
    private Color color;

    public Square(int x, int y,Color color){
        this.x=x;
        this.y=y;
        this.color=color;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public void setW(int w) {
        this.w = w;
    }

    public void setH(int h) {
        this.h = h;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    public void draw(Graphics2D g){
        g.setColor(color);
        g.fillOval(x,y,w,h);
    }
}
