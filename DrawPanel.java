import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;



public class DrawPanel extends JPanel {

    CarWorld carW;

    ArrayList<Vehicle> vehicleList;

    public void sendToDraw(ArrayList<Vehicle> Lista){
        this.vehicleList = Lista;
    }
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.white);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int index = 0;

        if (vehicleList != null){
            for (Vehicle vehicle : vehicleList) {
                int x = (int) Math.round(vehicle.getX());
                int y = (int) Math.round(vehicle.getY());
                g.drawImage(carW.getVehicleImageList().get(index),x,y + 100 * index, null);

                index += 1;
            }
        }
    }
}



