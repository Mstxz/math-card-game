package GUI.Component;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import utils.SharedResource;

public class PopupMenu extends JPanel {
    private JComboBox<String> comboBox;
    private JPopupMenu popupMenu;
    private ArrayList<String> items;

    public PopupMenu() {
        setLayout(new FlowLayout(FlowLayout.LEFT));

        items = new ArrayList<>();
        items.add("Deck 1");
        items.add("Deck 2");
        items.add("Deck 3");

        comboBox = new JComboBox<>(new String[]{"Select Decks"});
        comboBox.setPreferredSize(new Dimension(490, 50));
        comboBox.setBackground(SharedResource.SIAMESE_BRIGHT);
        comboBox.setEditable(false);
        comboBox.setFocusable(false);

        popupMenu = new JPopupMenu();
        updatePopupMenu();

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component c = (Component) e.getSource();
                int width = comboBox.getWidth();
                popupMenu.setPreferredSize(new Dimension(width, popupMenu.getPreferredSize().height));
                popupMenu.setPopupSize(new Dimension(width, popupMenu.getPreferredSize().height));
                popupMenu.show(c, 0, c.getHeight());
            }
        });

        add(comboBox);
    }

    private void updatePopupMenu() {
        popupMenu.removeAll();
        for (int i = 0; i < items.size(); i++) {
            popupMenu.add(createItemPanel(i, items.get(i)));
        }
        popupMenu.revalidate();
        popupMenu.repaint();
    }

    private JPanel createItemPanel(int index, String name) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(480, 40));
        panel.setBackground(SharedResource.SIAMESE_BRIGHT);
        panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        JLabel nameLabel = new JLabel(name);
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        JButton editButton = new JButton("✏");
        JButton deleteButton = new JButton("🗑");
        JButton selectButton = new JButton("Select");

        editButton.setBorderPainted(false);
        editButton.setFocusPainted(false);
        editButton.setContentAreaFilled(false);
        editButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        deleteButton.setBorderPainted(false);
        deleteButton.setFocusPainted(false);
        deleteButton.setContentAreaFilled(false);
        deleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        selectButton.setBorderPainted(false);
        selectButton.setFocusPainted(false);
        selectButton.setContentAreaFilled(false);
        selectButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newName = JOptionPane.showInputDialog(null, "Edit Name:", name);
                if (newName != null && !newName.trim().isEmpty()) {
                    items.set(index, newName);
                    updatePopupMenu();
                    updateComboBoxSelection(newName);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Delete " + name + "?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    items.remove(index);
                    updatePopupMenu();
                    updateComboBoxSelection("Select Decks");
                }
            }
        });

        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateComboBoxSelection(name);
                popupMenu.setVisible(false);
            }
        });

        JPanel iconPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        iconPanel.setOpaque(false);
        iconPanel.add(editButton);
        iconPanel.add(deleteButton);
        iconPanel.add(selectButton);

        panel.add(nameLabel, BorderLayout.WEST);
        panel.add(iconPanel, BorderLayout.EAST);

        return panel;
    }

    private void updateComboBoxSelection(String value) {
        comboBox.removeAllItems();
        comboBox.addItem(value);
        comboBox.setSelectedItem(value);
    }

    public void addItem(String item) {
        items.add(item);
        updatePopupMenu();
    }

    public ArrayList<String> getItems() {
        return new ArrayList<>(items);
    }
}
