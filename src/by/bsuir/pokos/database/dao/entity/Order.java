package by.bsuir.pokos.database.dao.entity;

import java.io.Serializable;
import java.sql.Date;

public class Order implements Serializable{
    
    static final long serialVersionUID = 3432063776451490808L;
     ////////////////////////////////////
    private final int ID;
    private final Date dateOfOrder;
    private final String numberOfOrder;
    private final String orderStatus;
    private final int orderBYforwarderID;
    private final int orderPLforwarderID;
    
    private final int carrierID;
    private final int loadingID;
    private final int unloadingID;
    private final int cargoID;
    private final Payment payment;
    //////////////////////////////////////

    public Order(int ID,
            Date dateOfOrder,
            String numberOfOrder,
            String orderStatus,
            int orderBYforwarderID,
            int orderPLforwarderID,
            int carrierID,
            int loadingID, 
            int unloadingID, 
            int cargoID,
            Payment payment) {
        this.ID = ID;
        this.dateOfOrder = dateOfOrder;
        this.numberOfOrder = numberOfOrder;
        this.orderStatus = orderStatus;
        this.orderBYforwarderID = orderBYforwarderID;
        this.orderPLforwarderID = orderPLforwarderID;
        this.carrierID = carrierID;
        this.loadingID = loadingID;
        this.unloadingID = unloadingID;
        this.cargoID = cargoID;
        this.payment = payment;
    }

    public int getID() {
        return ID;
    }

    public Date getDateOfOrder() {
        return dateOfOrder;
    }

    public String getNumberOfOrder() {
        return numberOfOrder;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public int getOrderBYforwarderID() {
        return orderBYforwarderID;
    }

    public int getOrderPLforwarderID() {
        return orderPLforwarderID;
    }

    public int getCarrierID() {
        return carrierID;
    }

    public int getLoadingID() {
        return loadingID;
    }

    public int getUnloadingID() {
        return unloadingID;
    }

    public int getCargoID() {
        return cargoID;
    }

    public Payment getPayment() {
        return payment;
    }
    
    
    
    public static class OrderBuilder {
        
        ////////////////////////////////////
        private  int nestedID;
        private  Date nesteDateOfOrder;
        private  String nestedNumberOfOrder;
        private  String nestedOrderStatus;
        private  int nestedOrderBYforwarderID;
        private  int nestedOrderPLforwarderID;
    
        private  int nestedCarrierID;
        private  int nestedLoadingID;
        private  int nestedUnloadingID;
        private  int nestedCargoID;
        private  Payment nestedPayment;
        //////////////////////////////////////

        public OrderBuilder(int nestedID, Date nesteDateOfOrder, String nestedNumberOfOrder, String nestedOrderStatus, int nestedOrderBYforwarderID, int nestedOrderPLforwarderID, int nestedCarrierID, int nestedLoadingID, int nestedUnloadingID, int nestedCargoID, Payment nestedPayment) {
            this.nestedID = nestedID;
            this.nesteDateOfOrder = nesteDateOfOrder;
            this.nestedNumberOfOrder = nestedNumberOfOrder;
            this.nestedOrderStatus = nestedOrderStatus;
            this.nestedOrderBYforwarderID = nestedOrderBYforwarderID;
            this.nestedOrderPLforwarderID = nestedOrderPLforwarderID;
            this.nestedCarrierID = nestedCarrierID;
            this.nestedLoadingID = nestedLoadingID;
            this.nestedUnloadingID = nestedUnloadingID;
            this.nestedCargoID = nestedCargoID;
            this.nestedPayment = nestedPayment;
        }

        public OrderBuilder ID(int nestedID) {
            this.nestedID = nestedID;
            return this;
        }

        public OrderBuilder dateOfOrder(Date nesteDateOfOrder) {
            this.nesteDateOfOrder = nesteDateOfOrder;
            return this;
        }

        public OrderBuilder numberOfOrder(String nestedNumberOfOrder) {
            this.nestedNumberOfOrder = nestedNumberOfOrder;
            return this;
        }

        public OrderBuilder orderStatus(String nestedOrderStatus) {
            this.nestedOrderStatus = nestedOrderStatus;
            return this;
        }

        public OrderBuilder orderBYforwarderID(int nestedOrderBYforwarderID) {
            this.nestedOrderBYforwarderID = nestedOrderBYforwarderID;
            return this;
        }

        public OrderBuilder orderPLforwarderID(int nestedOrderPLforwaderID) {
            this.nestedOrderPLforwarderID = nestedOrderPLforwaderID;
            return this;
        }

        public OrderBuilder carrierID(int nestedCarrierID) {
            this.nestedCarrierID = nestedCarrierID;
            return this;
        }

        public OrderBuilder loadingID(int nestedLoadingID) {
            this.nestedLoadingID = nestedLoadingID;
            return this;
        }

        public OrderBuilder unloadingID(int nestedUnloadingID) {
            this.nestedUnloadingID = nestedUnloadingID;
            return this;
        }

        public OrderBuilder cargoID(int nestedCargoID) {
            this.nestedCargoID = nestedCargoID;
            return this;
        }

        public OrderBuilder payment(Payment nestedPayment) {
            this.nestedPayment = nestedPayment;
            return this;
        }

        
        public Order createOrder() {
             return new Order(nestedID,
                     nesteDateOfOrder,
                     nestedNumberOfOrder,
                     nestedOrderStatus,
                     nestedOrderBYforwarderID,
                     nestedOrderPLforwarderID,
                     nestedCarrierID,
                     nestedLoadingID, 
                     nestedUnloadingID,
                     nestedCargoID, 
                     nestedPayment);
        } 
        
    }
}
