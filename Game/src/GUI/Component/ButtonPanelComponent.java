package GUI.Component;

import utils.SharedResource;
import utils.UIManager.ButtonUI;
import utils.UIManager.RoundPanelUI;

import java.awt.*;
import javax.swing.*;

public class ButtonPanelComponent extends JPanel {
    private JButton deckButton, readyButton;

    public ButtonPanelComponent() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        
        deckButton = new JButton("Decks");
        readyButton = new JButton("Ready");
        deckButton.setUI(new ButtonUI());
        readyButton.setUI(new ButtonUI());
        readyButton.setEnabled(false);

        deckButton.setPreferredSize(new Dimension(240, 80));
        readyButton.setPreferredSize(new Dimension(240, 80));

        add(deckButton);
        add(readyButton);
        this.setUI(new RoundPanelUI(SharedResource.SIAMESE_LIGHT,30,30,false,false,true,true));
    }

    public JButton getDeckButton() {
        return deckButton;
    }

    public JButton getReadyButton() {
        return readyButton;
    }
}
