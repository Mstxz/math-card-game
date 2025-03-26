package AudioPlayer;

import GUI.Setting.UserPreference;
import utils.Calculation;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class BGMPlayer {
    private static Clip bgmClip;
    private static String filepath;

    public static void playBackgroundMusic(String musicFile) {
        try {
            File audioFile = new File(musicFile);
            filepath = musicFile;
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

    public static boolean checkIfPlaying(){
        return bgmClip.isRunning();
    }

    public static Clip getBgmClip(){return bgmClip;}

    public static String getFilepath(){return filepath;}

    public static void updateVolume(){
        if (bgmClip != null){

            FloatControl gainControl = (FloatControl) bgmClip.getControl(FloatControl.Type.MASTER_GAIN);
            float gain = Calculation.percentOfRangeLog10(-60.0f,gainControl.getMaximum(),((float) UserPreference.getInstance().getMusicVolume() / 100));
            if (gain == -60.0f){
                gain = gainControl.getMinimum();
            }
            gainControl.setValue(gain);
        }
    }
}
