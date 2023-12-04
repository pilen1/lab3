package src;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.*;



public class DrawPanel extends JPanel {

    CarController carC = new CarController();


    public ArrayList<Vehicle> getCars() {
        return carC.getVehicles();
    }

    ArrayList<BufferedImage> carImage = new ArrayList<>();
    ArrayList<Vehicle> carList;

    public void sendToDraw(ArrayList<Vehicle> Lista){
        this.carList = Lista;
    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);

        carC.vehicles.add(new Volvo240(2, 1, Color.blue, "Volvo240", 0, 0));
        carC.vehicles.add(new Saab95(2, 100, Color.red, "Saab95", 0, 100));
        carC.vehicles.add(new Scania(2, 50, Color.black, "Scania", 0, 200));

        for (Vehicle vehicle : getCars()) {
            try {
                String modelName = vehicle.getModelName();

                BufferedImage image = ImageIO.read(DrawPanel.class.getResourceAsStream("../pics/" + modelName + ".jpg"));
                carImage.add(image);
            } catch (IOException ex) {
                // print felmeddelande
                ex.printStackTrace();
            }
        }
    }

/*    void setInitialPositions() {
        int yPos = 0;
        for (Vehicle vehicle : getCars()) {
            vehicle.setY(yPos);
            yPos += 100;
        }
    }
*/


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int index = 0;

        if (carList != null){
            for (Vehicle vehicle : carList) {
                int x = (int) Math.round(vehicle.getX());
                int y = (int) Math.round(vehicle.getY());
                g.drawImage(carImage.get(index), x, y, null);
                index += 1;
            }
        }
    }
}
