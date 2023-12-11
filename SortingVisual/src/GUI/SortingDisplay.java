package GUI;

import javax.swing.*;
import java.awt.*;

public class SortingDisplay extends JPanel {
    private int[] values;
    private int accessCount;
    private int comparisons;
    private int swapCount;
    private double timeExecuted;

    public SortingDisplay(int[] values) {
        this.values = values;
    }

    public void setValues(int[] values) {
        this.values = values;
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
            g.setColor(Color.BLUE);
            g.fillRect(empty_space / 2 + i * barWidth, maxHeight - barHeight, barWidth, barHeight);
            g.setColor(Color.BLACK);
            g.drawRect(empty_space / 2 + i * barWidth, maxHeight - barHeight, barWidth, barHeight);
        }

        // Display statistics
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 10));
        g.drawString("Access Count: " + accessCount, 10, 20);
        g.drawString("Comparisons: " + comparisons, 10, 40);
        g.drawString("Swap Count: " + swapCount, 10, 60);
        g.drawString("Time Executed: " + timeExecuted / 1000.0 + " s", 10, 80);
    }

    private int getMaxValue() {
        int max = Integer.MIN_VALUE;
        for (int value : values) {
            max = Math.max(max, value);
        }
        return max;
    }
}