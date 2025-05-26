package view;

import javax.swing.*;

import controller.ControllerDangKy;
import controller.ControllerDangNhap;
import repository.UserRepository;

import java.awt.*;

public class ViewDangNhap extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private UserRepository userRepository;

    public ViewDangNhap(UserRepository userRepository) {
        this.userRepository = userRepository;
        initGUI();
    }

    public ViewDangNhap() {
       initGUI();
    }
    private void initGUI() {
    	 setTitle("Đăng nhập");
         setSize(400, 250);
         setLocationRelativeTo(null);
         setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         setResizable(false);

         Font labelFont = new Font("Segoe UI", Font.PLAIN, 16);
         Font fieldFont = new Font("Segoe UI", Font.PLAIN, 16);

         // Panel chính
         JPanel mainPanel = new JPanel();
         mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
         mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
         mainPanel.setBackground(new Color(245, 245, 245));

         // Panel nhập username
         JPanel usernamePanel = new JPanel(new BorderLayout(5, 5));
         usernamePanel.setBackground(mainPanel.getBackground());
         JLabel usernameLabel = new JLabel("Tên đăng nhập:");
         usernameLabel.setFont(labelFont);
         usernameField = new JTextField(20);
         usernameField.setFont(fieldFont);
         usernamePanel.add(usernameLabel, BorderLayout.WEST);
         usernamePanel.add(usernameField, BorderLayout.CENTER);

         // Panel nhập mật khẩu
         JPanel passwordPanel = new JPanel(new BorderLayout(5, 5));
         passwordPanel.setBackground(mainPanel.getBackground());
         JLabel passwordLabel = new JLabel("Mật khẩu:");
         passwordLabel.setFont(labelFont);
         passwordField = new JPasswordField(20);
         passwordField.setFont(fieldFont);
         passwordPanel.add(passwordLabel, BorderLayout.WEST);
         passwordPanel.add(passwordField, BorderLayout.CENTER);

         // Nút đăng nhập
         loginButton = new JButton("Đăng nhập");
         loginButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
         loginButton.setBackground(new Color(0, 123, 255));
         loginButton.setForeground(Color.WHITE);
         loginButton.setFocusPainted(false);
         loginButton.setPreferredSize(new Dimension(120, 40));

         // Panel chứa nút
         JPanel buttonPanel = new JPanel();
         buttonPanel.setBackground(mainPanel.getBackground());
         buttonPanel.add(loginButton);

         // Label + nút đăng ký
         JPanel registerPanel = new JPanel();
         registerPanel.setBackground(mainPanel.getBackground());
         JLabel message = new JLabel("Chưa có tài khoản?");
         message.setFont(labelFont);
         registerButton = new JButton("Đăng ký");
         registerButton.setFont(labelFont);
         registerButton.setFocusPainted(false);
         registerPanel.add(message);
         registerPanel.add(registerButton);

         // Sự kiện mở ViewDangKy
         registerButton.addActionListener(e -> {
             ViewDangKy viewDangKy = new ViewDangKy();
             UserRepository userRepository = UserRepository.getInstance(); // dùng cùng instance
             new ControllerDangKy(viewDangKy, userRepository);
             viewDangKy.setVisible(true);
         });


         // Thêm tất cả vào mainPanel
         mainPanel.add(usernamePanel);
         mainPanel.add(Box.createVerticalStrut(15));
         mainPanel.add(passwordPanel);
         mainPanel.add(Box.createVerticalStrut(20));
         mainPanel.add(buttonPanel);
         mainPanel.add(Box.createVerticalStrut(10));
         mainPanel.add(registerPanel);

         add(mainPanel);
    }

    // Getter cho controller
    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }
   public static void main(String[] args) {
	   SwingUtilities.invokeLater(() -> {
	        UserRepository repo = UserRepository.getInstance(); // dùng singleton
	        ViewDangNhap viewDangNhap = new ViewDangNhap(repo);
	        new ControllerDangNhap(viewDangNhap, repo); 
	        viewDangNhap.setVisible(true);
	    });
	
}
}
