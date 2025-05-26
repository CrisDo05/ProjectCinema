package model;

import java.util.ArrayList;
import java.util.List;

public class Phim {
	private String id;
	private String tenPhim;
	private String theloai;
	private String director;
	private int releaseYear;
	private int duration;
	private String trangthai;
	private List<Observer> observers;
	private LichChieu lichChieu;
	public Phim() {
		super();
	}
	
	
	public Phim(String id, String tenPhim, String theloai, String director, int releaseYear, int duration) {
		super();
		this.id = id;
		this.tenPhim = tenPhim;
		this.theloai = theloai;
		this.director = director;
		this.releaseYear = releaseYear;
		this.duration = duration;
	}


	public Phim(String id, String tenPhim, String theloai, String director, int releaseYear, int duration, String trangthai, LichChieu lichChieu) {
		super();
		this.id = id;
		this.tenPhim = tenPhim;
		this.theloai = theloai;
		this.director = director;
		this.releaseYear = releaseYear;
		this.duration = duration;
		this.trangthai = trangthai;
		this.observers = new ArrayList<>();
		this.lichChieu = lichChieu;
	}
//	@Override
//	public String toString() {
//	    return String.format(
//	        "ID: %s | Tên: %s | Thể loại: %s | Đạo diễn: %s | Năm: %d | Thời lượng: %d phút | Trạng thái: %s | %s",
//	        id, tenPhim, theloai, director, releaseYear, duration, trangthai, lichChieu.toString()
//	    );
//	}
	@Override
	public String toString() {
	    return String.format(
	        "ID: %s | Tên: %s | Thể loại: %s | Đạo diễn: %s | Năm: %d | Thời lượng: %d phút | Trạng thái: %s | %s",
	        id, tenPhim, theloai, director, releaseYear, duration, trangthai,
	        (lichChieu != null ? lichChieu.toString() : "Chưa có lịch chiếu")
	    );
	}


	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTenPhim() {
		return tenPhim;
	}
	public void setTenPhim(String tenPhim) {
		this.tenPhim = tenPhim;
	}
	public String getTheloai() {
		return theloai;
	}
	public void setTheloai(String theloai) {
		this.theloai = theloai;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public int getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getTrangthai() {
		return trangthai;
	}
	  public void setTrangthai(String trangthai) {
	        this.trangthai = trangthai;
//	        thongBaoChoObserver("Phim '" + tenPhim + "' cập nhật trạng thái: " + trangthai);
	    }
	public List<Observer> getObservers() {
		return observers;
	}
	public void setObservers(List<Observer> observers) {
		this.observers = observers;
	}
	public LichChieu getLichChieu() {
		return lichChieu;
	}


	public void setLichChieu(LichChieu lichChieu) {
		this.lichChieu = lichChieu;
	}

	
	
	
	

}
