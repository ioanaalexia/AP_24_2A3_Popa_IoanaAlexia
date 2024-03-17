import java.util.Objects;
import java.time.LocalDate;
import java.util.Map;

public abstract class Attraction implements Comparable<Attraction> {
    private String name;

    public Attraction() { }

    public Attraction(String name) {
        this.name = name;
    }

    // Getter and setter for name

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attraction that = (Attraction) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(Attraction other) {
        if (name == null && other.name == null) {
            return 0;
        } else if (name == null) {
            return -1;
        } else if (other.name == null) {
            return 1;
        }
        return this.name.compareTo(other.name);
    }

    // Override toString method
    @Override
    public String toString() {
        return "Attraction{" +
                "name='" + name + '\'' +
                '}';
    }

}
