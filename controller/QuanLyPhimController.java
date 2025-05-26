package controller;

import model.LichChieu;
import model.Phim;
import model.QuanLyPhim;
import view.DanhSachPhimView;
import view.MainView;
import view.QuanLyPhimView;

public class QuanLyPhimController {
    private QuanLyPhim qlpmodel;
    private QuanLyPhimView view;
    private DanhSachPhimView danhSachView;
    private MainView mainView;

    public QuanLyPhimController(QuanLyPhim qlpmodel, QuanLyPhimView view) {
        this.qlpmodel = qlpmodel;
        this.view = view;

        //khoi tao DanhSachPhimView dang ky observer
        danhSachView = new DanhSachPhimView(mainView);
        qlpmodel.dangKyObserver(danhSachView);
        danhSachView.setVisible(true);

        // dang ký observers cho QuanLyPhimView 
        qlpmodel.dangKyObserver(view);
       

        //khoi tao du lieu
        qlpmodel.themPhimMau(qlpmodel);

        // them su kien
        view.addThemListener(e -> themPhimMoi());
        view.addXoaTheoIdListener(e -> xoaPhimTheoId());
    }

    private void themPhimMoi() {
        // lay du lieu tu view
        String id = view.getId();
        String tenPhim = view.getTenPhim();
        String theLoai = view.getTheLoai();
        String daoDien = view.getDaoDien();
        int namSX = view.getNamSX();
        int thoiLuong = view.getThoiLuong();
        String trangThai = view.getTrangThai();
        String lichChieuText = view.getLichChieu();
        Phim p = new Phim(id, tenPhim, theLoai, daoDien, namSX, thoiLuong);
        p.setTrangthai(trangThai);
        p.setLichChieu(new LichChieu(lichChieuText));
        qlpmodel.themPhim(p);
    }

    private void xoaPhimTheoId() {
        String id = view.getId();
        qlpmodel.xoaPhimTheoId(id);
    }

}
