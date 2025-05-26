package model;

import java.util.Date;

public class LichChieu {
	public String ngayChieu; 
	public PhongChieu phongChieu;
	public LichChieu() {
		super();
	}
	public LichChieu(String ngayChieu) {
		super();
		this.ngayChieu = ngayChieu;
		this.phongChieu = phongChieu;
	}
	 @Override
	    public String toString() {
	        return "Ngày chiếu: " + ngayChieu;
	    }
	
//	@Override
//	public String toString() {
//		return "Ngay chieu: " + ngayChieu + ", phong chieu: " + phongChieu;
//	}
	public String getNgayChieu() {
		return ngayChieu;
	}
	public void setNgayChieu(String ngayChieu) {
		this.ngayChieu = ngayChieu;
	}
	public PhongChieu getPhongChieu() {
		return phongChieu;
	}
	public void setPhongChieu(PhongChieu phongChieu) {
		this.phongChieu = phongChieu;
	}
	
	
	
}
