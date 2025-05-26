package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class PaymentDetailView extends JFrame {
    private JLabel paymentMethodLabel;
    private JLabel accountOwnerLabel;
    private JLabel accountNumberLabel;
    private JLabel amountLabel;
    private JLabel transferContentLabel;
    private JLabel timeWarningLabel;
    private JButton backButton;
    private JButton confirmButton;
    
    public PaymentDetailView() {
        setTitle("Chi tiết thanh toán");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Màu nền nhẹ nhàng
        getContentPane().setBackground(new Color(250, 250, 250));
        
        // Header đơn giản
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180));
        headerPanel.setBorder(new EmptyBorder(15, 20, 15, 20));
        
        JLabel titleLabel = new JLabel("Chi tiết thanh toán", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);
        
        // Panel thông tin chính
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(new EmptyBorder(20, 25, 20, 25));
        
        // Các label với font dễ đọc
        paymentMethodLabel = new JLabel("Hình thức thanh toán: ");
        paymentMethodLabel.setFont(new Font("Arial", Font.BOLD, 14));
        paymentMethodLabel.setForeground(new Color(60, 60, 60));
        
        accountOwnerLabel = new JLabel("Chủ tài khoản: ");
        accountOwnerLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        accountOwnerLabel.setForeground(new Color(80, 80, 80));
        
        accountNumberLabel = new JLabel("Số tài khoản: ");
        accountNumberLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        accountNumberLabel.setForeground(new Color(80, 80, 80));
        
        amountLabel = new JLabel("Số tiền: ");
        amountLabel.setFont(new Font("Arial", Font.BOLD, 16));
        amountLabel.setForeground(new Color(220, 20, 60));
        
        transferContentLabel = new JLabel("Nội dung chuyển khoản: ");
        transferContentLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        transferContentLabel.setForeground(new Color(80, 80, 80));
        
        timeWarningLabel = new JLabel("Vui lòng thanh toán trong vòng 15 phút");
        timeWarningLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        timeWarningLabel.setForeground(new Color(255, 140, 0));
        timeWarningLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timeWarningLabel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(255, 200, 100), 1),
            new EmptyBorder(8, 15, 8, 15)
        ));
        timeWarningLabel.setOpaque(true);
        timeWarningLabel.setBackground(new Color(255, 248, 220));
        
        // Thêm các component
        infoPanel.add(paymentMethodLabel);
        infoPanel.add(Box.createVerticalStrut(12));
        infoPanel.add(accountOwnerLabel);
        infoPanel.add(Box.createVerticalStrut(8));
        infoPanel.add(accountNumberLabel);
        infoPanel.add(Box.createVerticalStrut(15));
        infoPanel.add(amountLabel);
        infoPanel.add(Box.createVerticalStrut(12));
        infoPanel.add(transferContentLabel);
        infoPanel.add(Box.createVerticalStrut(15));
        infoPanel.add(timeWarningLabel);
        
        // Panel nút đơn giản
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        buttonPanel.setBackground(new Color(245, 245, 245));
        
        
        backButton = new JButton("Quay lại");
        backButton.setFont(new Font("Arial", Font.PLAIN, 13));
        backButton.setPreferredSize(new Dimension(100, 35));
        
        confirmButton = new JButton("Xác nhận thanh toán");
        confirmButton.setPreferredSize(new Dimension(160, 35));
        confirmButton.setFont(new Font("Arial", Font.BOLD, 13));
        confirmButton.setBackground(new Color(70, 130, 180));
        confirmButton.setForeground(Color.WHITE);
        
        buttonPanel.add(backButton);
        buttonPanel.add(confirmButton);
        
        add(headerPanel, BorderLayout.NORTH);
        add(infoPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    public void setPaymentDetails(String method, double amount, String invoiceCode) {
        paymentMethodLabel.setText("Hình thức thanh toán: " + method);
        amountLabel.setText("Số tiền: " + String.format("%,d", (int)amount) + " VNĐ");
        transferContentLabel.setText("Nội dung chuyển khoản: " + invoiceCode);
        
        if ("MoMo".equals(method)) {
            accountOwnerLabel.setText("Chủ tài khoản: AE Cinema ");
            accountNumberLabel.setText("Số điện thoại: 0901234567");
        } else if ("Bank".equals(method) || "Chuyển khoản ngân hàng".equals(method)) {
            accountOwnerLabel.setText("Chủ tài khoản: AE Cinema");
            accountNumberLabel.setText("Số tài khoản: 1234567890 - Ngân hàng MB Bank");
        }
    }
    
    public void addBackListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }
    
    public void addConfirmListener(ActionListener listener) {
        confirmButton.addActionListener(listener);
    }
}
