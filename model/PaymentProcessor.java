package model;

public class PaymentProcessor {
    private PaymentStrategy paymentStrategy;
    
    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.paymentStrategy = strategy;
    }
    
    public String executePayment(double amount) {
        if (paymentStrategy == null) {
            return "Vui lòng chọn phương thức thanh toán!";
        }
        return paymentStrategy.processPayment(amount);
    }
    
    public String getSelectedPaymentMethod() {
        return paymentStrategy != null ? paymentStrategy.getPaymentMethodName() : "Chưa chọn";
    }
}