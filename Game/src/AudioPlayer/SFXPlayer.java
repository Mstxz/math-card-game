package AudioPlayer;

import GUI.Setting.UserPreference;
import utils.Calculation;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SFXPlayer {
    private static Clip clip;

    public static void playSound(String soundFile, float volume) {
        try {
            if (clip!=null){
                clip.stop();
            }
            File audioFile = new File(soundFile);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(Calculation.percentOfRange(gainControl.getMinimum(),volume,((float) UserPreference.getInstance().getSFXVolume() / 100)));
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
