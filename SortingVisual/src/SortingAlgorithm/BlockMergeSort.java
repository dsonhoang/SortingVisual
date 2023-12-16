package SortingAlgorithm;

import GUI.SortingDisplay;

import java.util.ArrayList;
import java.util.List;

public class BlockMergeSort extends SortingAbstract {
    private int[] values;
    private SortingDisplay sortingDisplay;
    private List<Integer> markedColumns;

    public BlockMergeSort(int[] values) {
        super();
        this.values = values;
        this.sortingDisplay = new SortingDisplay(values);
        this.markedColumns = new ArrayList<>();
    }

    @Override
    public void sort() {
        isRunning = true;
        long startTime = System.nanoTime();

        wikiSort(0, values.length, startTime);

        long endTime = System.nanoTime();
        timeExecuted = (endTime - startTime) / 1e6;

        isRunning = false;
        sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted, markedColumns);
        sortingDisplay.setSorted(true);

        notifyDisplay();
    }

    private void wikiSort(int start, int end, long startTime) {
        if (end - start <= 1) {
            return;
        }

        int mid = start + ((end - start) / 2);

        wikiSort(start, mid, startTime);
        wikiSort(mid, end, startTime);
        merge(start, mid, end, startTime);
    }

    private void merge(int start, int mid, int end, long startTime) {
        int[] merged = new int[end - start];
        int left = start;
        int right = mid;
        int mergedIndex = 0;

        while (left < mid && right < end) {
            accessCount += 2;
            comparisons++;
            if (values[left] <= values[right]) {
                merged[mergedIndex++] = values[left++];

                markedColumns.clear(); // Clear the previous swapped columns
                markedColumns.add(left); // Add the first column being swapped
                markedColumns.add(right);
                swapCount++;
                timeExecuted = (System.nanoTime() - startTime) / 1e6;
                sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted, markedColumns);

                notifyDisplay();
            } else {
                merged[mergedIndex++] = values[right++];

                markedColumns.clear(); // Clear the previous swapped columns
                markedColumns.add(right); // Add the first column being swapped
                markedColumns.add(left);
                swapCount++;
                timeExecuted = (System.nanoTime() - startTime) / 1e6;
                sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted, markedColumns);

                notifyDisplay();
            }


        }

        while (left < mid) {
            merged[mergedIndex++] = values[left++];

            markedColumns.clear(); // Clear the previous swapped columns
            //markedColumns.add(left); // Add the first column being swapped
            //markedColumns.add(mergedIndex);
            swapCount++;
            timeExecuted = (System.nanoTime() - startTime) / 1e6;
            sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted, markedColumns);

            notifyDisplay();
        }

        while (right < end) {
            merged[mergedIndex++] = values[right++];

            //markedColumns.clear(); // Clear the previous swapped columns
            //markedColumns.add(mergedIndex); // Add the first column being swapped
            //markedColumns.add(right);
            swapCount++;
            timeExecuted = (System.nanoTime() - startTime) / 1e6;
            sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted, markedColumns);

            notifyDisplay();
        }

        for (int i = 0; i < merged.length; i++) {
            values[start + i] = merged[i];
            swapCount++;
            markedColumns.clear();
            markedColumns.add(start + i);
            //markedColumns.add(i);
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

    @Override
    public void reset() {
        super.reset();
        sortingDisplay.setSorted(false);
        sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted, markedColumns);
    }

    private void notifyDisplay() {
        if (sortingDisplay != null) {
            sortingDisplay.repaint();
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}