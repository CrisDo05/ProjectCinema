package model;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SeatButtonView2 extends JButton implements Observer2 {
    private final GheModel model;
    private boolean isSelected = false;

    public SeatButtonView2(GheModel model, JLabel statusLabel, List<SeatButtonView2> danhSachGhe) {
        super(model.getMaGhe());
        this.model = model;
        this.setBackground(Color.LIGHT_GRAY);
        this.setFocusPainted(false);
        this.setPreferredSize(new Dimension(50, 40));

        model.themObserver2(this);

        this.addActionListener(e -> {
            if (model.isDaDat()) return;

            isSelected = !isSelected;
            this.setBackground(isSelected ? Color.GREEN : Color.LIGHT_GRAY);

            // Cập nhật trạng thái số ghế đã chọn
            long soGheDangChon = danhSachGhe.stream().filter(SeatButtonView2::isDangChon).count();
            statusLabel.setText("Ghe da chon: " + soGheDangChon);
        });
    }

    public boolean isDangChon() {
        return isSelected;
    }

    public void xacNhanDat() {
        if (isSelected) {
            model.datGhe(); // thông báo observer
            isSelected = false;
        }
    }

    @Override
    public void capNhatGhe() {
        setBackground(Color.RED);
        setEnabled(false);
    }
}