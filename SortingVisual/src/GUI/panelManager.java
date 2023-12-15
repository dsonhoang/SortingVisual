package GUI;

import javax.swing.*;
import java.awt.*;

public class panelManager {
    private static panelManager instance;

    private JPanel cardPanel;
    private CardLayout cardLayout;

    private panelManager() {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        addPanels();
    }

    public static panelManager getInstance() {
        if (instance == null) {
            instance = new panelManager();
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
        CustomCodePanel customCodeModePanel = new CustomCodePanel();

        cardPanel.add(choicePanel, "ChoicePanel");
        cardPanel.add(singleModePanel, "SingleModePanel");
        cardPanel.add(compareModePanel, "CompareModePanel");
        cardPanel.add(customCodeModePanel, "CustomCodePanel");
    }

    public static void showPanel(String panelName) {
        panelManager listPanels = getInstance();
        listPanels.cardLayout.show(listPanels.cardPanel, panelName);
    }
}
