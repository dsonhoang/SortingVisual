package chart;

import GUI.ChartPanel;
import GUI.PanelManager;
import Helper.ElementCreater;
import Helper.Helper;
import SortingAlgorithm.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class ParallelCoordinatesChartPanel extends JPanel {
    private JButton backButton;
    private ChartPanel chartPanel;
    private List<SortingAlgorithm> sortingAlgorithms = new ArrayList<>();

    public ParallelCoordinatesChartPanel() {
        Helper helper = new Helper();
        String[] names = helper.getListAlgorithm().toArray(new String[0]);
        List<String> namesList = Arrays.asList(names);
        double[][] sampleData = new double[names.length][4];

        int[] arr = helper.generateRandomIntArray(100);

        for (String name : names) {
            SortingAlgorithm s = helper.getSortSelected(name, arr.clone(), false);
            sortingAlgorithms.add(s);
        }

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

        chartPanel = new ChartPanel(sampleData, names);
        add(chartPanel, BorderLayout.CENTER);

        // Create a panel for the bottom buttons
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton changeSizeButton = ElementCreater.createButton("Change array size", 12, 150, 30);
        changeSizeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle the change size button action
                String input = JOptionPane.showInputDialog(null, "Enter the length of the new array:");
                if (input != null) {
                    try {
                        int newArrayLength = Integer.parseInt(input);

                        if (newArrayLength > 1000000 || newArrayLength <= 1) {
                            JOptionPane.showMessageDialog(null,
                                    "Please enter an integer less or equal than 1000000 and greater than 1");
                            return;
                        }

                        int[] newArray = helper.generateRandomIntArray(newArrayLength);

                        List<Thread> threads = new ArrayList<>();
                        for (String name : namesList) {
                            Thread thread = new Thread(() -> {
                                SortingAlgorithm sortingAlgorithm = helper.getSortSelected(name, newArray.clone(), false);
                                sortingAlgorithm.sort();
                                sampleData[namesList.indexOf(name)] = sortingAlgorithm.getStatistics();
                            });

                            threads.add(thread);
                            thread.start();
                        }

                        for (Thread thread : threads) {
                            try {
                                thread.join();
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }

                        chartPanel.repaint();

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid integer.");
                    }
                }
            }
        });
        bottomPanel.add(changeSizeButton);

        JButton resetArrayButton = ElementCreater.createButton("Reset array", 12, 150, 30);
        resetArrayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int[] newArray = helper.generateRandomIntArray(sortingAlgorithms.get(0).getValues().length);

                List<Thread> threads = new ArrayList<>();
                for (String name : namesList) {
                    Thread thread = new Thread(() -> {
                        SortingAlgorithm sortingAlgorithm = helper.getSortSelected(name, newArray.clone(), false);
                        sortingAlgorithm.sort();
                        sampleData[namesList.indexOf(name)] = sortingAlgorithm.getStatistics();
                    });

                    threads.add(thread);
                    thread.start();
                }

                for (Thread thread : threads) {
                    try {
                        thread.join();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }

                chartPanel.repaint();
            }
        });
        bottomPanel.add(resetArrayButton);

        add(bottomPanel, BorderLayout.SOUTH);
    }
}
