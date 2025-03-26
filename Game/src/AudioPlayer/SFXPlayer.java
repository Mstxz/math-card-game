package AudioPlayer;

import GUI.Setting.UserPreference;
import utils.Calculation;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SFXPlayer {
    private static final List<Clip> activeClips = new ArrayList<>();

    public static void playSound(String soundFile, float volume) {
        try {
            File audioFile = new File(soundFile);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            updateVolume(clip);
            clip.start();

            // Remove the clip from the list when finished
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.close();
                    activeClips.remove(clip);
                }
            });

            activeClips.add(clip);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void updateVolume(Clip clip){
        if (clip != null){
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float gain = Calculation.percentOfRangeLog10(-60.0f, gainControl.getMaximum(), ((float) UserPreference.getInstance().getSFXVolume() / 100));
            if (gain == -60.0f){
                gain = gainControl.getMinimum();
            }
            gainControl.setValue(gain);
        }
    }

    public static void stopAllSounds() {
        for (Clip clip : new ArrayList<>(activeClips)) {
            clip.stop();
            clip.close();
        }
        activeClips.clear();
    }
}
