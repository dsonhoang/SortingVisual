package chart;

import GUI.ChartPanel;
import GUI.PanelManager;
import Helper.ElementCreater;
import Helper.Helper;
import SortingAlgorithm.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ParallelCoordinatesChart extends JFrame {
    private JButton backButton;
    private ChartPanel chartPanel;
    private final double[][] data;
    private final String[] names;

    public ParallelCoordinatesChart(double[][] data, String[] names) {
        this.data = data;
        this.names = names;

        setLayout(new BorderLayout());

        // Create a panel for the top center components
        JPanel topCenterPanel = new JPanel();
        topCenterPanel.setLayout(new BorderLayout());

        JPanel labelCenter = ElementCreater.createLabel("Test Mode");
        topCenterPanel.add(labelCenter, BorderLayout.NORTH);

        Box hbox = Box.createHorizontalBox();

        backButton = ElementCreater.createButton("Back", 12, 60, 30);
        backButton.addActionListener(e -> PanelManager.showPanel("ChoicePanel"));
        hbox.add(backButton);

        topCenterPanel.add(hbox, BorderLayout.SOUTH);
        add(topCenterPanel, BorderLayout.NORTH);

        chartPanel = new ChartPanel(this.data, this.names);
        add(chartPanel, BorderLayout.CENTER);

        // Create a panel for the bottom buttons
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton changeSizeButton = ElementCreater.createButton("Change array size", 12, 150, 30);
        changeSizeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle the change size button action

            }
        });
        bottomPanel.add(changeSizeButton);

        JButton resetArrayButton = ElementCreater.createButton("Reset array", 12, 150, 30);
        resetArrayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        bottomPanel.add(resetArrayButton);

        JButton startButton = ElementCreater.createButton("Start", 12, 60, 30);
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Start");
            }
        });
        bottomPanel.add(startButton);

        add(bottomPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Parallel Coordinates Chart");
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        Helper helper = new Helper();
        int[] values = helper.generateRandomIntArray(50);
        String[] names = helper.getListAlgorithm().toArray(new String[0]);
        double[][] sampleData = new double[names.length][4];

        for (int i = 0; i < sampleData.length; i++) {
            SortingAlgorithm sortingAlgorithm = helper.getSortSelected(names[i], values);
            sortingAlgorithm.sort();
            sampleData[i] = sortingAlgorithm.getStatistics();
        }

        ParallelCoordinatesChart chart = new ParallelCoordinatesChart(sampleData, names);
    }
}
