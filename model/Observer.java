package model;

import java.util.List;

public interface Observer {

	public void capNhat(String thongbao, List<Phim> phims);
	  void capNhatDanhSachPhim(List<Phim> danhSachPhim);
	

}
