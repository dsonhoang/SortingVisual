package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CustomCodePanel extends JPanel {
    private JTextArea editableArea;
    private JButton saveButton;
    private JButton backButton;

    public CustomCodePanel() {
        setLayout(new BorderLayout());

        editableArea = new JTextArea();
        editableArea.setBackground(new Color(255, 255, 255));
        editableArea.setFont(new Font("Segoe UI", 1, 16));

        JScrollPane scroller = new JScrollPane(editableArea);
        scroller.setPreferredSize(new Dimension(300, 600));

        saveButton = ElementCreater.createButton("Save", 12);
        saveButton.setPreferredSize(new Dimension(60, 30));

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveCode();
                JOptionPane.showMessageDialog(null, "Saved successfully!", "Save Notification", JOptionPane.INFORMATION_MESSAGE);
                PanelManager.showPanel("ChoicePanel");
            }
        });


        backButton = ElementCreater.createButton("Back", 12);
        backButton.setPreferredSize(new Dimension(60, 30));
        backButton.addActionListener(e -> PanelManager.showPanel("ChoicePanel"));

        JPanel footer = new JPanel();
        footer.add(saveButton);
        footer.add(backButton);

        add(scroller, BorderLayout.NORTH);
        add(footer, BorderLayout.SOUTH);

        loadCode();
    }

    private void saveCode() {
        String filePath = ".\\SortingVisual\\src\\SortingAlgorithm\\CustomSort.java";
        try {
            String fileContent = Files.readString(Path.of(filePath));
            String text = editableArea.getText();
            String[] arr = splitCode(fileContent);

            String newFileContent = arr[0] + "\n" + text + "\n" + arr[2];
            Files.write(Path.of(filePath), newFileContent.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadCode() {
        String filePath = ".\\SortingVisual\\src\\SortingAlgorithm\\CustomSort.java";
        try {
            String fileContent = Files.readString(Path.of(filePath));

            String[] arr = splitCode(fileContent);
            editableArea.setText(arr[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[] splitCode(String fileContent) {
        String[] result = new String[3];
        String startTag = "        long startTime = System.nanoTime();";
        String endTag = "        long endTime = System.nanoTime();";
        int startIndex = fileContent.indexOf(startTag) + startTag.length();
        int endIndex = fileContent.indexOf(endTag);

        if (startIndex != -1 && endIndex != -1) {
            result[0] = fileContent.substring(0, startIndex);
            result[1] = fileContent.substring(startIndex, endIndex);
            result[2] = fileContent.substring(endIndex, fileContent.length());
        } else {
            return null;
        }

        return result;
    }
}
