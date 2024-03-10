import java.time.LocalTime;
public class Client {
    private String name;
    private LocalTime minTime;
    private LocalTime maxTime;

    private ClientType type;
    public Client(){}
    public Client(String name){
        this(name, null, null);
    }

    public Client(String name, LocalTime minTime, LocalTime maxTime){
        this.name = name;
        this.minTime = minTime;
        this.maxTime = maxTime;
    }

    public String toString(){
        return "Client{" + "name=' " + name + '\'' + ", minTime=" + minTime + ", maxTime=" + maxTime + '}';
    }

    public String getName(){
        return name;
    }

    public LocalTime getMinTime(){
        return minTime;
    }

    public LocalTime getMaxTime(){
        return maxTime;
    }

    public ClientType getType(){
        return type;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setMinTime(LocalTime minTime){
        this.minTime = minTime;
    }

    public void setMaxTime(LocalTime maxTime){
        this.maxTime = maxTime;
    }

    public void setType(ClientType type){
        this.type=type;
    }
}
