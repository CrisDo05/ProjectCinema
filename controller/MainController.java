package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.QuanLyPhim;
import view.DanhSachPhimView;
import view.HistoryView;
import view.MainView;
import view.PageAdmin;
import view.QuanLyPhimView;
import view.ViewDangNhap;

public class MainController {

    private QuanLyPhim qlpmodel;
    private QuanLyPhimView quanLyPhimView;
    private DanhSachPhimView danhSachPhimView;

    private MainView mainView;
    private PageAdmin pageAdmin;

    // Constructor dùng khi chạy từ MainView (menu chính)
    public MainController(MainView mainView, PageAdmin pageAdmin) {
        this.mainView = mainView;
        this.pageAdmin = pageAdmin;
        this.qlpmodel = new QuanLyPhim();

        qlpmodel.themPhimMau(qlpmodel);

        initMainMenu();
        initAdminMenu();
    }

    // Chỉ gọi khi dùng PageAdmin độc lập
    public MainController(PageAdmin pageAdmin) {
        this(null, pageAdmin);
    }

    // Chỉ gọi khi dùng MainView độc lập
    public MainController(MainView mainView) {
        this(mainView, new PageAdmin());
    }

    // Các menu của MainView (menu tổng)
    private void initMainMenu() {
        if (mainView == null) return;

        mainView.getViewHistoryMenuItem().addActionListener(e -> new HistoryView().setVisible(true));
        mainView.getLoginMenuItem().addActionListener(e -> new ViewDangNhap().setVisible(true));
        mainView.getBookTicketsMenuItem().addActionListener(e -> moDanhSachPhim());
    }

    // Các menu của PageAdmin (admin panel)
    private void initAdminMenu() {
        if (pageAdmin == null) return;

        pageAdmin.getMenuQuanLyPhim().addActionListener(e -> moQuanLyPhim());
//        pageAdmin.getMenuDanhSachPhim().addActionListener(e -> moDanhSachPhim());
    }

    private void moQuanLyPhim() {
        if (quanLyPhimView == null) {
            quanLyPhimView = new QuanLyPhimView();
            new QuanLyPhimController(qlpmodel, quanLyPhimView);
        }
        quanLyPhimView.setVisible(true);
    }

    private void moDanhSachPhim() {
        if (danhSachPhimView == null) {
            danhSachPhimView = new DanhSachPhimView(mainView);
            qlpmodel.dangKyObserver(danhSachPhimView);
        }
        danhSachPhimView.setVisible(true);
    }
}

