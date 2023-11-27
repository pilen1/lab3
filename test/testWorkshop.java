

import org.junit.Assert;
import org.junit.Test;

import java.awt.*;


public class testWorkshop{

    @Test
    public void testAdd(){ // Funkar!
        Workshop<Car> testShop = new Workshop<>(3);
        Volvo240 testVolvo = new Volvo240(4, 100, Color.black, "Volvo240");
        testShop.addVehicle(testVolvo);
        Assert.assertEquals(testVolvo, testShop.getGarage().get(0));
    }

    @Test
    public void testOverAdding(){ // Funkar!
        Workshop<Volvo240> testShop = new Workshop<>(3);
        Volvo240 testVolvo1 = new Volvo240(4, 100, Color.black, "Volvo240_1");
        Volvo240 testVolvo2 = new Volvo240(4, 100, Color.black, "Volvo240_2");
        Volvo240 testVolvo3 = new Volvo240(4, 100, Color.black, "Volvo240_3");
        Volvo240 testVolvo4 = new Volvo240(4, 100, Color.black, "Volvo240_4");

        testShop.addVehicle(testVolvo1);
        testShop.addVehicle(testVolvo2);
        testShop.addVehicle(testVolvo3);
        testShop.addVehicle(testVolvo4);

        Assert.assertEquals(3, testShop.getGarage().size());
        Assert.assertEquals(testVolvo1, testShop.getGarage().get(0));
        Assert.assertEquals(testVolvo2, testShop.getGarage().get(1));
        Assert.assertEquals(testVolvo3, testShop.getGarage().get(2));
    }

    @Test
    public void testCapacityLeft(){ // Funkar!
        Workshop<Car> testShop = new Workshop<>(3);
        Volvo240 testVolvo = new Volvo240(4, 100, Color.black, "Volvo240");
        Saab95 testSaab = new Saab95(2, 120, Color.red, "Saab95");
        testShop.addVehicle(testSaab);
        testShop.addVehicle(testVolvo);
        Assert.assertEquals(1,testShop.capacityLeft());
    }


   @Test
   public void testSpecificCar(){ // Complains in compiler because workshop only takes volvo240
       Workshop<Volvo240> testShop = new Workshop<>(3);
       Volvo240 testVolvo = new Volvo240(4, 100, Color.black, "Volvo240");
       Saab95 testSaab = new Saab95(2, 120, Color.red, "Saab95");
       testShop.addVehicle(testVolvo);
       Assert.assertEquals(testVolvo, testShop.getGarage().get(0));

      // testShop.addVehicle(testSaab);
       Assert.assertEquals(testVolvo, testShop.getGarage().get(0)); // Check so the volvo is not replaced
       Assert.assertEquals(1,testShop.getGarage().size()); // Check that the saab is not added
   }

   @Test
   public void testWrongType() { // Complains in compiler (Good)
        Workshop<Car> testShop = new Workshop<>(3);
        Volvo240 testVolvo = new Volvo240(4, 100, Color.black, "Volvo240");
        Scania scania = new Scania(2, 60, Color.green, "Scania");
        Biltransport biltransport = new Biltransport(2, 50, Color.gray, "biltransport", 10);

        testShop.addVehicle(testVolvo);
      //  testShop.addVehicle(scania);
      //  testShop.addVehicle((biltransport));
        Assert.assertEquals(1, testShop.getGarage().size());
    }

    @Test
    public void testReturningCar(){ // Funkar!
        Workshop<Car> testShop = new Workshop<>(3);
        Volvo240 testVolvo1 = new Volvo240(4, 100, Color.black, "Volvo240_1");
        Saab95 testSaab = new Saab95(2, 120, Color.red, "Saab95");
        Volvo240 testVolvo2 = new Volvo240(4, 100, Color.black, "Volvo240_2");

        testShop.addVehicle(testVolvo1);
        testShop.addVehicle(testSaab);
        testShop.addVehicle(testVolvo2);

        Assert.assertEquals(testSaab, testShop.releaseVehicle("Saab95"));
        Assert.assertEquals(2, testShop.getGarage().size());
    }






}