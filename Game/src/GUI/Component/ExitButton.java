package GUI.Component;

import GUI.Router;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitButton extends JButton implements ActionListener {
    private String route;
    public ExitButton(String route) {
        super("< Exit");
        this.route = route;
        setPreferredSize(new Dimension(100, 40));
        setForeground(Color.BLACK);
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFont(new Font("Arial", Font.BOLD, 16));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Router.setRoute(route, null);
    }
}
