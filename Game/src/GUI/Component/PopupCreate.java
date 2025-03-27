package GUI.Component;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PopupCreate extends JPanel implements ActionListener {
    private JPanel PopupPanel, ButtonPanel;
    private JButton ConfirmButton, CancelButton;
    private JTextField renameField;

    public PopupCreate() {
        setLayout(new BorderLayout());

        PopupPanel = new JPanel(new GridLayout(2, 1));
        PopupPanel.setPreferredSize(new Dimension(400, 100));

        renameField = new JTextField(20);
        ButtonPanel = new JPanel(new FlowLayout());
        ConfirmButton = new JButton("Confirm");
        CancelButton = new JButton("Cancel");

        ButtonPanel.add(ConfirmButton);
        ButtonPanel.add(CancelButton);

        PopupPanel.add(renameField);
        PopupPanel.add(ButtonPanel);

        this.add(PopupPanel, BorderLayout.CENTER);

        PopupPanel.setOpaque(true);

        /*add events here*/
        ConfirmButton.addActionListener(this);
        CancelButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == ConfirmButton) {
            System.out.println("Confirm save or smth");
        }
        else if(e.getSource() == CancelButton) {
            System.out.println("Cancel lol");
        }
    }
}
