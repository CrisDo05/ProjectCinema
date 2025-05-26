package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class PaymentMethodView extends JFrame {
    private JLabel movieInfoLabel;
    private JLabel seatInfoLabel;
    private JLabel scheduleInfoLabel;
    private JLabel ticketTypeLabel;
    private JTextArea comboDetailsArea;
    private JLabel totalAmountLabel;
    private JRadioButton momoRadio;
    private JRadioButton bankRadio;
    private ButtonGroup paymentGroup;
    private JButton continueButton;
    private JButton backButton;
    
    public PaymentMethodView() {
        setTitle("Chọn phương thức thanh toán");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Header đơn giản
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180));
        headerPanel.setBorder(new EmptyBorder(12, 20, 12, 20));
        
        JLabel titleLabel = new JLabel("Chọn phương thức thanh toán", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);
        
        // Panel chính chia đôi
        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);
        
        // Panel bên trái - Thông tin đơn hàng
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createTitledBorder("Thông tin đơn hàng"));
        
        movieInfoLabel = new JLabel("Tên phim: ");
        movieInfoLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        
        seatInfoLabel = new JLabel("Ghế: ");
        seatInfoLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        
        scheduleInfoLabel = new JLabel("Suất: 17h 30 ");
        scheduleInfoLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        
        ticketTypeLabel = new JLabel("Loại vé: ");
        ticketTypeLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        
        comboDetailsArea = new JTextArea(4, 20);
        comboDetailsArea.setEditable(false);
        comboDetailsArea.setFont(new Font("Arial", Font.PLAIN, 12));
        comboDetailsArea.setBorder(new EmptyBorder(5, 5, 5, 5));
        JScrollPane comboScroll = new JScrollPane(comboDetailsArea);
        comboScroll.setBorder(BorderFactory.createTitledBorder("Combo đã chọn"));
        
        totalAmountLabel = new JLabel("Tổng tiền: ");
        totalAmountLabel.setFont(new Font("Arial", Font.BOLD, 14));
        totalAmountLabel.setForeground(new Color(200, 50, 50));
        
        leftPanel.add(movieInfoLabel);
        leftPanel.add(Box.createVerticalStrut(5));
        leftPanel.add(seatInfoLabel);
        leftPanel.add(Box.createVerticalStrut(5));
        leftPanel.add(scheduleInfoLabel);
        leftPanel.add(Box.createVerticalStrut(5));
        leftPanel.add(ticketTypeLabel);
        leftPanel.add(Box.createVerticalStrut(12));
        leftPanel.add(comboScroll);
        leftPanel.add(Box.createVerticalStrut(12));
        leftPanel.add(totalAmountLabel);
        
        // Panel bên phải - Phương thức thanh toán
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createTitledBorder("Phương thức thanh toán"));
        
        momoRadio = new JRadioButton("Thanh toán qua MoMo");
        momoRadio.setFont(new Font("Arial", Font.PLAIN, 13));
        
        bankRadio = new JRadioButton("Chuyển khoản ngân hàng");
        bankRadio.setFont(new Font("Arial", Font.PLAIN, 13));
        
        paymentGroup = new ButtonGroup();
        paymentGroup.add(momoRadio);
        paymentGroup.add(bankRadio);
        
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(momoRadio);
        rightPanel.add(Box.createVerticalStrut(15));
        rightPanel.add(bankRadio);
        rightPanel.add(Box.createVerticalGlue());
        
        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);
        
        // Panel nút ở dưới
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        buttonPanel.setBackground(new Color(245, 245, 245));
        
        backButton = new JButton("Quay lại");
        backButton.setFont(new Font("Arial", Font.PLAIN, 13));
        backButton.setPreferredSize(new Dimension(100, 35));
        
        continueButton = new JButton("Tiếp tục thanh toán");
        continueButton.setFont(new Font("Arial", Font.BOLD, 13));
        continueButton.setPreferredSize(new Dimension(160, 35));
        continueButton.setBackground(new Color(70, 130, 180));
        continueButton.setForeground(Color.WHITE);
        
        buttonPanel.add(backButton);
        buttonPanel.add(continueButton);
        
        add(headerPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    // Getters và setters
    public void setMovieInfo(String movieName, String room) {
        movieInfoLabel.setText("Tên phim: " + movieName + " - Phòng: " + room);
    }
    
    public void setSeatInfo(String seats) {
        seatInfoLabel.setText("Ghế: " + seats);
    }
    
    public void setScheduleInfo() {
        scheduleInfoLabel.setText("Suất: 17h 30 ");
    }
    
    
    
    public void setTicketType(String ticketType) {
        ticketTypeLabel.setText("Loại vé: " + ticketType);
    }
    
    
    public void setComboDetails(String comboDetails) {
        comboDetailsArea.setText(comboDetails);
    }
    
    public void setTotalAmount(double amount) {
        totalAmountLabel.setText("Tổng tiền: " + String.format("%,d", (int)amount) + " VNĐ");
    }
    
    public String getSelectedPaymentMethod() {
        if (momoRadio.isSelected()) return "MoMo";
        if (bankRadio.isSelected()) return "Bank";
        return null;
    }
    
    public void addContinueListener(ActionListener listener) {
        continueButton.addActionListener(listener);
    }
    
    public void addBackListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }
}
