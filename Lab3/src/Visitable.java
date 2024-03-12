import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
public interface Visitable {

    String getName();
    Map<LocalDate, TimeInterval> getTimetable();

    default LocalTime getOpeningHour(LocalDate date) {
        Map<LocalDate, TimeInterval> timetable = getTimetable();
        TimeInterval timeInterval = getTimetable().get(date);
        if (timeInterval != null) {
            return timeInterval.getStartTime();
        }
        return null;
    }
}