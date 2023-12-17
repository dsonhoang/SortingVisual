package SortingAlgorithm;

public abstract class SortingAbstract implements SortingAlgorithm {
    protected int accessCount;
    protected int comparisons;
    protected int swapCount;
    protected double timeExecuted;
    protected boolean isRunning;
    protected boolean isVisual;
    public static final int DELAY = 5;

    public SortingAbstract(boolean isVisual) {
        this.isVisual = isVisual;
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

    @Override
    public double[] getStatistics() {
        double[] statistics = {accessCount, comparisons, swapCount, timeExecuted};
        return statistics;
    }

    public boolean isRunning() {
        return isRunning;
    }
}
