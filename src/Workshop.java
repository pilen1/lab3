
import java.util.ArrayList;
import java.util.List;
public class Workshop<A extends Vehicle> {
    int capacity;
    private List<A> garage = new ArrayList<A>();
    public Workshop(int capacity){
    this.capacity = capacity;
    }

    public int getCapacity(){
        return capacity;
    }

    public List<A> getGarage(){
        return garage;
    }

    public int capacityLeft() {
        return capacity - garage.size();
    }

    public void addVehicle(A vehicle){
        if (garage.size() < capacity){
            garage.add(vehicle);
        }
    }

    public A releaseVehicle(String modelName){
        for (A vehicle : garage) {
            if (vehicle.getModelName() == modelName) {
                garage.remove(vehicle);
                return vehicle;
            }
        }
        return null;
    }






}
