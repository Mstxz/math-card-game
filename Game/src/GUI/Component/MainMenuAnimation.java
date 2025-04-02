package GUI.Component;

import GUI.Setting.Controller.SettingController;
import GUI.Setting.UserPreference;
import utils.ResourceLoader;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.EventListener;

public class MainMenuAnimation extends JPanel implements ActionListener {
    private BufferedImage[] frames;
    private int currentFrame = 0;
    private Timer timer;

    public MainMenuAnimation(){
        frames = new BufferedImage[12];
        String resolution = SettingController.resolutionList.get(UserPreference.getInstance().getResolutionIndex());
        if (resolution.equals("Fullscreen")){
            updateSize(800,600);
        }
        else if (resolution.equals("1920x1080")){
            updateSize(800,600);
        }
        timer = new Timer(150,this);

        this.setOpaque(false);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(frames[currentFrame], 0, 0, this);
    }

    public void updateSize(int width,int height){

        for (int i = 0; i < 12; i++) {
            frames[i] = ResourceLoader.loadBufferedPicture(ResourceLoader.loadPicture("assets/Animation/frame"+(i+1)+".webp",width,height).getImage());
        }
        this.setPreferredSize(new Dimension(width, height));
        this.revalidate();
        this.repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Image Animation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        //frame.setSize(500, 400);

        MainMenuAnimation animationPanel = new MainMenuAnimation();
        frame.add(animationPanel);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(timer)){
            currentFrame = (currentFrame + 1) % frames.length;
            repaint();
        }
    }

    public void stop(){
        timer.stop();
    }
}

