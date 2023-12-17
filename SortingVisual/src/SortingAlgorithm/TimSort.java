package SortingAlgorithm;

import GUI.SortingDisplay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TimSort extends SortingAbstract {
    private int[] values;
    private SortingDisplay sortingDisplay;
    private List<Integer> markedColumns;

    public TimSort(int[] values, boolean isVisual) {
        super(isVisual);
        this.values = values;
        this.sortingDisplay = new SortingDisplay(values);
        this.markedColumns = new ArrayList<>();
    }

    @Override
    public void sort() {
        isRunning = true;
        long startTime = System.nanoTime();

        timSort(values, values.length, startTime);

        sortingDisplay.setSorted(true);
        notifyDisplay();

        long endTime = System.nanoTime();
        timeExecuted = (endTime - startTime) / 1e6;
        sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted, markedColumns);
        isRunning = false;
    }

    private void timSort(int[] arr, int n, long startTime) {
        int minRun = calculateMinRun(n);

        for (int i = 0; i < n; i += minRun) {
            insertionSort(arr, i, Math.min(i + minRun - 1, n - 1), startTime);
        }

        int size = minRun;
        while (size < n) {
            for (int left = 0; left < n; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min(left + 2 * size - 1, n - 1);
                merge(arr, left, mid, right, startTime);
            }
            size *= 2;
        }
    }

    private int calculateMinRun(int n) {
        int r = 0;
        while (n >= 64) {
            r |= (n & 1);
            n >>= 1;
        }
        return n + r;
    }

    private void insertionSort(int[] arr, int left, int right, long startTime) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;

            accessCount++;
            while (j >= left && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
                accessCount++;
                comparisons++;
            }

            arr[j + 1] = key;
            swapCount++;
            markedColumns.clear();
            markedColumns.add(j + 1);
            timeExecuted = (System.nanoTime() - startTime) / 1e6;
            sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted, markedColumns);
            notifyDisplay();
        }
    }

    private void merge(int[] arr, int left, int mid, int right, long startTime) {
        int len1 = mid - left + 1;
        int len2 = right - mid;

        int[] leftArr = new int[len1];
        int[] rightArr = new int[len2];

        for (int i = 0; i < len1; i++) {
            leftArr[i] = arr[left + i];

            markedColumns.clear();
            markedColumns.add(i);
            markedColumns.add(left + i);

            timeExecuted = (System.nanoTime() - startTime) / 1e6;
            sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted, markedColumns);
            notifyDisplay();
        }
        for (int i = 0; i < len2; i++) {
            rightArr[i] = arr[mid + 1 + i];

            markedColumns.clear();
            markedColumns.add(mid + i + 1);
            markedColumns.add(i);

            timeExecuted = (System.nanoTime() - startTime) / 1e6;
            sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted, markedColumns);
            notifyDisplay();
        }

        int i = 0, j = 0, k = left;
        while (i < len1 && j < len2) {
            accessCount++;
            comparisons++;
            if (leftArr[i] <= rightArr[j]) {

                arr[k] = leftArr[i];
                i++;

                markedColumns.clear();
                markedColumns.add(k);
                markedColumns.add(i);

                timeExecuted = (System.nanoTime() - startTime) / 1e6;
                sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted, markedColumns);
                notifyDisplay();
            } else {
                arr[k] = rightArr[j];
                j++;

                markedColumns.clear();
                markedColumns.add(k);
                markedColumns.add(j);

                timeExecuted = (System.nanoTime() - startTime) / 1e6;
                sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted, markedColumns);
                notifyDisplay();
            }
            k++;
            swapCount++;
            markedColumns.clear();
            markedColumns.add(k);
            timeExecuted = (System.nanoTime() - startTime) / 1e6;
            sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted, markedColumns);
            notifyDisplay();
        }

        while (i < len1) {
            arr[k] = leftArr[i];
            i++;
            k++;
            swapCount++;
            markedColumns.clear();
            markedColumns.add(k);
            timeExecuted = (System.nanoTime() - startTime) / 1e6;
            sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted, markedColumns);
            notifyDisplay();
        }

        while (j < len2) {
            arr[k] = rightArr[j];
            j++;
            k++;
            swapCount++;
            markedColumns.clear();
            markedColumns.add(k);
            timeExecuted = (System.nanoTime() - startTime) / 1e6;
            sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted, markedColumns);

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
