package view;

import javax.swing.*;

import model.GheModel;
import model.SeatButtonView2;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ghe extends JPanel {
    private final JLabel statusLabel;
    private final JButton buttonContinue;
    private final JButton buttonBack;
    private final List<String> selectedSeats;
    private final List<SeatButtonView2> seatButtons;

    public Ghe(JLabel seatLabel, JLabel roomLabel, CardLayout cardLayout, JPanel cardContainer) {
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createTitledBorder("Chọn ghế"));

        seatButtons = new ArrayList<>();
        selectedSeats = new ArrayList<>();
        statusLabel = new JLabel("Ghế đã chọn: 0");
        buttonContinue = new JButton("Tiếp tục");
        buttonBack = new JButton("Quay lại");

        // Panel chứa ghế
        JPanel seatPanel = new JPanel(new GridLayout(4, 4, 10, 10));
        for (char row = 'A'; row <= 'D'; row++) {
            for (int col = 1; col <= 4; col++) {
                String maGhe = row + String.valueOf(col);
                GheModel ghe = new GheModel(maGhe);
                SeatButtonView2 btn = new SeatButtonView2(ghe, statusLabel, seatButtons);
                seatButtons.add(btn);
                seatPanel.add(btn);
            }
        }

        // Panel chú thích
        JPanel panelChuThich = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        panelChuThich.add(createLegend("Trống", Color.LIGHT_GRAY));
        panelChuThich.add(createLegend("Đang chọn", Color.GREEN));
        panelChuThich.add(createLegend("Đã đặt", Color.RED));

        // Bottom Panel
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(statusLabel, BorderLayout.WEST);

        JPanel bottomRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomRight.add(buttonBack);
        bottomRight.add(buttonContinue);
        bottomPanel.add(bottomRight, BorderLayout.EAST);

        this.add(panelChuThich, BorderLayout.NORTH);
        this.add(seatPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);

        // Sự kiện
        buttonContinue.addActionListener(e -> {
            long soGheDangChon = seatButtons.stream().filter(SeatButtonView2::isDangChon).count();

            if (soGheDangChon == 0) {
                JOptionPane.showMessageDialog(this, "Bạn chưa chọn ghế nào.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this,
                    "Bạn có chắc muốn đặt " + soGheDangChon + " ghế?",
                    "Xác nhận đặt ghế", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                selectedSeats.clear();
                for (SeatButtonView2 btn : seatButtons) {
                    if (btn.isDangChon()) {
                        btn.xacNhanDat();
                        selectedSeats.add(btn.getText());
                    }
                }

                statusLabel.setText("Ghế đã chọn: 0");
                seatLabel.setText("Ghế: " + String.join(", ", selectedSeats));
                roomLabel.setText("Phòng: " + (new Random().nextInt(5) + 1));
                cardLayout.show(cardContainer, "Tiếp tục");
            }
        });

        buttonBack.addActionListener(e -> cardLayout.show(cardContainer, "Đặt vé"));
    }

    private JPanel createLegend(String text, Color color) {
        JPanel legend = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        JLabel colorBox = new JLabel();
        colorBox.setOpaque(true);
        colorBox.setBackground(color);
        colorBox.setPreferredSize(new Dimension(20, 20));

        JLabel labelText = new JLabel(text);
        legend.add(colorBox);
        legend.add(labelText);
        return legend;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Test giao diện chọn ghế");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 500);
            frame.setLocationRelativeTo(null);

            // CardLayout setup
            CardLayout cardLayout = new CardLayout();
            JPanel cardPanel = new JPanel(cardLayout);

            // Label giả lập
            JLabel labelSeat = new JLabel("Ghế: ");
            JLabel labelRoom = new JLabel("Phòng: ");

            // Panel tiếp theo sau khi chọn ghế (tạm thời)
            JPanel nextPanel = new JPanel();
            nextPanel.add(new JLabel("Màn hình tiếp theo sau khi chọn ghế"));

            // Thêm giao diện ghế vào CardLayout
            Ghe seatPanel = new Ghe(labelSeat, labelRoom, cardLayout, cardPanel);
            cardPanel.add(seatPanel, "Chọn ghế");
            cardPanel.add(nextPanel, "Tiếp tục");

            frame.add(cardPanel);
            frame.setVisible(true);

            // Hiện panel ghế trước
            cardLayout.show(cardPanel, "Chọn ghế");
        });
    }
}
