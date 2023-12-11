package Lista3.zad3;

import java.awt.*;

public class Figure {
    int x, y;
    private  int width = 50;
    private  int height = 50;
    public Figure(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public boolean isClicked(int clickX, int clickY) {
        return (clickX >= x && clickX <= x + width && clickY >= y && clickY <= y + height);
    }
    public void moveTo(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }
    public void draw(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(x, y, width, height);
    }
}