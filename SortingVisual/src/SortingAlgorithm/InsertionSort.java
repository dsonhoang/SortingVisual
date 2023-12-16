package SortingAlgorithm;

import GUI.SortingDisplay;

import java.util.ArrayList;
import java.util.List;

public class InsertionSort extends SortingAbstract implements SortingAlgorithm {

    private int[] values;
    private SortingDisplay sortingDisplay;
    private List<Integer> markedColumns;

    public InsertionSort(int[] values) {
        super();
        this.values = values;
        this.sortingDisplay = new SortingDisplay(values);
        markedColumns = new ArrayList<>();
    }

    @Override
    public void sort() {
        isRunning = true;
        long startTime = System.nanoTime();

        int n = values.length;
        for (int i = 1; i < n; i++) {
            int key = values[i];
            int j = i - 1;

            while (j >= 0 && values[j] > key) {
                accessCount += 2;
                values[j + 1] = values[j];
                values[j] = key;

                markedColumns.clear();
                markedColumns.add(j);
                markedColumns.add(j + 1);
                markedColumns.add(j - 1);

                j = j - 1;
                swapCount++;
                comparisons++;
                timeExecuted = (System.nanoTime() - startTime) / 1e6;
                sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted, markedColumns);
                notifyDisplay();
            }
            values[j + 1] = key;
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
