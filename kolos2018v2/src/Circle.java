import java.awt.Color;
import java.awt.Graphics;

public class Circle implements Comparable {

    int x, y;
    String nazwa;

    public Circle(String nazwa, int x, int y) {
        this.nazwa = nazwa;
        this.x = x;
        this.y = y;
    }

    void move(int x, int y) {
        this.x -= x;
        this.y -= y;
    }

    @Override
    public int compareTo(Object a) {
        Circle circle = (Circle) a;
        return nazwa.compareTo(circle.nazwa);

    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean isMouseOver(int mouseX, int mouseY) {
        return (x - mouseX) * (x - mouseX) + (y - mouseY) * (y - mouseY) <= 35 * 35;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nazwa == null) ? 0 : nazwa.hashCode());
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        Circle other = (Circle) obj;
        if (nazwa == null) {
            if (other.nazwa != null)
                return false;
        } else if (!nazwa.equals(other.nazwa))
            return false;
        return true;
    }

    void paint(Graphics g) {
        Color tmpColor = g.getColor();
        g.setColor(Color.black);
        g.drawString(this.nazwa, this.x, this.y);
        g.setColor(Color.GREEN);
        g.fillOval(x, y, 35, 35);
        g.setColor(tmpColor);
    }
}