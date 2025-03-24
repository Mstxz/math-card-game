package GUI.Component;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import utils.SharedResource;

public class PopupMenu extends JPanel {
    private JButton mainButton;
    private JLabel textLabel, arrowLabel;
    private JPanel menuPanel;
    private ArrayList<String> items;
    private boolean isMenuVisible = false;

    public PopupMenu() {
        setLayout(new BorderLayout());

        items = new ArrayList<>();
        items.add("Deck 1");
        items.add("Deck 2");
        items.add("Deck 3");

        mainButton = new JButton();
        mainButton.setLayout(new BorderLayout());
        mainButton.setPreferredSize(new Dimension(490, 50));
        mainButton.setBackground(SharedResource.SIAMESE_BRIGHT);
        mainButton.setFocusPainted(false);
        mainButton.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        textLabel = new JLabel("Select Decks");
        textLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        arrowLabel = new JLabel("⏷", SwingConstants.RIGHT);
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

    private void toggleMenu() {
        isMenuVisible = !isMenuVisible;
        menuPanel.setVisible(isMenuVisible);
        revalidate();
        repaint();
    }

    private void updateMenuPanel() {
        menuPanel.removeAll();
        for (int i = 0; i < items.size(); i++) {
            menuPanel.add(createItemPanel(i, items.get(i)));
        }
        revalidate();
        repaint();
    }

    private JPanel createItemPanel(int index, String name) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(480, 40));
        panel.setBackground(SharedResource.SIAMESE_BRIGHT);
        panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        JButton nameButton = new JButton(name);
        nameButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        nameButton.setContentAreaFilled(false);
        nameButton.setFocusPainted(false);
        nameButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        nameButton.addActionListener(e -> {
            updateMainButton(name);
            toggleMenu();
        });

        JButton editButton = new JButton("\u270F"); // ✏
        JButton deleteButton = new JButton("\uD83D\uDDD1"); // 🗑

        setupButton(editButton);
        setupButton(deleteButton);

        editButton.addActionListener(e -> {
            String newName = JOptionPane.showInputDialog(null, "Edit Name:", name);
            if (newName != null && !newName.trim().isEmpty()) {
                items.set(index, newName);
                updateMenuPanel();
                updateMainButton(newName);
            }
        });

        deleteButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(null, "Delete " + name + "?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                items.remove(index);
                updateMenuPanel();
                updateMainButton("Select Decks");
            }
        });

        JPanel iconPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        iconPanel.setOpaque(false);
        iconPanel.add(editButton);
        iconPanel.add(deleteButton);

        panel.add(nameButton, BorderLayout.WEST);
        panel.add(iconPanel, BorderLayout.EAST);

        return panel;
    }

    private void setupButton(JButton button) {
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    private void updateMainButton(String value) {
        textLabel.setText(value);
    }

    public void addItem(String item) {
        items.add(item);
        updateMenuPanel();
    }

    public ArrayList<String> getItems() {
        return new ArrayList<>(items);
    }
}
