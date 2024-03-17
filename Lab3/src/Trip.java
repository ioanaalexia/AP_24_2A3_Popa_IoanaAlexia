import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;
import java.time.LocalTime;

public class Trip {
    private String city;

    private LocalDate start, end;

    private List<Attraction>  attractions;

    public Trip(String city, LocalDate start, LocalDate end, List<Attraction> attractions) {
        this.city = city;
        this.start = start;
        this.end = end;
        this.attractions = attractions;
    }

    public void addAttraction(Attraction attraction){
        attractions.add(attraction);
    }
    public String getCity() {
        return city;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public List<Attraction> getAttractions() {
        return attractions;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public void setAttractions(List<Attraction> attractions) {
        this.attractions = attractions;
    }

    //afisare atractii care sunt vizitabile si gratuite
    public void displayVisitableNonPayableLocations() {
        List<Attraction> visitableNonPayableLocations = new ArrayList<>();

        for (Attraction attraction : attractions) {
            if (attraction instanceof Visitable && !(attraction instanceof Payable)) {
                visitableNonPayableLocations.add(attraction);
            }
        }

        Collections.sort(visitableNonPayableLocations, new Comparator<Attraction>() {
            @Override
            public int compare(Attraction attraction1, Attraction attraction2) {
                for (LocalDate date : ((Visitable) visitableNonPayableLocations.get(0)).getTimetable().keySet()) {
                    LocalTime openingHour1 = ((Visitable) attraction1).getOpeningHour(date);
                    LocalTime openingHour2 = ((Visitable) attraction2).getOpeningHour(date);
                    if (openingHour1 == null && openingHour2 == null) {
                        continue;
                    } else if (openingHour1 == null) {
                        return 1;
                    } else if (openingHour2 == null) {
                        return -1;
                    } else {
                        return openingHour1.compareTo(openingHour2);
                    }
                }
                return 0;
            }
        });

        System.out.println("Attractiile vizitabile dar gratuite sortate dupa ora de intrare:");
        for (Attraction attraction : visitableNonPayableLocations) {
            LocalTime openingHour = null;
            if (attraction instanceof Visitable) {
                openingHour = ((Visitable) attraction).getOpeningHour(start);
            }
            System.out.println("Atractia: " + attraction.getName() + ", Ora de deschidere: " + openingHour);
        }
    }

    @Override
    public String toString() {
        return "Trip{" +
                "city='" + city + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", attractions=" + attractions +
                '}';
    }

}
