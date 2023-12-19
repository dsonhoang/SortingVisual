package SortingAlgorithm;

public interface SortingAlgorithm {
    void sort();

    void setValues(int[] values);

    int[] getValues();

    SortingDisplay getSortingDisplay();
    double[] getStatistics();
    boolean isRunning();
}
