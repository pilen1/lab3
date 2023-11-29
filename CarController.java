import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with an listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    // A list of cars, modify if needed
    ArrayList<Vehicle> vehicles = new ArrayList<>();        //Ändrad till Car

    public void addCars(Vehicle vehicle){
        vehicles.add(vehicle);
    }
    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        cc.vehicles.add(new Volvo240(4, 100, Color.blue, "Volvo240"));

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Vehicle vehicle : vehicles) {
                vehicle.move();
                int x = (int) Math.round(vehicle.getX());
                int y = (int) Math.round(vehicle.getY());
                frame.drawPanel.moveit(x, y);
                if (vehicle.getX() > 800 || vehicle.getX() < 0){
                    vehicle.stopEngine();
                    vehicle.turnleft();
                    vehicle.turnleft();
                    vehicle.startEngine();
                }
                if (vehicle.getX() > 800) {
                    vehicle.setX(800);
                }
                else if (vehicle.getX() < 0){
                    vehicle.setX(0);
                }
            }
            // repaint() calls the paintComponent method of the panel
            frame.drawPanel.repaint();
        }
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Vehicle vehicle : vehicles) {
            vehicle.gas(gas);
        }
    }
    void turnRight() {
        for (Vehicle vehicle : vehicles) {
            vehicle.turnright();
        }
    }
    void turnLeft() {
        for (Vehicle vehicle : vehicles) {
            vehicle.turnleft();
        }
    }
    void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Vehicle vehicle : vehicles
        ) {
            vehicle.brake(brake);
        }
    }
    void turboon() {
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Saab95) {
                ((Saab95) vehicle).setTurboOn();        // om det är en saab så går det att använda turboon/off
            }
        }
    }
    void turbooff() {
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Saab95) {
                ((Saab95) vehicle).setTurboOff();
            }
        }
    }
    void liftBed(int amount) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Scania) {
                ((Scania) vehicle).raiseBody(amount);
            }
        }
    }
    void lowerBed(int amount) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Scania) {
                ((Scania) vehicle).lowerBody(amount);
            }
        }
    }
}
