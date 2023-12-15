package GUI;

import javax.swing.*;
import java.awt.*;

public class ChoicePanel extends JPanel {
    private JButton singleModeBtn;
    private JButton compareModeBtn;
    private JButton customCodeButton;

    public ChoicePanel() {
        setBackground(new Color(68, 110, 213));
        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);

        JLabel choiceLabel = new JLabel("SELECT YOUR CHOICE");
        choiceLabel.setFont(new Font("Segoe UI", 1, 36));
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.NORTH;
        add(choiceLabel, c);

        singleModeBtn = createStyledButton("Single Mode");
        c.gridy = 1;
        add(singleModeBtn, c);
        singleModeBtn.addActionListener(e ->
            ListPanels.showPanel("SingleModePanel")
        );

        compareModeBtn = createStyledButton("Compare Mode");
        c.gridy = 2;
        add(compareModeBtn, c);
        compareModeBtn.addActionListener(e ->
            ListPanels.showPanel("CompareModePanel")
        );

        customCodeButton = createStyledButton("Your custom code");
        c.gridy = 3;
        add(customCodeButton, c);
        customCodeButton.addActionListener(e ->
            ListPanels.showPanel("CustomCodePanel")
        );
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", 1, 18));
        button.setBackground(new Color(21, 68, 53));
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(200, 40));
        return button;
    }
}
