import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * Clasa care reprezinta o problema de alocare a clientilor la vehicule.
 *
 * Aceasta clasa gestioneaza depozitele, vehiculele, clientii și alocarea acestora la vehicule.
 */

public class Problem {

    private List<Depot> depots;
    private List<Vehicle> vehicles;
    private List<Client> clients;

    /**
     * Construieste o instanta a problemei cu depozitele, vehiculele, clientii si timpii de calatorie specificati.
     * @param depots Lista de depozite
     * @param vehicles Lista de vehicule
     * @param clients Lista de clienți
     * @param travelTimes Matricea de timpi de calatorie intre clienti
     */

    public Problem(List<Depot> depots, List<Vehicle> vehicles, List<Client> clients, int[][] travelTimes) {
        this.depots = depots;
        this.vehicles = vehicles;
        this.clients = clients;
        this.travelTimes = travelTimes;
    }

    public Problem() {
        this.depots = new ArrayList<>();
        this.clients = new ArrayList<>();
        this.vehicles = new ArrayList<>();
    }

    /**
     * Obține lista de depozite.
     * @return Lista de depozite
     */

    public List<Depot> getDepots() {
        return depots;
    }

    /**
     * Seteaza lista de depozite.
     * @param depots Lista de depozite
     */

    public void setDepots(List<Depot> depots) {
        this.depots = depots;
    }

    /**
     * Obtine lista de clienti.
     * @return Lista de clienti
     */

    public List<Client> getClients() {
        return clients;
    }

    /**
     * Seteaza lista de clienti.
     * @param clients Lista de clienti
     */

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public void addDepot(Depot depot) {
        if (!depots.contains(depot)) {
            depots.add(depot);
        }
    }
    public void addVehicle(Vehicle vehicle) {
        if (!vehicles.contains(vehicle)) {
            vehicles.add(vehicle);
        }
    }
    public void addClient(Client client) {
        if (!clients.contains(client)) {
            clients.add(client);
        }
    }

    public Vehicle[] getVehicles() {
        List<Vehicle> allVehicles = new ArrayList<>();
        for (Depot depot : depots) {
            allVehicles.addAll(Arrays.asList(depot.getVehicles()));
        }
        return allVehicles.toArray(new Vehicle[0]);
    }

    /**
     * Generam si stocam timpii intre clienti
     */
    private static final int MAX_TRAVEL_TIME = 30;
    public int generateRandomTravelTime(){
        Random random = new Random();
        return random.nextInt(MAX_TRAVEL_TIME) + 1;
    }

    private int[][] travelTimes;

    public void generateRandomTravelsTime(List<Client> clients) {
        int numClients = clients.size();
        travelTimes = new int[numClients][numClients];

        for (int i = 0; i < numClients; i++) {
            for (int j = 0; j < numClients; j++) {
                if (i == j) {
                    travelTimes[i][j] = 0;
                } else {
                    travelTimes[i][j] = generateRandomTravelTime(); // Genereaza un timp de calatorie aleatoriu intre clienti
                }
            }
        }
    }

    private boolean canAddClient(Vehicle vehicle, Client client) {
        return true;
    }


    /**
     * Alocam clienti vehicolelor
     */

    public void allocateClients() {
        clients.sort(Comparator.comparing(Client::getMinTime));

        List<Tour> tours = new ArrayList<>();

        // Iteram clientii si ii asignam vehicolelor
        for (Client client : clients) {

            Vehicle nearestVehicle = findNearestVehicle(client);

            if (nearestVehicle != null && canAddClient(nearestVehicle, client)) {
                // Verificam daca exista un tur in nearestVehicle
                Tour tour = findTourByVehicle(tours, nearestVehicle);
                if (tour == null) {
                    // Cream un tur nou
                    tour = new Tour(nearestVehicle);
                    tours.add(tour);
                }

                // Adaugam clientul
                tour.addClient(client);
                System.out.println("Clientul " + client.getName() + " este asignat catre " + nearestVehicle);
            } else {
                System.out.println("Niciun autovehicul valabil pentru clientul " + client.getName());
            }
        }

        System.out.println("\nTours:");
        for (Tour tour : tours) {
            System.out.println(tour);
        }
    }

    /**
     * cautam cel mai apropiat autovehicul de un anumit client pentru a face repartizarea
     */
    private Vehicle findNearestVehicle(Client client) {
        Vehicle nearestVehicle = null;
        double minTravelTime = Double.MAX_VALUE;

        int clientIndex = clients.indexOf(client);
        if (clientIndex == -1) {
            return null; // Clientul nu a fost gasit în lista
        }

        for (Vehicle vehicle : getVehicles()) {
            if (canAddClient(vehicle, client)) {
                int vehicleIndex = vehicles.indexOf(vehicle);
                if (vehicleIndex != -1) {
                    int travelTime = travelTimes[clientIndex][vehicleIndex];
                    if (travelTime < minTravelTime) {
                        minTravelTime = travelTime;
                        nearestVehicle = vehicle;
                    }
                }
            }
        }

        return nearestVehicle;
    }
    private Tour findTourByVehicle(List<Tour> tours, Vehicle vehicle) {
        for (Tour tour : tours) {
            if (tour.getVehicle().equals(vehicle)) {
                return tour;
            }
        }
        return null;
    }

}
