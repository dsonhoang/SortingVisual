package SortingAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class HeapSort extends SortingAbstract{
    private int[] values;
    private SortingDisplay sortingDisplay;
    private List<Integer> markedColumns;

    public HeapSort(int[] values, boolean isVisual) {
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

        // Build max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(n, i, startTime);
        }

        // Extract elements from heap one by one
        for (int i = n - 1; i >= 0; i--) {
            accessCount++;
            int temp = values[0];
            values[0] = values[i];
            values[i] = temp;
            swapCount++;
            markedColumns.clear(); // Clear the previous swapped columns
            markedColumns.add(0); // Add the root column being swapped
            markedColumns.add(i);
            timeExecuted = (System.nanoTime() - startTime) / 1e6;
            sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted, markedColumns);
            notifyDisplay();

            heapify(i, 0, startTime);
        }

        sortingDisplay.setSorted(true);
        notifyDisplay();

        long endTime = System.nanoTime();
        timeExecuted = (endTime - startTime) / 1e6;
        sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted, markedColumns);
        isRunning = false;
    }

    private void heapify(int n, int i, long startTime) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        accessCount++;

        if (left < n && values[left] > values[largest]) {
            comparisons++;
            accessCount++;
            largest = left;
        }

        if (right < n && values[right] > values[largest]) {
            comparisons++;
            accessCount++;
            largest = right;
        }

        if (largest != i) {
            comparisons++;
            accessCount++;
            int swap = values[i];
            values[i] = values[largest];
            values[largest] = swap;
            swapCount++;
            markedColumns.clear(); // Clear the previous swapped columns
            markedColumns.add(i);
            markedColumns.add(largest);
            timeExecuted = (System.nanoTime() - startTime) / 1e6;
            sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted, markedColumns);
            notifyDisplay();

            heapify(n, largest, startTime);
        }

        comparisons += 3;
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