package GUI.Component;

import GUI.Router;
import utils.SharedResource;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

import static utils.ResourceLoader.loadPicture;

public class CreditProfile extends JPanel {
    private JLabel profilePicture, profileNameLabel, jobLabel, quoteLabel;
    private String profilePath;
    private ImageIcon image;

    public CreditProfile(String name, String path, String job, String quote) {
        this.profilePath = path;
        this.image = loadPicture(getProfileURL(), Router.getMainFrame().getWidth()/11, Router.getMainFrame().getWidth()/11);

        /*Setup*/
        setBackground(SharedResource.SIAMESE_BRIGHT);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(200, 400));
        setBorder(new RoundBorder(SharedResource.TRANSPARENT, SharedResource.SIAMESE_LIGHT, 10, 40));
        add(Box.createRigidArea(new Dimension(20, 20)));

        /*Profile Picture*/
        profilePicture = new JLabel(image);
        profilePicture.setAlignmentX(Component.CENTER_ALIGNMENT);

        /*Profile Name*/
        profileNameLabel = new JLabel(name);
        profileNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        profileNameLabel.setFont(SharedResource.getCustomSizeFont(30));
        profileNameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        /*Job*/
        jobLabel = new JLabel("<html><div style='text-align: center; display: flex; justify-content: center;'>(" + job + ")</div></html>");
        jobLabel.setHorizontalAlignment(SwingConstants.CENTER);
        jobLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        jobLabel.setPreferredSize(new Dimension(250, 20));
        jobLabel.setFont(SharedResource.getCustomSizeFont(14));

        /*Quote*/
        quoteLabel = new JLabel("<html><div style='width: 200px; text-align: center;'>\"" + quote + "\"</div></html>");
        quoteLabel.setPreferredSize(new Dimension(300, 300));
        quoteLabel.setHorizontalAlignment(SwingConstants.CENTER);
        quoteLabel.setOpaque(false);
        quoteLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        quoteLabel.setFont(SharedResource.getCustomSizeFont(20));

        /*Add Everything*/
        add(profilePicture);
        add(profileNameLabel);
        add(jobLabel);
        if(Router.getMainFrame().getWidth() >= 1920){
            add(quoteLabel);
        }
    }

    public String getProfileURL(){
        return String.format("assets/Profile/%s", profilePath);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Credit Profile");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(300, 400);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.add(new CreditProfile("Mstxz","Mystyr.webp", "(Sound Producer , UX/UI Designer)", "I’m just making music with my mouse and keyboard, and wasting some bucks."));
    }
}
