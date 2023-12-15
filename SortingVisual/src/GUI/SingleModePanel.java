package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Helper.Helper;
import SortingAlgorithm.*;

public class SingleModePanel extends JPanel {
    private JComboBox<String> sortAlgorithmCombo;
    private JPanel sortingPanel;
    private SortingAlgorithm sortingAlgorithm;
    private String prevSelection;
    private JButton backButton;

    public SingleModePanel() {
        Helper helper = new Helper();
        setLayout(new BorderLayout());

        // Create a panel for the top center components
        JPanel topCenterPanel = new JPanel();
        topCenterPanel.setLayout(new BorderLayout());

        JPanel labelCenter = ElementCreater.createLabel("Single Mode");
        topCenterPanel.add(labelCenter, BorderLayout.NORTH);

        // Create a box for the buttons
        Box hbox = Box.createHorizontalBox();

        backButton = ElementCreater.createButton("Back", 12);
        backButton.setPreferredSize(new Dimension(60, 30));
        backButton.addActionListener(e -> panelManager.showPanel("ChoicePanel"));
        hbox.add(backButton);

        sortAlgorithmCombo = new JComboBox<>(helper.getListAlgorithm().toArray(new String[0]));
        sortAlgorithmCombo.setFont(new Font("Segoe UI", 1, 12));
        sortAlgorithmCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (sortingAlgorithm.isRunning()) {
                    JOptionPane.showMessageDialog(null, "Please wait for the sorting to complete.");
                    sortAlgorithmCombo.setSelectedItem(prevSelection);
                    return;
                }

                String selectedAlgorithm = (String) sortAlgorithmCombo.getSelectedItem();
                prevSelection = selectedAlgorithm;
                int[] currentArr = sortingAlgorithm.getValues();
                if (helper.isSortedArray(currentArr)) {
                    currentArr = helper.generateRandomIntArray(sortingAlgorithm.getValues().length);
                }

                sortingAlgorithm = helper.getSortSelected(selectedAlgorithm, currentArr);
                JPanel newPanel = sortingAlgorithm.getSortingDisplay();

                remove(sortingPanel);
                sortingPanel = newPanel;
                add(sortingPanel, BorderLayout.CENTER);

                // Refresh UI
                revalidate();
                repaint();
            }
        });
        hbox.add(sortAlgorithmCombo);

        topCenterPanel.add(hbox, BorderLayout.SOUTH);
        add(topCenterPanel, BorderLayout.NORTH);

        int[] arr = helper.generateRandomIntArray(100);
        sortingAlgorithm = helper.getSortSelected("Bubble Sort", arr);
        prevSelection = "Bubble Sort";

        sortingPanel = sortingAlgorithm.getSortingDisplay();
        add(sortingPanel, BorderLayout.CENTER);

        // Create a panel for the bottom buttons
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton changeSizeButton = ElementCreater.createButton("Change array size", 12);
        changeSizeButton.setPreferredSize(new Dimension(150, 30));
        changeSizeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle the change size button action
                if (sortingAlgorithm.isRunning()) {
                    return;
                }
                String input = JOptionPane.showInputDialog(null, "Enter the length of the new array:");
                if (input != null) {
                    try {
                        int newArrayLength = Integer.parseInt(input);

                        if (newArrayLength > 1000 || newArrayLength <= 1) {
                            JOptionPane.showMessageDialog(null,
                                    "Please enter an integer less or equal than 1000 and greater than 1");
                            return;
                        }

                        int[] newArray = helper.generateRandomIntArray(newArrayLength);
                        sortingAlgorithm.setValues(newArray);
                        repaint();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid integer.");
                    }
                }
            }
        });
        bottomPanel.add(changeSizeButton);

        JButton resetArrayButton = ElementCreater.createButton("Reset array", 12);
        resetArrayButton.setPreferredSize(new Dimension(150, 30));
        resetArrayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (sortingAlgorithm.isRunning()) {
                    return;
                }
                int[] newArray = helper.generateRandomIntArray(sortingAlgorithm.getValues().length);
                sortingAlgorithm.setValues(newArray);
                repaint();
            }
        });
        bottomPanel.add(resetArrayButton);

        JButton startButton = ElementCreater.createButton("Start", 12);
        startButton.setPreferredSize(new Dimension(60, 30));
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (sortingAlgorithm.isRunning() || helper.isSortedArray(sortingAlgorithm.getValues())) {
                    return;
                }
                Thread newSortingThread = new Thread(() -> {
                    sortingAlgorithm.sort();
                });
                newSortingThread.start();
            }
        });
        bottomPanel.add(startButton);

        add(bottomPanel, BorderLayout.SOUTH);
    }
}
