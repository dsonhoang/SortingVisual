package SortingAlgorithm;

import GUI.SortingDisplay;

import java.util.ArrayList;
import java.util.List;

public class BinaryInsertionSort extends SortingAbstract{
    private int[] values;
    private SortingDisplay sortingDisplay;
    private List<Integer> markedColumns;

    public BinaryInsertionSort(int[] values, boolean isVisual) {
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
        for (int i = 1; i < n; i++) {
            int key = values[i];
            int j = binarySearch(key, i);
            for (int k = i - 1; k >= j; k--) {
               values[k + 1] = values[k];
               values[k] = key;
                swapCount++;

                markedColumns.clear();
                markedColumns.add(k - 1);
                markedColumns.add(k);
                markedColumns.add(k + 1);
                markedColumns.add(key);
                accessCount++;

                timeExecuted = (System.nanoTime() - startTime) / 1e6;
                sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted, markedColumns);
                notifyDisplay();
            }
            values[j] = key;
        }

        sortingDisplay.setSorted(true);
        notifyDisplay();

        long endTime = System.nanoTime();
        timeExecuted = (endTime - startTime) / 1e6;
        sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted, markedColumns);
        isRunning = false;
    }

    private int binarySearch(int key, int endIndex) {
        int start = 0;
        int end = endIndex - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (values[mid] == key) {
                return mid;
            } else if (values[mid] < key) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
            comparisons++;
        }
        return start;
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
        sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted, markedColumns);
    }
}