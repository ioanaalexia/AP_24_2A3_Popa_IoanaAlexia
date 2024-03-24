package org.example;

import java.util.*;

import com.github.javafaker.Faker;

public class Laboratorul4 {
    public static void main(String[] args) {
        List<Person> persons = generateRandomPersons(10);

        //Punem persoanele ca sofer sau ca pasageri
        List<Driver> drivers = new ArrayList<>();

        List<Passenger> passengers = new ArrayList<>();

        for(Person person : persons){
            if(person instanceof Driver){
                drivers.add((Driver) person);
            }else
                if(person instanceof Passenger){
                    passengers.add((Passenger) person);
            }
        }

        //Apelam problema
        Problem problem = new Problem(drivers, passengers);

        //Sortam si afisam drivers dupa varsta
        List<Driver> sortedDrivers = problem.getDriversSortedByAge();
        System.out.println("Soferii sortati dupa varsta:");
        sortedDrivers.forEach(System.out::println);

        //Sortam si afisam passengers dupa nume
        List<Passenger> sortedPassengers = problem.getPassengersSortedByName();
        System.out.println("\nPasagerii sortati dupa nume:");
        sortedPassengers.forEach(System.out::println);


        //Apelam matchul
        problem.matchDriversWithPassengers();

        //Afisam toate destinatiile pentru drivers
        List<String> allDestinationsForDrivers = problem.getAllDestinationsForDrivers();
        System.out.println("\nDestinatiile soferilor" + allDestinationsForDrivers);

        //Harta destinatiilor a passengers
        Map<String, Set<Person>> destinationPassengerMap = problem.getDestinationPassengerMap();
        System.out.println("\nHarta destinatiilor pasagerilor:");
        for (Map.Entry<String, Set<Person>> entry : destinationPassengerMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private static List<Person> generateRandomPersons(int count) {
        Faker faker = new Faker();
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            if (faker.bool().bool()) {
                int numDestinations = faker.number().numberBetween(1,5);
                List<String> destinations = new ArrayList<>();
                for(int j=0; j<numDestinations; j++){
                    destinations.add(faker.address().city());
                }
                persons.add(new Driver(faker.name().fullName(), faker.number().numberBetween(18,60), destinations));
            } else {
                persons.add(new Passenger(faker.name().fullName(), faker.number().numberBetween(18, 60), faker.address().city()));
            }
        }
        return persons;
    }
}
