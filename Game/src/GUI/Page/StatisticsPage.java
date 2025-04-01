package GUI.Page;

import GUI.Component.ExitButton;
import GUI.Component.OmmThukStudentCard;
import GUI.Router;
import utils.SharedResource;

import javax.swing.*;
import java.awt.*;

public class StatisticsPage extends Page {
    private JPanel topPanel;
    private JPanel contentPanel;
    private final JLabel titleLabel = new JLabel("Statistics");
    private ExitButton exitButton;
    public StatisticsPage() {
        mainPanel.setBackground(SharedResource.SIAMESE_BRIGHT);

        exitButton = new ExitButton("MainMenu");
        topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setPreferredSize(new Dimension(Router.getMainFrame().getWidth(), 150));
        topPanel.setBackground(SharedResource.SIAMESE_BRIGHT);
        JPanel fknblank = new JPanel();
        fknblank.setPreferredSize(new Dimension(exitButton.getPreferredSize().width, exitButton.getPreferredSize().height));
        fknblank.setBackground(SharedResource.SIAMESE_BRIGHT);

        topPanel.add(exitButton, BorderLayout.WEST);
        topPanel.add(titleLabel, BorderLayout.CENTER);
        topPanel.add(fknblank, BorderLayout.EAST);

        contentPanel = new JPanel();
        contentPanel.setBackground(SharedResource.TRANSPARENT);
        contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        contentPanel.add(new OmmThukStudentCard("Mstxz", "Mystyr")); //username, profile path

        titleLabel.setFont(SharedResource.getCustomSizeFont(96));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(contentPanel);
    }
}
