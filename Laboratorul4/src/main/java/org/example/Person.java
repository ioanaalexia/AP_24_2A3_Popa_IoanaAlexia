package org.example;

import java.util.Objects;

public abstract class Person implements Comparable<Person> {
    private String name;
    private int age;
    private String destination;

    public Person(String name, int age, String destination) {
        this.name = name;
        this.age = age;
        this.destination = destination;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDestination() {
        return destination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name) && Objects.equals(destination, person.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, destination);
    }

    @Override
    public int compareTo(Person otherPerson) {
        return Integer.compare(this.age, otherPerson.age);
    }

    @Override
    public String toString() {
        return name + " - Age: " + age + " - Destination: " + destination;
    }
}
