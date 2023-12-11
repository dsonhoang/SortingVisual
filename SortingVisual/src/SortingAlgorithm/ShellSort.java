package SortingAlgorithm;

import GUI.SortingDisplay;

public class ShellSort extends SortingAbstract implements SortingAlgorithm {
    private int[] values;
    private SortingDisplay sortingDisplay;

    public ShellSort(int[] values) {
        super();
        this.values = values;
        this.sortingDisplay = new SortingDisplay(values);
    }

    @Override
    public void sort() {
        isRunning = true;
        long startTime = System.nanoTime();

        int n = values.length;
        int gap = n / 2;

        while (gap > 0) {
            for (int i = gap; i < n; i++) {
                int temp = values[i];
                int j = i;

                while (j >= gap && values[j - gap] > temp) {
                    accessCount += 2; // Two accesses: values[j - gap] and values[j]
                    values[j] = values[j - gap];
                    j -= gap;
                    swapCount++;
                    comparisons++;
                }

                values[j] = temp;
                accessCount++; // Access values[j]

                // Update display after sorting step
                timeExecuted = (System.nanoTime() - startTime) / 1e6;
                sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted);
                notifyDisplay();
            }

            gap /= 2;
        }

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
