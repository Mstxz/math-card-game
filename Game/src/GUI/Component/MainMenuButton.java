package GUI.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import utils.ResourceLoader;
import utils.SharedResource;

public class MainMenuButton extends JButton implements MouseListener {
    private static ImageIcon originalIcon = ResourceLoader.loadPicture("assets/catpaw_icon.png",30,30);
    private ImageIcon img = null;

    public MainMenuButton(String text) {
        super(text);
        setButtonProperties();
        this.setIcon(originalIcon);
        this.setFont(SharedResource.getCustomSizeFont(36));
        this.setIconTextGap(15);
    }

    public MainMenuButton(String text,int scale){
        super(text);
        setButtonProperties();
        img = new ImageIcon(originalIcon.getImage().getScaledInstance(scale,scale,Image.SCALE_SMOOTH));
        this.setIcon(img);
        this.setFont(SharedResource.getCustomSizeFont(52));
        this.setIconTextGap(10);
    }

    private void setButtonProperties() {
        this.setOpaque(false);
        this.setBackground(new Color(255, 255, 255, 0));
        this.setBorder(new EmptyBorder(5, 50, 0, 0));

        this.setFocusPainted(false);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);

        this.setForeground(SharedResource.SIAMESE_DARK);
        this.setHorizontalTextPosition(SwingConstants.RIGHT);
        this.setPreferredSize(new Dimension(300, 50));

        // Save the original icon for later use


        // MouseListener
        this.addMouseListener(this);
    }

    private ImageIcon rotateIcon(ImageIcon icon, double angle) {
        Image image = icon.getImage();
        int w = image.getWidth(null);
        int h = image.getHeight(null);

        Image rotatedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) rotatedImage.getGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.rotate(Math.toRadians(angle), w / 2, h / 2); // Rotate around the center
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();

        return new ImageIcon(rotatedImage);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (img!=null){
            setIcon(rotateIcon(img,90));
            return;
        }
        setIcon(rotateIcon(originalIcon, 90));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (img!=null){
            setIcon(img);
            return;
        }
        setIcon(originalIcon);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }
}
