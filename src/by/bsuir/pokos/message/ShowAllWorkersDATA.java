package by.bsuir.pokos.message;

import java.io.Serializable;
import java.util.ArrayList;


public class ShowAllWorkersDATA implements Serializable{
    
    static final long serialVersionUID = 3432063776451490808L;
    private ArrayList<User> workers = new ArrayList<>(); 

    public ArrayList<User> getWorkers() {
        return workers;
    }

    public void setWorkers(ArrayList<User> workers) {
        this.workers = workers;
    }

    
    
    
}
