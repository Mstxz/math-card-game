package GUI.Component;

import java.awt.*;
import javax.swing.*;
import utils.SharedResource;

public class ColoredComboBoxRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        label.setFont(new Font("Madimi One Regular",Font.PLAIN,18));
        if ("Create New".equals(value)) {
            label.setBackground(new Color(173, 216, 230));
            label.setForeground(Color.WHITE);
            label.setHorizontalAlignment(SwingConstants.CENTER);
        } else {
            label.setBackground(SharedResource.SIAMESE_BASE);
            label.setForeground(Color.WHITE);
            label.setHorizontalAlignment(SwingConstants.LEFT);
        }

        if (isSelected) {
            label.setBackground(label.getBackground().darker());
        }

        label.setOpaque(true);
        return label;
    }
}
// เปลี่ยนสี Combobox ของอันเก่า (ตอนนี้ไม่ได้ใช้)