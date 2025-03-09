package GUI.Component;

import java.awt.*;
import javax.swing.*;

public class PlayerChoose extends JPanel {
    private JLabel imageLabel;
    private JLabel nameLabel;
    private JLabel statusLabel;

    public PlayerChoose(ImageIcon image, String name, boolean isReady) {
        // Using a BoxLayout to stack the components vertically
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(210, 200, 180));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Image Label
        imageLabel = new JLabel();
        imageLabel.setIcon(image);
        imageLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(imageLabel);

        // Name Label
        nameLabel = new JLabel(name);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nameLabel.setAlignmentX(CENTER_ALIGNMENT); // Ensure it's centered
        add(nameLabel);

        // Status Label
        statusLabel = new JLabel(isReady ? "Ready" : "Not Ready");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setForeground(isReady ? Color.GREEN : Color.RED);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 12));
        statusLabel.setAlignmentX(CENTER_ALIGNMENT); // Ensure it's centered
        add(statusLabel);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Player Card Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 300);

        ImageIcon playerImage = new ImageIcon("your-image-path.png"); // Replace with your image path
        PlayerChoose card = new PlayerChoose(playerImage, "Soda Mun Za 007", true);

        frame.add(card);
        frame.setVisible(true);
    }
}
