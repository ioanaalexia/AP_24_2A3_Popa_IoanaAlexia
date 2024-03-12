import java.time.LocalDate;
import java.util.Map;
public class Statue extends Attraction implements Visitable {
    private String name;
    public Statue() {}

    public String getName() {
        return name;
    }

    public Statue(String name) {
        super(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<LocalDate, TimeInterval> getTimetable() {
        return null;
    }

    public double getPrice(){
        return 0;
    }

    public double getTicketPrice() {
        return 0;
    }
}
