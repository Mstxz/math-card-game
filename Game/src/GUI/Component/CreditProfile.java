package GUI.Component;

import GUI.Router;
import utils.SharedResource;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import static utils.ResourceLoader.loadPicture;

public class CreditProfile extends JPanel implements ComponentListener {
    private JLabel profilePicture, profileNameLabel, jobLabel, quoteLabel;
    private String profilePath;
    private ImageIcon image;
    private int width;

    public CreditProfile(String name, String path, String job, String quote) {
        this.width = Router.getMainFrame().getWidth();
        this.profilePath = path;
        this.image = loadPicture(getProfileURL(), Router.getMainFrame().getWidth()/11, Router.getMainFrame().getWidth()/11);

        /*Setup*/
        setBackground(SharedResource.SIAMESE_BRIGHT);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(200, 400));
        setMaximumSize(new Dimension(200, 400));
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
        jobLabel = new JLabel("<html><div style='display: flex; justify-content: center;'>(" + job + ")</div></html>");
        jobLabel.setHorizontalAlignment(SwingConstants.CENTER);
        jobLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        jobLabel.setPreferredSize(new Dimension(250, 20));

        /*Quote*/
        quoteLabel = new JLabel("<html><div style='text-align: center;'>\"" + quote + "\"</div></html>");
        quoteLabel.setPreferredSize(new Dimension(250, 300));
        quoteLabel.setHorizontalAlignment(SwingConstants.CENTER);
        quoteLabel.setOpaque(false);
        quoteLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        /*Add Everything*/
        add(profilePicture);
        add(profileNameLabel);
        add(jobLabel);
        if(width  > 1366){
            add(quoteLabel);
        }

        addComponentListener(this);
    }

    public String getProfileURL(){
        return String.format("assets/Profile/%s", profilePath);
    }

    public void componentResized(ComponentEvent e) {
        Dimension parentSize = getParent() != null ? getParent().getSize() : Toolkit.getDefaultToolkit().getScreenSize();
        int width = parentSize.width;
        int height = parentSize.height;

        int panelWidth = Math.min(200, width / 5);
        int panelHeight = Math.min(400, height / 2);
        setPreferredSize(new Dimension(panelWidth, panelHeight));
        System.out.println(panelWidth + " " + panelHeight);


        if (200 >= width) {
            jobLabel.setFont(SharedResource.getCustomSizeFont(10));
            quoteLabel.setFont(SharedResource.getCustomSizeFont(14));
        } else {
            jobLabel.setFont(SharedResource.getCustomSizeFont(14));
            quoteLabel.setFont(SharedResource.getCustomSizeFont(24));
        }

        revalidate();
        repaint();
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

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
