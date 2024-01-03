package Traffic;

public class TrafficS {
    Intersection leftIntersection = new Intersection(450,240,0,280,210,0,250,560);
    Intersection rightIntersection= new Intersection(560,240,290,280,490,0,530,560);

    public TrafficS(){

    }
    public void updateTraffic(){
        leftIntersection.changeLight();
        rightIntersection.changeLight();
        leftIntersection.updateCars();
        rightIntersection.updateCars();
        transferCars();
    }
    public void transferCars(){
        for(Car car: leftIntersection.getCars()){
            if(car.getDirection()==direction.left && !leftIntersection.isCarBeforeIntersection(car.getX(), car.getY(), car.getDirection())){
                rightIntersection.addCar(direction.left);
                leftIntersection.removeCar(car);
            }
        }
        for(Car car: rightIntersection.getCars()){
            if(car.getDirection()==direction.right && !rightIntersection.isCarBeforeIntersection(car.getX(), car.getY(), car.getDirection())){
                leftIntersection.addCar(direction.right);
                rightIntersection.removeCar(car);
            }
        }
    }

    public Intersection getLeftIntersection() {
        return leftIntersection;
    }

    public Intersection getRightIntersection() {
        return rightIntersection;
    }
}
