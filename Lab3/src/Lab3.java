import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Lab3 {
    public static void main(String[] args) {

        Church church = new Church("Saint Mary's Church");
        Map<LocalDate, TimeInterval> churchTimetable = new HashMap<>();
        churchTimetable.put(LocalDate.of(2024, 3, 2), new TimeInterval(LocalTime.of(8, 0), LocalTime.of(20, 0)));
        church.setTimetable(churchTimetable);

        Statue statue = new Statue("Mihai Eminescu");

        Concert concert = new Concert("Dua Lipa");
        Map<LocalDate, TimeInterval> concertTimetable = new HashMap<>();
        concertTimetable.put(LocalDate.of(2024, 3, 3), new TimeInterval(LocalTime.of(19, 0), LocalTime.of(22, 0)));
        concert.setTimetable(concertTimetable);
        concert.setTicketPrice(30);

        Visitable[] arr = {church, statue, concert};

        // Testare
        for (Visitable visitable : arr) {
            System.out.println("Name: " + visitable.getName());

            if (visitable instanceof Payable) {
                System.out.println("Ticket Price: " + ((Payable) visitable).getTicketPrice());
            }

            Map<LocalDate, TimeInterval> timetable = visitable.getTimetable();
            if (timetable != null) {
                System.out.println("Timetable: " + timetable);
            }

        }
    }
}
