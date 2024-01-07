import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class SimulationPanel extends JPanel implements ActionListener {

    static int amnt = 1;
    static final int WIDTH = 600;
    static final int HEIGHT = 600;
    static final int DELAY = 20;

    Timer timer;

    public static ArrayList<CircleComponent> circles = new ArrayList<>(amnt);
    ArrayList<Thread> threads = new ArrayList<>();

    public SimulationPanel() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.black);
        startSimulation();
    }
    public void startSimulation(){
        for(int i = 0; i < amnt; i++){
            CircleComponent circleComponent = new CircleComponent(WIDTH, HEIGHT);
            circles.add(circleComponent);
        }
        calculateBehind();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    private void calculateBehind() {
        CircleComponent last = null;
        CircleComponent first = circles.get(0);
        for (CircleComponent component: circles){
            if(first.getAngle() > component.getAngle()){
                first = component;
            }
            int posAngle = 999;
            int id = -1;
            for(int i = 0; i < circles.size(); i++){
                if(component.getId() == i) continue;
                if(circles.get(i).getAngle() > component.getAngle()){
                    if(posAngle > (int)circles.get(i).getAngle()){
                        id = circles.get(i).getId();
                        posAngle = (int)circles.get(i).getAngle();
                    }
                }
            }
            if(posAngle == 999){
                last = component;
            }
            component.setBehind(id);
        }
        assert last != null;
        last.setBehind(first.getId());
    }

    public void mainThread(){
        threads.clear();
        circles.forEach(circleComponent -> threads.add(new Thread(circleComponent)));
        for (int i = 0; i < circles.size(); i++){
            threads.get(i).start();
            try {
                threads.get(i).join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        g.setColor(Color.white);
        g.drawOval(10, 10, WIDTH-20, HEIGHT-20);
        for(CircleComponent circleComponent: circles){
            circleComponent.draw(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainThread();
        repaint();
    }
}
