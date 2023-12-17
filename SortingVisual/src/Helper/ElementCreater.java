package Helper;

import javax.swing.*;
import java.awt.*;

public class ElementCreater {
    public static JButton createButton(String text, int size, int width, int height) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", 1, size));
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
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
