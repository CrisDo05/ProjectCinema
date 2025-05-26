package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

import model.Observer;
import model.Phim;

public class QuanLyPhimView extends JFrame implements Observer {
    private JTextField txtId, txtTenPhim, txtTheLoai, txtDaoDien, txtNamSX, txtThoiLuong, txtLichChieu, txtTrangThai;
    private JButton btnThem, btnXoaTheoId;
    private JTextArea textArea;


    public QuanLyPhimView() {
        setTitle("Quản Lý Phim");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel nhập liệu phim
        JPanel inputPanel = new JPanel(new GridLayout(8, 2, 5, 5));
        inputPanel.add(new JLabel("ID:"));
        txtId = new JTextField();
        inputPanel.add(txtId);

        inputPanel.add(new JLabel("Tên Phim:"));
        txtTenPhim = new JTextField();
        inputPanel.add(txtTenPhim);

        inputPanel.add(new JLabel("Thể Loại:"));
        txtTheLoai = new JTextField();
        inputPanel.add(txtTheLoai);

        inputPanel.add(new JLabel("Đạo Diễn:"));
        txtDaoDien = new JTextField();
        inputPanel.add(txtDaoDien);

        inputPanel.add(new JLabel("Năm SX:"));
        txtNamSX = new JTextField();
        inputPanel.add(txtNamSX);

        inputPanel.add(new JLabel("Thời Lượng:"));
        txtThoiLuong = new JTextField();
        inputPanel.add(txtThoiLuong);
        
        inputPanel.add(new JLabel("Lịch Chiếu:"));
        txtLichChieu = new JTextField();
        inputPanel.add(txtLichChieu);

        inputPanel.add(new JLabel("Trạng Thái:"));
        txtTrangThai = new JTextField();
        inputPanel.add(txtTrangThai);


        // Panel nút thêm phim
        JPanel btnThemPanel = new JPanel();
        btnThem = new JButton("Thêm phim");
        btnThemPanel.add(btnThem);

        // Panel xóa theo ID (input + nút)
        JPanel xoaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        xoaPanel.add(new JLabel("Nhập ID xóa:"));
        JTextField txtIdXoa = new JTextField(10);
        xoaPanel.add(txtIdXoa);
        btnXoaTheoId = new JButton("Xóa phim theo ID");
        xoaPanel.add(btnXoaTheoId);

        // TextArea hiển thị
        textArea = new JTextArea(10, 50);
        textArea.setEditable(false);

        // Layout tổng thể
        setLayout(new BorderLayout(5,5));
        add(inputPanel, BorderLayout.NORTH);
        add(btnThemPanel, BorderLayout.CENTER);
        add(xoaPanel, BorderLayout.WEST);
        add(new JScrollPane(textArea), BorderLayout.SOUTH);

        // Tạo phương thức lấy ID để xóa
        this.txtIdXoa = txtIdXoa;  // lưu biến để bên Controller gọi được
    }

    // Các getter lấy dữ liệu phim để thêm
    public String getId() { return txtId.getText().trim(); }
    public String getTenPhim() { return txtTenPhim.getText().trim(); }
    public String getTheLoai() { return txtTheLoai.getText().trim(); }
    public String getDaoDien() { return txtDaoDien.getText().trim(); }
    public int getNamSX() { return Integer.parseInt(txtNamSX.getText().trim()); }
    public int getThoiLuong() { return Integer.parseInt(txtThoiLuong.getText().trim()); }
    public String getLichChieu() {
        return txtLichChieu.getText().trim();
    }

    public String getTrangThai() {
        return txtTrangThai.getText().trim();
    }


    // Lấy ID để xóa phim
    private JTextField txtIdXoa;
    public String getIdXoa() {
        return txtIdXoa.getText().trim();
    }

    // Hiển thị danh sách phim
    public void hienThiDanhSachPhim(List<Phim> danhSach) {
        textArea.setText("");
        for (Phim p : danhSach) {
            textArea.append(p.toString() + "\n");
        }
    }

    // Thêm thông báo ra TextArea
    public void appendThongBao(String msg) {
        textArea.append(msg + "\n");
    }

    // Đăng ký sự kiện nút thêm phim
    public void addThemListener(ActionListener listener) {
        btnThem.addActionListener(listener);
    }

    // Đăng ký sự kiện nút xóa phim theo ID
    public void addXoaTheoIdListener(ActionListener listener) {
        btnXoaTheoId.addActionListener(listener);
    }
    
    @Override
    public void capNhat(String thongbao, List<Phim> phims) {
        appendOutput(thongbao);
        hienThiDanhSachPhim(phims);  // cập nhật lại bảng dữ liệu
    }

    public void appendOutput(String text) {
    	textArea.append(text + "\n");
    }

	@Override
	public void capNhatDanhSachPhim(List<Phim> danhSachPhim) {
		hienThiDanhSachPhim(danhSachPhim);
		
	}
	
    
    
}
