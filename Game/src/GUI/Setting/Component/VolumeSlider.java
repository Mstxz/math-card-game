package GUI.Setting.Component;

import GUI.Setting.UIManager.SliderUI;
import utils.ResourceLoader;
import utils.SharedResource;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class VolumeSlider extends JPanel {
    private JSlider slider;
    private ImageIcon iconOpen = ResourceLoader.loadPicture("assets/Component/VolumeHigh.png",50,50);
    private ImageIcon iconClose = ResourceLoader.loadPicture("assets/Component/VolumeMute.png",50,50);
    private JButton button;

    public VolumeSlider(){
        this.setLayout(new BorderLayout());

        slider = new JSlider(0,100);
        slider.setUI(new SliderUI());
        slider.setMajorTickSpacing(0);
        slider.setMinorTickSpacing(0);
        slider.setPaintLabels(false);
        slider.setPaintTicks(false);
        slider.setPaintTrack(true);
        slider.setOrientation(JSlider.HORIZONTAL);
        slider.setBackground(SharedResource.SIAMESE_LIGHT);
        slider.setSize(500,slider.getHeight());

        button = new JButton(iconOpen);
        button.setBackground(SharedResource.SIAMESE_LIGHT);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setUI(new BasicButtonUI());

        this.add(slider,BorderLayout.CENTER);
        this.add(button,BorderLayout.EAST);
    }

    public JSlider getSlider() {
        return slider;
    }

    public int getValue(){
        return slider.getValue();
    }

    public void setValue(int value){
        slider.setValue(value);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add(new VolumeSlider());
        frame.setSize(700,100);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
