package GUI.Component;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

import utils.ResourceLoader;
import utils.SharedResource;

public class PopupMenu extends JPanel {
    private JButton mainButton;
    private JLabel textLabel, arrowLabel;
    private JPanel menuPanel;
    public static ArrayList<PopupItem> items;
    private boolean isMenuVisible = false;

    public PopupMenu() {
        setLayout(new BorderLayout());

        items = new ArrayList<>();
        items.add(new PopupItem("Deck 1"));

        mainButton = new JButton();
        mainButton.setLayout(new BorderLayout());
        mainButton.setPreferredSize(new Dimension(490, 50));
        mainButton.setBackground(SharedResource.SIAMESE_BRIGHT);
        mainButton.setFocusPainted(false);
        mainButton.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        textLabel = new JLabel("Select Decks");
        textLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        arrowLabel = new JLabel(ResourceLoader.loadPicture("assets/Component/DownArrow.png",24,18), SwingConstants.RIGHT);
        arrowLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));

        mainButton.add(textLabel, BorderLayout.WEST);
        mainButton.add(arrowLabel, BorderLayout.EAST);
        mainButton.addActionListener(e -> toggleMenu());

        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(0, 1));
        menuPanel.setBackground(Color.WHITE);
        menuPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        menuPanel.setVisible(false);
        updateMenuPanel();

        add(mainButton, BorderLayout.NORTH);
        add(menuPanel, BorderLayout.CENTER);
    }

    public void toggleMenu() {
        isMenuVisible = !isMenuVisible;
        menuPanel.setVisible(isMenuVisible);
        revalidate();
        repaint();
    }

    public void updateMenuPanel() {
        menuPanel.removeAll();
        for (int i = 0; i < items.size(); i++) {
            menuPanel.add(items.get(i));
        }
        revalidate();
        repaint();
    }

    private void setupButton(JButton button) {
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public void updateMainButton(String value) {
        textLabel.setText(value);
    }

    public void addItem(PopupItem item) {
        items.add(item);
        updateMenuPanel();
    }

    public ArrayList<PopupItem> getItems() {
        return new ArrayList<>(items);
    }
}
