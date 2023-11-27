
import java.awt.*;

public abstract class Vehicle implements Imovable {
    private int nrDoors;
    private double enginePower;
    private Color color;
    private String modelName;
    protected double currentSpeed;
    private double x;
    private double y;
    private directions dir;

    enum directions {
        NORTH, EAST, SOUTH, WEST
    }

    public Vehicle(int nrDoors, double enginePower, Color color, String modelName) {
        this.nrDoors = nrDoors;
        this.color = color;
        this.enginePower = enginePower;
        this.modelName = modelName;
        x = 0; // Starting position
        y = 0;
        dir = directions.NORTH; // Start with a direction, north.
        stopEngine();
    }

    public void turnleft() {
        switch (dir) {
            case NORTH:
                dir = directions.WEST;
                break;
            case WEST:
                dir = directions.SOUTH;
                break;
            case SOUTH:
                dir = directions.EAST;
                break;
            case EAST:
                dir = directions.NORTH;
                break;
        }
    }

    public void turnright() {
        switch (dir) {
            case NORTH:
                dir = directions.EAST;
                break;
            case EAST:
                dir = directions.SOUTH;
                break;
            case SOUTH:
                dir = directions.WEST;
                break;
            case WEST:
                dir = directions.NORTH;
                break;
        }
    }

    public void move() {
        switch (dir) {
            case NORTH:
                x += currentSpeed;
                break;
            case WEST:
                y += currentSpeed;
                break;
            case SOUTH:
                x -= currentSpeed;
                break;
            case EAST:
                y -= currentSpeed;
                break;
        }

    }

    public int getNrDoors() {
        return nrDoors;
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public void setX(double xpos){
        x = xpos;
    }
    public void setY(double ypos){
        y = ypos;
    }


    public directions getDirection(){
        return dir;
    }

    public String getModelName() {
        return modelName;
    }

    public double getEnginePower() {
        return enginePower;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color clr) {
        color = clr;
    }

    public void startEngine() {
        currentSpeed = 0.1;
    }

    public void stopEngine() {
        currentSpeed = 0;
    }

    protected abstract void incrementSpeed(double amount);

    protected abstract void decrementSpeed(double amount);


    public void gas(double amount) {
        if (getEnginePower() >= 0) {
            if (0 <= amount && amount <= 1) {
                incrementSpeed(amount);
                if (getCurrentSpeed() > enginePower) {
                    currentSpeed = enginePower;
                }
            }
        }
    }

    public void brake(double amount) {
        if (getEnginePower() >= 0) {
            if (0 <= amount && amount <= 1) {
                decrementSpeed(amount);
                if (getCurrentSpeed() < 0) {
                    currentSpeed = 0;
                }
            }
        }
    }



}
