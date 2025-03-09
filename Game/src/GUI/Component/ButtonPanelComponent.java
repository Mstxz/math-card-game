package GUI.Component;

import java.awt.*;
import javax.swing.*;

public class ButtonPanelComponent extends JPanel {
    private JButton deckButton, readyButton;

    public ButtonPanelComponent() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        
        deckButton = new JButton("Decks");
        readyButton = new JButton("Ready");

        deckButton.setBackground(Color.BLUE);
        deckButton.setForeground(Color.WHITE);
        readyButton.setBackground(Color.BLUE);
        readyButton.setForeground(Color.WHITE);

        deckButton.setPreferredSize(new Dimension(200, 40));
        readyButton.setPreferredSize(new Dimension(200, 40));

        add(deckButton);
        add(readyButton);
    }
}
