import java.util.*;
public class Road {
    public static ArrayList<Car> cars_array;
    public static ArrayList<Traffic_light> traffic_lights_array;
    private int length, width;

    private int position_x;
    private int position_y;
    boolean direction; // true = right, false = left+
    private final int number_of_fields;
    private Timer timer;

    public Road(int len,int number_of_lights,int[] tab_x, boolean color, int x, int y, boolean dir) {
        width = 80;
        length = len;
        number_of_fields=len/40;
        position_x = x;
        position_y = y;
        direction = dir;
        cars_array = new ArrayList<>();
        traffic_lights_array = new ArrayList<>();
        create_lights_array(number_of_lights,tab_x, color);
        timer = new Timer();
        timer.scheduleAtFixedRate(new TrafficLightTask(), 0, 5000);
        timer.scheduleAtFixedRate(new CarAddTask(), 0, 10000);
    }
    class TrafficLightTask extends TimerTask {
        @Override
        public void run() {
            changeLight();
            System.out.println("Zmieniono światła.");
        }
    }

    class CarAddTask extends TimerTask {
        @Override
        public void run() {
            place_car();
            System.out.println("Dodano nowy samochód.");
        }
    }
    private void create_lights_array(int number, int[] tab_x, boolean color){
        if(tab_x.length!=number) return;
        for(int i=0;i<number;i++){
            Traffic_light traffic_light = new Traffic_light(tab_x[i], color);
            traffic_lights_array.add(traffic_light);
        }
    }

    public void changeLight() {
        for (Traffic_light traffic_light : traffic_lights_array) {
            traffic_light.setGreen(!traffic_light.isGreen());
        }
    }
    private void place_car() {
        if (!is_empty(0)) return;
        if(cars_array.size() > 0) return; // zeby auta nie spawnowaly sie na sobie
        Car car = new Car(0);
        cars_array.add(car);
    }
    public boolean can_drive(int x){
        if(!is_traffic_light(x)) return true;
        if(is_light_red(x)) return false;
        return true;
    }
    public void update_car_array(){
        for(Car car:cars_array){
            int x=car.getX();
            if(is_empty(x+1) && can_drive(x)){
                car.move();
            }
        }
    }
    public void update_road(){
        changeLight();
        place_car();
        update_car_array();
    }
    public boolean is_empty(int x){
        for(Car car:cars_array){
            if(car.getX()==x)return false;
        }
        return true;
    }

    public boolean is_traffic_light(int x){
        for(Traffic_light traffic_light:traffic_lights_array){
            if(traffic_light.getX()==x)return true;
        }
        return false;
    }

    public boolean is_light_red(int x){
        for(Traffic_light traffic_light:traffic_lights_array){
            if(traffic_light.getX()==x && !traffic_light.isGreen()) return true;
        }
        return false;
    }

    public static ArrayList<Car> get_cars_array() {
        return cars_array;
    }

    /*public static Car get_car(int number) {
        return cars_array.get(number);
    }*/

    public boolean get_direction() {return direction;}

    public  int get_X() {return position_x;}
    public  int get_Y() {return position_y;}

    public static ArrayList<Traffic_light> get_traffic_lights_array() {
        return traffic_lights_array;
    }
}
