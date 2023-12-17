package SortingAlgorithm;

import GUI.SortingDisplay;

public class CustomSort extends SortingAbstract implements SortingAlgorithm {
       private int[] values;
       private SortingDisplay sortingDisplay;

       public CustomSort(int[] values, boolean isVisual) {
           super(isVisual);
              this.values = values;
              this.sortingDisplay = new SortingDisplay(values);
       }

       @Override
       public void sort() {
              isRunning = true;
              long startTime = System.nanoTime();
              int n = values.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                accessCount++;
                if (values[j] > values[j + 1]) {
                    int temp = values[j];
                    values[j] = values[j + 1];
                    values[j + 1] = temp;
                    swapCount++;
                    timeExecuted = (System.nanoTime() - startTime) / 1e6;
                    sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted);
                    notifyDisplay();
                }
                comparisons++;
            }
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
           if (!this.isVisual) {
               return;
           }
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