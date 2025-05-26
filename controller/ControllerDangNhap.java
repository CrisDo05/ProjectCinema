package controller;

import model.Admin;
import model.Customer;
import model.User;
import repository.UserRepository;
import view.MainView;
import view.PageAdmin;
import view.ViewDangNhap;

import javax.swing.*;

public class ControllerDangNhap {
    private ViewDangNhap view;
    private UserRepository userRepository;

    public ControllerDangNhap(ViewDangNhap view, UserRepository userRepository) {
        this.view = view;
        this.userRepository = userRepository;
        initController();
    }

    private void initController() {
        view.getLoginButton().addActionListener(e -> handleLogin());
    }

    private void handleLogin() {
        String username = view.getUsername();
        String password = view.getPassword();

        User user = userRepository.findUserByUsername(username);

        if (user == null) {
            showMessage("Tên đăng nhập không tồn tại.");
            return;
        }

        if (!user.login(password)) {
            showMessage("Mật khẩu không đúng.");
            return;
        }

        // Xác định loại user
        if (user instanceof Admin) {
            showMessage("Đăng nhập thành công với quyền Admin.");
            
            PageAdmin pageAdmin = new PageAdmin(); // Tạo đối tượng
            new MainController(pageAdmin);   
            pageAdmin.setVisible(true);            // Gọi đúng cách
            view.dispose();                        // Đóng cửa sổ đăng nhập nếu cần

        } else if (user instanceof Customer) {
            showMessage("Đăng nhập thành công với quyền Customer.");
            
            MainView mainView = new MainView();   // Tạo đối tượng
            new MainController(mainView);  
            mainView.setVisible(true);            // Gọi đúng cách
            view.dispose();
        }

       
    }

    private void showMessage(String msg) {
        JOptionPane.showMessageDialog(view, msg);
    }
}
