//เดี๋ยวมาทำต่อ
package GUI.Component;

import java.awt.*;
import javax.swing.*;

public class PopupCreate extends JPanel{
    private JPanel PopupPanel,ButtonPanel;
    private JButton ConfirmButton,CancelButton;
    private JTextField TextField;
    public PopupCreate() {
        PopupPanel = new JPanel(new GridLayout(2,1));

        ButtonPanel = new JPanel(new FlowLayout());
        ConfirmButton = new JButton("Confirm");
        CancelButton = new JButton("Cancel");
        ButtonPanel.add(ConfirmButton);
        ButtonPanel.add(CancelButton);

        PopupPanel.add(TextField);
        PopupPanel.add(ButtonPanel);

        PopupPanel.setOpaque(true);

    }
}