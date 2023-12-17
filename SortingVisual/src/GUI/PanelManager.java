package GUI;

import chart.ParallelCoordinatesChartPanel;

import javax.swing.*;
import java.awt.*;

public class PanelManager {
    private static PanelManager instance;

    private JPanel cardPanel;
    private CardLayout cardLayout;

    private PanelManager() {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        addPanels();
    }

    public static PanelManager getInstance() {
        if (instance == null) {
            instance = new PanelManager();
        }
        return instance;
    }

    public JPanel getCardPanel() {
        return cardPanel;
    }

    private void addPanels() {
        ChoicePanel choicePanel = new ChoicePanel();
        SingleModePanel singleModePanel = new SingleModePanel();
        CompareModePanel compareModePanel = new CompareModePanel();
//        CustomCodePanel customCodeModePanel = new CustomCodePanel();
        ParallelCoordinatesChartPanel chart = new ParallelCoordinatesChartPanel();

        cardPanel.add(choicePanel, "ChoicePanel");
        cardPanel.add(singleModePanel, "SingleModePanel");
        cardPanel.add(compareModePanel, "CompareModePanel");
//        cardPanel.add(customCodeModePanel, "CustomCodePanel");
        cardPanel.add(chart, "ParallelCoordinatesChartPanel");
    }

    public static void showPanel(String panelName) {
        PanelManager panelManager = getInstance();
        panelManager.cardLayout.show(panelManager.cardPanel, panelName);
    }
}
