

import org.junit.Assert;
import org.junit.Test;
import org.junit.*;

import java.awt.*;



public class test {
    @Test
    public void testdoors(){ // Funkar
        Volvo240 testcar = new Volvo240(4, 100, Color.black, "Volvo240");
        Assert.assertEquals(4,testcar.getNrDoors());
    }
    @Test
    public void  testturn(){ // Funkar
        Volvo240 turntest = new Volvo240(4, 100, Color.black, "Volvo240");
        turntest.turnright();
        Assert.assertEquals(Car.directions.EAST,turntest.getDirection());
    }

    @Test
    public void incrementVolvo(){ // Funkar för 0 , 1 , 0.5
        Volvo240 testspeed = new Volvo240(4, 100, Color.black, "Volvo240");
        testspeed.startEngine();
        testspeed.incrementSpeed(0.5);
        Assert.assertEquals(0.725,testspeed.getCurrentSpeed(), 0);
    }
    @Test
    public void incrementSaab(){ // Funkar för 0 , 1 , 0.5 med turbo on/off
        Saab95 testspeed = new Saab95(2, 125, Color.red, "Saab95");
        testspeed.startEngine();
        testspeed.setTurboOn();
        testspeed.incrementSpeed(0.5);
        Assert.assertEquals(0.9125,testspeed.getCurrentSpeed(), 0);
    }

    @Test
    public void braketest(){ // Funkar för edgecases och allt, stämmer att det inte funkar med ogiltliga värden som negativ enginePower
        Volvo240 braketest = new Volvo240(4, 100, Color.black, "Volvo240");
        braketest.startEngine();
        braketest.currentSpeed = 10;
        braketest.brake(1);
        Assert.assertEquals(8.75,braketest.getCurrentSpeed(), 0);
    }

    @Test
    public void movetest(){ // Funkar att röra sig i olika riktningar och att använda metoder som setTurboOn och turnright.
        Saab95 testcar = new Saab95(2, 125, Color.red, "Saab95");
        testcar.startEngine();
        testcar.currentSpeed = 5;
        testcar.move();
        testcar.turnright();
        testcar.setTurboOn();
        testcar.gas(0.8);
        testcar.move();
        Assert.assertEquals(5, testcar.getX(), 0);
        Assert.assertEquals(-6.3, testcar.getY(), 0);


    }

}


