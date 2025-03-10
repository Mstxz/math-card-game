package GUI.Component;

import java.awt.*;
import javax.swing.*;

public class PlayerChoose extends JPanel {
    private JLabel imageLabel;
    private JLabel nameLabel;
    private JLabel statusLabel;

    public PlayerChoose(ImageIcon image, String name, boolean isReady) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);
        //setBackground(new Color(210, 200, 180));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        imageLabel = new JLabel();
        imageLabel.setIcon(image);
        imageLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(imageLabel);

        nameLabel = new JLabel(name);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nameLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(nameLabel);

        statusLabel = new JLabel(isReady ? "Ready" : "Not Ready");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setForeground(isReady ? Color.GREEN : Color.RED);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 12));
        statusLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(statusLabel);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Player Card Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 300);

        ImageIcon playerImage = new ImageIcon("your-image-path.png");
        PlayerChoose card = new PlayerChoose(playerImage, "Soda Mun Za 007", true);

        frame.add(card);
        frame.setVisible(true);
    }
}
