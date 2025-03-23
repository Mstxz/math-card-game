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
            updateVolume();
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void updateVolume(){
        if (clip != null){

            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float gain = Calculation.percentOfRangeLog10(-60.0f,gainControl.getMaximum(),((float) UserPreference.getInstance().getSFXVolume() / 100));
            if (gain == -60.0f){
                gain = gainControl.getMinimum();
            }
            gainControl.setValue(gain);
        }
    }
}
