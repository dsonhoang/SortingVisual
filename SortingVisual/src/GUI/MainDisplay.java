package GUI;

import javax.swing.*;

public class MainDisplay extends JFrame {
    private PanelManager panelManager;
    public MainDisplay() {
        initializeFrame();
        panelManager = PanelManager.getInstance();
        panelManager.showPanel("ChoicePanel");
        add(panelManager.getCardPanel());

        setVisible(true);
    }

    private void initializeFrame() {
        setTitle("Sorting Visualization");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> new MainDisplay());
    }
}
