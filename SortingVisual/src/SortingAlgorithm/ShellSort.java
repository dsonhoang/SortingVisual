package SortingAlgorithm;

import GUI.SortingDisplay;

import java.util.ArrayList;
import java.util.List;

public class ShellSort extends SortingAbstract {
    private int[] values;
    private SortingDisplay sortingDisplay;
    private List<Integer> markedColumns;

    public ShellSort(int[] values, boolean isVisual) {
        super(isVisual);
        this.values = values;
        this.sortingDisplay = new SortingDisplay(values);
        markedColumns = new ArrayList<>();
    }

    @Override
    public void sort() {
        isRunning = true;
        long startTime = System.nanoTime();

        int n = values.length;
        int gap = n / 2;

        while (gap > 0) {
            for (int i = gap; i < n; i++) {
                int temp = values[i];
                int j = i;

                while (j >= gap && values[j - gap] > temp) {
                    accessCount += 2; // Two accesses: values[j - gap] and values[j]
                    values[j] = values[j - gap];

                    markedColumns.clear(); // Clear the previous swapped columns
                    markedColumns.add(j); // Add the first column being swapped
                    markedColumns.add(j - gap);
                    timeExecuted = (System.nanoTime() - startTime) / 1e6;
                    sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted, markedColumns);
                    notifyDisplay();

                    j -= gap;
                    swapCount++;
                    comparisons++;
                }

                values[j] = temp;
                accessCount++;

            }

            gap /= 2;
        }

        sortingDisplay.setSorted(true);
        notifyDisplay();

        isRunning = false;
    }

    @Override
    public void setValues(int[] values) {
        reset();
        this.values = values;
        this.sortingDisplay.setValues(values);
    }

    @Override
    public int[] getValues() {
        return values;
    }

    @Override
    public SortingDisplay getSortingDisplay() {
        return sortingDisplay;
    }

    private void notifyDisplay() {
        if (!this.isVisual) {
            return;
        }
        if (sortingDisplay != null) {
            sortingDisplay.repaint();
            try {
                Thread.sleep(DELAY); // Delay for visualization
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void reset() {
        super.reset();
        sortingDisplay.setSorted(false);
        sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted);
    }
}
