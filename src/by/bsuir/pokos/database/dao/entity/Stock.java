
package by.bsuir.pokos.database.dao.entity;

import java.io.Serializable;


public class Stock implements Serializable {
    
    static final long serialVersionUID = 3432063776451490808L;
    
    private int stockID;
    private String stockName;
    private String stockAdress;
    private String stockPostalCode;
    private String stockCity;
    private String stockCountry;

    public Stock(int stockID, String stockName, String stockAdress,String stockPostalCode,String stockCity, String stockCountry) {
        this.stockID = stockID;
        this.stockName = stockName;
        this.stockAdress = stockAdress;
        this.stockPostalCode = stockPostalCode;
        this.stockCity = stockCity;
        this.stockCountry = stockCountry;
    }

    public int getStockID() {
        return stockID;
    }

    public void setStockID(int stockID) {
        this.stockID = stockID;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStockAdress() {
        return stockAdress;
    }

    public void setStockAdress(String stockAdress) {
        this.stockAdress = stockAdress;
    }

    public String getStockPostalCode() {
        return stockPostalCode;
    }

    public void setStockPostalCode(String stockPostalCode) {
        this.stockPostalCode = stockPostalCode;
    }

    public String getStockCity() {
        return stockCity;
    }

    public void setStockCity(String stockCity) {
        this.stockCity = stockCity;
    }

    public String getStockCountry() {
        return stockCountry;
    }

    public void setStockCountry(String stockCountry) {
        this.stockCountry = stockCountry;
    }

    
    
    
}
