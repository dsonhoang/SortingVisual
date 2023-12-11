package SortingAlgorithm;

import GUI.SortingDisplay;

public class HeapSort extends SortingAbstract implements SortingAlgorithm {
    private int[] values;
    private SortingDisplay sortingDisplay;

    public HeapSort(int[] values) {
        super();
        this.values = values;
        this.sortingDisplay = new SortingDisplay(values);
    }

    @Override
    public void sort() {
        isRunning = true;
        long startTime = System.nanoTime();

        int n = values.length;

        // Build max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(n, i);

            timeExecuted = (System.nanoTime() - startTime) / 1e6;
            sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted);
//            notifyDisplay();
        }

        // Extract elements from the heap one by one
        for (int i = n - 1; i > 0; i--) {
            // Swap values[0] with the last element
            swap(0, i);

            // Increment swapCount for each swap
            swapCount++;
            timeExecuted = (System.nanoTime() - startTime) / 1e6;
            sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted);

            heapify(i, 0);
            notifyDisplay();
        }

        long endTime = System.nanoTime();
        timeExecuted = (endTime - startTime) / 1e6;
        sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted);
        isRunning = false;
    }

    private void heapify(int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        // Increment accessCount for accessing values[i]
        accessCount++;

        if (left < n && values[left] > values[largest]) {
            largest = left;
        }

        // Increment comparisons for the comparison in the if statement
        comparisons++;

        // Increment accessCount for accessing values[left]
        accessCount++;

        if (right < n && values[right] > values[largest]) {
            largest = right;
        }

        // Increment comparisons for the comparison in the if statement
        comparisons++;

        // Increment accessCount for accessing values[right]
        accessCount++;

        if (largest != i) {
            // Swap values[i] with values[largest]
            swap(i, largest);

            // Increment swapCount for each swap
            swapCount++;

            heapify(n, largest);

            timeExecuted = (System.nanoTime() - System.nanoTime()) / 1e6;
            sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted);
            notifyDisplay();
        }
    }

    private void swap(int i, int j) {
        int temp = values[i];
        values[i] = values[j];
        values[j] = temp;
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

    @Override
    public void reset() {
        super.reset();
        sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted);
    }
}
