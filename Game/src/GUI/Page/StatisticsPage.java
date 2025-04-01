package GUI.Page;

import GUI.Component.ExitButton;
import GUI.Component.OmmThukStudentCard;
import GUI.Router;
import GUI.Setting.UserPreference;
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
        topPanel.add(exitButton, BorderLayout.WEST);
        topPanel.add(titleLabel, BorderLayout.CENTER);
        topPanel.setBackground(SharedResource.SIAMESE_BRIGHT);

        contentPanel = new JPanel();
        contentPanel.setBackground(SharedResource.TRANSPARENT);
        contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        contentPanel.add(new OmmThukStudentCard(UserPreference.getInstance().getProfile().getName(), UserPreference.getInstance().getProfile().getProfileName())); //username, profile path

        titleLabel.setFont(SharedResource.getCustomSizeFont(96));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(contentPanel);
    }
}
