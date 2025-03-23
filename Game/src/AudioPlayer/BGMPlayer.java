package AudioPlayer;

import GUI.Setting.UserPreference;
import utils.Calculation;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class BGMPlayer {
    private static Clip bgmClip;

    public static void playBackgroundMusic(String musicFile, float volume) {
        try {
            File audioFile = new File(musicFile);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            bgmClip = AudioSystem.getClip();
            bgmClip.open(audioStream);
            //FloatControl gainControl = (FloatControl) bgmClip.getControl(FloatControl.Type.MASTER_GAIN);
            //gainControl.setValue(Calculation.percentOfRange(gainControl.getMinimum(),volume,((float) UserPreference.getInstance().getMusicVolume() / 100)));
            updateVolume();
            bgmClip.loop(Clip.LOOP_CONTINUOUSLY);
            bgmClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void stopBackgroundMusic() {
        if (bgmClip != null && bgmClip.isRunning()) {
            bgmClip.stop();
        }
    }

    public static void updateVolume(){
        if (bgmClip != null){

            FloatControl gainControl = (FloatControl) bgmClip.getControl(FloatControl.Type.MASTER_GAIN);
            System.out.println(Calculation.percentOfRangeLog10(gainControl.getMinimum(),gainControl.getMaximum(),((float) UserPreference.getInstance().getMusicVolume() / 100)));
            gainControl.setValue(Calculation.percentOfRangeLog10(gainControl.getMinimum(),gainControl.getMaximum(),((float) UserPreference.getInstance().getMusicVolume() / 100)));
        }
    }
}
