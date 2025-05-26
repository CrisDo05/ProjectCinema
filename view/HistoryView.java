package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HistoryView extends JFrame {
    public HistoryView() {
        setTitle("Lịch sử đặt vé");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

       
        String[] data = {
            "Vé 1:  phim A, ghế B2, ngày 12/05,9h30",
            "Vé 2:  phim B, ghế C1, ngày 10/05,1h30",
            "Vé 3:  phim C, ghế D3, ngày 08/05,3h"
        };

        JList<String> list = new JList<>(data);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String selected = list.getSelectedValue();
                    JOptionPane.showMessageDialog(null,
                        "Chi tiết vé:\n" + selected,
                        "Thông tin vé",
                        JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        add(new JScrollPane(list), BorderLayout.CENTER);
    }
}
