package GUI.Component;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.*;
import GUI.Component.PopupRoundedBorder;

public class PopupCreate extends JPanel implements ActionListener {
    private JFrame fr;
    private JPanel PopupPanel, ButtonPanel, TextPanel;
    private JButton ConfirmButton, CancelButton;
    private JTextField renameField;

    public PopupCreate() {
        setLayout(new BorderLayout());

        PopupPanel = new JPanel(new BorderLayout());
        PopupPanel.setPreferredSize(new Dimension(400, 150));
        PopupPanel.setBackground(new Color(200, 220, 240));
        PopupPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        TextPanel = new JPanel();
        TextPanel.setLayout(new BoxLayout(TextPanel, BoxLayout.X_AXIS));
        TextPanel.setBackground(new Color(200, 220, 240));
        TextPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 5, 10));

        renameField = new JTextField("Deck Name", 20);
        renameField.setFont(new Font("SansSerif", Font.BOLD, 20));
        renameField.setForeground(new Color(200, 220, 240));
        renameField.setBackground(new Color(230, 240, 250));
        renameField.setMargin(new Insets(10, 10, 10, 10));
        renameField.setPreferredSize(new Dimension(300, 40));
        renameField.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        renameField.setOpaque(true);

        TextPanel.add(renameField);
        PopupPanel.add(TextPanel, BorderLayout.NORTH);

        ButtonPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        ButtonPanel.setBackground(new Color(200, 220, 240));
        ButtonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        ConfirmButton = new JButton("Confirm");
        styleButton(ConfirmButton);
        CancelButton = new JButton("Cancel");
        styleButton(CancelButton);

        ButtonPanel.add(ConfirmButton);
        ButtonPanel.add(CancelButton);

        PopupPanel.add(ButtonPanel, BorderLayout.SOUTH);

        this.add(PopupPanel, BorderLayout.CENTER);

        /*add events here*/
        ConfirmButton.addActionListener(this);
        CancelButton.addActionListener(this);
    }

    private Border createRoundedBorder(Color borderColor, int thickness, int radius) {
        return new PopupRoundedBorder(borderColor, thickness, radius);
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("SansSerif", Font.BOLD, 20));
        button.setForeground(new Color(163, 190, 208));
        button.setBackground(new Color(230, 240, 250));
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setPreferredSize(new Dimension(0, 50));
        button.setBorder(createRoundedBorder(new Color(102, 142, 169), 2, 10));
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ConfirmButton) {
        } else if (e.getSource() == CancelButton) {
            fr.dispose();
        }
    }
}
