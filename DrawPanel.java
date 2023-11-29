import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represent the animated part of the view with the car images.

public class DrawPanel extends JPanel {

    CarController carC = new CarController();
    // Just a single image, TODO: Generalize
    BufferedImage carImage;

    public ArrayList<Vehicle> getCars() {
        return carC.getVehicles();
    }

    Map<Vehicle, BufferedImage> vehicleImages = new HashMap<>();


/*    public void bufferImage() {
        for (Car car : getCars()) {
            try {
                String modelName = car.getModelName();
                BufferedImage carImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/" + modelName + ".jpg"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
*/
//    BufferedImage saabImage;
//    BufferedImage volvoImage;
//    BufferedImage scaniaImage;
    // To keep track of a singel cars position

    /*    Point volvoPoint = new Point();
        Point saabPoint = new Point();
        Point scaniaPoint = new Point();

     */   // TODO: Make this genereal for all cars
    void moveit(int x, int y) {
        setInitialPositions();
        for (Vehicle vehicle : getCars()) {
        }
    }

        // Initializes the panel and reads the images
    public DrawPanel( int x, int y){
            this.setDoubleBuffered(true);
            this.setPreferredSize(new Dimension(x, y));
            this.setBackground(Color.green);

            for (Vehicle vehicle : getCars()) {
                try {
                    String modelName = vehicle.getModelName();
                    carImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/" + modelName + ".jpg"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        void setInitialPositions () {
            int yPos = 0;
            for (Vehicle vehicle : getCars()) {
                vehicle.setY(yPos);
                yPos += 100;
            }
        }

        // Print an error message in case file is not found with a try/catch block
/*        try {
           // You can remove the "pics" part if running outside of IntelliJ and
            // everything is in the same main folder.
            // volvoImage = ImageIO.read(new File("Volvo240.jpg"));

            // Rememember to rightclick src New -> Package -> name: pics -> MOVE *.jpg to pics.
            // if you are starting in IntelliJ.
//            volvoImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"));
//            saabImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg"));
//            scaniaImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg"));

        } catch (IOException ex) {  //System.out.println("Picture not found");     // Felmeddelande
            ex.printStackTrace();
        }
    }
*/
        // This method is called each time the panel updates/refreshes/repaints itself
        // TODO: Change to suit your needs.
        @Override
        protected void paintComponent (Graphics g){
            super.paintComponent(g);
            for (Vehicle vehicle : getCars()) {
                int x = (int) Math.round(vehicle.getX());
                int y = (int) Math.round(vehicle.getY());
                BufferedImage carImage = vehicleImages.get(vehicle);

                g.drawImage(carImage, x, y, null);
            }
        }
    }

