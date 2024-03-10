import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Tour {
    private Vehicle vehicle;
    private List <Client> clients;

    public Tour(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Tour(Vehicle vehicle, List<Client> clients) {
        this.vehicle = vehicle;
        this.clients = clients;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    /**
     * adaugam si stergem clientii
     */
    public void addClient(Client client) {
        clients.add(client);
    }

    public void removeClient(Client client) {
        clients.remove(client);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tour tour = (Tour) o;
        return Objects.equals(vehicle, tour.vehicle) && Objects.equals(clients, tour.clients);
    }

    @Override
    public String toString() {
        return "Tour{" +
                "vehicle=" + vehicle +
                ", clients=" + clients +
                '}';
    }
}
