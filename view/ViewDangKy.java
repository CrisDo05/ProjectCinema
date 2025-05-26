package view;

import javax.swing.*;
import java.awt.*;

public class ViewDangKy extends JFrame {
    private JTextField usernameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JButton registerButton;
    private JRadioButton adminRadio;
    private JRadioButton customerRadio;

    public ViewDangKy() {
        setTitle("Đăng ký tài khoản");
        setSize(400, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        Font labelFont = new Font("Segoe UI", Font.PLAIN, 16);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 16);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(245, 245, 245));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        mainPanel.add(createLabeledField("Tên đăng nhập:", usernameField = new JTextField(20), labelFont, fieldFont));
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(createLabeledField("Email:", emailField = new JTextField(20), labelFont, fieldFont));
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(createLabeledField("Mật khẩu:", passwordField = new JPasswordField(20), labelFont, fieldFont));
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(createLabeledField("Nhập lại mật khẩu:", confirmPasswordField = new JPasswordField(20), labelFont, fieldFont));
        mainPanel.add(Box.createVerticalStrut(20));

        // Panel chọn vai trò
        JPanel rolePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rolePanel.setBackground(mainPanel.getBackground());
        JLabel roleLabel = new JLabel("Vai trò:");
        roleLabel.setFont(labelFont);

        adminRadio = new JRadioButton("Admin");
        adminRadio.setFont(fieldFont);
        adminRadio.setBackground(mainPanel.getBackground());

        customerRadio = new JRadioButton("Customer");
        customerRadio.setFont(fieldFont);
        customerRadio.setBackground(mainPanel.getBackground());
        customerRadio.setSelected(true); // mặc định là Customer

        ButtonGroup roleGroup = new ButtonGroup();
        roleGroup.add(adminRadio);
        roleGroup.add(customerRadio);

        rolePanel.add(roleLabel);
        rolePanel.add(adminRadio);
        rolePanel.add(customerRadio);

        mainPanel.add(rolePanel);
        mainPanel.add(Box.createVerticalStrut(25));

        // Nút đăng ký
        registerButton = new JButton("Đăng ký");
        registerButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        registerButton.setBackground(new Color(0, 123, 255));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.setPreferredSize(new Dimension(150, 40));
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(registerButton);
        add(mainPanel);
    }

    private JPanel createLabeledField(String labelText, JComponent inputField, Font labelFont, Font fieldFont) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBackground(new Color(245, 245, 245));
        JLabel label = new JLabel(labelText);
        label.setFont(labelFont);
        inputField.setFont(fieldFont);
        inputField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        panel.add(label, BorderLayout.WEST);
        panel.add(inputField, BorderLayout.CENTER);
        return panel;
    }

    // Getter methods
    public JButton getRegisterButton() {
        return registerButton;
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getEmail() {
        return emailField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public String getConfirmPassword() {
        return new String(confirmPasswordField.getPassword());
    }

    public String getRole() {
        return adminRadio.isSelected() ? "Admin" : "Customer";
    }
}
