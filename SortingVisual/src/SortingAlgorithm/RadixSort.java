package SortingAlgorithm;

import GUI.SortingDisplay;

import java.util.ArrayList;
import java.util.List;

public class RadixSort extends SortingAbstract {
    private int[] values;
    private SortingDisplay sortingDisplay;
    private List<Integer> markedColumns;

    public RadixSort(int[] values) {
        super();
        this.values = values;
        this.sortingDisplay = new SortingDisplay(values);
        this.markedColumns = new ArrayList<>();
    }

    @Override
    public void sort() {
        isRunning = true;
        long startTime = System.nanoTime();

        int max = getMaxValue();
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countSort(exp, startTime);
        }

        long endTime = System.nanoTime();
        timeExecuted = (endTime - startTime) / 1e6;

        isRunning = false;
        sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted, markedColumns);
        sortingDisplay.setSorted(true);

        notifyDisplay();
    }

    private int getMaxValue() {
        int max = values[0];
        for (int i = 1; i < values.length; i++) {
            accessCount++;
            comparisons++;
            if (values[i] > max) {
                max = values[i];
            }
        }
        return max;
    }

    private void countSort(int exp, long startTime) {
        int n = values.length;
        int[] output = new int[n];
        int[] count = new int[10];

        for (int i = 0; i < n; i++) {
            count[(values[i] / exp) % 10]++;
            timeExecuted = (System.nanoTime() - startTime) / 1e6;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
            timeExecuted = (System.nanoTime() - startTime) / 1e6;
        }

        for (int i = n - 1; i >= 0; i--) {
            output[count[(values[i] / exp) % 10] - 1] = values[i];
            count[(values[i] / exp) % 10]--;
            timeExecuted = (System.nanoTime() - startTime) / 1e6;
        }

        for (int i = 0; i < n; i++) {
            values[i] = output[i];
            swapCount++;
            markedColumns.clear();
            markedColumns.add(i);
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