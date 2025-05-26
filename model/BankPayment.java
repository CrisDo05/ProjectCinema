package model;

public class BankPayment implements PaymentStrategy {
    @Override
    public String processPayment(double amount) {
        // Simulate bank transfer processing
        String transactionId = "BANK" + System.currentTimeMillis();
        return "Thanh toán ngân hàng thành công!\nMã giao dịch: " + transactionId + 
               "\nSố tiền: " + (int)amount + " VNĐ";
    }
    
    @Override
    public String getPaymentMethodName() {
        return "Chuyển khoản ngân hàng";
    }
}
