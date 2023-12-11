package GUI;
//Testtttttttttttttt
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Sorting Visualization");
//        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setResizable(false);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(68, 110, 213));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);

        JLabel choiceLabel = new JLabel("SELECT YOUR CHOICE");
        choiceLabel.setFont(new Font("Arial", Font.BOLD, 36));
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.NORTH;
        add(choiceLabel, c);

        JButton singleModeBtn = createStyledButton("Single Mode");
        c.gridy = 1;
        add(singleModeBtn, c);
        singleModeBtn.addActionListener(e -> {
            dispose();
            new SingleMode().setVisible(true);
        });

        JButton compareModeBtn = createStyledButton("Compare Mode");
        c.gridy = 2;
        add(compareModeBtn, c);
        compareModeBtn.addActionListener(e -> {
            dispose();
            new CompareMode().setVisible(true);
        });

        setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        button.setBackground(new Color(21, 68, 53));
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(200, 40));
        return button;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> new MainFrame());
    }
}
