package SortingAlgorithm;

import javax.swing.*;
import java.io.*;

public class TestCustomSort extends JFrame {

    public static void addCode() {
        String filePath = "D:\\git_project\\SortingVisual\\SortingVisual\\src\\SortingAlgorithm\\CustomSort.java";
        String searchString = "/*YOUR CODE HERE*/";
        String replacementString =
                                "for (int i = 1; i < n; i++) {\n" +
                        "            System.out.println();\n" +
                        "            int key = values[i];\n" +
                        "            int j = i - 1;\n" +
                        "\n" +
                        "            while (j >= 0 && values[j] > key) {\n" +
                        "                accessCount += 2;\n" +
                        "                values[j + 1] = values[j];\n" +
                        "                j = j - 1;\n" +
                        "                swapCount++;\n" +
                        "                comparisons++;\n" +
                        "                timeExecuted = (System.nanoTime() - startTime) / 1e6;\n" +
                        "                sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted);\n" +
                        "                notifyDisplay();\n" +
                        "            }\n" +
                        "            values[j + 1] = key;\n" +
                        "            swapCount++;\n" +
                        "            timeExecuted = (System.nanoTime() - startTime) / 1e6;\n" +
                        "            sortingDisplay.setStatistics(accessCount, comparisons, swapCount, timeExecuted);\n" +
                        "            notifyDisplay();\n" +
                        "        }\n";
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            StringBuilder newContent = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains(searchString)) {
                    line = line.replace(searchString, replacementString);
                }

                // Append the line to the new content
                newContent.append(line).append(System.lineSeparator());
            }

            // Close the reader
            bufferedReader.close();

            FileWriter fileWriter = new FileWriter(filePath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(newContent.toString());

            // Close the writer
            bufferedWriter.close();

            System.out.println("String replaced successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}