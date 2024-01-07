import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;

public class Circle implements Comparable<Circle> {

    private String name;
    private int x;
    private int y;
    private int r;
    private Color bg = Color.WHITE;
    private Color fg = Color.BLACK;

    public Circle(String name, int x, int y, int r) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public Color getBg() {
        return bg;
    }

    public void setBg(Color bg) {
        this.bg = bg;
    }

    public Color getFg() {
        return fg;
    }

    public void setFg(Color fg) {
        this.fg = fg;
    }

    public void draw(Graphics g) {
        g.setColor(this.bg);
        g.fillOval(x-r, y-r, 2*r, 2*r);
        g.setColor(this.fg);
        g.drawOval(x-r, y-r, 2*r, 2*r);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bg == null) ? 0 : bg.hashCode());
        result = prime * result + ((fg == null) ? 0 : fg.hashCode());
        result = prime * result + r;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Circle other = (Circle) obj;
        if (bg == null) {
            if (other.bg != null)
                return false;
        } else if (!bg.equals(other.bg))
            return false;
        if (fg == null) {
            if (other.fg != null)
                return false;
        } else if (!fg.equals(other.fg))
            return false;
        if (r != other.r)
            return false;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }

    @Override
    public int compareTo(Circle o) {
        return name.compareTo(o.name);
    }

    public boolean circleUnderCursor(int x, int y) {
        return (this.x - x)*(this.x - x) + (this.y - y)*(this.y - y) <= r*r;
    }

}