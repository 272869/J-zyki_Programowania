import javax.swing.*;
public class Traffic extends JFrame{
    Road[] roads = new Road[6];
    private Road horizontal_road_upper ;
    private Road horizontal_road_lower ;
    private Road vertical_road1_left;
    private Road vertical_road1_right;
    private Road vertical_road2_left;
    private Road vertical_road2_right;

    public Traffic(){
        horizontal_road_upper = new Road(760, 2, new int[]{3, 10},true, 720, 180, false);
        horizontal_road_lower = new Road(760,2, new int[]{3, 10}, true, 0, 220, true);
        vertical_road1_left = new Road(480,1, new int[]{3},false, 200, 0, true);
        vertical_road1_right = new Road(480,1, new int[]{3},false, 240, 400, false); //440
        vertical_road2_left = new Road(480,1, new int[]{3},false, 480, 0, true);
        vertical_road2_right = new Road(480,1, new int[]{3},false, 520, 400, false); //440
        roads[0]=(horizontal_road_upper);
        roads[1]=(horizontal_road_lower);
        roads[2]=(vertical_road1_left);
        roads[3]=(vertical_road1_right);
        roads[4]=(vertical_road2_left);
        roads[5]=(vertical_road2_right);
    }

    public void update_roads() {
        for(Road road : roads){
            road.update_road();
        }
    }
    public Road[] get_roads() {
        return roads;
    }
    public Road get_road(int number){
        return roads[number];
    }
}