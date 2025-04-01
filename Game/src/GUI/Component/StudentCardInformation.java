package GUI.Component;

import utils.SharedResource;

import javax.swing.*;
import java.awt.*;

public class StudentCardInformation extends JPanel {
    private JLabel titleLabel;
    private JLabel informationLabel;
    public StudentCardInformation(Dimension d, String title, String information) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(d);
        setBackground(SharedResource.SIAMESE_LIGHT);
        add(Box.createRigidArea(new Dimension(20, 0)));

        titleLabel = new JLabel(title);
        titleLabel.setFont(SharedResource.getCustomSizeFont(24));
        titleLabel.setForeground(SharedResource.SIAMESE_BASE);

        informationLabel = new JLabel(information);
        informationLabel.setFont(SharedResource.getCustomSizeFont(64));
        informationLabel.setForeground(Color.BLACK);

        add(titleLabel);
        add(informationLabel);
    }
}
