import java.time.LocalDate;
import java.util.Map;

public class Concert extends Attraction implements Visitable, Payable {
    private Map<LocalDate, TimeInterval> timetable;
    private double ticketPrice;

    public Concert(String name){
        super(name);
    }

    public Concert(Map<LocalDate, TimeInterval> timetable, double ticketPrice) {
        this.timetable = timetable;
        this.ticketPrice = ticketPrice;
    }

    public Concert(String name, Map<LocalDate, TimeInterval> timetable) {
        super(name);
        this.timetable = timetable;
    }

    public Concert(String name, double ticketPrice) {
        super(name);
        this.ticketPrice = ticketPrice;
    }

    @Override
    public Map<LocalDate, TimeInterval> getTimetable() {
        return timetable;
    }

    @Override
    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTimetable(Map<LocalDate, TimeInterval> timetable) {
        this.timetable = timetable;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }


}