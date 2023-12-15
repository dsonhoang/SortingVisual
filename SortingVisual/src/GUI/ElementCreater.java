package GUI;

import javax.swing.*;
import java.awt.*;

public class ElementCreater {
    public static JButton createButton(String text, int size) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", 1, size));
        button.setBackground(new Color(21, 68, 53));
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(200, 40));
        return button;
    }

    public static JPanel createLabel(String text) {
        JPanel label = new JPanel();
        label.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel(text);
        titleLabel.setFont(new Font("Segoe UI", 1, 30));
        label.add(titleLabel);
        return label;
    }
}
