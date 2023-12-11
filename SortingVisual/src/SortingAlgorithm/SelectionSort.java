package SortingAlgorithm;

import GUI.SortingDisplay;

public class SelectionSort extends SortingAbstract implements SortingAlgorithm {
    private int[] values;
    private SortingDisplay sortingDisplay;

    public SelectionSort(int[] values) {
        super();
        this.values = values;
        this.sortingDisplay = new SortingDisplay(values);
    }

    @Override
    public void sort() {
        isRunning = true;
        long startTime = System.nanoTime();

        int n = values.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                accessCount += 2;
                if (values[j] < values[minIndex]) {
                    minIndex = j;
                }
                comparisons++;
            }

            int tmp = values[minIndex];
            values[minIndex] = values[i];
            values[i] = tmp;
            swapCount++;
            timeExecuted = (System.nanoTime() - startTime) / 1e6;
            sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted);
            notifyDisplay();
        }

        long endTime = System.nanoTime();
        timeExecuted = (endTime - startTime) / 1e6; // Convert to milliseconds
        isRunning = false;
    }

    @Override
    public void setValues(int[] values) {
        this.values = values;
        this.sortingDisplay.setValues(values);
        reset();
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
                Thread.sleep(5); // Delay for visualization
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
