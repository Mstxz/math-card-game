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
    private static Thread playlistThread;

    public static void playBackgroundMusic(String musicFile, boolean loop) {
        try {
            File audioFile = new File(musicFile);
            filepath = musicFile;
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            bgmClip = AudioSystem.getClip();
            bgmClip.open(audioStream);
            //FloatControl gainControl = (FloatControl) bgmClip.getControl(FloatControl.Type.MASTER_GAIN);
            //gainControl.setValue(Calculation.percentOfRange(gainControl.getMinimum(),volume,((float) UserPreference.getInstance().getMusicVolume() / 100)));
            updateVolume();
            if (loop){
                bgmClip.loop(Clip.LOOP_CONTINUOUSLY);
            }
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

    private static void playPlaylist(ArrayList<String> playlist){
        int current = 0;
       while (true){
           Clip audio;
            try {
                audio = AudioSystem.getClip();
                audio.open(AudioSystem.getAudioInputStream(new File(playlist.get(current))));
                bgmClip = audio;
                updateVolume();
                bgmClip.start();
                while (audio.isRunning()){
                    try {
                        Thread.sleep(500);
                    }
                    catch (InterruptedException ex){
                        ex.printStackTrace();
                    }
                }
            }
            catch (UnsupportedAudioFileException | IOException | LineUnavailableException e){

            }
            current = (current+1)%playlist.size();
       }
    }

    public static void playlistRunner(ArrayList<String> playlist){
        playlistThread = new Thread(()->playPlaylist(playlist));
        playlistThread.start();
    }

}
