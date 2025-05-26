package view;

import model.LichChieu;
import model.Observer;
import model.Phim;
import model.PhongChieu;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DanhSachPhimView extends JFrame implements Observer {
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnKiemTraChon;
    private MainView mainView;
    

	public DanhSachPhimView(MainView mainView) {
		this.mainView=mainView;
        setTitle("Danh sách phim");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        String[] columns = {"Chọn", "Tên phim", "Thể loại", "Đạo diễn", "Năm SX", "Thời lượng", "Lịch chiếu", "Trạng thái"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0;  // chỉ cột checkbox được chỉnh sửa
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return (columnIndex == 0) ? Boolean.class : String.class;
            }
        };

        table = new JTable(tableModel);
        btnKiemTraChon = new JButton("Kiểm tra dòng được chọn");
        btnKiemTraChon.addActionListener(new handleLabel());

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(btnKiemTraChon, BorderLayout.SOUTH);

        add(panel);

        btnKiemTraChon.addActionListener(e -> {
            List<Phim> dsPhimDuocChon = new ArrayList<>();
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                Boolean checked = (Boolean) tableModel.getValueAt(i, 0);
                if (checked != null && checked) {
                    Phim p = new Phim();
                    p.setTenPhim((String) tableModel.getValueAt(i, 1));
                    p.setTheloai((String) tableModel.getValueAt(i, 2));
                    p.setDirector((String) tableModel.getValueAt(i, 3));
                    p.setReleaseYear((int) tableModel.getValueAt(i, 4));
                    p.setDuration((int) tableModel.getValueAt(i, 5));
                    p.setLichChieu((LichChieu) tableModel.getValueAt(i, 6));
                    p.setTrangthai((String) tableModel.getValueAt(i, 7));
                    dsPhimDuocChon.add(p);
                }
            }
            
            if (mainView != null) {
            	mainView.xuLyPhimDuocChon(dsPhimDuocChon); // gửi về
            }

            this.dispose(); // đóng view
        });

        
    }

    @Override
    public void capNhatDanhSachPhim(List<Phim> danhSachPhim) {
        tableModel.setRowCount(0);
        for (Phim p : danhSachPhim) {
            Object[] rowData = {
                false,
                p.getTenPhim(),
                p.getTheloai(),
                p.getDirector(),
                p.getReleaseYear(),
                p.getDuration(),
                p.getLichChieu(),
                p.getTrangthai()
            };
            tableModel.addRow(rowData);
        }
    }

    @Override
    public void capNhat(String thongbao, List<Phim> phims) {
        // Cập nhật khác nếu cần
    }
    private class handleLabel implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JButton src = (JButton) e.getSource();
			if(src==btnKiemTraChon) {
				int selectedRow = table.getSelectedRow();
				if(selectedRow!=-1) {
					String name = (String) table.getValueAt(	selectedRow, 1);
					LichChieu perform = (LichChieu) table.getValueAt(	selectedRow, 6);
					String per = perform.getNgayChieu();
					PhongChieu room = perform.getPhongChieu();
					mainView.lable_name.setText("Tên phim: "+name);
					mainView.lable_performance.setText("Suất: "+per);
					mainView.label_room.setText("Phòng chiếu: "+room);
					
					
				}
			}
		}
    	
    }
}
