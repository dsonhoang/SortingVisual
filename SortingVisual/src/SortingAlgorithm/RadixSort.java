package SortingAlgorithm;

import GUI.SortingDisplay;

public class RadixSort extends SortingAbstract implements SortingAlgorithm {
    private int[] values;
    private SortingDisplay sortingDisplay;

    public RadixSort(int[] values) {
        super();
        this.values = values;
        this.sortingDisplay = new SortingDisplay(values);
    }

    @Override
    public void sort() {
        isRunning = true;
        long startTime = System.nanoTime();

        int n = values.length;

        // Find the maximum number to determine the number of digits
        int maxNum = getMax(values, n);

        // Perform RadixSort using CountingSort as a subroutine
        for (int exp = 1; maxNum / exp > 0; exp *= 10) {
            countingSort(values, n, exp, startTime);
            timeExecuted = (System.nanoTime() - startTime) / 1e6;
            sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted);
            notifyDisplay();
        }

        isRunning = false;
    }

    private void countingSort(int arr[], int n, int exp, long startTime) {
        int output[] = new int[n];
        int count[] = new int[10];

        for (int i = 0; i < n; i++) {
            accessCount++;
            count[(arr[i] / exp) % 10]++;
            timeExecuted = (System.nanoTime() - startTime) / 1e6;
            sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted);
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
            timeExecuted = (System.nanoTime() - startTime) / 1e6;
            sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted);
            notifyDisplay();
        }

        for (int i = n - 1; i >= 0; i--) {
            accessCount++;
            int index = (arr[i] / exp) % 10;
            output[count[index] - 1] = arr[i];
            count[index]--;
            timeExecuted = (System.nanoTime() - startTime) / 1e6;
            sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted);
        }

        for (int i = 0; i < n; i++) {
            accessCount++;
            arr[i] = output[i];
            swapCount++;
            timeExecuted = (System.nanoTime() - startTime) / 1e6;
            sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted);
            notifyDisplay();
        }
    }

    private int getMax(int arr[], int n) {
        int max = arr[0];
        for (int i = 1; i < n; i++) {
            accessCount++;
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
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
