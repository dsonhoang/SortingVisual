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
            int s = random.nextInt(1000000);
            while (s <= 0) {
                s = random.nextInt();
            }
            randomArray[i] = s;
        }

        return randomArray;
    }

    public List<String> getListAlgorithm() {
        List<String> list = new ArrayList<String>();
        list.add("Binary Insertion Sort");
        list.add("Bitonic Sort (Only works properly with arrays whose number of elements is a power of 2)");
        list.add("Block Merge Sort");
        list.add("Bubble Sort");
        list.add("Cocktail Shaker Sort");
        list.add("Comb Sort");
        list.add("Custom Sort");
        list.add("Cycle Sort");
        list.add("Gnome Sort");
        list.add("Heap Sort");
        list.add("Insertion Sort");
        list.add("Merge Sort");
        list.add("Odd Even Sort");
        list.add("Quick Sort");
        list.add("Radix Sort");
        list.add("Selection Sort");
        list.add("Shell Sort");
        list.add("Smooth Sort");
        list.add("Standard Sort");
        list.add("Tim Sort");
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
            case "Shell Sort":
                return new ShellSort(values);
            case "Radix Sort":
                return new RadixSort(values);
            case "Custom Sort":
                return new CustomSort(values);
            case "Binary Insertion Sort":
                return new BinaryInsertionSort(values);
            case "Bitonic Sort (Only works properly with arrays whose number of elements is a power of 2)":
                return new BitonicSort(values);
            case "Block Merge Sort":
                return new BlockMergeSort(values);
            case "Cocktail Shaker Sort":
                return new CocktailShakerSort(values);
            case "Comb Sort":
                return new CombSort(values);
            case "Cycle Sort":
                return new CycleSort(values);
            case "Gnome Sort":
                return new GnomeSort(values);
            case "Odd Even Sort":
                return new OddEvenSort(values);
            case "Tim Sort":
                return new TimSort(values);
            case "Smooth Sort":
                return new SmoothSort(values);
            case "Standard Sort":
                return new StandardSort(values);
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
