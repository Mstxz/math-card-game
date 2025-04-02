package GUI.Component;

import GUI.Router;
import GUI.Setting.Controller.AchievementProfile;
import GUI.Setting.UserPreference;
import utils.SharedResource;

import javax.swing.*;
import java.awt.*;

import static utils.ResourceLoader.loadPicture;

public class OmmThukStudentCard extends JPanel {
    private JLabel ProfilePicture;
    private JLabel OmmThukCertifiedPicture;
    private final JLabel cardName = new JLabel("OMM THUK's Student ID Card");
    private JLabel userNameLabel;
    private JPanel userInfoPanel;
    private JPanel statsPanel;
    private JPanel nameInfo;
    private ImageIcon image;
    private ImageIcon OmmThukCertified;
    private String profilePicturePath;
    private AchievementProfile achievementProfile = UserPreference.getInstance().getAchievementProfile();

    private StudentCardInformation username;
    private StudentCardInformation playBotCount;
    private StudentCardInformation winBotCount;
    private StudentCardInformation loseBotCount;


    public OmmThukStudentCard(String name, String path) {
        super();
        this.profilePicturePath = path;
        this.image = loadPicture(getProfileURL(), 230, 230);
        OmmThukCertified = loadPicture("assets/OmmThuk_Certified", 458/2, 278/2);
        OmmThukCertifiedPicture = new JLabel(OmmThukCertified);

        username = new StudentCardInformation(new Dimension(600, 230), "NAME", name);
        playBotCount = new StudentCardInformation(new Dimension(200, 150), "PLAY BOT", String.format("%d", UserPreference.getInstance().getWinStat().getPlay()));
        winBotCount = new StudentCardInformation(new Dimension(200, 150), "WIN BOT", String.format("%d",UserPreference.getInstance().getWinStat().getWin()));
        loseBotCount = new StudentCardInformation(new Dimension(200, 150), "LOSE BOT", String.format("%d",UserPreference.getInstance().getWinStat().getLose()));

        /*extra declare*/
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(SharedResource.SIAMESE_LIGHT);
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setPreferredSize(new Dimension(1000, 300));
        JPanel userCertifiedPanel = new JPanel();
        userCertifiedPanel.setBackground(SharedResource.SIAMESE_LIGHT);
        userCertifiedPanel.setLayout(new BorderLayout());
        userCertifiedPanel.setPreferredSize(new Dimension(300, 300));
        if(achievementProfile.isWinOmmThuk()){
            userCertifiedPanel.add(OmmThukCertifiedPicture, BorderLayout.CENTER);
        }
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
        statsPanel.setPreferredSize(new Dimension(700, 300));
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.X_AXIS));
        statsPanel.setBackground(SharedResource.SIAMESE_LIGHT);

        statsPanel.add(playBotCount);
        statsPanel.add(Box.createHorizontalStrut(20));
        statsPanel.add(winBotCount);
        statsPanel.add(Box.createHorizontalStrut(20));
        statsPanel.add(loseBotCount);
        statsPanel.add(Box.createHorizontalStrut(20));

        bottomPanel.add(statsPanel, BorderLayout.WEST);
        bottomPanel.add(userCertifiedPanel, BorderLayout.EAST);

        add(cardName, BorderLayout.NORTH);
        add(userInfoPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.CENTER);
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
