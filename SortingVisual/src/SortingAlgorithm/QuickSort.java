package SortingAlgorithm;

import GUI.SortingDisplay;

import java.util.ArrayList;
import java.util.List;

public class QuickSort extends SortingAbstract implements SortingAlgorithm {
    private int[] values;
    private SortingDisplay sortingDisplay;
    private List<Integer> markedColumns;

    public QuickSort(int[] values, boolean isVisual) {
        super(isVisual);
        this.values = values;
        this.sortingDisplay = new SortingDisplay(values);
        this.markedColumns = new ArrayList<>();
    }

    @Override
    public void sort() {
        isRunning = true;
        long startTime = System.nanoTime();

        quickSort(0, values.length - 1, startTime);

        sortingDisplay.setSorted(true);
        notifyDisplay();

        long endTime = System.nanoTime();
        timeExecuted = (endTime - startTime) / 1e6;
        sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted, markedColumns);
        isRunning = false;
    }

    private void quickSort(int low, int high, long startTime) {
        if (low < high) {
            int partitionIndex = partition(low, high, startTime);

            quickSort(low, partitionIndex - 1, startTime);
            quickSort(partitionIndex + 1, high, startTime);
        }
    }

    private int partition(int low, int high, long startTime) {
        int pivot = values[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            accessCount += 2;
            if (values[j] <= pivot) {
                i++;

                int temp = values[i];
                values[i] = values[j];
                values[j] = temp;

                swapCount++;

                markedColumns.clear(); // Clear the previous swapped columns
                markedColumns.add(i); // Add the partitioned column
                markedColumns.add(j);

                timeExecuted = (System.nanoTime() - startTime) / 1e6;
                sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted, markedColumns);
                notifyDisplay();
            }
            comparisons++;
        }
        int temp = values[i + 1];
        values[i + 1] = values[high];
        values[high] = temp;

        markedColumns.clear(); // Clear the previous swapped columns
        markedColumns.add(i + 1); // Add the partitioned column
        markedColumns.add(high);
        timeExecuted = (System.nanoTime() - startTime) / 1e6;

        sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted, markedColumns);
        notifyDisplay();

        return i + 1;
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