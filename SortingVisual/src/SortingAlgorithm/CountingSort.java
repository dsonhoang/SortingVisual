package SortingAlgorithm;

import GUI.SortingDisplay;

public class CountingSort extends SortingAbstract implements SortingAlgorithm {
    private int[] values;
    private SortingDisplay sortingDisplay;

    public CountingSort(int[] values) {
        super();
        this.values = values;
        this.sortingDisplay = new SortingDisplay(values);
    }

    @Override
    public void sort() {
        isRunning = true;
        long startTime = System.nanoTime();
        countingSort(values, startTime);
        isRunning = false;
    }

    public void countingSort(int[] arr, long startTime) {
        if (arr == null || arr.length <= 1) {
            return; // Already sorted
        }

        // Find the range of the input values
        int max = arr[0];
        int min = arr[0];
        for (int num : arr) {
            if (num > max) {
                max = num;
            }
            if (num < min) {
                min = num;
            }
        }

        // Create a counting array to store the count of each value
        int range = max - min + 1;
        int[] count = new int[range];

        // Count the occurrences of each value in the input array
        for (int num : arr) {
            count[num - min]++;
            accessCount++;
            notifyDisplay(startTime);
        }

        // Update the input array with sorted values
        int index = 0;
        for (int i = 0; i < range; i++) {
            while (count[i] > 0) {
                arr[index++] = i + min;
                count[i]--;
                swapCount++;
                notifyDisplay(startTime);
            }
        }
    }

    private void notifyDisplay(long startTime) {
        timeExecuted = (System.nanoTime() - startTime) / 1e6;
        sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted);

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
