package SortingAlgorithm;

import GUI.SortingDisplay;

import java.util.ArrayList;
import java.util.List;

public class SmoothSort extends SortingAbstract {
    private int[] values;
    private SortingDisplay sortingDisplay;
    private List<Integer> markedColumns;

    public SmoothSort(int[] values) {
        super();
        this.values = values;
        this.sortingDisplay = new SortingDisplay(values);
        this.markedColumns = new ArrayList<>();
    }

    @Override
    public void sort() {
        isRunning = true;
        long startTime = System.nanoTime();

        smoothsort(values);

        long endTime = System.nanoTime();
        timeExecuted = (endTime - startTime) / 1e6;

        isRunning = false;
        sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted, markedColumns);
        sortingDisplay.setSorted(true);

        notifyDisplay();
    }

    private void smoothsort(int[] arr) {
        int n = arr.length;
        Heap heap = new Heap(arr);

        int p = 1;
        int r = 0;
        while (p < n) {
            if ((heap.compare(p - 1, p) || p + 1 < n && heap.compare(p, p + 1))) {
                if (heap.compare(p - 1, p + 1)) {
                    heap.swap(p - 1, p + 1);
                } else {
                    heap.swap(p, p + 1);
                }
            }
            heap.trinkle(r, p);
            p += 2;
            r++;
        }

        while (r > 0) {
            r--;
            heap.trinkle(r, n);
        }
    }

    private class Heap {
        private int[] arr;
        private int[] tree;
        private int[] preds;

        public Heap(int[] arr) {
            this.arr = arr;
            this.tree = new int[arr.length];
            this.preds = new int[arr.length];
            for (int i = 0; i < arr.length; i++) {
                tree[i] = i;
                preds[i] = i - 1;
            }
        }

        public boolean compare(int i, int j) {
            accessCount += 2;
            comparisons++;
            return arr[tree[i]] > arr[tree[j]];
        }

        public void swap(int i, int j) {
            int temp = tree[i];
            tree[i] = tree[j];
            tree[j] = temp;

            preds[tree[i]] = i;
            preds[tree[j]] = j;

            swapCount++;
            markedColumns.clear();
            markedColumns.add(i);
            markedColumns.add(j);
            sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted, markedColumns);
            notifyDisplay();
        }

        public void sift(int r, int p) {
            while (r > 0) {
                int q = preds[p];
                accessCount++;
                comparisons++;
                if (!compare(q, p)) {
                    break;
                }
                swap(q, p);
                p = q;
                r--;
            }
        }

        public void sift0(int p) {
            int q;
            while (p > 0 && compare(q = preds[p], p)) {
                swap(q, p);
                p = q;
            }
        }

        public void trinkle(int r, int p) {
            int q, pp = p;
            while (r > 0) {
                while ((p & 1) == 0) {
                    p >>= 1;
                    r--;
                }
                q = preds[p];
                accessCount++;
                comparisons++;
                if (q != pp && !compare(q, p)) {
                    sift(r, pp);
                    break;
                }
                sift0(p);
                p = q;
                r--;
            }
            sift(r, pp);
        }
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
