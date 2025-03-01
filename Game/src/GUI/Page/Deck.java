package GUI.Page;

import java.awt.*;
import javax.swing.*;

public class Deck {
    private JFrame fr;
    private JPanel paLeft, paRight;
    private JLabel title;

    public Deck() {
        fr = new JFrame("Your Deck");
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Title Label
        title = new JLabel("Your Deck");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        fr.add(title, gbc);

        // Load and scale images
        ImageIcon icon = new ImageIcon("./Game/Src/assets/catmemetest.jpg"); // Change to your image path
        Image img = icon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(img);

        // Left Panel (3x2 grid with images)
        paLeft = new JPanel(new GridLayout(3, 2, 10, 10));
        for (int i = 1; i <= 6; i++) {
            paLeft.add(new JButton(scaledIcon));
        }
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.3;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        fr.add(paLeft, gbc);

        // Right Panel (4x3 grid with images)
        paRight = new JPanel(new GridLayout(4, 4, 10, 10));
        for (int i = 1; i <= 16; i++) {
            paRight.add(new JButton(scaledIcon));
        }
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.7;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        fr.add(paRight, gbc);

        // Frame Settings
        fr.setSize(1920, 1080);
        fr.setVisible(true);
    }

    public static void main(String[] args) {
        new Deck();
    }
}

/* if We have image per button, use this code
package GUI.Page;

import java.awt.*;
import javax.swing.*;

public class Deck {
    private JFrame fr;
    private JPanel paLeft, paRight;
    private JLabel title;

    public Deck() {
        fr = new JFrame("Your Deck");
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Title Label
        title = new JLabel("Your Deck");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        fr.add(title, gbc);

        // Custom image names for buttons (replace with your actual names)
        String[] imageNames = {
            "cat.jpg", "dog.jpg", "bird.jpg", "fish.jpg", "rabbit.jpg", "hamster.jpg",
            "tiger.jpg", "lion.jpg", "elephant.jpg", "giraffe.jpg", "zebra.jpg", "monkey.jpg",
            "panda.jpg", "koala.jpg", "kangaroo.jpg", "wolf.jpg", "bear.jpg", "fox.jpg", "deer.jpg", "owl.jpg", "duck.jpg", "goose.jpg"
        };

        // Left Panel (3x2 grid with images)
        paLeft = new JPanel(new GridLayout(3, 2, 10, 10));
        for (int i = 0; i < 6; i++) {
            // Load a different image for each button from the imageNames array
            ImageIcon icon = new ImageIcon("./Game/Src/assets/" + imageNames[i]); // Update with custom image names
            Image img = icon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(img);
            paLeft.add(new JButton(scaledIcon));
        }
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.3;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        fr.add(paLeft, gbc);

        // Right Panel (4x4 grid with images)
        paRight = new JPanel(new GridLayout(4, 4, 10, 10));
        for (int i = 6; i < 22; i++) {
            // Load a different image for each button from the imageNames array
            ImageIcon icon = new ImageIcon("./Game/Src/assets/" + imageNames[i]); // Update with custom image names
            Image img = icon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(img);
            paRight.add(new JButton(scaledIcon));
        }
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.7;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        fr.add(paRight, gbc);

        // Frame Settings
        fr.setSize(1920, 1080);
        fr.setLocationRelativeTo(null); // Center the window on the screen
        fr.setVisible(true);
    }

    public static void main(String[] args) {
        new Deck();
    }
}

 */