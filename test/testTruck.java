

import org.junit.Assert;
import org.junit.Test;

import java.awt.*;


public class testTruck {

    @Test
    public void rampTest() { // Testing if the ramp work standing-still/moving // Funkar
        Biltransport truck = new Biltransport(2, 60, Color.green, "Biltransport", 2);
        truck.setRampOpen();
        Assert.assertEquals(true, truck.isRampOpen());
        truck.setRampClose();
        truck.startEngine();
        truck.setRampOpen();
        Assert.assertEquals(false, truck.isRampOpen());
    }

    @Test
    public void testLoading_1() { // Funkar
        Biltransport Truck = new Biltransport(2, 60, Color.green, "Biltransport", 2);
        Volvo240 volvo240 = new Volvo240(2, 100, Color.black, "Volvo240");
        Truck.setRampOpen();
        Truck.loadCar(volvo240);
        Truck.setRampClose();
        Truck.startEngine();
        Truck.currentSpeed = 90;
        Truck.gas(1);
        Assert.assertEquals(0, volvo240.getCurrentSpeed(), 0);
        Assert.assertEquals(60, Truck.currentSpeed, 0);
    }


    @Test
    public void testMoving() { // Funkar
        Biltransport Truck = new Biltransport(2, 60, Color.green, "Biltransport", 2);
        Saab95 testSaab = new Saab95(2, 120, Color.red, "Saab95");
        Truck.setRampOpen();
        Truck.loadCar(testSaab);
        Assert.assertEquals(testSaab, Truck.getCarlist().get(0));
        Truck.startEngine();
        Assert.assertEquals(0, Truck.getCurrentSpeed(), 0); // Check so we can't move with open ramp
        Truck.setRampClose();
        Truck.currentSpeed = 30;
        Truck.gas(1);
        Truck.move();
        Assert.assertEquals(30.6, Truck.getX(), 0); // coordinate for Truck
        Assert.assertEquals(Truck.getX(), testSaab.getX(), 0); // same coordinates

    }
    @Test
    public void testTurning() { // Funkar
        Biltransport Truck = new Biltransport(2, 60, Color.green, "Biltransport", 2);
        Saab95 testSaab = new Saab95(2, 120, Color.red, "Saab95");
        Truck.setRampOpen();
        Truck.loadCar(testSaab);
        Assert.assertEquals(Vehicle.directions.NORTH, Truck.getDirection());
        Assert.assertEquals(testSaab.getDirection(), Truck.getDirection());
        Truck.turnright();
        Truck.turnright();
        Truck.turnleft();
        Assert.assertEquals(Vehicle.directions.EAST, Truck.getDirection());
        Assert.assertEquals(testSaab.getDirection(), Truck.getDirection());
    }

    @Test
    public void testMaxLoad(){ // Funkar
        Biltransport Truck = new Biltransport(2, 60, Color.green, "Biltransport", 2);
        Saab95 testSaab = new Saab95(2, 120, Color.red, "Saab95");
        Volvo240 testVolvo = new Volvo240(4, 100, Color.black, "Volvo240");
        Volvo240 testVolvo2 = new Volvo240(4, 100, Color.black, "Volvo240_2");
        Truck.setRampOpen();
        Truck.loadCar(testSaab);
        Assert.assertEquals(testSaab, Truck.getCarlist().get(0));

        Truck.loadCar(testVolvo);
        Assert.assertEquals(testVolvo, Truck.getCarlist().get(1));

        Truck.loadCar(testVolvo2);
        Assert.assertEquals(2, Truck.getCarlist().size(), 0);
        Assert.assertEquals(testSaab, Truck.getCarlist().get(0));
        Assert.assertEquals(testVolvo, Truck.getCarlist().get(1));
    }


    @Test
    public void testUnload(){
        Biltransport Truck = new Biltransport(2, 60, Color.green, "Biltransport", 2);
        Saab95 testSaab = new Saab95(2, 120, Color.red, "Saab95");
        Volvo240 testVolvo = new Volvo240(4, 100, Color.black, "Volvo240");
        Truck.setRampOpen();
        Truck.loadCar(testSaab);
        Truck.loadCar(testVolvo);
        Truck.setRampClose();
        Truck.startEngine();
        Truck.gas(1);
        Truck.gas(1);
        Truck.gas(1);
        Truck.move();
        Truck.stopEngine();
        Truck.setRampOpen();
        Truck.unloadCar();
        Assert.assertEquals(-3.1,testVolvo.getX(), 0);
        Assert.assertEquals(0, testVolvo.getY(), 0);
    }

    @Test
    public void testNoMoving() { // We can set currentSpeed by accessing the variable, but we can't move (Good) // Funkar
        Biltransport Truck = new Biltransport(2, 60, Color.green, "Biltransport", 2);
        Truck.setRampOpen();
        Truck.currentSpeed = 40;
        Truck.move();
        Assert.assertEquals(0, Truck.getX(), 0);
    }


    @Test
    public void testRaiseBody(){ //testar så att flaket bara kan fällas när currentspeed = 0.
        Scania scania = new Scania(2,80,Color.black, "Scania");
        scania.startEngine();
        scania.currentSpeed = 1;
        scania.raiseBody(50);
        Assert.assertEquals(0, scania.getAngle(),0);
        scania.currentSpeed = 0;
        scania.raiseBody(50);
        Assert.assertEquals(50, scania.getAngle(), 0);
        scania.raiseBody(90);
        Assert.assertEquals(70, scania.getAngle(), 0);
    }
    @Test
    public void testLowerBody() {
        Scania scania = new Scania(2, 80, Color.black, "Scania");
        scania.angle = 20;
        scania.lowerBody(15);
        Assert.assertEquals(5, scania.getAngle(), 0);
        scania.angle = 20;
        scania.lowerBody(30);
        Assert.assertEquals(0, scania.getAngle(), 0);
    }
}
















