package SortingAlgorithm;

import GUI.SortingDisplay;

public class InsertionSort extends SortingAbstract implements SortingAlgorithm {

    private int[] values;
    private SortingDisplay sortingDisplay;

    public InsertionSort(int[] values) {
        super();
        this.values = values;
        this.sortingDisplay = new SortingDisplay(values);
    }

    @Override
    public void sort() {
        isRunning = true;
        long startTime = System.nanoTime();

        int n = values.length;
        for (int i = 1; i < n; i++) {
            int key = values[i];
            int j = i - 1;

            while (j >= 0 && values[j] > key) {
                accessCount += 2;
                values[j + 1] = values[j];
                j = j - 1;
                swapCount++;
                comparisons++;
                timeExecuted = (System.nanoTime() - startTime) / 1e6;
                sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted);
                notifyDisplay();
            }
            values[j + 1] = key;
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
}
