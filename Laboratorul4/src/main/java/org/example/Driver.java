package org.example;
import java.util.List;

class Driver extends Person {
    private List<String> destinations;    private Passenger passenger;
    public Driver(String name, int age, List<String> destination) {
        super(name, age);
        this.destinations = destination;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public List<String> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<String> destinations) {
        this.destinations = destinations;
    }

    public void pickPassenger(Passenger passenger) {
        // Perform actions to pick the passenger, e.g., add them to a list of passengers in the car
        System.out.println("Driver " + getName() + " picks up passenger " + passenger.getName());
    }

    @Override
    public String toString() {
        return "Driver{" +
                "name='" + getName() + '\'' +
                ", age=" + getAge() +
                ", destinations=" + destinations+
                '}';
    }
}