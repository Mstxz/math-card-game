package GUI.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.EmptyBorder;
import java.awt.image.BufferedImage;

import utils.ResourceLoader;
import utils.SharedResource;

public class MainMenuButton extends JButton {
    private ImageIcon originalIcon;

    public MainMenuButton(String text, String iconPath, int width, int height) {
        super(text);
        setButtonProperties(iconPath, width, height);
    }

    private void setButtonProperties(String iconPath, int width, int height) {
        this.setOpaque(false);
        this.setBackground(new Color(255, 255, 255, 0));
        this.setBorder(new EmptyBorder(5, 50, 0, 0));
        this.setFocusPainted(false);
        this.setForeground(SharedResource.SIAMESE_DARK);
        this.setIcon(ResourceLoader.loadPicture(iconPath, 30, 30));
        this.setHorizontalTextPosition(SwingConstants.RIGHT);
        this.setIconTextGap(10);
        this.setPreferredSize(new Dimension(300, 50));
        this.setFont(SharedResource.getCustomSizeFont(36));

        // Save the original icon for later use
        originalIcon = (ImageIcon) this.getIcon();

        // MouseListener
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                ImageIcon rotatedIcon = rotateIcon(originalIcon, +90);
                setIcon(rotatedIcon);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setIcon(originalIcon);
            }
        });
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
}
