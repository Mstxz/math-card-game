package GUI.Page;

import GUI.Component.ExitButton;
import GUI.Component.OmmThukStudentCard;
import GUI.Router;
import GUI.Setting.UserPreference;
import utils.SharedResource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class StatisticsPage extends Page implements KeyListener {
    private JPanel topPanel;
    private JPanel contentPanel;
    private final JLabel titleLabel = new JLabel("Statistics");
    private ExitButton exitButton;
    public StatisticsPage() {
        mainPanel.setBackground(SharedResource.SIAMESE_BRIGHT);

        exitButton = new ExitButton("MainMenu"){
            @Override
            public void cleanUp() {
                mainFrame.removeKeyListener(StatisticsPage.this);
            }
        };
        mainFrame.addKeyListener(this);
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
        contentPanel.add(new OmmThukStudentCard(UserPreference.getInstance().getProfile().getName(), UserPreference.getInstance().getProfile().getProfileName())); //username, profile path

        titleLabel.setFont(SharedResource.getCustomSizeFont(96));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(contentPanel);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code;

        if (!this.getMainPanel().isFocusable())
            return;
        code = e.getKeyCode();
        if (code == KeyEvent.VK_ESCAPE){
            this.getMainFrame().removeKeyListener(this);
            Router.setRoute("MainMenu",null);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
