import java.util.List;
public class Trucks extends Vehicle{
    private int capacity;

    public Trucks(int capacity) {
        this.capacity = capacity;
    }

    public Trucks(String name, int capacity) {
        super(name);
        this.capacity = capacity;
    }

    public Trucks(String name, Depot depot, int capacity) {
        super(name, depot);
        this.capacity = capacity;
    }

    public Trucks(Depot depot, int capacity) {
        super(depot);
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Trucks{" +
                "capacity=" + capacity +
                '}';
    }

    public boolean canAddClient(Client client){
        List<Client> currentClients = getClients();
        int currentCapacity = currentClients.size();
        int maxCapacity = getCapacity();
        if (currentCapacity >= maxCapacity) {
            return false;
        }

        return true;
    }
}
