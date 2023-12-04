import java.util.ArrayList;
import java.util.List;

public class Garage <T> {


    private int maxCars;

    public Garage(int maxCars) {
        this.maxCars = maxCars;
    }

    private List<T> CarGarage = new ArrayList<>();

    public List<T> getCarList() {
        return CarGarage;
    }

    public String loadCarGarage(T car) {
        if (CarGarage.size() < maxCars) {
            CarGarage.add(car);
            return "Car is parked in garage";
        } else {
            return "Garage is full!";
        }
    }
    public void removeCarGarage(T car) {
        CarGarage.remove(car);
    }
}


