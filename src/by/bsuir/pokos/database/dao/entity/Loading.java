/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.pokos.database.dao.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Gavrilik
 */
public final class Loading implements Serializable{
    
    static final long serialVersionUID = 3432063776451490808L;
    
    private final int ID;
    private final String loadingCompanyName;
    private final String loadingAdress;
    private final String loadingPostalCode;
    private final String loadingCity;
    private final String loadingCountry;
    private final Date loadingDate;
    private final Time loadingTime;

    public Loading(int ID,String loadingCompanyName,
            String loadingAdress, String loadingPostalCode,
            String loadingCity,
            String loadingCountry,
            Date loadingDate, Time loadingTime)
    {
        this.ID=ID;
        this.loadingCompanyName = loadingCompanyName;
        this.loadingAdress = loadingAdress;
        this.loadingPostalCode = loadingPostalCode;
        this.loadingCity = loadingCity;
        this.loadingCountry = loadingCountry;
        this.loadingDate = loadingDate;
        this.loadingTime = loadingTime;
    }

    public int getID() {
        return ID;
    }
    
    public String getLoadingCompanyName() {
        return loadingCompanyName;
    }

    public String getLoadingAdress() {
        return loadingAdress;
    }

    public String getLoadingPostalCode() {
        return loadingPostalCode;
    }

    public String getLoadingCity() {
        return loadingCity;
    }

    public String getLoadingCountry() {
        return loadingCountry;
    }

    public Date getLoadingDate() {
        return loadingDate;
    }

    public Time getLoadingTime() {
        return loadingTime;
    }
    
    public static class LoadingBuilder {
        
        private int nestedID;
        private String nestedLoadingCompanyName;
        private String nestedLoadingAdress;
        private String nestedLoadingPostalCode;
        private String nestedLoadingCity;
        private String nesteLoadingCountry;
        private Date nestedLoadingDate;
        private Time nestedLoadingTime;

        public LoadingBuilder(
                int nestedID,
                String nestedLoadingCompanyName,
                String nestedLoadingAdress,
                String nestedLoadingPostalCode,
                String nestedLoadingCity,
                String nesteLoadingCountry,
                Date nestedLoadingDate,
                Time nestedLoadingTime) 
        {
            this.nestedID=nestedID;
            this.nestedLoadingCompanyName = nestedLoadingCompanyName;
            this.nestedLoadingAdress = nestedLoadingAdress;
            this.nestedLoadingPostalCode = nestedLoadingPostalCode;
            this.nestedLoadingCity = nestedLoadingCity;
            this.nesteLoadingCountry = nesteLoadingCountry;
            this.nestedLoadingDate = nestedLoadingDate;
            this.nestedLoadingTime = nestedLoadingTime;
        }

        public void setNestedID(int nestedID) {
            this.nestedID = nestedID;
        }

        public void setNestedLoadingCompanyName(String nestedLoadingCompanyName) {
            this.nestedLoadingCompanyName = nestedLoadingCompanyName;
        }

        public void setNestedLoadingAdress(String nestedLoadingAdress) {
            this.nestedLoadingAdress = nestedLoadingAdress;
        }

        public void setNestedLoadingPostalCode(String nestedLoadingPostalCode) {
            this.nestedLoadingPostalCode = nestedLoadingPostalCode;
        }

        public void setNestedLoadingCity(String nestedLoadingCity) {
            this.nestedLoadingCity = nestedLoadingCity;
        }

        public void setNesteLoadingCountry(String nesteLoadingCountry) {
            this.nesteLoadingCountry = nesteLoadingCountry;
        }

        public void setNestedLoadingDate(Date nestedLoadingDate) {
            this.nestedLoadingDate = nestedLoadingDate;
        }

        public void setNestedLoadingTime(Time nestedLoadingTime) {
            this.nestedLoadingTime = nestedLoadingTime;
        }
        
        
        
        public Loading createLoading(){
            return new Loading(
                                nestedID,    
                                nestedLoadingCompanyName,
                                nestedLoadingAdress,
                                nestedLoadingPostalCode,
                                nestedLoadingCity,
                                nesteLoadingCountry,
                                nestedLoadingDate,
                                nestedLoadingTime);
        }
    }
    
}
