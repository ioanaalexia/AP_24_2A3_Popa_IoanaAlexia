import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Lab3 {
    public static void main(String[] args) {
        // Create visiting timetable for attractions
        Map<LocalDate, TimeInterval> statueTimetable = new HashMap<>();
        statueTimetable.put(LocalDate.of(2024, 3, 18), new TimeInterval(LocalTime.of(9, 0), LocalTime.of(18, 0)));
        statueTimetable.put(LocalDate.of(2024, 3, 19), new TimeInterval(LocalTime.of(10, 0), LocalTime.of(17, 0)));

        Map<LocalDate, TimeInterval> churchTimetable = new HashMap<>();
        churchTimetable.put(LocalDate.of(2024, 3, 20), new TimeInterval(LocalTime.of(8, 0), LocalTime.of(20, 0)));
        churchTimetable.put(LocalDate.of(2024, 3, 22), new TimeInterval(LocalTime.of(9, 0), LocalTime.of(16, 0)));

        Map<LocalDate, TimeInterval> concertTimetable = new HashMap<>();
        concertTimetable.put(LocalDate.of(2024, 3, 21), new TimeInterval(LocalTime.of(20, 0), LocalTime.of(23, 0)));

        // Create attractions
        Statue statue = new Statue("Statue of Liberty", statueTimetable);
        Church church = new Church("St. Peter's Basilica", churchTimetable);
        Concert concert = new Concert("Live Aid Concert", concertTimetable);

        // Create trip
        List<Attraction> attractions = new ArrayList<>();
        attractions.add(statue);
        attractions.add(church);
        attractions.add(concert);

        LocalDate tripStartDate = LocalDate.of(2024, 3, 18);
        LocalDate tripEndDate = LocalDate.of(2024, 3, 23);

        Trip trip = new Trip("New York", tripStartDate, tripEndDate, attractions);


        // Create travel plan
        TravelPlan travelPlan = new TravelPlan();
        travelPlan.addVisit(statue, "2024-03-18");
        travelPlan.addVisit(church, "2024-03-22");
        travelPlan.addVisit(concert, "2024-03-21");
        travelPlan.addVisit(church, "2024-03-20"); // Trying to add another church visit on the same day

        // Print travel plan
        System.out.println("Travel Plan:");
        travelPlan.printPlan();

        // Display visitable non-payable locations sorted by opening hour
        trip.displayVisitableNonPayableLocations();
    }
}
