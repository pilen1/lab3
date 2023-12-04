import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CarTransport extends Vehicle{

    private boolean rampOpen = false;

    public boolean isRampOpen() {
        return rampOpen;
    }

    public CarTransport(int nrDoors, double enginePower, Color color, String modelName) {
        super(nrDoors, enginePower, color, modelName);
    }

    private double speedFactor(){
        return getEnginePower() * 0.01;
    }

    private List<Car> carlist = new ArrayList<Car>();

    public List getCarList() {
        return carlist;
    }

    public void loadCar(Car car){
        if (rampOpen == true){
            double diffX = getX() - car.getX();
            double diffY = getY() - car.getY();
            if(diffX <= 5 && diffY <= 5){
                if (car.getNrDoors() <= 4) {
                    carlist.add(car);
                }
            }
        }
    }
    public void unloadCar() {
        if (rampOpen == true) {
            int lastIndex = carlist.size() - 1;
            Car car = carlist.get(lastIndex);
            car.setX(getX() - 5);
            car.setY(getY() - 5);
            carlist.remove(lastIndex);
        }
    }

    @Override
    protected void incrementSpeed(double amount) {
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount,getEnginePower());
    }

    @Override
    protected void decrementSpeed(double amount) {
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
    }
    @Override
    public void startEngine() {
        if (rampOpen == false) {
            super.startEngine();
        }
    }
    public void setRampOpen(){
        if (currentSpeed == 0) {
            rampOpen = true;
        }
    }
    public void setRampClosed() {
        rampOpen = false;
    }

}
