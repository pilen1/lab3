
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Saab95 extends Car {

    private boolean turboOn = false;

    public Saab95(int nrDoors, double enginePower, Color color, String modelname) {
        super(nrDoors, enginePower, color, modelname);
    }

    public void setTurboOn() {
        turboOn = true;
    }

    public void setTurboOff() {
        turboOn = false;
    }

    private double speedFactor() {
        double turbo = 1;
        if (turboOn) turbo = 1.3;
        return getEnginePower() * 0.01 * turbo;
    }

    @Override
    protected void incrementSpeed(double amount) {
        currentSpeed = getCurrentSpeed() + speedFactor() * amount;
    }
    @Override
    protected void decrementSpeed(double amount) {
        currentSpeed = getCurrentSpeed() - speedFactor() * amount;
    }

    public abstract static class Truck extends Vehicle {

        public Truck(int nrDoors, double enginePower, Color color, String modelName){
            super(nrDoors, enginePower, color, modelName);
        }








    }

    public static class DrawPanel extends JPanel {

        // Just a single image, TODO: Generalize
        BufferedImage volvoImage;
        // To keep track of a singel cars position
        Point volvoPoint = new Point();

        // TODO: Make this genereal for all cars
        void moveit(int x, int y){
            volvoPoint.x = x;
            volvoPoint.y = y;
        }

        // Initializes the panel and reads the images
        public DrawPanel(int x, int y) {
            this.setDoubleBuffered(true);
            this.setPreferredSize(new Dimension(x, y));
            this.setBackground(Color.green);
            // Print an error message in case file is not found with a try/catch block
            try {
                // You can remove the "pics" part if running outside of IntelliJ and
                // everything is in the same main folder.
                // volvoImage = ImageIO.read(new File("Volvo240.jpg"));

                // Rememember to rightclick src New -> Package -> name: pics -> MOVE *.jpg to pics.
                // if you are starting in IntelliJ.
                volvoImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"));
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }

        }

        // This method is called each time the panel updates/refreshes/repaints itself
        // TODO: Change to suit your needs.
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(volvoImage, volvoPoint.x, volvoPoint.y, null); // see javadoc for more info on the parameters
        }
    }
}