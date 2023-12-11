package Helper;

import SortingAlgorithm.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Helper {
    public static int[] generateRandomIntArray(int size) {
        int[] randomArray = new int[size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            int s = random.nextInt();
            while (s <= 0) {
                s = random.nextInt();
            }
            randomArray[i] = s;
        }

        return randomArray;
    }

    public List<String> getListAlgorithm() {
        List<String> list = new ArrayList<String>();
        list.add("Bubble Sort");
        list.add("Bucket Sort");
        list.add("Counting Sort");
        list.add("Heap Sort");
        list.add("Insertion Sort");
        list.add("Merge Sort");
        list.add("Quick Sort");
        list.add("Radix Sort");
        list.add("Selection Sort");
        list.add("Shell Sort");
        list.add("Custom Sort");

        return list;
    }

    public SortingAlgorithm getSortSelected(String algorithmName, int[] values) {
        switch (algorithmName) {
            case "Bubble Sort":
                return new BubbleSort(values);
            case "Insertion Sort":
                return new InsertionSort(values);
            case "Selection Sort":
                return new SelectionSort(values);
            case "Quick Sort":
                return new QuickSort(values);
            case "Merge Sort":
                return new MergeSort(values);
            case "Heap Sort":
                return new HeapSort(values);
            case "Bucket Sort":
                return new BucketSort(values);
            case "Shell Sort":
                return new ShellSort(values);
            case "Counting Sort":
                return new CountingSort(values);
            case "Radix Sort":
                return new RadixSort(values);
            case "Custom Sort":
                return new CustomSort(values);
            default:
                return null;
        }
    }

    public boolean isSortedArray(int[] values) {
        for (int i = 0; i < values.length - 1; i++) {
            if (values[i] > values[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
