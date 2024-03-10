public class Drones extends Vehicle{

    private int maxFlightDuration;

    public Drones(int maxFlightDuration) {
        this.maxFlightDuration = maxFlightDuration;
    }

    public Drones(String name, int maxFlightDuration) {
        super(name);
        this.maxFlightDuration = maxFlightDuration;
    }

    public Drones(String name, Depot depot, int maxFlightDuration) {
        super(name, depot);
        this.maxFlightDuration = maxFlightDuration;
    }

    public Drones(Depot depot, int maxFlightDuration) {
        super(depot);
        this.maxFlightDuration = maxFlightDuration;
    }

    public Drones(String name, Depot depot) {
        super(name, depot);
    }

    public Drones(String name) {
        super(name);
    }

    public Drones(Depot depot) {
        super(depot);
    }

    public int getMaxFlightDuration() {
        return maxFlightDuration;
    }

    public void setMaxFlightDuration(int maxFlightDuration) {
        this.maxFlightDuration = maxFlightDuration;
    }

    @Override
    public String toString() {
        return "Drones{" +
                "maxFlighDuration=" + maxFlightDuration +
                '}';
    }
}
