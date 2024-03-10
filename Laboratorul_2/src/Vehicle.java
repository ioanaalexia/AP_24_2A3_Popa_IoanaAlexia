import java.util.ArrayList;
import java.util.List;
public abstract class Vehicle {
    private String name;
    private Depot depot;

    private List<Client> clients = new ArrayList<>();

    public Vehicle(){}
    public Vehicle(String name){
        this.name = name;
    }

    public Vehicle(String name, Depot depot) {
        this.name = name;
        this.depot = depot;
    }

    public Vehicle(Depot depot) {
        this.depot = depot;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Depot getDepot(){
        return depot;
    }

    public void setDepot(Depot depot){
        this.depot=depot;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public String toString() {
        if (depot != null) {
            return "Vehicle{" +
                    "name='" + name + '\'' +
                    ", depot=" + depot.getName() +
                    '}';
        } else {
            return "Vehicle{" +
                    "name='" + name + '\'' +
                    ", depot=No depot assigned" +
                    '}';
        }
    }

    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }

        if(obj == null || getClass() != obj.getClass()){
            return false;
        }

        Vehicle vehicle = (Vehicle) obj;
        return name.equals(vehicle.name);
    }

}
