package GUI.Component;

import java.awt.*;
import javax.swing.*;

public class ButtonPanelComponent extends JPanel {
    private JButton deckButton, readyButton;

    public ButtonPanelComponent() {
        setLayout(new FlowLayout());

        deckButton = new JButton("Decks");
        readyButton = new JButton("Ready");

        add(deckButton);
        add(readyButton);
    }
}
