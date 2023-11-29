import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represent the animated part of the view with the car images.

public class DrawPanel extends JPanel {

    CarController carC = new CarController();
    // Just a single image, TODO: Generalize
    //BufferedImage carImage;


    public ArrayList<Vehicle> getCars() {
        return carC.getVehicles();
    }
    ArrayList<BufferedImage> carImage = new ArrayList<>();





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

            carC.vehicles.add(new Volvo240(2,100,Color.blue,"Volvo240"));
            carC.vehicles.add(new Saab95(2,100,Color.red,"Saab95"));
            carC.vehicles.add(new Scania(2,80,Color.black,"Scania"));

            for (Vehicle vehicle : getCars()) {
                try {
                    String modelName = vehicle.getModelName();
                    BufferedImage image;
                    image = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/" + modelName + ".jpg"));
                    carImage.add(image);
                } catch (IOException ex) {
                    // print felmeddelande
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
            int index = 0;
            for (Vehicle vehicle : getCars()) {
                setInitialPositions();
                int x = (int) Math.round(vehicle.getX());
                int y = (int) Math.round(vehicle.getY());

                g.drawImage(carImage.get(index), x, y, null);
                index += 1;
            }
        }
    }

