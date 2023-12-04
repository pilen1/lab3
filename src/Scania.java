package src;

import java.awt.*;


public class Scania extends lastbil {
    protected double angle = 0;

    public Scania(int nrDoors, double enginePower, Color color, String modelName, int x, int y) {
        super(nrDoors, enginePower, color, modelName, x, y);
    }


    public double getAngle(){
        return angle;
    }

    public void raiseBody(double amount){
        if (currentSpeed == 0) {
            if (amount > 0) {
                if (angle + amount <= 70) { // raise body, but no more than 70 degrees
                    angle += amount;
                }
                else { // this will make the body reach 70 degrees exactly if amount is too much
                    angle = 70;
                }
            }
        }
    }

    public void lowerBody(double amount){
        if (angle != 0){
            if (amount > 0){
                if (angle - amount >= 0){ // lower body, but no less than 0 degrees
                    angle -= amount;
                }
                else{ // this will make the body reach 0 degrees exactly if amount is too much
                    angle = 0;
                }
            }
        }
    }




    @Override
    public void startEngine(){
        if (angle == 0){
            super.startEngine();
        }
    }
    private double speedFactor(){
        return getEnginePower() * 0.01;
    }
    @Override
    protected void incrementSpeed(double amount){
        if (angle == 0) {
            currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, getEnginePower());
        }
    }
    @Override
    protected void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() + speedFactor()*amount,0);
    }






}