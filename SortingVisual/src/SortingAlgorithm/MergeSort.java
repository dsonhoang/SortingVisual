package SortingAlgorithm;

import GUI.SortingDisplay;

public class MergeSort extends SortingAbstract implements SortingAlgorithm {
    private int[] values;
    private SortingDisplay sortingDisplay;

    public MergeSort(int[] values) {
        super();
        this.values = values;
        this.sortingDisplay = new SortingDisplay(values);
    }

    @Override
    public void sort() {
        isRunning = true;
        long startTime = System.nanoTime();

        mergeSort(values, 0, values.length - 1, startTime);

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

            timeExecuted = (System.nanoTime() - startTime) / 1e6;
            sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted);
        }
        for (int j = 0; j < n2; ++j) {
            rightArray[j] = arr[mid + 1 + j];
            accessCount++;

            timeExecuted = (System.nanoTime() - startTime) / 1e6;
            sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted);
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
            swapCount++;

            timeExecuted = (System.nanoTime() - startTime) / 1e6;
            sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted);

            notifyDisplay();

            k++;
        }

        while (i < n1) {
            arr[k] = leftArray[i];
            i++;
            k++;

            swapCount++;

            timeExecuted = (System.nanoTime() - startTime) / 1e6;
            sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted);

            notifyDisplay();
        }

        while (j < n2) {
            arr[k] = rightArray[j];
            j++;
            k++;

            swapCount++;

            timeExecuted = (System.nanoTime() - startTime) / 1e6;
            sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted);

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
        sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted);
    }
}
