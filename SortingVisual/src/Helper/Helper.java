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

    public SortingAlgorithm getSortSelected(String algorithmName, int[] values, boolean isVisual) {
        switch (algorithmName) {
            case "Bubble Sort":
                return new BubbleSort(values, isVisual);
            case "Insertion Sort":
                return new InsertionSort(values, isVisual);
            case "Selection Sort":
                return new SelectionSort(values, isVisual);
            case "Quick Sort":
                return new QuickSort(values, isVisual);
            case "Merge Sort":
                return new MergeSort(values, isVisual);
            case "Heap Sort":
                return new HeapSort(values, isVisual);
            case "Shell Sort":
                return new ShellSort(values, isVisual);
            case "Radix Sort":
                return new RadixSort(values, isVisual);
            case "Custom Sort":
                return new CustomSort(values, isVisual);
            case "Binary Insertion Sort":
                return new BinaryInsertionSort(values, isVisual);
            case "Bitonic Sort (Only works properly with arrays whose number of elements is a power of 2)":
                return new BitonicSort(values, isVisual);
            case "Block Merge Sort":
                return new BlockMergeSort(values, isVisual);
            case "Cocktail Shaker Sort":
                return new CocktailShakerSort(values, isVisual);
            case "Comb Sort":
                return new CombSort(values, isVisual);
            case "Cycle Sort":
                return new CycleSort(values, isVisual);
            case "Gnome Sort":
                return new GnomeSort(values, isVisual);
            case "Odd Even Sort":
                return new OddEvenSort(values, isVisual);
            case "Tim Sort":
                return new TimSort(values, isVisual);
            case "Smooth Sort":
                return new SmoothSort(values, isVisual);
            case "Standard Sort":
                return new StandardSort(values, isVisual);
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
