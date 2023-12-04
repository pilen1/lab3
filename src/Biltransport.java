package src;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Biltransport extends lastbil {
    private boolean rampOpen = false;
    private List<Car> carlist = new ArrayList<Car>();
    private int maxload;

    public Biltransport(int nrDoors, double enginePower, Color color, String modelName, int x, int y, int maxload) {
        super(nrDoors, enginePower, color, modelName, x, y);
        this.maxload = maxload;

    }

    public List getCarlist() {
        return carlist;
    }

    public void loadCar(Car car) {
        if (rampOpen == true && carlist.size() < maxload) {
            double diffX = getX() - car.getX();
            double diffY = getY() - car.getY();
            if (diffX <= 5 && diffY <= 5) {
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
            if (getDirection() == Vehicle.directions.NORTH || getDirection() == Vehicle.directions.SOUTH) {
                car.setX(car.getX() - 5);
            }
            if (getDirection() == Vehicle.directions.WEST || getDirection() == Vehicle.directions.EAST) {
                car.setY(car.getY() - 5);
            }
            carlist.remove(lastIndex);

        }
    }

    public boolean isRampOpen() {
        return rampOpen;
    }

    public void setRampOpen() {
        if (currentSpeed == 0) {
            rampOpen = true;
        }
    }

    public void setRampClose() {
        rampOpen = false;
    }

    @Override
    public void turnleft() {
        for (Car car : carlist) {
            car.turnleft();
        }
        super.turnleft();
    }

    @Override
    public void turnright() {
        for (Car car : carlist) {
            car.turnright();
        }
        super.turnright();
    }


    @Override
    public void move() {
        if (rampOpen == false) {
            super.move();
            for (Car car : carlist) {
                car.setY(getY());
                car.setX(getX());
            }
        }
    }


    @Override
    public void startEngine() {
        if (rampOpen == false) {
            super.startEngine();
        }
    }

    private double speedFactor() {
        return getEnginePower() * 0.01;
    }

    @Override
    protected void incrementSpeed(double amount) {
        if (rampOpen == false) {
            currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, getEnginePower());
        }
    }

    @Override
    protected void decrementSpeed(double amount) {
        currentSpeed = Math.max(getCurrentSpeed() + speedFactor() * amount, 0);
    }

}