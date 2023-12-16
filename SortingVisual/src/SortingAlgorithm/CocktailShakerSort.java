package SortingAlgorithm;

import GUI.SortingDisplay;

import java.util.ArrayList;
import java.util.List;

public class CocktailShakerSort extends SortingAbstract {
    private int[] values;
    private SortingDisplay sortingDisplay;
    private List<Integer> markedColumns;

    public CocktailShakerSort(int[] values) {
        super();
        this.values = values;
        this.sortingDisplay = new SortingDisplay(values);
        this.markedColumns = new ArrayList<>();
    }

    @Override
    public void sort() {
        isRunning = true;
        long startTime = System.nanoTime();

        int left = 0;
        int right = values.length - 1;

        while (left <= right) {
            boolean swapped = false;

            for (int i = left; i < right; i++) {
                accessCount++;
                if (values[i] > values[i + 1]) {
                    swap(i, i + 1);
                    swapped = true;

                    markedColumns.clear(); // Clear the previous swapped columns
                    markedColumns.add(i); // Add the first column being swapped
                    markedColumns.add(i + 1);

                    swapCount++;
                    timeExecuted = (System.nanoTime() - startTime) / 1e6;
                    sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted, markedColumns);

                    notifyDisplay();
                }
                comparisons++;
            }
            right--;

            for (int i = right; i > left; i--) {
                accessCount++;
                if (values[i] < values[i - 1]) {
                    swap(i, i - 1);

                    markedColumns.clear(); // Clear the previous swapped columns
                    markedColumns.add(i); // Add the first column being swapped
                    markedColumns.add(i - 1);
                    swapCount++;
                    timeExecuted = (System.nanoTime() - startTime) / 1e6;
                    sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted, markedColumns);

                    notifyDisplay();
                    swapped = true;
                }
                comparisons++;
            }
            left++;

            if (!swapped) {
                break;
            }

            timeExecuted = (System.nanoTime() - startTime) / 1e6;
            sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted, markedColumns);
            notifyDisplay();
        }

        sortingDisplay.setSorted(true);
        notifyDisplay();

        long endTime = System.nanoTime();
        timeExecuted = (endTime - startTime) / 1e6;
        sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted, markedColumns);
        isRunning = false;
    }

    private void swap(int i, int j) {
        int temp = values[i];
        values[i] = values[j];
        values[j] = temp;
        swapCount++;
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
        sortingDisplay.setSorted(false);
        sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted, markedColumns);
    }

}