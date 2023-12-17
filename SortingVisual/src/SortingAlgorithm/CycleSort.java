package SortingAlgorithm;

import GUI.SortingDisplay;
import Helper.Helper;

import java.util.ArrayList;
import java.util.List;

public class CycleSort extends SortingAbstract {
    private int[] values;
    private SortingDisplay sortingDisplay;
    private List<Integer> markedColumns;

    public CycleSort(int[] values, boolean isVisual) {
        super(isVisual);
        this.values = values;
        this.sortingDisplay = new SortingDisplay(values);
        this.markedColumns = new ArrayList<>();
    }

    @Override
    public void sort() {
        isRunning = true;
        long startTime = System.nanoTime();

        int n = values.length;
        for (int cycleStart = 0; cycleStart < n - 1; cycleStart++) {
            int item = values[cycleStart];
            int pos = cycleStart;

            for (int i = cycleStart + 1; i < n; i++) {
                accessCount++;
                comparisons++;
                if (values[i] < item) {
                    pos++;
                }
                markedColumns.clear(); // Clear the previous swapped columns
                markedColumns.add(i); // Add the first column being swapped
                markedColumns.add(pos);
                timeExecuted = (System.nanoTime() - startTime) / 1e6;
                sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted, markedColumns);

                notifyDisplay();

            }

            if (pos == cycleStart) {
                continue;
            }

            while (item == values[pos]) {
                pos++;
            }

            if (pos != cycleStart) {
                int temp = item;
                item = values[pos];
                values[pos] = temp;

                swapCount++;
                markedColumns.clear();
                markedColumns.add(pos);
                sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted, markedColumns);
                notifyDisplay();
            }

            while (pos != cycleStart) {
                pos = cycleStart;

                for (int i = cycleStart + 1; i < n; i++) {
                    accessCount++;
                    comparisons++;
                    if (values[i] < item) {
                        pos++;
                    }
                }

                while (item == values[pos]) {
                    pos++;
                }

                if (item != values[pos]) {
                    int temp = item;
                    item = values[pos];
                    values[pos] = temp;
                    swapCount++;
                    markedColumns.clear();
                    markedColumns.add(pos);
                    sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted, markedColumns);
                    notifyDisplay();
                }

            }

            comparisons += 2;
        }

        long endTime = System.nanoTime();
        timeExecuted = (endTime - startTime) / 1e6;

        isRunning = false;
        sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted, markedColumns);
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

    @Override
    public void reset() {
        super.reset();
        sortingDisplay.setSorted(false);
        sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted, markedColumns);
    }

    private void notifyDisplay() {
        if (!this.isVisual) {
            return;
        }
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