package SortingAlgorithm;

import GUI.SortingDisplay;

public class CustomSort extends SortingAbstract implements SortingAlgorithm {
    private int[] values;
    private SortingDisplay sortingDisplay;

    public CustomSort(int[] values) {
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
            System.out.println();
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
        timeExecuted = (endTime - startTime) / 1e6;
        sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted);
        isRunning = false;
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
        sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted);
    }
}
