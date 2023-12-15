package GUI;

import Helper.Helper;
import SortingAlgorithm.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class CompareMode extends JFrame {
    private JButton backButton;
    private JComboBox<String> sortAlgorithmCombo1;
    private JComboBox<String> sortAlgorithmCombo2;
    private JPanel sortingPanelsContainer;
    private JPanel sortingPanel1;
    private JPanel sortingPanel2;
    private SortingAlgorithm firstSortingAlgorithm;
    private SortingAlgorithm secondSortingAlgorithm;
    private String prevSelection1;
    private String prevSelection2;
    private final int LENGTH = 280;
    private final int WIDTH = 1280;

    public CompareMode() {
        Helper helper = new Helper();
        setLayout(new BorderLayout());

        Box hbox = Box.createHorizontalBox();

        JPanel topCenterPanel = new JPanel();
        topCenterPanel.setLayout(new BorderLayout());

        JPanel labelCenter = new JPanel();
        labelCenter.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("Compare Mode");
        titleLabel.setFont(new Font("Segoe UI", 1, 30));
        labelCenter.add(titleLabel);
        topCenterPanel.add(labelCenter, BorderLayout.NORTH);

        backButton = createStyledButton("Back");
        backButton.setPreferredSize(new Dimension(60, 30));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MainFrame().setVisible(true);
            }
        });
        hbox.add(backButton);

        sortAlgorithmCombo1 = new JComboBox<>(helper.getListAlgorithm().toArray(new String[0]));
        sortAlgorithmCombo1.setFont(new Font("Segoe UI", 1, 12));
        sortAlgorithmCombo1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (firstSortingAlgorithm.isRunning() || secondSortingAlgorithm.isRunning()) {
                    JOptionPane.showMessageDialog(null, "Please wait for the sorting to complete.");
                    sortAlgorithmCombo1.setSelectedItem(prevSelection1);
                    return;
                }
                String selectedAlgorithm = (String) sortAlgorithmCombo1.getSelectedItem();
                int[] currentArr = firstSortingAlgorithm.getValues();
                if (helper.isSortedArray(currentArr)) {
                    currentArr = helper.generateRandomIntArray(firstSortingAlgorithm.getValues().length);
                }

                firstSortingAlgorithm = helper.getSortSelected(selectedAlgorithm, currentArr);
                secondSortingAlgorithm.setValues(currentArr.clone());
                JPanel newPanel = firstSortingAlgorithm.getSortingDisplay();
                newPanel.setPreferredSize(new Dimension(WIDTH, LENGTH));
                sortingPanelsContainer.remove(sortingPanel1);

                // Update sortingPanel1 reference and add the new panel
                sortingPanel1 = newPanel;
                sortingPanelsContainer.add(sortingPanel1, BorderLayout.NORTH);

                // Refresh UI
                sortingPanelsContainer.revalidate();
                sortingPanelsContainer.repaint();
                prevSelection1 = selectedAlgorithm;
            }
        });
        hbox.add(sortAlgorithmCombo1);

        sortAlgorithmCombo2 = new JComboBox<>(helper.getListAlgorithm().toArray(new String[0]));
        sortAlgorithmCombo2.setFont(new Font("Segoe UI", 1, 12));
        sortAlgorithmCombo2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (firstSortingAlgorithm.isRunning() || secondSortingAlgorithm.isRunning()) {
                    JOptionPane.showMessageDialog(null, "Please wait for the sorting to complete.");
                    sortAlgorithmCombo2.setSelectedItem(prevSelection2);
                    return;
                }
                String selectedAlgorithm = (String) sortAlgorithmCombo2.getSelectedItem();

                int[] currentArr = secondSortingAlgorithm.getValues();
                if (helper.isSortedArray(currentArr)) {
                    currentArr = helper.generateRandomIntArray(secondSortingAlgorithm.getValues().length);
                }

                secondSortingAlgorithm = helper.getSortSelected(selectedAlgorithm, currentArr);
                firstSortingAlgorithm.setValues(currentArr.clone());
                JPanel newPanel = secondSortingAlgorithm.getSortingDisplay();
                newPanel.setPreferredSize(new Dimension(WIDTH, LENGTH));
                sortingPanelsContainer.remove(sortingPanel2);

                // Update sortingPanel1 reference and add the new panel
                sortingPanel2 = newPanel;
                sortingPanelsContainer.add(sortingPanel2, BorderLayout.SOUTH);

                // Refresh UI
                sortingPanelsContainer.revalidate();
                sortingPanelsContainer.repaint();
                prevSelection2 = selectedAlgorithm;
            }
        });
        hbox.add(sortAlgorithmCombo2);

        topCenterPanel.add(hbox, BorderLayout.SOUTH);
        add(topCenterPanel, BorderLayout.NORTH);

        int[] arr = helper.generateRandomIntArray(100);

        firstSortingAlgorithm = helper.getSortSelected("Bubble Sort", arr);
        prevSelection1 = "Bubble Sort";
        sortingPanel1 = firstSortingAlgorithm.getSortingDisplay();
        sortingPanel1.setPreferredSize(new Dimension(WIDTH, LENGTH));
        Thread firstSortingThread = new Thread(() -> {
            firstSortingAlgorithm.sort();
        });

        secondSortingAlgorithm = helper.getSortSelected("Bubble Sort", arr.clone());
        prevSelection2 = "Bubble Sort";
        sortingPanel2 = secondSortingAlgorithm.getSortingDisplay();
        sortingPanel2.setPreferredSize(new Dimension(WIDTH, LENGTH));
        Thread secondSortingThread = new Thread(() -> {
            secondSortingAlgorithm.sort();
        });

        // Use a panel with BorderLayout for the sorting panels
        sortingPanelsContainer = new JPanel(new BorderLayout());
        sortingPanelsContainer.add(sortingPanel1, BorderLayout.NORTH);
        sortingPanelsContainer.add(sortingPanel2, BorderLayout.SOUTH);

        add(sortingPanelsContainer, BorderLayout.CENTER);

        // Create a panel for the bottom buttons
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton changeSizeButton = createStyledButton("Change array size");
        changeSizeButton.setPreferredSize(new Dimension(150, 30));
        changeSizeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (firstSortingAlgorithm.isRunning() || secondSortingAlgorithm.isRunning()) {
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

                        int[] newArray = new Helper().generateRandomIntArray(newArrayLength);
                        firstSortingAlgorithm.setValues(newArray);
                        secondSortingAlgorithm.setValues(newArray.clone());
                        repaint();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid integer.");
                    }
                }
            }
        });
        bottomPanel.add(changeSizeButton);

        JButton resetArrayButton = createStyledButton("Reset array");
        resetArrayButton.setPreferredSize(new Dimension(150, 30));
        resetArrayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (firstSortingAlgorithm.isRunning() || secondSortingAlgorithm.isRunning()) {
                    return;
                }

                int[] newArray = new Helper().generateRandomIntArray(firstSortingAlgorithm.getValues().length);
                firstSortingAlgorithm.setValues(newArray);
                secondSortingAlgorithm.setValues(newArray.clone());
                repaint();

            }
        });
        bottomPanel.add(resetArrayButton);

        JButton startButton = createStyledButton("Start");
        startButton.setPreferredSize(new Dimension(60, 30));
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (firstSortingAlgorithm.isRunning()
                        || secondSortingAlgorithm.isRunning()
                        || helper.isSortedArray(firstSortingAlgorithm.getValues())
                        || helper.isSortedArray(secondSortingAlgorithm.getValues())) {
                    return;
                }
                Thread newFirstSortingThread = new Thread(() -> {
                    firstSortingAlgorithm.sort();
                });
                Thread newSecondSortingThread = new Thread(() -> {
                    secondSortingAlgorithm.sort();
                });
                newFirstSortingThread.start();
                newSecondSortingThread.start();
            }
        });
        bottomPanel.add(startButton);

        add(bottomPanel, BorderLayout.SOUTH);

        setTitle("Compare Mode");
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", 1, 12));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        return button;
    }
}