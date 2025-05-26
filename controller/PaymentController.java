// Controller cho payment flow
package controller;

import model.*;
import view.*;

import java.awt.event.ActionListener;

import javax.swing.*;

public class PaymentController {
    private PaymentMethodView paymentMethodView;
    private PaymentDetailView paymentDetailView;
    private PaymentSuccessView paymentSuccessView;
    private PaymentProcessor paymentProcessor;
    private MainView originalMainView; 
    
    // Thông tin từ màn hình trước
    private String movieName;
    private String room;
    private String seats;
    private String ticketType;
    private String comboDetails;
    private double totalAmount;
    private String invoiceCode; // Mã hóa đơn
    private String ticketCode;  // Mã in vé
    

    public PaymentController(String movieName, String room, String seats, 
            String ticketType, String comboDetails, double totalAmount,
            MainView originalMainView) {
           this.movieName = movieName;
           this.room = room;
           this.seats = seats;
           this.ticketType = ticketType;
           this.comboDetails = comboDetails;
           this.totalAmount = totalAmount;
           this.originalMainView =  originalMainView; // Cast về MainView

           this.paymentProcessor = new PaymentProcessor();


           this.invoiceCode = "HD" + System.currentTimeMillis();
           this.ticketCode = "VE" + System.currentTimeMillis();

           initPaymentMethodView();
   }
    
    private void initPaymentMethodView() {
        paymentMethodView = new PaymentMethodView();
        
  
        paymentMethodView.setMovieInfo(movieName, room);
        paymentMethodView.setSeatInfo(seats);
        paymentMethodView.setTicketType(ticketType);
        paymentMethodView.setComboDetails(comboDetails);
        paymentMethodView.setTotalAmount(totalAmount);
        
     
        paymentMethodView.addContinueListener(e -> handleContinuePayment());
        paymentMethodView.addBackListener(e -> handleBackToMainView());
        
        paymentMethodView.setVisible(true);
    }
    
    private void handleContinuePayment() {
        String selectedMethod = paymentMethodView.getSelectedPaymentMethod();
        
        if (selectedMethod == null) {
            JOptionPane.showMessageDialog(paymentMethodView, 
                "Vui lòng chọn phương thức thanh toán!", 
                "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Set payment strategy
        if ("MoMo".equals(selectedMethod)) {
        	paymentProcessor.setPaymentStrategy(new MoMoPayment());
        } else if ("Bank".equals(selectedMethod)) {
        	paymentProcessor.setPaymentStrategy(new BankPayment());
        }
        
        // Show payment detail screen
        showPaymentDetail(selectedMethod);
        paymentMethodView.dispose();
    }
    
    private void showPaymentDetail(String method) {
        paymentDetailView = new PaymentDetailView();
        paymentDetailView.setPaymentDetails(method, totalAmount, invoiceCode);
        
        paymentDetailView.addBackListener(e -> {
            paymentDetailView.dispose();
            paymentMethodView.setVisible(true);
        });
        
        paymentDetailView.addConfirmListener(e -> {
            // Process payment
            String paymentResult = paymentProcessor.executePayment(totalAmount);
            showPaymentSuccess(method);
            paymentDetailView.dispose();
        });
        
        paymentDetailView.setVisible(true);
    }
    
    private void showPaymentSuccess(String method) {
        paymentSuccessView = new PaymentSuccessView();
        paymentSuccessView.setPaymentResult(totalAmount, method, invoiceCode, ticketCode);
        
        paymentSuccessView.addHomeListener(e -> {
            paymentSuccessView.dispose();
            originalMainView.setVisible(true);
        });
        
        paymentSuccessView.setVisible(true);
    }
    
//    private String extractTransactionId(String paymentResult) {
//        // Simple extraction - có thể improve sau
//        String[] lines = paymentResult.split("\n");
//        for (String line : lines) {
//            if (line.contains("Mã giao dịch:")) {
//                return line.substring(line.indexOf(":") + 1).trim();
//            }
//        }
//        return "N/A";
//    }
//    
    
        
    private void handleBackToMainView() {
        paymentMethodView.dispose();
        originalMainView.setVisible(true); //
    }
    
    
}