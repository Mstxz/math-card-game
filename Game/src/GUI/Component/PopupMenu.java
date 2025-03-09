package GUI.Component;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import utils.SharedResource;

public class PopupMenu extends JPanel {
    private JComboBox<String> comboBox;
    private JButton button1, button2;

    public PopupMenu() {
        setLayout(new BorderLayout());

        comboBox = new JComboBox<>(new String[]{"Deck 1", "Deck 2", "Deck 3"});
        comboBox.setRenderer(new ColoredComboBoxRenderer());
        comboBox.setBorder(BorderFactory.createTitledBorder("Your Decks"));
        comboBox.setBackground(SharedResource.SIAMESE_BRIGHT);
        comboBox.setPreferredSize(new Dimension(500, 40));
        comboBox.setBorder(BorderFactory.createLineBorder(SharedResource.SIAMESE_BASE, 1, true));

        button1 = new JButton("Delete");
        button2 = new JButton("Edit");

        //ActionListener Delete
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) comboBox.getSelectedItem();
                if (selectedItem != null) {
                    ((DefaultComboBoxModel<String>) comboBox.getModel()).removeElement(selectedItem);
                    JOptionPane.showMessageDialog(null, "Deleted: " + selectedItem);
                }
            }
        });

        //ActionListener Edit
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) comboBox.getSelectedItem();
                if (selectedItem != null) {
                    String newName = JOptionPane.showInputDialog(null, "Edit name for " + selectedItem, selectedItem);
                    if (newName != null && !newName.trim().isEmpty()) {
                        int index = comboBox.getSelectedIndex();
                        comboBox.getModel().setSelectedItem(newName);
                        ((DefaultComboBoxModel<String>) comboBox.getModel()).insertElementAt(newName, index);
                        ((DefaultComboBoxModel<String>) comboBox.getModel()).removeElementAt(index + 1);
                    }
                }
            }
        });

        JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.add(button1);
        popupMenu.add(button2);

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboBox.isPopupVisible()) {
                    popupMenu.show(comboBox, comboBox.getX(), comboBox.getY() + comboBox.getHeight());
                }
            }
        });

        JPanel comboPanel = new JPanel();
        comboPanel.add(comboBox);

        add(comboPanel, BorderLayout.NORTH);
    }
}
