package GUI.Setting.Component;

import GUI.Setting.UIManager.SliderUI;

import javax.swing.*;
import java.awt.*;

public class VolumeSlider extends JPanel {
    private JSlider slider;
    private ImageIcon icon;

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

        this.add(slider,BorderLayout.CENTER);
        this.setSize(500,200);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add(new VolumeSlider());
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
