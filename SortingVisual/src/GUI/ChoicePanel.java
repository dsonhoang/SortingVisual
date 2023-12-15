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

        singleModeBtn = ElementCreater.createButton("Single Mode", 18);
        c.gridy = 1;
        add(singleModeBtn, c);
        singleModeBtn.addActionListener(e ->
            PanelManager.showPanel("SingleModePanel")
        );

        compareModeBtn = ElementCreater.createButton("Compare Mode", 18);
        c.gridy = 2;
        add(compareModeBtn, c);
        compareModeBtn.addActionListener(e ->
            PanelManager.showPanel("CompareModePanel")
        );

        customCodeButton = ElementCreater.createButton("Your custom code", 18);
        c.gridy = 3;
        add(customCodeButton, c);
        customCodeButton.addActionListener(e ->
            PanelManager.showPanel("CustomCodePanel")
        );
    }
}
