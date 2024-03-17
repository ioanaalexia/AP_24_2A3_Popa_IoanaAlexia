import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class TravelPlan {
    private Map<LocalDate, Attraction> plan;

    public TravelPlan() {
        plan = new HashMap<>();
    }

    public void addVisit(Attraction attraction, String date) {
        LocalDate visitDate = LocalDate.parse(date);
        if (plan.containsKey(visitDate)) {
            // Check if the attraction type is different from already scheduled attractions for that day
            if (!plan.get(visitDate).getClass().equals(attraction.getClass())) {
                plan.put(visitDate, attraction);
            } else {
                System.out.println("Cannot add visit to " + attraction.getName() + " on " + visitDate + ". Different types of attractions are recommended each day.");
            }
        } else {
            plan.put(visitDate, attraction);
        }
    }

    public void printPlan() {
        for (Map.Entry<LocalDate, Attraction> entry : plan.entrySet()) {
            System.out.println("On " + entry.getKey() + ", visit " + entry.getValue().getName());
        }
    }
}
