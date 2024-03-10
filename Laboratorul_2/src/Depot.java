import java.util.Arrays;
public class Depot {
    private String name;
    private Vehicle[] vehicles;

    public Depot(String name){
        this.name = name;
    }

    public Depot(String name, Vehicle[] vehicles) {
        this.name = name;
        this.vehicles = vehicles;
    }

    public Depot(Vehicle[] vehicles) {
        this.vehicles = vehicles;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){

        this.name = name;
    }

    public Vehicle[] getVehicles(){

        return vehicles;
    }

    public void setVehicles(Vehicle...vehicles){
        this.vehicles = vehicles;
        for(Vehicle v : vehicles){
            v.setDepot(this);
        }
    }
    public boolean canAddVehicle(Vehicle vehicle){
        final int MAX_CAPACITY = 10;
        if(vehicle == null || vehicles.length >= MAX_CAPACITY){
                return false;
        }
        return true;
    }

    public void addVehicle(Vehicle vehicle){
        if(canAddVehicle(vehicle)){
            vehicle.setDepot(this);

            if(vehicles == null){
                vehicles = new Vehicle[]{vehicle};
            }else{
                Vehicle[] newVehicles = Arrays.copyOf(vehicles, vehicles.length + 1);
                newVehicles[vehicles.length] = vehicle;
                vehicles = newVehicles;
            }

        }else{
            System.out.println("Vehiculul nu poate fi adaugat in depozitul" + name + ".");
        }
    }

    public String toString(){
        return "Depot{" + "name=' " + name + '\'' + ",vehicles=" + Arrays.toString(vehicles)+'}';
    }

    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Depot depot = (Depot) obj;
        if (!name.equals(depot.name))
            return false;
        // Probably you would also want to compare the vehicles array,
        // but it's already compared in the equals method of Vehicle class
        return Arrays.equals(vehicles, depot.vehicles);
    }
}
