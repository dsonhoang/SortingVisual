package SortingAlgorithm;

import GUI.SortingDisplay;

import java.util.ArrayList;
import java.util.List;

public class OddEvenSort extends SortingAbstract {
    private int[] values;
    private SortingDisplay sortingDisplay;
    private List<Integer> markedColumns;

    public OddEvenSort(int[] values, boolean isVisual) {
        super(isVisual);
        this.values = values;
        this.sortingDisplay = new SortingDisplay(values);
        this.markedColumns = new ArrayList<>();
    }

    @Override
    public void sort() {
        isRunning = true;
        long startTime = System.nanoTime();

        boolean sorted = false;
        while (!sorted) {
            sorted = true;

            // Sort odd-indexed elements
            for (int i = 1; i < values.length - 1; i += 2) {
                accessCount++;
                comparisons++;
                if (values[i] > values[i + 1]) {
                    int temp = values[i];
                    values[i] = values[i + 1];
                    values[i + 1] = temp;

                    markedColumns.clear();
                    markedColumns.add(i);
                    markedColumns.add(i + 1);

                    timeExecuted = (System.nanoTime() - startTime) / 1e6;
                    sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted, markedColumns);

                    notifyDisplay();
                    swapCount++;
                    sorted = false;
                }
            }

            // Sort even-indexed elements
            for (int i = 0; i < values.length - 1; i += 2) {
                accessCount++;
                comparisons++;
                if (values[i] > values[i + 1]) {
                    int temp = values[i];
                    values[i] = values[i + 1];
                    values[i + 1] = temp;

                    markedColumns.clear();
                    markedColumns.add(i);
                    markedColumns.add(i + 1);
                    timeExecuted = (System.nanoTime() - startTime) / 1e6;
                    sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted, markedColumns);
                    notifyDisplay();

                    swapCount++;
                    sorted = false;
                }
            }
        }

        long endTime = System.nanoTime();
        timeExecuted = (endTime - startTime) / 1e6;

        isRunning = false;

        sortingDisplay.setSorted(true);
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