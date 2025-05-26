package model;

public interface PaymentStrategy {
    String processPayment(double amount);
    String getPaymentMethodName();
}