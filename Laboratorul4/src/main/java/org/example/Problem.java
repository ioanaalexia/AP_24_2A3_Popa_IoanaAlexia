package org.example;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.*;

public class Problem {
    private List<Driver> drivers;
    private List<Passenger> passengers;

    public Problem(List<Driver> drivers, List<Passenger> passengers) {
        this.drivers = drivers;
        this.passengers = passengers;
    }

    public List<Driver> getDriversSortedByAge() {
        return drivers.stream()
                .sorted((d1, d2) -> Integer.compare(d1.getAge(), d2.getAge()))
                .collect(Collectors.toList());
    }

    public List<Passenger> getPassengersSortedByName() {
        return passengers.stream()
                .sorted((p1, p2) -> p1.getName().compareTo(p2.getName()))
                .collect(Collectors.toList());
    }

    public void matchDriversWithPassengers() {
        Set<Passenger> pickedPassengers = new HashSet<>();

        for (Driver driver : drivers) {
            boolean driverMatched = false;
            int maxPassengerCount = 0;
            Passenger bestPassenger = null;

            for (Passenger passenger : passengers) {
                if (pickedPassengers.contains(passenger)) {
                    continue;
                }

                int passengerCount = 0;
                for (String destination : driver.getDestinations()) {
                    if (destination.equals(passenger.getDestination())) {
                        passengerCount++;
                    }
                }
                if (passengerCount > maxPassengerCount) {
                    maxPassengerCount = passengerCount;
                    bestPassenger = passenger;
                }
            }
            if (bestPassenger != null) {
                driver.pickPassenger(bestPassenger);
                pickedPassengers.add(bestPassenger);
                driverMatched = true;
            }

            if (!driverMatched) {
                System.out.println("Driver " + driver.getName() + " cannot pick up any passenger.");
            }
        }
    }

    public List<String> getAllDestinationsForDrivers() {
        return drivers.stream()
                .flatMap(driver -> driver.getDestinations().stream())
                .distinct()
                .collect(Collectors.toList());
    }

    public Map<String, Set<Person>> getDestinationPassengerMap(){
        Map<String, Set<Person>> destinationPassengerMap = new HashMap<>();

        passengers.forEach(passenger -> destinationPassengerMap.computeIfAbsent(passenger.getDestination(), k -> new HashSet<>())
                  .add(passenger));

        return destinationPassengerMap;
    }



}
