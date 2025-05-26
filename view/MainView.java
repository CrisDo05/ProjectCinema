package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

//import controller.DSPhimController;
import controller.MainController;
import controller.PaymentController;
import model.AdultTicket;
import model.BasicTicket;
import model.CaramelCorn;
import model.ChesesCorn;
import model.GheModel;
import model.HSSV_OldPersonTicket;
import model.Phim;
import model.RegularCorn;
import model.SeatButtonView2;
import model.Softdrink;
import model.Ticket;

public class MainView extends JFrame {
	private JMenuItem manageMoviesMenuItem;
	private JMenuItem bookTicketsMenuItem;
	private JMenuItem listfilmMenuItem;
	private JMenuItem viewHistoryMenuItem;
	private JMenuItem loginMenuItem;
	
	
	//Truong
	private JTable tablee;
	private DefaultTableModel tableModel;
	private JButton button_cont;
	private JPanel pane_DSP, panel_but, panel_chung;
	private JButton button_cancel;
	
	
	//Khanh
    private final int rows = 8; // a - h (theo chiều dọc)
    private final int cols = 5; // 1 - 5 (theo chiều ngang)
    private final Set<JButton> selectedSeats = new HashSet<>();
    private final JLabel statusLabel = new JLabel("Ghe da chon: 0");
    private JPanel panel_ghe, pan_but;
    private JButton button_conti, button_qlai;
    private List<String> seatList = new ArrayList<String>();
    private JPanel panel_chung1, panel_chung2;
    private CardLayout card;
    private JPanel taoMucChuThich(String moTa, Color mau) {
        JPanel muc = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        JLabel mauLabel = new JLabel();
        mauLabel.setOpaque(true);
        mauLabel.setBackground(mau);
        mauLabel.setPreferredSize(new Dimension(20, 20));

        JLabel textLabel = new JLabel(moTa);
        muc.add(mauLabel);
        muc.add(textLabel);

        return muc;
    }

	
    //An
	private JPanel panel_infor, panel_combo, panel_rice_button, panel_center, panel_button2, panel_button1_ ,panel_card, card_common,
	card_home, card_menu_dsp, card_menu_;
	public JLabel lable_name, lable_performance, label_room, lable_seat, lable_rice;
	private JButton button_continue, button_back, button_remove, button_complete;
	private JRadioButton rd_adult, rd_student_OldPerson;
	private ButtonGroup group;
	private JCheckBox cb_corn_caramel, cb_corn_regular, cb_corn_cheses, cb_softdrink;
	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scroll;
	private int stt;
	public int totalPrice;
//	private Ticket ticket = new AdultTicket();

	
	private PaymentController paymentController;
	
	
	public MainView() {
		setTitle("Hệ thống đặt vé phim");
		setSize(700, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		

		// Tạo menu bar
		JMenuBar menuBar = new JMenuBar();

		JMenu menu = new JMenu("Menu");
//		manageMoviesMenuItem = new JMenuItem("Quản lý phim");
		bookTicketsMenuItem = new JMenuItem("Đặt vé");
	//	listfilmMenuItem = new JMenuItem("Danh sách phim");
		viewHistoryMenuItem = new JMenuItem("Xem lịch sử đặt vé");
		loginMenuItem = new JMenuItem("Đăng nhập");

//		menu.add(manageMoviesMenuItem);
		menu.add(bookTicketsMenuItem);
	//	menu.add(listfilmMenuItem);
		menu.add(viewHistoryMenuItem);
		menu.add(loginMenuItem);
		menuBar.add(menu);
		panel_chung1 = new JPanel();
		
		
		
		
		pane_DSP = new JPanel();
		pane_DSP.setLayout(new BorderLayout());

		// Tạo panel chứa 2 nút
		panel_but = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
		button_cancel = new JButton("Hủy");
		button_cont = new JButton("Chọn ghế");

		panel_but.add(button_cancel);
		panel_but.add(button_cont);

		// Thêm panel nút vào panel chính
		pane_DSP.add(panel_but, BorderLayout.SOUTH);
	        
	      
	        
	        
	        //Ghe-Khanh
	     // ====== GHẾ - Khanh (ĐÃ SỬA) ======
	        panel_ghe = new JPanel(new BorderLayout());
	        panel_ghe.setBorder(BorderFactory.createTitledBorder("Chọn ghế"));
	        
	        List<SeatButtonView2> danhSachGhe = new ArrayList<>();

	        // Panel chứa ghế
	        JPanel seatPanel = new JPanel(new GridLayout(4, 4, 10, 10));
	        for (char row = 'A'; row <= 'D'; row++) {
	            for (int col = 1; col <= 4; col++) {
	                String maGhe = row + String.valueOf(col);
	                GheModel ghe = new GheModel(maGhe);
	                SeatButtonView2 btn = new SeatButtonView2(ghe, statusLabel, danhSachGhe);
	                danhSachGhe.add(btn);
	                seatPanel.add(btn);
	            }
	        }

	        // Panel status và nút
	        JPanel bottomPanel = new JPanel(new BorderLayout());
	        bottomPanel.add(statusLabel, BorderLayout.WEST);

	        button_conti = new JButton("Tiếp tục");
	        button_qlai = new JButton("Quay lại");

	        JPanel bottomRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	        bottomRight.add(button_qlai);
	        bottomRight.add(button_conti);

	        bottomPanel.add(bottomRight, BorderLayout.EAST);
	        
	        card = new CardLayout();
	        card_common = new JPanel(card);

	        // Action ghế sang tiếp tục
	        button_conti.addActionListener(e -> {
	            long soGheDangChon = danhSachGhe.stream().filter(SeatButtonView2::isDangChon).count();

	            if (soGheDangChon == 0) {
	                JOptionPane.showMessageDialog(panel_ghe, "Bạn chưa chọn ghế nào.", "Thông báo", JOptionPane.WARNING_MESSAGE);
	                return;
	            }

	            int confirm = JOptionPane.showConfirmDialog(panel_ghe,
	                    "Bạn có chắc muốn đặt " + soGheDangChon + " ghế?",
	                    "Xác nhận đặt ghế", JOptionPane.YES_NO_OPTION);

	            if (confirm == JOptionPane.YES_OPTION) {
	                seatList.clear(); // clear danh sách cũ
	                for (SeatButtonView2 btn : danhSachGhe) {
	                    if (btn.isDangChon()) {
	                        btn.xacNhanDat();
	                        seatList.add(btn.getText());
	                    }
	                }
	                statusLabel.setText("Ghế đã chọn: 0");
	                lable_seat.setText("Ghế: " + String.join(", ", seatList));
	                Random ran = new Random();
	                label_room.setText("Phòng: " + (ran.nextInt(5) + 1));
	            }
	        });

	        button_qlai.addActionListener(e -> card.show(card_common, "Đặt vé"));

	        // Panel chú thích
	        JPanel panelChuThich = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
	        panelChuThich.add(taoMucChuThich("Trống", Color.LIGHT_GRAY));
	        panelChuThich.add(taoMucChuThich("Đang chọn", Color.GREEN));
	        panelChuThich.add(taoMucChuThich("Đã đặt", Color.RED));

	        // Add vào panel_ghe
	        panel_ghe.add(panelChuThich, BorderLayout.NORTH);
	        panel_ghe.add(seatPanel, BorderLayout.CENTER);
	        panel_ghe.add(bottomPanel, BorderLayout.SOUTH);

	        // Thêm vào card layout
	        card_common.add("Chọn xong", panel_ghe);

	        
	        
	        
	        //Thong tin ve-An
			panel_infor = new JPanel();
			panel_infor.setBorder(BorderFactory.createTitledBorder("Thông tin vé"));
			panel_infor.setLayout(new GridLayout(3,2));
			
			lable_name = new JLabel("Tên phim: ");
			lable_performance = new JLabel("Suất: ");
			label_room = new JLabel("Phòng chiếu: ");
			lable_seat = new JLabel("Ghế: ");
			
			panel_infor.add(lable_name);panel_infor.add(label_room);
			panel_infor.add(lable_performance);panel_infor.add(lable_seat);
			
			rd_adult= new JRadioButton("Người lớn");
			rd_student_OldPerson = new JRadioButton("Hssv-Người cao tuổi");
			group = new ButtonGroup();
			group.add(rd_adult);group.add(rd_student_OldPerson);
			
			panel_infor.add(rd_adult);
			panel_infor.add(rd_student_OldPerson);
			
			//Thong tin combo
			panel_center = new JPanel();
			panel_center.setBorder(BorderFactory.createTitledBorder("Combo"));
			panel_center.setLayout(new BorderLayout());
			panel_combo = new JPanel();
			panel_combo.setLayout(new GridLayout(2,4));
			
			cb_corn_regular = new JCheckBox("Bắp thường(40000)");
			cb_corn_caramel = new JCheckBox("Bắp caramel(45000)");
			cb_corn_cheses = new JCheckBox("Bắp phô mai(60000)");
			cb_softdrink = new JCheckBox("Nước ngọt(28000)");
			
			// Tạo spinner để nhập số lượng từng combo (0 đến 10)
			JSpinner spinner_caramel = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
			JSpinner spinner_regular = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
			JSpinner spinner_cheses = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
			JSpinner spinner_softdrink = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));

			
			panel_combo.add(cb_corn_regular);panel_combo.add(spinner_regular);
			panel_combo.add(cb_softdrink);panel_combo.add(spinner_softdrink);
			panel_combo.add(cb_corn_caramel);panel_combo.add(spinner_caramel);
			panel_combo.add(cb_corn_cheses);panel_combo.add(spinner_cheses);
			
			model = new DefaultTableModel();
			model.addColumn("STT");
			model.addColumn("Tên sản phẩm");
			model.addColumn("Đơn giá");
			table = new JTable(model);
			scroll = new JScrollPane(table);
			
			panel_button1_ = new JPanel();
			button_complete = new JButton("Hoàn thành");
			button_complete.addActionListener(new ActionListener() {
				// Tao ra loai ve trung gian de cac decor phủ lên và tách gias vé riêng ra nhằm tránh các vé bị phủ lên = với số lượng vé.
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					String textSeat = lable_seat.getText();
					textSeat = textSeat.replace("Ghế: ", "").trim();
					String[] seatArray = textSeat.split(",");
					int numTicket = seatArray.length;  
					
					// Xu ly lay gia ve
					int price =0;
					if(rd_adult.isSelected()) {
						price = (int) new AdultTicket().price();
					}else {
						if(rd_student_OldPerson.isSelected()) {
							price = (int) new HSSV_OldPersonTicket().price();
						}
					}
					int numPriceTicket = price*numTicket;
		                model.setRowCount(0);
		                stt = 0;
		                
		                //Xu ly chon combo
		                Ticket ticket = new BasicTicket();
		                int sl_regular = (int) spinner_regular.getValue();
		                int sl_caramel = (int) spinner_caramel.getValue();
		                int sl_cheses = (int) spinner_cheses.getValue();
		                int sl_softdrink = (int) spinner_softdrink.getValue();

		                for (int i = 0; i < sl_regular; i++) {
		                    ticket = new RegularCorn(ticket);
		                    model.addRow(new Object[]{++stt, "Bắp thường", 40000});
		                }

		                for (int i = 0; i < sl_caramel; i++) {
		                    ticket = new CaramelCorn(ticket);
		                    model.addRow(new Object[]{++stt, "Bắp caramel", 45000});
		                }

		                for (int i = 0; i < sl_cheses; i++) {
		                    ticket = new ChesesCorn(ticket);
		                    model.addRow(new Object[]{++stt, "Bắp phô mai", 60000});
		                }

		                for (int i = 0; i < sl_softdrink; i++) {
		                    ticket = new Softdrink(ticket);
		                    model.addRow(new Object[]{++stt, "Nước ngọt", 28000});
		                }

		                // Tổng tiền: giá vé + combo
		                int totalCombo = (int) ticket.price();
		                totalPrice = numPriceTicket + totalCombo;
		                lable_rice.setText("Tổng đơn hàng: " + totalPrice + " đồng     "+"Mã vé: "+ticket.ticketID);

		     
		                spinner_regular.setValue(0);
		                spinner_caramel.setValue(0);
		                spinner_cheses.setValue(0);
		                spinner_softdrink.setValue(0);
		                cb_corn_caramel.setSelected(false);
		                cb_corn_cheses.setSelected(false);
		                cb_corn_regular.setSelected(false);
		                cb_softdrink.setSelected(false);
		            }
		        });
			panel_button1_.add(button_complete);
			
			panel_center.add(panel_combo,BorderLayout.NORTH);
			panel_center.add(scroll, BorderLayout.CENTER);
			panel_center.add(panel_button1_,BorderLayout.SOUTH);
			
			// Thao tác
			panel_rice_button = new JPanel();
			panel_rice_button.setBorder(BorderFactory.createTitledBorder("Thao tác"));
			panel_rice_button.setLayout(new BorderLayout());
			
			lable_rice = new JLabel("Tổng đơn hàng", JLabel.LEFT);
			button_back = new JButton("Quay lại chọn ghế");
			button_continue = new JButton("Tiếp tục");
			button_continue.addActionListener(new ActionListener() {
			    @Override
			    public void actionPerformed(ActionEvent e) {
			        // Get thông tin từ UI hiện tại
			        String movieName = lable_name.getText().replace("Tên phim: ", "");
			        String room = label_room.getText().replace("Phòng: ", "");
			        String seats = lable_seat.getText().replace("Ghế: ", "");
			       // String showschedule = lable_seat.getText().replace("Suất: ", "");
			        
			        // Determine ticket type
			        String ticketType = "";
			        if (rd_adult.isSelected()) {
			            ticketType = "Người lớn";
			        } else if (rd_student_OldPerson.isSelected()) {
			            ticketType = "Học sinh - Người cao tuổi";
			        }
			        
			        // Get combo details từ table
			        StringBuilder comboDetails = new StringBuilder();
			        for (int i = 0; i < model.getRowCount(); i++) {
			            String productName = (String) model.getValueAt(i, 1);
			            int price = (Integer) model.getValueAt(i, 2);
			            comboDetails.append(productName).append(" - ").append(price).append(" VNĐ\n");
			        }
			        
			        // Get total amount từ label
			        String totalText = lable_rice.getText().replace("Tổng đơn hàng: ", "").replace(" đồng", "");
			        double totalAmount = 0;
			        try {
			            totalAmount = Double.parseDouble(totalText);
			        } catch (NumberFormatException ex) {
			            totalAmount = 0;
			        }
			        
			        // Set default data nếu chưa có
			        if (movieName.isEmpty() || movieName.equals("Tên phim: ")) {
			            movieName = "Avengers: Endgame"; // Default movie
			            lable_name.setText("Tên phim: " + movieName);
			        }
			        
			        if (seats.isEmpty() || seats.equals("Ghế: ")) {
			            seats = "A1, A2"; // Default seats if none selected
			            lable_seat.setText("Ghế: " + seats);
			        }
			        
			        if (ticketType.isEmpty()) {
			            ticketType = "Người lớn"; // Default ticket type
			            rd_adult.setSelected(true);
			        }
			        
			        if (room.isEmpty() || room.equals("Phòng: ")) {
			            room = "Phòng 1"; // Default room
			            label_room.setText(room);
			        }
//			        if (showschedule.isEmpty() || showschedule.equals("Suất: ")) {
//			        	showschedule = "17"; // Default room
//			            label_room.setText(showschedule);
//			        }
			        
			        // Nếu chưa có combo nào, tính lại tổng tiền chỉ với vé
			        if (totalAmount == 0) {
			            int ticketPrice = ticketType.equals("Người lớn") ? 60000 : 45000;
			            String[] seatArray = seats.split(",");
			            totalAmount = ticketPrice * seatArray.length;
			            lable_rice.setText("Tổng đơn hàng: " + (int)totalAmount + " đồng");
			        }
			        
			        // Tạo PaymentController và chuyển sang màn hình thanh toán
			        // Sử dụng MainView.this thay vì this
			        paymentController = new PaymentController(
				            movieName, room, seats, ticketType, 
				            comboDetails.toString(), totalAmount, MainView.this
				        );
			        
			        // Ẩn cửa sổ hiện tại thay vì đóng
			        setVisible(false);
			    }
			});

			
			panel_rice_button.add(lable_rice, BorderLayout.WEST);
			panel_button2 = new JPanel();
			panel_button2.setLayout(new FlowLayout(FlowLayout.RIGHT));
			panel_button2.add(button_back);
			panel_button2.add(button_continue);
			panel_rice_button.add(panel_button2,BorderLayout.EAST);
			
			panel_card = new JPanel();
			panel_card.setLayout(new BorderLayout());
			panel_card.add(panel_infor,BorderLayout.NORTH);
			panel_card.add(panel_center,BorderLayout.CENTER);
			panel_card.add(panel_rice_button,BorderLayout.SOUTH);
				
				// Nội dung chính
				JLabel welcomeLabel = new JLabel("Chào mừng bạn đến với hệ thống đặt vé phim!", SwingConstants.CENTER);
				welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
				card_home = new JPanel();
				card_home.add(welcomeLabel, BorderLayout.CENTER);
				
				CardLayout card = new CardLayout();
				card_common = new JPanel(card);
				card_common.add("Menu",card_home);
				card_common.add("Đặt vé", pane_DSP);
				card_common.add("Chọn xong",panel_ghe);
				card_common.add("Tiếp tục", panel_card);
				bookTicketsMenuItem.addActionListener(e-> card.show(card_common	, "Đặt vé"));
				button_cont.addActionListener(e->card.show(card_common, "Chọn xong"));
				button_qlai.addActionListener(e-> card.show(card_common, "Đặt vé"));
				button_conti.addActionListener(e->card.show(card_common, "Tiếp tục"));
				button_back.addActionListener(e-> card.show(card_common, "Chọn xong"));
				button_cancel.addActionListener(e-> card.show(card_common, "Menu"));
			
				
				getContentPane().add(card_common);
				setVisible(true);

		setJMenuBar(menuBar);


	}
	 public void themPhimVaoTable(Phim phim) {
	        Object[] rowData = {
	            phim.getTenPhim(),
	            phim.getTheloai(),
	            phim.getDirector(),
	            phim.getReleaseYear(),
	            phim.getDuration() + " phút",
	            phim.getTrangthai(),
	        };
	        tableModel.addRow(rowData);
	    }
	 private JLabel createLegendLabel(String text, Color color) {
	        JLabel label = new JLabel(text);
	        label.setOpaque(true);
	        label.setBackground(color);
	        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	        label.setPreferredSize(new Dimension(80, 25));
	        label.setHorizontalAlignment(SwingConstants.CENTER);
	        return label;
	    }

//	public JMenuItem getManageMoviesMenuItem() {
//		return manageMoviesMenuItem;
//	}

	public JMenuItem getBookTicketsMenuItem() {
		return bookTicketsMenuItem;
	}

	public JMenuItem getListfilmMenuItem() {
		return listfilmMenuItem;
	}

	public JMenuItem getViewHistoryMenuItem() {
		return viewHistoryMenuItem;
	}

	public JMenuItem getLoginMenuItem() {
		return loginMenuItem;
	}
	private class handleSeat implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JButton src = (JButton) e.getSource();
			if(src==button_conti) {
				for (JButton jButton : selectedSeats) {
					seatList.add(jButton.getText());
				}
				String seat =String.join(",", seatList);
				lable_seat.setText("Ghế: "+seat);
//				Random ran = new Random();
//				int numRoom = ran.nextInt(5)+1;
//				label_room.setText("Phòng: "+numRoom);
				
		}
		
	}
	}
	
	public void xuLyPhimDuocChon(List<Phim> danhSachPhim) {
	    StringBuilder sb = new StringBuilder("Phim đã chọn:\n");
	    for (Phim p : danhSachPhim) {
	        sb.append("Tên phim: ").append(p.getTenPhim()).append("\n");
	        sb.append("Thể loại: ").append(p.getTheloai()).append("\n");
	        sb.append("NSX: ").append(p.getDirector()).append("\n");
	        sb.append("Năm SX: ").append(p.getReleaseYear()).append("\n");
	        sb.append("Thời lượng: ").append(p.getDuration()).append("\n");
	        sb.append("").append(p.getLichChieu()).append("\n");
	        sb.append("Trạng thái: ").append(p.getTrangthai()).append("\n");
	    }
	   
	    JOptionPane.showMessageDialog(this, sb.toString());
	    
	}


	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainView view = new MainView();
				new MainController(view);
				view.setVisible(true);
			}
		});
	}
	
	
}
