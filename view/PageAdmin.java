package view;

import java.awt.Font;

import javax.swing.AbstractButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

import controller.ControllerDangNhap;
import controller.MainController;
import repository.UserRepository;

public class PageAdmin extends JFrame {
	private JMenuItem revenueStatisticssMenuItem;
	private final JMenuItem menuQuanLyPhim = new JMenuItem("Quản lý phim");
	private JMenuItem loginMenuItem;
	public PageAdmin() {
		setTitle("Hệ thống đặt vé phim");
		setSize(700, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// Tạo menu bar
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		revenueStatisticssMenuItem = new JMenuItem("Thống kê doanh thu");
		loginMenuItem = new JMenuItem("Đăng nhập vào người dùng(test)");	
		menu.add(menuQuanLyPhim);
		menu.add(revenueStatisticssMenuItem);
		menu.add(loginMenuItem);
		menuBar.add(menu);

		setJMenuBar(menuBar);

		// Nội dung chính
		JLabel welcomeLabel = new JLabel("Chào mừng bạn đến với trang quản lý!", SwingConstants.CENTER);
		welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
		add(welcomeLabel);
		System.out.println("");
		// GẮN SỰ KIỆN MỞ GIAO DIỆN THỐNG KÊ
		revenueStatisticssMenuItem.addActionListener(e -> {
			ViewThongKeDoanhThu view = new ViewThongKeDoanhThu();
			view.setVisible(true);
		});
		loginMenuItem.addActionListener(e -> {
		    UserRepository repo = UserRepository.getInstance(); // đảm bảo dùng chung instance
		    ViewDangNhap loginView = new ViewDangNhap(repo); // truyền repo vào ViewDangNhap
		    new ControllerDangNhap(loginView, repo); // gắn controller
		    loginView.setVisible(true); 
		});
	}
	public JMenuItem getMenuQuanLyPhim() {
		return menuQuanLyPhim;
	}

	public JMenuItem getRevenueStatisticssMenuItem() {
		return revenueStatisticssMenuItem;
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				PageAdmin viewAdmin = new PageAdmin();
				 new controller.MainController(viewAdmin);
				new PageAdmin();
				viewAdmin.setVisible(true);
			}
		});
	}

	

}
