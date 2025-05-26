package model;

import java.util.ArrayList;
import java.util.List;

public class DanhSachPhim {
    private List<Phim> dsPhim;

    public DanhSachPhim() {
        dsPhim = new ArrayList<>();
    }

    public void themPhim(Phim phim) {
        dsPhim.add(phim);
    }

    public boolean xoaPhimTheoTen(String tenPhim) {
        return dsPhim.removeIf(p -> p.getTenPhim().equalsIgnoreCase(tenPhim));
    }

    public List<Phim> getDanhSach() {
        return dsPhim;
    }

    public boolean tonTaiPhim(String tenPhim) {
        return dsPhim.stream().anyMatch(p -> p.getTenPhim().equalsIgnoreCase(tenPhim));
    }
}
