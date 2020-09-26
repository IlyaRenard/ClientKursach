
package by.bsuir.pokos.database.dao.entity;

import java.io.Serializable;


public final class Carrier implements Serializable{
    
    static final long serialVersionUID = 3432063776451490808L;
    
    private final int ID;
    private final String carrierCompanyName;
    private final String carrierContact;
    private final String carrierTelephone;
    private final String carrierElMail;
    private final String carrierForeignRegNumber;
    private final String carrierDriverTelephoneNumber;

    public Carrier(
            int ID,
            String carrierCompanyName,
            String carrierContact,
            String carrierTelephone,
            String carrierElMail,
            String carrierForeignRegNaumber,
            String carrierDriverTelephoneNumber)
    {
        this.carrierCompanyName = carrierCompanyName;
        this.carrierContact = carrierContact;
        this.carrierTelephone = carrierTelephone;
        this.carrierElMail = carrierElMail;
        this.carrierForeignRegNumber = carrierForeignRegNaumber;
        this.carrierDriverTelephoneNumber = carrierDriverTelephoneNumber;
        this.ID=ID;
    }

    public int getID() {
        return ID;
    }

    
    
    public String getCarrierCompanyName() {
        return carrierCompanyName;
    }

    public String getCarrierContact() {
        return carrierContact;
    }

    public String getCarrierTelephone() {
        return carrierTelephone;
    }

    public String getCarrierElMail() {
        return carrierElMail;
    }

    public String getCarrierForeignRegNumber() {
        return carrierForeignRegNumber;
    }

    public String getCarrierDriverTelephoneNumber() {
        return carrierDriverTelephoneNumber;
    }
    
    public static class CarrierBuilder {
        
        private int nestedID;
        private String nestedCarrierCompanyName;
        private String nestedCarrierContact;
        private String nestedCarrierTelephone;
        private String nestedCarrierElMail;
        private String nestedCarrierForeignRegNumber;
        private String nestedCarrierDriverTelephoneNumber;

        public CarrierBuilder(
                int nestedID,
                String nestedCarrierCompanyName,
                String nestedCarrierContact,
                String nestedCarrierTelephone,
                String nestedCarrierElMail,
                String nestedCarrierForeignRegNaumber,
                String nestedCarrierDriverTelephoneNumber)
        {
            this.nestedID=nestedID;
            this.nestedCarrierCompanyName = nestedCarrierCompanyName;
            this.nestedCarrierContact = nestedCarrierContact;
            this.nestedCarrierTelephone = nestedCarrierTelephone;
            this.nestedCarrierElMail = nestedCarrierElMail;
            this.nestedCarrierForeignRegNumber = nestedCarrierForeignRegNaumber;
            this.nestedCarrierDriverTelephoneNumber = nestedCarrierDriverTelephoneNumber;
        }

        public void setNestedID(int nestedID) {
            this.nestedID = nestedID;
        }

        public void setNestedCarrierCompanyName(String nestedCarrierCompanyName) {
            this.nestedCarrierCompanyName = nestedCarrierCompanyName;
        }

        public void setNestedCarrierContact(String nestedCarrierContact) {
            this.nestedCarrierContact = nestedCarrierContact;
        }

        public void setNestedCarrierTelephone(String nestedCarrierTelephone) {
            this.nestedCarrierTelephone = nestedCarrierTelephone;
        }

        public void setNestedCarrierElMail(String nestedCarrierElMail) {
            this.nestedCarrierElMail = nestedCarrierElMail;
        }

        public void setNestedCarrierForeignRegNaumber(String nestedCarrierForeignRegNaumber) {
            this.nestedCarrierForeignRegNumber = nestedCarrierForeignRegNaumber;
        }

        public void setNestedCarrierDriverTelephoneNumber(String nestedCarrierDriverTelephoneNumber) {
            this.nestedCarrierDriverTelephoneNumber = nestedCarrierDriverTelephoneNumber;
        }
        
        public Carrier createCarrier() {
            return new Carrier(
                nestedID,    
                nestedCarrierCompanyName,
                nestedCarrierContact,
                nestedCarrierTelephone,
                nestedCarrierElMail,
                nestedCarrierForeignRegNumber,
                nestedCarrierDriverTelephoneNumber
            );
        }
        
        
    }
    
    
    
}
