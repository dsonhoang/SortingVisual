package SortingAlgorithm;

import GUI.SortingDisplay;

public class QuickSort extends SortingAbstract implements SortingAlgorithm {
    private int[] values;
    private SortingDisplay sortingDisplay;

    public QuickSort(int[] values) {
        super();
        this.values = values;
        this.sortingDisplay = new SortingDisplay(values);
    }

    @Override
    public void sort() {
        isRunning = true;
        long startTime = System.nanoTime();

        quickSort(values, 0, values.length - 1, startTime);

        isRunning = false;
    }

    private void quickSort(int[] arr, int low, int high, long startTime) {
        if (low < high) {
            int partitionIndex = partition(arr, low, high, startTime);

            quickSort(arr, low, partitionIndex - 1, startTime);
            quickSort(arr, partitionIndex + 1, high, startTime);
        }
    }

    private int partition(int[] arr, int low, int high, long startTime) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            accessCount++;

            timeExecuted = (System.nanoTime() - startTime) / 1e6;
            sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted);

            if (arr[j] < pivot) {
                i++;

                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                swapCount++;

                notifyDisplay();
            }

            comparisons++;
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        swapCount++;

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
