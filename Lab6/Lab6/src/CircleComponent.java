import java.awt.*;
import java.awt.geom.Point2D;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class CircleComponent extends Rectangle implements Runnable{

    //---------------------------------------------------------------
    private static int SIZE = 20;
    private static Set<Color> usedColors = new HashSet<>();
    private static Set<Integer> usedAngles = new HashSet<>();
    private static int circCount = 0;
    private static Random random = new Random();
    private static volatile Vector<Point2D> positions = new Vector<>();
    //---------------------------------------------------------------
    private int id;
    private int behind;
    private double baseSpeed;
    private volatile double actSpeed;
    private double angle ;
    private final Color color;
    private final int X;
    private final int Y;
    private volatile Point2D position;

    public CircleComponent(int X, int Y) {
        this.id = circCount++;
        this.X = X;
        this.Y = Y;
        this.angle = randAngle();
        this.baseSpeed = randSpeed();
        this.actSpeed = this.baseSpeed;
        this.color = randColor();
        Point2D pos = calculatePointOnCricle((double) (X/2 - SIZE/2),(double) (Y/2 - SIZE/2), (double) X/2-10, angle);
        positions.add(pos);
        position = pos;
    }

    public void draw(Graphics g){
        Point2D coordinates = calculatePointOnCricle((double) (X/2 - SIZE/2),(double) (Y/2 - SIZE/2), (double) X/2-10, angle);
        position = coordinates;
        g.setColor(color);
        g.fillOval((int)coordinates.getX(), (int)coordinates.getY() ,SIZE, SIZE);
    }

    public static synchronized void updatePosition(CircleComponent component){
        if(positions.size() > component.id){
            positions.set(component.id, component.position);
        }
    }
    public static Color randColor(){
        Color color;
        do{
            color = new Color(random.nextInt(220)+35,random.nextInt(220)+35,random.nextInt(220)+35);
        }while (usedColors.contains(color));
        usedColors.add(color);
        return color;
    }
    public static double randSpeed(){
        return (double)(random.nextInt(19) + 1) /10;
    }
    public static double randAngle(){
        Integer angle;
        do{
            angle = random.nextInt(44);
        }while (usedAngles.contains(angle));
        usedAngles.add(angle);
        return angle * 8;
    }
    public Point2D calculatePointOnCricle(double srodekX, double srodekY, double odleglosc, double kat) {
        double radiany = Math.toRadians(kat);
        double x = srodekX + odleglosc * Math.cos(radiany);
        double y = srodekY + odleglosc * Math.sin(radiany);
        return new Point2D.Double(x, y);
    }

    public static synchronized double calcSpeed(CircleComponent circleComponent){
        CircleComponent enemy = SimulationPanel.circles.get(circleComponent.getBehind());
        if(enemy.equals(circleComponent)) return circleComponent.getBaseSpeed();
        if(enemy.position.distance(circleComponent.position) < 20+1){
            if(enemy.actSpeed > circleComponent.getBaseSpeed()){
                return circleComponent.getBaseSpeed();
            }
            return enemy.actSpeed;
        }
        return circleComponent.getBaseSpeed();
    }

    public Color getColor() {
        return color;
    }

    public synchronized double getBaseSpeed() {
        return baseSpeed;
    }

    public synchronized void setBaseSpeed(double baseSpeed) {
        this.baseSpeed = baseSpeed;
    }

    public int getId() {
        return id;
    }

    public int getBehind() {
        return behind;
    }

    public void setBehind(int behind) {
        this.behind = behind;
    }

    @Override
    public void run() {
        this.actSpeed = calcSpeed(this);
        angle += actSpeed;
        updatePosition(this);
        if (angle > 360) angle -= 360;
    }

    public double getAngle() {
        return angle;
    }
}
