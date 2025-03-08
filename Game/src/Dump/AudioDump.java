package Dump;

import javax.swing.*;
import Audio.BGMPlayer;

public class AudioDump extends JFrame {

    public AudioDump() {
        setTitle("Background Music in Java Swing");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        new BGMPlayer();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AudioDump frame = new AudioDump();
            frame.setVisible(true);
        });
    }
}
