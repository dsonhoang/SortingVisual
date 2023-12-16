package SortingAlgorithm;

public abstract class SortingAbstract implements SortingAlgorithm {
    protected int accessCount;
    protected int comparisons;
    protected int swapCount;
    protected double timeExecuted;
    protected boolean isRunning;
    public static final int DELAY = 40;

    public SortingAbstract() {
        accessCount = 0;
        comparisons = 0;
        swapCount = 0;
        timeExecuted = 0;
        isRunning = false;
    }

    public void reset() {
        accessCount = 0;
        comparisons = 0;
        swapCount = 0;
        timeExecuted = 0;
    }

    public boolean isRunning() {
        return isRunning;
    }
}
