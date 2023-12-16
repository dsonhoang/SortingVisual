package GUI;

import javax.swing.*;
import java.awt.*;

public class ChartPanel extends JPanel {
    private final int chartWidth = 1100;
    private final int chartHeight = 500;
    private final int padding = 50;
    private final int numDimensions;
    private final double[][] data;
    private final String[] columnNames = {"accessCount", "comparisons", "swapCount", "timeExecuted"};
    private String[] algorithmNames;
    private Color[] lineColors = {Color.BLUE, Color.RED, Color.GREEN, Color.ORANGE, Color.GRAY, Color.YELLOW}; // Add more colors as needed

    public ChartPanel(double[][] data, String[] names) {
        this.data = data;
        this.numDimensions = data[0].length;
        this.algorithmNames = names;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int chartWidth = this.chartWidth - 2 * padding;
        int chartHeight = this.chartHeight - 2 * padding;

        // Draw individual vertical axes with tick marks
        for (int i = 0; i < numDimensions; i++) {
            int x = padding + i * (chartWidth / (numDimensions - 1));
            g2d.drawLine(x, padding, x, this.chartHeight - padding);

            //int maxOfDimension = getMax(i);
            int maxOfDimension = 20;
            //split axes into maxOfDimension and add a dot at the position
            for (int k = 0; k < maxOfDimension; k++) {
                int y = (int) (this.chartHeight - padding - (k * (chartHeight / (maxOfDimension - 1))));
                g2d.drawLine(x - 5, y, x + 5, y); // Tick mark

                // Add dot at the position
                g2d.fillOval(x - 2, y - 2, 4, 4);
                g2d.drawString(Integer.toString(k), x + 8, y + 4);
            }

            // Draw column name
            g2d.drawString(columnNames[i], x - 40, this.chartHeight - padding + 20);
        }

        // Draw lines for each data point with legend
        for (int i = 0; i < data.length; i++) {
            Color lineColor = lineColors[i % lineColors.length]; // Cycle through line colors
            g2d.setColor(lineColor);

            for (int j = 0; j < numDimensions - 1; j++) {
                int x1 = padding + j * (chartWidth / (numDimensions - 1));
                int y1 = (int) (this.chartHeight - padding - (normalize(data[i][j], j) * chartHeight));
                int x2 = padding + (j + 1) * (chartWidth / (numDimensions - 1));
                int y2 = (int) (this.chartHeight - padding - (normalize(data[i][j + 1], j + 1) * chartHeight));

                g2d.drawLine(x1, y1, x2, y2);
            }

            // Draw legend box next to the main chart
            int legendBoxX = chartWidth + padding + 40;
            int legendBoxY = padding + i * 20;
            g2d.fillRect(legendBoxX, legendBoxY, 100, 3);
            g2d.setColor(Color.BLACK);
            g2d.drawString(algorithmNames[i], legendBoxX + 30, legendBoxY + 12);
        }
    }

    private double normalize(double value, int index) {
        double min = 0.0;
        int maxInt = getMax(index);

        return (value - min) / (maxInt - min);
    }

    private int getMax(int index) {
        double max = 1.0;
        for(int i = 0; i < data.length; i++) {
            if (data[i][index] > max) {
                max = data[i][index];
            }
        }

        if (max == 1.0) {
            return 1;
        }

        int maxInt = (int) (max + 1);
        return maxInt;
    }
}