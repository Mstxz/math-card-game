package GUI.Component;

import AudioPlayer.SFXPlayer;
import GUI.Router;
import utils.SharedResource;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotLeftButton extends JButton implements ActionListener {
    private String route;
    public BotLeftButton(String route) {
        super("<");
        this.route = route;
        setPreferredSize(new Dimension(100, 40));
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFont(SharedResource.getCustomSizeFont(24));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public void cleanUp(){}

    @Override
    public void actionPerformed(ActionEvent e) {
        SFXPlayer.playSound("Game/src/assets/Audio/SFX/Button_Click.wav");
        cleanUp();
        Router.setRoute(route, null);
    }
}
