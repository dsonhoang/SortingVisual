package SortingAlgorithm;

import GUI.SortingDisplay;

import java.util.ArrayList;
import java.util.List;

public class BitonicSort extends SortingAbstract {
    private int[] values;
    private SortingDisplay sortingDisplay;
    private List<Integer> markedColumns;

    public BitonicSort(int[] values) {
        super();
        this.values = values;
        this.sortingDisplay = new SortingDisplay(values);
        this.markedColumns = new ArrayList<>();
    }

    @Override
    public void sort() {
        isRunning = true;
        long startTime = System.nanoTime();

        bitonicSort(0, values.length, true);

        long endTime = System.nanoTime();
        timeExecuted = (endTime - startTime) / 1e6;

        isRunning = false;
        sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted, markedColumns);
        sortingDisplay.setSorted(true);

        notifyDisplay();
        for(int i = 0; i < values.length; i++) {
            System.out.print(values[i] + " ");
        }

    }


    private void bitonicSort(int low, int count, boolean ascending) {
        if (count > 1) {
            int k = count / 2;
            bitonicSort(low, k, true);
            bitonicSort(low + k, k, false);
            bitonicMerge(low, count, ascending);
        }
    }

    private void bitonicMerge(int low, int count, boolean ascending) {
        if (count > 1) {
            int k = count / 2;
            for (int i = low; i < low + k; i++) {
                compareAndSwap(i, i + k, ascending);
            }
            bitonicMerge(low, k, ascending);
            bitonicMerge(low + k, k, ascending);
        }
    }

    private void compareAndSwap(int a, int b, boolean ascending) {
        accessCount += 2;
        comparisons++;
        if ((values[a] > values[b] && ascending) || (values[a] < values[b] && !ascending)) {
            swap(a, b);
        }
    }

    private void swap(int a, int b) {
        int temp = values[a];
        values[a] = values[b];
        values[b] = temp;
        swapCount++;
        markedColumns.clear();
        markedColumns.add(a);
        markedColumns.add(b);
        sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted, markedColumns);

        notifyDisplay();
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