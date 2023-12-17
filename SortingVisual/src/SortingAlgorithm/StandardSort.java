package SortingAlgorithm;

import GUI.SortingDisplay;

import java.util.ArrayList;
import java.util.List;

public class StandardSort extends SortingAbstract {
    private int[] values;
    private SortingDisplay sortingDisplay;
    private List<Integer> markedColumns;

    public StandardSort(int[] values, boolean isVisual) {
        super(isVisual);
        this.values = values;
        sortingDisplay = new SortingDisplay(values);
        markedColumns = new ArrayList<>();
    }

    @Override
    public void sort() {
        isRunning = true;
        long startTime = System.nanoTime();

        int n = values.length;
        boolean swapped;

        do {
            swapped = false;
            for (int i = 1; i < n; i++) {
                accessCount++;
                if (values[i - 1] > values[i]) {
                    comparisons++;
                    // Hoán đổi các phần tử nếu cần thiết
                    swap(values, i - 1, i);
                    markedColumns.clear();
                    markedColumns.add(i - 1);
                    markedColumns.add(i);
                    sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted, markedColumns);
                    notifyDisplay();

                    swapped = true;
                }
                timeExecuted = (System.nanoTime() - startTime) / 1e6;
            }
        } while (swapped);

        long endTime = System.nanoTime();
        timeExecuted = (endTime - startTime) / 1e6;
        isRunning = false;

        sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted, markedColumns);
        sortingDisplay.setSorted(true);
        notifyDisplay();
    }


    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        swapCount++;
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