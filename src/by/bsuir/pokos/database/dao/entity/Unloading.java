package by.bsuir.pokos.database.dao.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public final class Unloading implements Serializable{
    
    static final long serialVersionUID = 3432063776451490808L;
    
    private final int ID;
    private final int unloadingStockID;
    private final String unloadingClient;
    private final String unloadingCity;
    private final String unloadingCountry;
    private final Date unloadingDate;
    private final Time unloadingTime;

    public Unloading(int ID,int unloadingStockID, String unloadingClient, String unloadingCity, String unloadingCountry, Date unloadingDate, Time unloadingTime) {
        this.ID=ID;
        this.unloadingStockID = unloadingStockID;
        this.unloadingClient = unloadingClient;
        this.unloadingCity = unloadingCity;
        this.unloadingCountry = unloadingCountry;
        this.unloadingDate = unloadingDate;
        this.unloadingTime = unloadingTime;
    }

    public int getID() {
        return ID;
    }

    public Date getUnloadingDate() {
        return unloadingDate;
    }

    public Time getUnloadingTime() {
        return unloadingTime;
    }

    public int getUnloadingStockID() {
        return unloadingStockID;
    }

    public String getUnloadingClient() {
        return unloadingClient;
    }

    public String getUnloadingCity() {
        return unloadingCity;
    }

    public String getUnloadingCountry() {
        return unloadingCountry;
    }

    public static class UnloadingBuilder {
        
        private int nestedID;
        private int nestedUnloadingStockID;
        private String nestedUnloadingClient;
        private String nestedUnloadingCity;
        private String nestedUnloadingCountry;
        private Date nestedunloadingDate;
        private Time nestedunloadingTime;

        public UnloadingBuilder(int nestedID,int nestedUnloadingStockID, String nestedUnloadingClient, String nestedUnloadingCity, String nestedUnloadingCountry, 
                Date nestedunloadingDate, Time nestedunloadingTime) {
            this.nestedID=nestedID;
            this.nestedUnloadingStockID = nestedUnloadingStockID;
            this.nestedUnloadingClient = nestedUnloadingClient;
            this.nestedUnloadingCity = nestedUnloadingCity;
            this.nestedUnloadingCountry = nestedUnloadingCountry;
            this.nestedunloadingDate = nestedunloadingDate;
            this.nestedunloadingTime = nestedunloadingTime;
        }

        public void setNestedunloadingDate(Date nestedunloadingDate) {
            this.nestedunloadingDate = nestedunloadingDate;
        }

        public void setNestedID(int nestedID) {
            this.nestedID = nestedID;
        }

        public void setNestedunloadingTime(Time nestedunloadingTime) {
            this.nestedunloadingTime = nestedunloadingTime;
        }
        
        public void setNestedUnloadingStockID(int nestedUnloadingStockID) {
            this.nestedUnloadingStockID = nestedUnloadingStockID;
        }

        public void setNestedUnloadingClient(String nestedUnloadingClient) {
            this.nestedUnloadingClient = nestedUnloadingClient;
        }

        public void setNestedUnloadingCity(String nestedUnloadingCity) {
            this.nestedUnloadingCity = nestedUnloadingCity;
        }

        public void setNestedUnloadingCountry(String nestedUnloadingCountry) {
            this.nestedUnloadingCountry = nestedUnloadingCountry;
        }

        public Unloading createUnloading() {
            return new Unloading(
                                nestedID,
                                nestedUnloadingStockID,
                                nestedUnloadingClient,
                                nestedUnloadingCity,
                                nestedUnloadingCountry,
                                nestedunloadingDate,
                                nestedunloadingTime
                                );
        }
        
    }
    
}
