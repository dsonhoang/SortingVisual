package SortingAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class SelectionSort extends SortingAbstract{
    private int[] values;
    private SortingDisplay sortingDisplay;
    private List<Integer> markedColumns;

    public SelectionSort(int[] values, boolean isVisual) {
        super(isVisual);
        this.values = values;
        this.sortingDisplay = new SortingDisplay(values);
        this.markedColumns = new ArrayList<>();
    }

    @Override
    public void sort() {
        isRunning = true;
        long startTime = System.nanoTime();

        int n = values.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                accessCount += 2;
                if (values[j] < values[minIndex]) {
                    minIndex = j;
                }
                markedColumns.clear(); // Clear the previous swapped columns
                markedColumns.add(j); // Add the first column being swapped
                markedColumns.add(minIndex);
                comparisons++;
            }

            int tmp = values[minIndex];
            values[minIndex] = values[i];
            values[i] = tmp;
            markedColumns.clear(); // Clear the previous swapped columns
            markedColumns.add(i); // Add the first column being swapped
            markedColumns.add(minIndex);
            swapCount++;
            timeExecuted = (System.nanoTime() - startTime) / 1e6;
            sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted, markedColumns);
            notifyDisplay();
        }

        sortingDisplay.setSorted(true);
        notifyDisplay();

        long endTime = System.nanoTime();
        timeExecuted = (endTime - startTime) / 1e6; // Convert to milliseconds
        isRunning = false;
    }

    @Override
    public void setValues(int[] values) {
        this.values = values;
        this.sortingDisplay.setValues(values);
        reset();
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
        sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted, markedColumns);
    }
}
