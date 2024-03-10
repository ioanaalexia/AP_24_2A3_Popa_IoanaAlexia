import java.time.LocalTime;

public class Lab2{
    public static void main(String args[]){

        Problem problema = new Problem();

        //Cream si adaugam depozite
        Depot depot1 = new Depot("Depot1");
        Depot depot2 = new Depot("Depot2");
        problema.addDepot(depot1);
        problema.addDepot(depot2);

        //Cream si adaugam vehicule
        Vehicle truck1 = new Trucks("Truck1", depot1, 10);
        Vehicle drone1 = new Drones("Drone1", depot2, 60);
        problema.addVehicle(truck1);
        problema.addVehicle(drone1);

        // Creare și adăugare clienți
        Client client1 = new Client("Client1", LocalTime.of(8, 0), LocalTime.of(12, 0)); // Exemplu de client cu interval de timp de vizitare între 8:00 și 12:00
        Client client2 = new Client("Client2", LocalTime.of(10, 0), LocalTime.of(14, 0)); // Exemplu de client cu interval de timp de vizitare între 10:00 și 14:00
        problema.addClient(client1);
        problema.addClient(client2);

        problema.generateRandomTravelsTime(problema.getClients());

        System.out.println(problema);
    }
}