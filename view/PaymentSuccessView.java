package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class PaymentSuccessView extends JFrame {
    private JLabel successLabel;
    private JLabel amountLabel;
    private JLabel methodLabel;
    private JLabel invoiceCodeLabel;
    private JLabel ticketCodeLabel;
    private JLabel instructionLabel;
    private JButton homeButton;
    
    public PaymentSuccessView() {
        setTitle("Thanh toán thành công");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Header Panel
        JPanel headerPanel = createHeaderPanel();
        
        // Main Content Panel
        JPanel mainPanel = createMainPanel();
        
        // Footer Panel
        JPanel footerPanel = createFooterPanel();
        
        add(headerPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);
    }
    
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        successLabel = new JLabel("THANH TOÁN THÀNH CÔNG!", SwingConstants.CENTER);
        successLabel.setFont(new Font("Arial", Font.BOLD, 20));
        successLabel.setForeground(Color.red);
        
        headerPanel.add(successLabel);
        
        return headerPanel;
    }
    
    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
        
        // Thiết lập các label với font lớn hơn
        amountLabel = new JLabel("Số tiền thanh toán: ");
        amountLabel.setFont(new Font("Arial", Font.BOLD, 16));
        
        methodLabel = new JLabel("Phương thức thanh toán: ");
        methodLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        
        invoiceCodeLabel = new JLabel("Mã hóa đơn: ");
        invoiceCodeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        
        ticketCodeLabel = new JLabel("Mã in vé: ");
        ticketCodeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        ticketCodeLabel.setForeground(new Color(0, 100, 200));
        
        instructionLabel = new JLabel("Vui lòng sử dụng mã trên để in vé tại rạp", SwingConstants.CENTER);
        instructionLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        instructionLabel.setForeground(Color.BLUE);
        
        // Thêm các component với khoảng cách
        mainPanel.add(amountLabel);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(methodLabel);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(invoiceCodeLabel);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(ticketCodeLabel);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(instructionLabel);
        
        return mainPanel;
    }
    
    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        homeButton = new JButton("Quay lại trang chủ");
        homeButton.setFont(new Font("Arial", Font.BOLD, 14));
        homeButton.setPreferredSize(new Dimension(180, 40));
        homeButton.setBackground(new Color(70, 130, 180));
        homeButton.setForeground(Color.WHITE);
        
        footerPanel.add(homeButton);
        
        return footerPanel;
    }
    
    public void setPaymentResult(double amount, String method, String invoiceCode, String ticketCode) {
        amountLabel.setText("Số tiền thanh toán: " + String.format("%,d", (int)amount) + " VNĐ");
        methodLabel.setText("Phương thức thanh toán: " + method);
        invoiceCodeLabel.setText("Mã hóa đơn: " + invoiceCode);
        ticketCodeLabel.setText("Mã in vé: " + ticketCode);
    }
    
    public void addHomeListener(ActionListener listener) {
        homeButton.addActionListener(listener);
    }
}
