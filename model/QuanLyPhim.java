package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuanLyPhim implements Subject {
    private List<Phim> danhSach = new ArrayList<>();
    private List<Observer> observers = new ArrayList<>();
 

    public void themPhim(Phim p) {
        danhSach.add(p);
//        for (Observer obs : observers) {
//            obs.capNhatDanhSachPhim(new ArrayList<>(danhSach));
//        }
//        for (Observer obs : customerObservers) {
//            obs.capNhatDanhSachPhim(new ArrayList<>(danhSach));
//        }
        thongBaoCapNhat("Đã thêm phim: " + p.getTenPhim());
    }

    public boolean xoaPhimTheoId(String id) {
        boolean removed = danhSach.removeIf(p -> p.getId().equalsIgnoreCase(id));
        if (removed) {
            thongBaoCapNhat("Đã xóa phim có ID: " + id);
        } else {
            thongBaoCapNhat("Không tìm thấy phim để xóa với ID: " + id);
        }
        return removed;
    }

    public List<Phim> getPhims() {
        return danhSach;
    }

    @Override
    public void dangKyObserver(Observer obs) {
        observers.add(obs);
        obs.capNhatDanhSachPhim(new ArrayList<>(danhSach));
//        obs.capNhat("Danh sách phim hiện tại", new ArrayList<>(danhSach));
    }

    @Override
    public void huyObserver(Observer obs) {
        observers.remove(obs);
    }

    @Override
    public void thongBaoCapNhat(String thongbao) {
        for (Observer obs : observers) {
//        	obs.capNhat(thongbao, new ArrayList<>(danhSach));
            obs.capNhatDanhSachPhim(new ArrayList<>(danhSach));
        }
    }
    public List<Phim> getDanhSachPhim() {
        return new ArrayList<>(danhSach);
    }
    
//    public List<Phim> getDanhSachPhim() {
//        return danhSach;
//    }
    public void themPhimMau(QuanLyPhim model) {
//    	PhongChieu phong1 = new PhongChieu("Phòng 1");
//        PhongChieu phong2 = new PhongChieu("Phòng 2");

        LichChieu lc1 = new LichChieu("13h 21/5/2025");
        LichChieu lc2 = new LichChieu("9h 22/5/2025");

        model.themPhim(new Phim("P01", "Phim A", "Hành động", "Đạo diễn A", 2020, 120, "Đang chiếu", lc1));
        model.themPhim(new Phim("P02", "Phim B", "Hài", "Đạo diễn B", 2019, 90, "Ngừng chiếu", lc2));
        model.themPhim(new Phim("P03", "Phim C", "Kinh dị", "Đạo diễn C", 2021, 110, "Sắp chiếu", lc1));
    	
    	
    }

}
