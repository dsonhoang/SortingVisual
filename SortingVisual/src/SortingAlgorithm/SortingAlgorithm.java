package SortingAlgorithm;

import GUI.SortingDisplay;

public interface SortingAlgorithm {
    void sort();

    void setValues(int[] values);

    int[] getValues();

    SortingDisplay getSortingDisplay();
    boolean isRunning();
}
