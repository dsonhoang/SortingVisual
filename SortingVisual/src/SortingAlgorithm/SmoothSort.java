package SortingAlgorithm;

import GUI.SortingDisplay;

import java.util.ArrayList;
import java.util.List;

public class SmoothSort extends SortingAbstract {
    private int[] values;
    private SortingDisplay sortingDisplay;
    private List<Integer> markedColumns;

    public SmoothSort(int[] values, boolean isVisual) {
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
            siftUp(values, i, startTime);
        }
        for (int i = n - 1; i > 0; i--) {
            swap(values, 0, i);
            siftDown(values, 0, i, startTime);
        }

        isRunning = false;
        sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted, markedColumns);
        sortingDisplay.setSorted(true);
        notifyDisplay();
    }

    private void siftUp(int[] arr, int i, long startTime) {
        while (i > 0 && arr[i] > arr[parent(i)]) {
            accessCount++;
            comparisons++;
            timeExecuted = (System.nanoTime() - startTime) / 1e6;
            swap(arr, i, parent(i));
            i = parent(i);
        }
    }

    private void siftDown(int[] arr, int i, int size, long startTime) {
        while (leftChild(i) < size) {
            accessCount++;
            int child = leftChild(i);
            if (child + 1 < size && arr[child] < arr[child + 1]) {
                comparisons++;
                child++;
            }
            if (arr[i] < arr[child]) {
                swap(arr, i, child);
                comparisons++;
            } else {
                break;
            }
            i = child;

            timeExecuted = (System.nanoTime() - startTime) / 1e6;
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

        swapCount++;
        markedColumns.clear();
        markedColumns.add(i);
        markedColumns.add(j);
        sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted, markedColumns);
        notifyDisplay();
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int leftChild(int i) {
        return 2 * i + 1;
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
