/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.pokos.database.dao.entity;

import java.io.Serializable;

/**
 *
 * @author Gavrilik
 */
public final class Cargo implements Serializable{
    
    static final long serialVersionUID = 3432063776451490808L;
    
    private final int ID;
    private final String cargoDescription;
    private final int cargoWeight;
    private final int cargoCount;
    private final String cargoDocument;

    public Cargo(int ID, String cargoDescription, int cargoWeight, int cargoCount, String cargoDocument) {
        this.ID = ID;
        this.cargoDescription = cargoDescription;
        this.cargoWeight = cargoWeight;
        this.cargoCount = cargoCount;
        this.cargoDocument = cargoDocument;
    }

    public int getID() {
        return ID;
    }

    public String getCargoDescription() {
        return cargoDescription;
    }

    public int getCargoWeight() {
        return cargoWeight;
    }

    public int getCargoCount() {
        return cargoCount;
    }

    public String getCargoDocument() {
        return cargoDocument;
    }
    
    public static class CargoBuilder {
        
        private int nestedID;
        private String nestedCargoDescription;
        private int nestedCargoWeight;
        private int nestedCargoCount;
        private String nestedCargoDocument;

        public void setNestedID(int nestedID) {
            this.nestedID = nestedID;
        }
        
        public CargoBuilder(int nestedID,String nestedCargoDescription, int nestedCargoWeight, int nestedCargoCount, String nestedCargoDocument) {
            this.nestedCargoDescription = nestedCargoDescription;
            this.nestedCargoWeight = nestedCargoWeight;
            this.nestedCargoCount = nestedCargoCount;
            this.nestedCargoDocument = nestedCargoDocument;
            this.nestedID=nestedID;
        }

        public void setNestedCargoDescription(String nestedCargoDescription) {
            this.nestedCargoDescription = nestedCargoDescription;
        }

        public void setNestedCargoWeight(int nestedCargoWeight) {
            this.nestedCargoWeight = nestedCargoWeight;
        }

        public void setNestedCargoCount(int nestedCargoCount) {
            this.nestedCargoCount = nestedCargoCount;
        }

        public void setNestedCargoDocument(String nestedCargoDocument) {
            this.nestedCargoDocument = nestedCargoDocument;
        }
        
        public Cargo createCargo(){
            return new Cargo(nestedID,
                            nestedCargoDescription,
                            nestedCargoWeight,
                            nestedCargoCount,
                            nestedCargoDocument);
        }
    }
    
    
}
