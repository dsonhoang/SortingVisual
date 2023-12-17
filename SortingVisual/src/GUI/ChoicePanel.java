package GUI;

import Helper.ElementCreater;

import javax.swing.*;
import java.awt.*;

public class ChoicePanel extends JPanel {
    private JButton singleModeBtn;
    private JButton compareModeBtn;
    private JButton customCodeButton;
    private JButton chartButton;

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

        singleModeBtn = ElementCreater.createButton("Single Mode", 18, 200, 400);
        singleModeBtn.setPreferredSize(new Dimension(260, 40));
        c.gridy = 1;
        add(singleModeBtn, c);
        singleModeBtn.addActionListener(e ->
            PanelManager.showPanel("SingleModePanel")
        );

        compareModeBtn = ElementCreater.createButton("Compare Mode", 18, 200, 400);
        compareModeBtn.setPreferredSize(new Dimension(260, 40));
        c.gridy = 2;
        add(compareModeBtn, c);
        compareModeBtn.addActionListener(e ->
            PanelManager.showPanel("CompareModePanel")
        );

//        customCodeButton = ElementCreater.createButton("Your custom code", 18, 200, 400);
//        customCodeButton.setPreferredSize(new Dimension(200, 40));
//        c.gridy = 3;
//        add(customCodeButton, c);
//        customCodeButton.addActionListener(e ->
//            PanelManager.showPanel("CustomCodePanel")
//        );

        chartButton = ElementCreater.createButton("Parallel Coordinates Chart", 18, 200, 400);
        chartButton.setPreferredSize(new Dimension(260, 40));
        c.gridy = 3;
        add(chartButton, c);
        chartButton.addActionListener(e ->
            PanelManager.showPanel("ParallelCoordinatesChartPanel")
        );
    }
}
