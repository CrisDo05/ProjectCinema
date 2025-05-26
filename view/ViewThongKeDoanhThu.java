package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ViewThongKeDoanhThu extends JFrame {

    private JTable revenueTable;
    private DefaultTableModel tableModel;

    public ViewThongKeDoanhThu() {
        setTitle("Thống kê doanh thu");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Tiêu đề
        JLabel titleLabel = new JLabel("Báo cáo doanh thu", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        add(titleLabel, BorderLayout.NORTH);

        // Dữ liệu bảng
        String[] columnNames = {"Ngày", "Số vé bán", "Tổng doanh thu (VNĐ)"};
        tableModel = new DefaultTableModel(columnNames, 0);
        revenueTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(revenueTable);
        add(scrollPane, BorderLayout.CENTER);

        // Dữ liệu mẫu (có thể thay bằng dữ liệu thật sau)
        tableModel.addRow(new Object[]{"2025-05-10", 120, 2400000});
        tableModel.addRow(new Object[]{"2025-05-11", 80, 1600000});
    }

    public void addRevenueData(String date, int ticketsSold, int totalRevenue) {
        tableModel.addRow(new Object[]{date, ticketsSold, totalRevenue});
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ViewThongKeDoanhThu frame = new ViewThongKeDoanhThu();
            frame.setVisible(true);
        });
    }
}
