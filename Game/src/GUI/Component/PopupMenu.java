package GUI.Component;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

        comboBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Component c = (Component) e.getSource();
                int width = comboBox.getWidth();setBackground(SharedResource.SIAMESE_BRIGHT);
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

    //TODO:Pls Refactor this code to component
    private JPanel createItemPanel(int index, String name) {
        JPanel panel = new JPanel(new BorderLayout()); 
        panel.setPreferredSize(new Dimension(480, 40)); 
        panel.setBackground(SharedResource.SIAMESE_BRIGHT);
        panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        namePanel.setOpaque(false);
        JLabel nameLabel = new JLabel(name);
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0)); 
        namePanel.add(nameLabel);

        JPanel iconPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        iconPanel.setOpaque(false);
    
        JLabel editIcon = new JLabel("🖊️");
        JLabel deleteIcon = new JLabel("❌");
        editIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deleteIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));

        editIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String newName = JOptionPane.showInputDialog(null, "Edit Name:", name);
                System.out.println(newName);
                if (newName != null && !newName.trim().isEmpty()) {
                    items.set(index, newName);
                    updatePopupMenu();
                    updateComboBoxSelection(newName);
                }
            }
        });

        deleteIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Delete " + name + "?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    items.remove(index);
                    updatePopupMenu();
                    updateComboBoxSelection("Select Decks");
                }
            }
        });

        iconPanel.add(editIcon);
        iconPanel.add(deleteIcon);
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                updateComboBoxSelection(name);
                popupMenu.setVisible(false);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                panel.setBackground(new Color(230, 230, 230));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panel.setBackground(Color.WHITE);
            }
        });

        panel.add(namePanel, BorderLayout.WEST);
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
