package GUI.Component;

import Gameplay.Card;
import Gameplay.Deck;
import utils.ResourceLoader;
import utils.SharedResource;
import utils.UIManager.RoundPanelUI;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicPanelUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class PopupItem extends JPanel implements ActionListener, MouseListener {
    private String fileName;
    public static PopupMenu menu;
    public static TempDeckZone deckZone;

    private JButton nameButton;
    private JButton editButton;
    private JButton deleteButton;
    private int index;

    public PopupItem(String name,int index) {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(360, 40));
        this.setBackground(SharedResource.SIAMESE_BRIGHT);
        this.setBorder(new MatteBorder(0,0,3,0,SharedResource.SIAMESE_DARK));
        this.fileName = name;
        this.index = index;

        nameButton = new JButton(name);
        nameButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        nameButton.setContentAreaFilled(false);
        nameButton.setFocusPainted(false);
        nameButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        nameButton.addMouseListener(this);

        JPanel iconPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 1));
        iconPanel.setOpaque(false);

        this.add(nameButton, BorderLayout.WEST);
        this.add(iconPanel, BorderLayout.EAST);

        this.addMouseListener(this);
    }

    private void setupButton(JButton button) {
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }


    public void setNotLast(){
        this.setBorder(new MatteBorder(0,0,3,0,SharedResource.SIAMESE_DARK));
    }

    public void setLast(){
        this.setBorder(null);
    }

    public String getFileName() {
        return fileName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        if (e.getSource().equals(editButton)){
//            String newName = JOptionPane.showInputDialog(null, "Edit Name:", name);
//            if (newName != null && !newName.trim().isEmpty()) {
//                PopupMenu.items.add(new PopupItem(newName));
//                menu.updateMenuPanel();
//                menu.updateMainButton(newName);
//            }
//        }
//        else if (e.getSource().equals(deleteButton)){
//            int confirm = JOptionPane.showConfirmDialog(null, "Delete " + name + "?", "Confirm", JOptionPane.YES_NO_OPTION);
//            if (confirm == JOptionPane.YES_OPTION) {
//                PopupMenu.items.remove(index);
//                menu.updateMenuPanel();
//                menu.updateMainButton("Select Decks");
//            }
//        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(this)||e.getSource().equals(nameButton)){
            menu.setSelectedIndex(index);
            menu.toggleMenu();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }
}
