package pl.kamilagronska;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ML extends MouseAdapter {

    public float x = -1.0f, y = -1.0f;

    @Override
    public void mouseMoved(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();
    }

}
