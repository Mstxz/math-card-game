package GUI.Page;

import GUI.Router;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import utils.SharedResource;

public class MainMenuPage extends Page implements ActionListener {
    private JPanel ButtonZone;
    private JPanel TitlePanel;
    private JButton playButton = new JButton("PLAY");
    private JButton yourDecksButton = new JButton("Your Decks");
    private JButton tutorialButton = new JButton("Tutorial");
    private JButton settingsButton = new JButton("Settings");
    private JButton creditButton = new JButton("Credits");
    private JButton exitButton = new JButton("Exit");
    private JLabel Title;
    private Image bg;

    public MainMenuPage() {
        super();
        // Ensure the background image exists
        try {
            bg = new ImageIcon(getClass().getClassLoader().getResource("assets/blankBG.jpg")).getImage();
        } catch (Exception e) {
            System.out.println("Error loading background image: " + e.getMessage());
            // You can use a default image or just a solid color as a fallback
        }
        initComponents();
        try {
            ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("assets/icon.png"));
            mainFrame.setIconImage(icon.getImage());
        } catch (Exception e) {
            System.out.println("Error loading icon: " + e.getMessage());
        }

        mainFrame.setResizable(false);
        mainFrame.setSize(1920, 1080);
    }

    private void initComponents() {
        mainPanel = new JPanel(new GridLayout(3,1)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); 
                if (bg != null) {
                    g.drawImage(bg, 0, 0, getWidth(), getHeight(), this); 
                }
            }
        };

        TitlePanel = new JPanel(new BorderLayout());
        TitlePanel.setBackground(new Color(255, 255, 255, 0));
        TitlePanel.add(Title = new JLabel("Meaothematicians"));
        Title.setForeground(new Color(100, 90, 82));
        Title.setFont(SharedResource.getCustomSizeFont(70));
        Title.setBorder(new EmptyBorder(50,50,0,0));

        ButtonZone = new JPanel();
        ButtonZone.setLayout(new BoxLayout(ButtonZone, BoxLayout.Y_AXIS));
        ButtonZone.setPreferredSize(new Dimension(400, 800));
        ButtonZone.setBackground(new Color(255, 255, 255, 0));
        ButtonZone.setBorder(new EmptyBorder(25,50,0,50));
        ButtonZone.setOpaque(false);

        ButtonZone.add(playButton);
        playButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        playButton.setIcon(getScaledIcon("assets/catpaw_icon.png", 20, 20));
        setButton(playButton);

        ButtonZone.add(yourDecksButton);
        yourDecksButton.setIcon(getScaledIcon("assets/catpaw_icon.png", 20, 20));
        yourDecksButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        setButton(yourDecksButton);

        ButtonZone.add(tutorialButton);
        tutorialButton.setIcon(getScaledIcon("assets/catpaw_icon.png", 20, 20));
        tutorialButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        setButton(tutorialButton);

        ButtonZone.add(settingsButton);
        settingsButton.setIcon(getScaledIcon("assets/catpaw_icon.png", 20, 20));
        settingsButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        setButton(settingsButton);

        ButtonZone.add(creditButton);
        creditButton.setIcon(getScaledIcon("assets/catpaw_icon.png", 20, 20));
        creditButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        setButton(creditButton);

        ButtonZone.add(exitButton);
        exitButton.setIcon(getScaledIcon("assets/catpaw_icon.png", 20, 20));
        exitButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        setButton(exitButton);

        mainPanel.add(TitlePanel, BorderLayout.NORTH);
        mainPanel.add(ButtonZone, BorderLayout.CENTER);
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    // Set button properties for transparency
    public void setButton(JButton button) {
        button.setOpaque(false);
        button.setBackground(new Color(255, 255, 255, 0));
        button.setBorder(new EmptyBorder(5, 50, 0, 0));
        button.setFocusPainted(false);
        button.setForeground(new Color(100, 90, 62));

        Dimension defaultSize = new Dimension(300, 200);
        Font defaultFont = SharedResource.getCustomSizeFont(25);

        if (button == playButton) {
            button.setPreferredSize(new Dimension(350, 200)); 
            button.setFont(SharedResource.getCustomSizeFont(45)); 
        } else {
            button.setPreferredSize(defaultSize);
            button.setFont(defaultFont);
        }

    // originalIcon
    ImageIcon originalIcon = (ImageIcon) button.getIcon();

    // MouseListener 
    button.addMouseListener(new MouseAdapter() {
        // mouseExited setIcon rotatedIcon
        @Override
        public void mouseEntered(MouseEvent e) {
            ImageIcon rotatedIcon = rotateIcon(originalIcon, +90);
            button.setIcon(rotatedIcon);
        }
        // mouseExited setIcon originalIcon
        @Override
        public void mouseExited(MouseEvent e) {
            button.setIcon(originalIcon);
        }
        });
        button.addActionListener(this);
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

    // Set ScaledIcon
    private ImageIcon getScaledIcon(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource(path));
        Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(playButton)){
            Router.setRoute("SelMode",null);
        }
        else if (e.getSource().equals(exitButton)){
            System.exit(0);
        }
        else if (e.getSource().equals(yourDecksButton)){
            Router.setRoute("DeckCreator",null);
        }
    }
}
