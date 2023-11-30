import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * This class represents the full view of the MVC pattern of your car simulator.
 * It initializes with being center on the screen and attaching it's controller in it's state.
 * It communicates with the Controller by calling methods of it when an action fires of in
 * each of it's components.
 * TODO: Write more actionListeners and wire the rest of the buttons
 **/

public class CarView extends JFrame{
    private static final int X = 800;
    private static final int Y = 800;


    // The controller member
    CarController carC;
    DrawPanel drawP;
    public void addCars(Car car) {
        carC.addCars(car);
    }

    public ArrayList<Vehicle> getCars(){
        return carC.getVehicles();
    }

    DrawPanel drawPanel = new DrawPanel(X, Y-240);
    JPanel controlPanel = new JPanel();
    JPanel gasPanel = new JPanel();
    JPanel bodyPanel = new JPanel();                        //
    JSpinner gasSpinner = new JSpinner();
    JSpinner bodyRaiseSpinner = new JSpinner();             //
    int gasOrBreakAmount = 0;
    int bodyRaiseAmount = 0;                                //
    JLabel gasLabel = new JLabel("Amount of gas");
    JLabel bodyLabel = new JLabel("Angle of body");     //
    JButton turnLeftButton = new JButton("Turn left");         //
    JButton turnRightButton = new JButton("Turn right");       // Lägger till left/right
    JButton gasButton = new JButton("Gas");
    JButton brakeButton = new JButton("Brake");
    JButton turboOnButton = new JButton("Saab Turbo on");
    JButton turboOffButton = new JButton("Saab Turbo off");
    JButton liftBedButton = new JButton("Scania Lift Bed");
    JButton lowerBedButton = new JButton("Lower Lift Bed");

    JButton startButton = new JButton("Start all cars");
    JButton stopButton = new JButton("Stop all cars");

    // Constructor
    public CarView(String framename, CarController cc){
        this.carC = cc;
        initComponents(framename);
    }

    // Sets everything in place and fits everything
    // TODO: Take a good look and make sure you understand how these methods and components work
    private void initComponents(String title) {

        this.setTitle(title);
        this.setPreferredSize(new Dimension(X,Y));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        this.add(drawPanel);



        SpinnerModel spinnerModel =
                new SpinnerNumberModel(0, //initial value
                        0, //min
                        100, //max
                        1);//step
        gasSpinner = new JSpinner(spinnerModel);
        gasSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                gasOrBreakAmount = (int) ((JSpinner)e.getSource()).getValue();
            }
        });


        //bodyRaiseSpinner = new JSpinner(spinnerModel);                          //tog bort
        bodyRaiseSpinner.addChangeListener(new ChangeListener() {               //
            public void stateChanged(ChangeEvent e) {                           //
                bodyRaiseAmount = (int) ((JSpinner)e.getSource()).getValue();   //
            }
        });

        gasPanel.setLayout(new BorderLayout());
        gasPanel.add(gasLabel, BorderLayout.PAGE_START);
        gasPanel.add(gasSpinner, BorderLayout.PAGE_END);

        this.add(gasPanel);

        bodyPanel.setLayout(new BorderLayout());                    //
        bodyPanel.add(bodyLabel, BorderLayout.PAGE_START);          //
        bodyPanel.add(bodyRaiseSpinner, BorderLayout.PAGE_END);     //

        this.add(bodyPanel);                                        //

        controlPanel.setLayout(new GridLayout(2,4));

        controlPanel.add(gasButton, 0);
        controlPanel.add(turboOnButton, 1);
        controlPanel.add(liftBedButton, 2);
        controlPanel.add(brakeButton, 3);
        controlPanel.add(turboOffButton, 4);
        controlPanel.add(lowerBedButton, 5);
        controlPanel.add(turnLeftButton, 6);
        controlPanel.add(turnRightButton, 7);       //Lägger till left/right i panelen
        controlPanel.add(startButton,8);
        controlPanel.add(stopButton,9);
        controlPanel.setPreferredSize(new Dimension((X/2)+4, 200));
        this.add(controlPanel);
        controlPanel.setBackground(Color.CYAN);


        startButton.setBackground(Color.blue);
        startButton.setForeground(Color.black);
        startButton.setPreferredSize(new Dimension(X/5-15,200));
        this.add(startButton);


        stopButton.setBackground(Color.red);
        stopButton.setForeground(Color.black);
        stopButton.setPreferredSize(new Dimension(X/5-15,200));
        this.add(stopButton);

        // This actionListener is for the gas button only
        // TODO: Create more for each component as necessary

        gasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {        // actionbutton gas
                carC.gas(gasOrBreakAmount);
            }
        });

        turnRightButton.addActionListener(new ActionListener() {
            @Override                                               // actionbutton turn right
            public void actionPerformed(ActionEvent e) {
                carC.turnRight();}
        });
        turnLeftButton.addActionListener(new ActionListener() {
            @Override                                               // actionbutton turn left
            public void actionPerformed(ActionEvent e) {
                carC.turnLeft();}
        });
        brakeButton.addActionListener(new ActionListener() {
            @Override                                               // actionbutton brake
            public void actionPerformed(ActionEvent e) {
                carC.brake(gasOrBreakAmount);}
        });
        turboOnButton.addActionListener(new ActionListener() {
            @Override                                               // actionbutton turbo on
            public void actionPerformed(ActionEvent e) {
                carC.turboon();}
        });
        turboOffButton.addActionListener(new ActionListener() {
            @Override                                               // actionbutton turbo off
            public void actionPerformed(ActionEvent e) {
                carC.turbooff();}
        });
        liftBedButton.addActionListener(new ActionListener() {
            @Override                                               // actionbutton turbo off
            public void actionPerformed(ActionEvent e) {
                carC.liftBed(bodyRaiseAmount);}
        });
        lowerBedButton.addActionListener(new ActionListener() {
            @Override                                               // actionbutton turbo off
            public void actionPerformed(ActionEvent e) {
                carC.lowerBed(bodyRaiseAmount);}
        });
        stopButton.addActionListener(new ActionListener() {
            @Override                                               // actionbutton turbo off
            public void actionPerformed(ActionEvent e) {
                carC.stopengine();}
        });
        startButton.addActionListener(new ActionListener() {
            @Override                                               // actionbutton turbo off
            public void actionPerformed(ActionEvent e) {
                carC.startengine();}
        });

        // Make the frame pack all it's components by respecting the sizes if possible.
        this.pack();

        // Get the computer screen resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Center the frame
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        // Make the frame visible
        this.setVisible(true);
        // Make sure the frame exits when "x" is pressed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}