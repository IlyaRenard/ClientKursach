
package by.bsuir.pokos.database.dao.entity;

import java.io.Serializable;

public final class Payment implements Serializable{
    
    static final long serialVersionUID = 3432063776451490808L;
    
    private final float freightCost;
    private final String paymentPeriod;

    public Payment(float freightCost, String paymentPeriod) {
        this.freightCost = freightCost;
        this.paymentPeriod = paymentPeriod;
    }

    public float getFreightCost() {
        return freightCost;
    }

    public String getPaymentPeriod() {
        return paymentPeriod;
    }
    
    public static class PaymentBuilder {
        
        private float nestedFreightCost;
        private String nestedPaymentPeriod;

        public PaymentBuilder(float nestedFreightCost, String nestedPaymentPeriod) {
            this.nestedFreightCost = nestedFreightCost;
            this.nestedPaymentPeriod = nestedPaymentPeriod;
        }

        public void setNestedFreightCost(float nestedFreightCost) {
            this.nestedFreightCost = nestedFreightCost;
        }

        public void setNestedPaymentPeriod(String nestedPaymentPeriod) {
            this.nestedPaymentPeriod = nestedPaymentPeriod;
        }
        
        public Payment createPayment(){
            return new Payment(nestedFreightCost, nestedPaymentPeriod);
        }
        
    }
}
