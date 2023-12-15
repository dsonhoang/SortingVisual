package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class SortingDisplay extends JPanel {
    private int[] values;
    private int accessCount;
    private int comparisons;
    private int swapCount;
    private double timeExecuted;
    private List<Integer> changedColumns = new ArrayList<>();
    private boolean sorted = false;

    public SortingDisplay(int[] values) {
        this.values = values;
    }

    public void setValues(int[] values) {
        this.values = values;
        changedColumns.clear();
        repaint();
    }

    public void setStatistics(int accessCount, int comparisons, int swapCount, double timeExecuted, List<Integer> changedColumns) {
        this.accessCount = accessCount;
        this.comparisons = comparisons;
        this.swapCount = swapCount;
        this.timeExecuted = timeExecuted;
        this.changedColumns = changedColumns;
        repaint();
    }

    public void setStatistics(int accessCount, int comparisons, int swapCount, double timeExecuted) {
        this.accessCount = accessCount;
        this.comparisons = comparisons;
        this.swapCount = swapCount;
        this.timeExecuted = timeExecuted;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int barWidth = getWidth() / values.length;
        int empty_space = getWidth() - barWidth * values.length;
        int maxValue = getMaxValue();
        int maxHeight = getHeight();

        for (int i = 0; i < values.length; i++) {
            int barHeight = (int) ((double) values[i] / maxValue * maxHeight * 0.9);
            if (sorted) {
                g.setColor(Color.GREEN);
            }
            else if (changedColumns.contains(i)) {
                g.setColor(Color.RED); // Sử dụng màu đỏ cho các cột đã thay đổi
            } else {
                g.setColor(Color.BLUE);
            }
            g.fillRect(empty_space / 2 + i * barWidth, maxHeight - barHeight, barWidth, barHeight);
            g.setColor(Color.BLACK);
            g.drawRect(empty_space / 2 + i * barWidth, maxHeight - barHeight, barWidth, barHeight);
        }

        g.setFont(new Font("Arial", Font.BOLD, 10));
        g.drawString("Access Count: " + accessCount, 10, 20);
        g.drawString("Comparisons: " + comparisons, 10, 40);
        g.drawString("Swap Count: " + swapCount, 10, 60);
        g.drawString("Time Executed: " + String.format("%.2f", timeExecuted / 1000.0) + " s", 10, 80);
    }

    private int getMaxValue() {
        int max = Integer.MIN_VALUE;
        for (int value : values) {
            max = Math.max(max, value);
        }
        return max;
    }

    public void setSorted(boolean sorted) {
        this.sorted = sorted;
        repaint();
    }
}