package controller;


import model.*;
import view.ViewDangKy;
import repository.UserRepository;

import java.util.UUID;

import javax.swing.*;

public class ControllerDangKy {
    private ViewDangKy view;
    private UserRepository userRepository;

    public ControllerDangKy(ViewDangKy view, UserRepository userRepository) {
        this.view = view;
        this.userRepository = userRepository;

        initController();
    }

    private void initController() {
        view.getRegisterButton().addActionListener(e -> register());
    }

    private void register() {
        String username = view.getUsername();
        String email = view.getEmail();
        String password = view.getPassword();
        String confirmPassword = view.getConfirmPassword();
        String role = view.getRole();
        String id = UUID.randomUUID().toString();
       
        // Kiểm tra rỗng
        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            showMessage("Vui lòng điền đầy đủ thông tin.");
            return;
        }

        // Kiểm tra mật khẩu
        if (!password.equals(confirmPassword)) {
            showMessage("Mật khẩu nhập lại không khớp.");
            return;
        }

        // Kiểm tra username trùng
        if (userRepository.usernameExists(username)) {
            showMessage("Tên đăng nhập đã tồn tại.");
            return;
        }
        if (userRepository.emailExists(email)) {
            showMessage("Email đã tồn tại.");
            return;
        }

        // Tạo user bằng factory
        User newUser = UserFactory.createUser( id,username, email, password,role);
        userRepository.addUser(newUser);
        System.out.println("Đăng ký thành công: " + newUser.getUsername() + ", Role: " + role);

        showMessage("Đăng ký thành công!");
        view.dispose(); // đóng form sau khi đăng ký
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(view, message);
    }
}
