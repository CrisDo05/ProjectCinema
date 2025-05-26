package model;

public class MoMoPayment implements PaymentStrategy {
    @Override
    public String processPayment(double amount) {
        // Simulate MoMo payment processing
        String transactionId = "MOMO" + System.currentTimeMillis();
        return "Thanh toán MoMo thành công!\nMã giao dịch: " + transactionId + 
               "\nSố tiền: " + (int)amount + " VNĐ";
    }
    
    @Override
    public String getPaymentMethodName() {
        return "MoMo";
    }
}
