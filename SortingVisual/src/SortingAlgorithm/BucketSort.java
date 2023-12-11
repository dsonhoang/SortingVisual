package SortingAlgorithm;

import GUI.SortingDisplay;
import java.util.ArrayList;
import java.util.Collections;

public class BucketSort extends SortingAbstract implements SortingAlgorithm {
    private int[] values;
    private SortingDisplay sortingDisplay;

    public BucketSort(int[] values) {
        super();
        this.values = values;
        this.sortingDisplay = new SortingDisplay(values);
    }

    @Override
    public void sort() {
        isRunning = true;
        long startTime = System.nanoTime();
        timeExecuted = startTime / 1e6;

        int n = values.length;
        int maxVal = getMaxValue();

        // Create buckets
        ArrayList<ArrayList<Integer>> buckets = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            buckets.add(new ArrayList<>());

            // Update display after sorting
            timeExecuted = (System.nanoTime() - startTime) / 1e6;
            sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted);
        }

        // Place elements in buckets
        for (int value : values) {
            int bucketIndex = (int) ((double) value / maxVal * (n - 1));
            buckets.get(bucketIndex).add(value);
            accessCount++;

            // Update display after sorting
            timeExecuted = (System.nanoTime() - startTime) / 1e6;
            sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted);
            notifyDisplay();
        }

        // Sort individual buckets
        for (ArrayList<Integer> bucket : buckets) {
            Collections.sort(bucket);
            comparisons += bucket.size() * Math.log(bucket.size()); // Estimation of comparisons
            swapCount += bucket.size() - 1;

            // Update display after sorting
            timeExecuted = (System.nanoTime() - startTime) / 1e6;
            sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted);
            notifyDisplay();
        }

        // Concatenate sorted buckets
        int index = 0;
        for (ArrayList<Integer> bucket : buckets) {
            for (int value : bucket) {
                values[index++] = value;
                swapCount++;
                accessCount++;

                // Update display after sorting
                timeExecuted = (System.nanoTime() - startTime) / 1e6;
                sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted);
                notifyDisplay();
            }
        }

        isRunning = false;
    }

    private void bucketSort() {
        int n = values.length;
        int maxVal = getMaxValue();

        // Create buckets
        ArrayList<ArrayList<Integer>> buckets = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            buckets.add(new ArrayList<>());

            // Update display after sorting
            timeExecuted = (System.nanoTime() - timeExecuted) / 1e6;
            sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted);
        }

        // Place elements in buckets
        for (int value : values) {
            int bucketIndex = (int) ((double) value / maxVal * (n - 1));
            buckets.get(bucketIndex).add(value);
            accessCount++;

            // Update display after sorting
            timeExecuted = (System.nanoTime() - timeExecuted) / 1e6;
            sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted);
            notifyDisplay();
        }

        // Sort individual buckets
        for (ArrayList<Integer> bucket : buckets) {
            Collections.sort(bucket);
            comparisons += bucket.size() * Math.log(bucket.size()); // Estimation of comparisons
            swapCount += bucket.size() - 1;

            // Update display after sorting
            timeExecuted = (System.nanoTime() - timeExecuted) / 1e6;
            sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted);
            notifyDisplay();
        }

        // Concatenate sorted buckets
        int index = 0;
        for (ArrayList<Integer> bucket : buckets) {
            for (int value : bucket) {
                values[index++] = value;
                swapCount++;
                accessCount++;

                // Update display after sorting
                timeExecuted = (System.nanoTime() - timeExecuted) / 1e6;
                sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted);
                notifyDisplay();
            }
        }
    }

    private int getMaxValue() {
        int max = values[0];
        for (int value : values) {
            if (value > max) {
                max = value;
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

    @Override
    public void reset() {
        super.reset();
        sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted);
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
}
