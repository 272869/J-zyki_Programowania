import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DrawingPanel extends JPanel implements KeyListener, MouseListener, MouseMotionListener, ActionListener {

    private static final long serialVersionUID = 652320242071006554L;
    private JButton addRandomCircle = new JButton("Dodaj losowe k�ko");
    private JButton removeCircles = new JButton("Usu� k�ka");

    private Random rand = new Random();
    private Set<Circle> setOfCircles = new TreeSet<Circle>();
    private ViewCollection view = new ViewCollection(setOfCircles, 200, 200);

    private Circle circleUnderCursor = null;

    private int mouseX = 0;
    private int mouseY = 0;

    public DrawingPanel() {
        addRandomCircle.addActionListener(this);
        removeCircles.addActionListener(this);
        this.add(addRandomCircle);
        this.add(removeCircles);
        this.add(view);

        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        requestFocus();
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(setOfCircles.size() > 0) {
            for(Circle c : setOfCircles) {
                c.draw(g);
            }
        }
    }

    public Circle circleUnderCursor(int x, int y) {
        for(Circle c : setOfCircles) {
            if(c.circleUnderCursor(x, y)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        circleUnderCursor = circleUnderCursor(e.getX(), e.getY());
        if(circleUnderCursor != null) {
            moveCircle(e.getX()- mouseX, e.getY()- mouseY, circleUnderCursor);
        }else {
            moveAllCircles(e.getX() - mouseX, e.getY() - mouseY);
        }
        mouseX = e.getX();
        mouseY = e.getY();
        repaint();
    }

    private void moveAllCircles(int x, int y) {
        for(Circle c : setOfCircles) {
            moveCircle(x, y, c);
        }
    }

    public void moveCircle(int x, int y, Circle circle) {
        circle.setX(circle.getX()+x);
        circle.setY(circle.getY()+y);
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        setMousePosition(e.getX(), e.getY());
    }

    private void setMousePosition(int x, int y) {
        mouseX = x;
        mouseY = y;
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int dist;
        if(e.isShiftDown()) {
            dist = 10;
        }else {
            dist = 1;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            moveAllCircles(-dist, 0);
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            moveAllCircles(dist, 0);
        }
        if(e.getKeyCode() == KeyEvent.VK_UP) {
            moveAllCircles(0, -dist);
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            moveAllCircles(0, dist);
        }
        if(e.getKeyCode() == KeyEvent.VK_DELETE) {
            setOfCircles.clear();
            view.refreshView();
            repaint();
        }
        if(e.getKeyCode() == KeyEvent.VK_C) {
            String name = JOptionPane.showInputDialog(this, "Podaj nazw� dla okr�gu: ", "Nazwa okr�gu");
            Circle tempCirc = new Circle(name, rand.nextInt(500), rand.nextInt(500), 20);
            setOfCircles.add(tempCirc);
            view.refreshView();
            repaint();
        }

    }

    @Override
    public void keyReleased(KeyEvent arg0) {

    }

    @Override
    public void keyTyped(KeyEvent arg0) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();

        if(source == addRandomCircle) {
            String name = JOptionPane.showInputDialog(this, "Podaj nazw� dla okr�gu: ", "Nazwa okr�gu");
            Circle tempCirc = new Circle(name, rand.nextInt(500), rand.nextInt(500), 20);
            setOfCircles.add(tempCirc);
            view.refreshView();
            repaint();
        }
        if(source == removeCircles) {
            setOfCircles.clear();
            view.refreshView();
            repaint();
        }
        this.requestFocus();
    }

}