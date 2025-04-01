package GUI.Component;

import GUI.Router;
import utils.SharedResource;

import javax.swing.*;
import java.awt.*;

import static utils.ResourceLoader.loadPicture;

public class OmmThukStudentCard extends JPanel {
    private JLabel ProfilePicture;
    private final JLabel cardName = new JLabel("OMM THUK's Student ID Card");
    private JLabel userNameLabel;
    private JPanel userInfoPanel;
    private JPanel statsPanel;
    private JPanel nameInfo;
    private ImageIcon image;
    private String profilePicturePath;

    private StudentCardInformation username;
    private StudentCardInformation playBotCount;
    private StudentCardInformation winBotCount;
    private StudentCardInformation loseBotCount;


    public OmmThukStudentCard(String name, String path) {
        super();
        this.profilePicturePath = path;
        this.image = loadPicture(getProfileURL(), 230, 230);

        username = new StudentCardInformation(new Dimension(600, 230), "NAME", name);
        playBotCount = new StudentCardInformation(new Dimension(200, 150), "PLAY BOT", String.format("%d",1));
        winBotCount = new StudentCardInformation(new Dimension(200, 150), "WIN BOT", String.format("%d",1));
        loseBotCount = new StudentCardInformation(new Dimension(200, 150), "LOSE BOT", String.format("%d",1));

        /*set up*/
        setBorder(new RoundBorder(SharedResource.SIAMESE_DARK, SharedResource.SIAMESE_LIGHT, 3, 100));
        setPreferredSize(new Dimension(1150, 660));
        add(Box.createRigidArea(new Dimension(20, 20)));

        cardName.setFont(SharedResource.getCustomSizeFont(80));
        cardName.setHorizontalAlignment(SwingConstants.CENTER);
        setBackground(SharedResource.SIAMESE_BRIGHT);

        /*User Info*/
        userInfoPanel = new JPanel();
        userInfoPanel.setBackground(SharedResource.SIAMESE_LIGHT);
        userInfoPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        userInfoPanel.setPreferredSize(new Dimension(1000, 230));

        ProfilePicture = new JLabel(image);
        ProfilePicture.setHorizontalAlignment(SwingConstants.CENTER);

        userInfoPanel.add(ProfilePicture);
        userInfoPanel.add(username);

        /*Stats Info*/
        statsPanel = new JPanel();
        statsPanel.setPreferredSize(new Dimension(1000, 300));
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.X_AXIS));
        statsPanel.setBackground(SharedResource.SIAMESE_LIGHT);

        statsPanel.add(playBotCount);
        statsPanel.add(Box.createHorizontalStrut(20));
        statsPanel.add(winBotCount);
        statsPanel.add(Box.createHorizontalStrut(20));
        statsPanel.add(loseBotCount);
        statsPanel.add(Box.createHorizontalStrut(20));

        add(cardName, BorderLayout.NORTH);
        add(userInfoPanel, BorderLayout.CENTER);
        add(statsPanel, BorderLayout.CENTER);
    }

    public String getProfileURL(){
        return String.format("assets/Profile/%s.webp", profilePicturePath);
    }

    /*main method that is an example*/
    public static void main(String[] args) {
        JFrame frame = new JFrame("Omm Thuk Student Card");
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.add(new OmmThukStudentCard("Mstxz.EXE", "Mystyr"));
        frame.setVisible(true);
    }
}
