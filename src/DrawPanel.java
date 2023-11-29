import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represent the animated part of the view with the car images.

public class DrawPanel extends JPanel {

    // Just a single image, TODO: Generalize

    CarController carC = new CarController();

    ArrayList<BufferedImage> carImageList = new ArrayList<>();

    BufferedImage carImage;

    ArrayList<Car> carList;

    Point carPoint = new Point();
    // To keep track of a single cars position
    //Point volvoPoint = new Point();

    // TODO: Make this genereal for all cars
    void moveit(int x, int y) {
        carPoint.x = x;
        carPoint.y = y;
    }

    public void sendToDraw(ArrayList<Car> Lista){
        this.carList = Lista;
    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        carC.cars.add(new Volvo240(4, 100, Color.black, "Volvo240"));
        carC.cars.add(new Saab95(2, 125, Color.red, "Saab95"));

        // Print an error message in case file is not found with a try/catch block
        // You can remove the "pics" part if running outside of IntelliJ and
        // everything is in the same main folder.
        // volvoImage = ImageIO.read(new File("Volvo240.jpg"));

        // Rememember to rightclick src New -> Package -> name: pics -> MOVE *.jpg to pics.
        // if you are starting in IntelliJ.
        for (Vehicle vehicle : carC.cars) {
            try {
                String modelName = vehicle.getModelName();
                System.out.println(modelName); // Just a print test incase I forget to remove it
                BufferedImage carImage;
                carImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/" + modelName + ".jpg"));
                carImageList.add(carImage);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int index = 0;

        if (carList != null){
        for (Vehicle vehicle : carList) {
            //if (volvoPoint.x == (int) Math.round(vehicle.getX())) {
                int x = (int) Math.round(vehicle.getX());
                int y = (int) Math.round(vehicle.getY());
                g.drawImage(carImageList.get(index),x,y + 100 * index, null);
                index += 1;
            }
        }
    }
}



