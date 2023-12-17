package SortingAlgorithm;

import GUI.SortingDisplay;

import java.util.ArrayList;
import java.util.List;

public class MergeSort extends SortingAbstract{
    private int[] values;
    private SortingDisplay sortingDisplay;
    private List<Integer> markedColumns;

    public MergeSort(int[] values, boolean isVisual) {
        super(isVisual);
        this.values = values;
        this.sortingDisplay = new SortingDisplay(values);
        this.markedColumns = new ArrayList<>();
    }

    @Override
    public void sort() {
        isRunning = true;
        long startTime = System.nanoTime();

        mergeSort(values, 0, values.length - 1, startTime);

        sortingDisplay.setSorted(true);
        notifyDisplay();

        isRunning = false;
    }

    private void mergeSort(int[] arr, int low, int high, long startTime) {
        if (low < high) {
            int mid = (low + high) / 2;

            mergeSort(arr, low, mid, startTime);
            mergeSort(arr, mid + 1, high, startTime);

            merge(arr, low, mid, high, startTime);
        }
    }

    private void merge(int[] arr, int low, int mid, int high, long startTime) {
        int n1 = mid - low + 1;
        int n2 = high - mid;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        for (int i = 0; i < n1; ++i) {
            leftArray[i] = arr[low + i];
            accessCount++;

            markedColumns.clear(); // Clear the previous swapped columns
            markedColumns.add(i + low); // Add the first column being swapped
            markedColumns.add(i + low + low);
            timeExecuted = (System.nanoTime() - startTime) / 1e6;
            sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted, markedColumns);
        }
        for (int j = 0; j < n2; ++j) {
            rightArray[j] = arr[mid + 1 + j];
            accessCount++;
            markedColumns.clear(); // Clear the previous swapped columns
            markedColumns.add(j + mid); // Add the first column being swapped
            markedColumns.add(j + mid + 1);

            timeExecuted = (System.nanoTime() - startTime) / 1e6;
            sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted, markedColumns);
        }

        int i = 0, j = 0;
        int k = low;

        while (i < n1 && j < n2) {
            comparisons++;
            if (leftArray[i] <= rightArray[j]) {
                arr[k] = leftArray[i];
                i++;
            } else {
                arr[k] = rightArray[j];
                j++;
            }

            markedColumns.clear(); // Clear the previous swapped columns
            markedColumns.add(i + low); // Add the first column being swapped
            markedColumns.add(j + mid);
            swapCount++;
            timeExecuted = (System.nanoTime() - startTime) / 1e6;
            sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted, markedColumns);

            notifyDisplay();

            k++;
        }

        while (i < n1) {
            arr[k] = leftArray[i];
            i++;
            k++;
            markedColumns.clear(); // Clear the previous swapped columns
            markedColumns.add(i + low); // Add the first column being swapped
            markedColumns.add(k);
            swapCount++;

            timeExecuted = (System.nanoTime() - startTime) / 1e6;
            sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted, markedColumns);
            notifyDisplay();
        }

        while (j < n2) {
            arr[k] = rightArray[j];

            markedColumns.clear(); // Clear the previous swapped columns
            markedColumns.add(j + mid); // Add the first column being swapped
            markedColumns.add(k);
            j++;
            k++;
            swapCount++;

            timeExecuted = (System.nanoTime() - startTime) / 1e6;
            sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted, markedColumns);

            notifyDisplay();
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
