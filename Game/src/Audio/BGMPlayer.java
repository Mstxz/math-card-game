package Audio;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class BGMPlayer {
    private Clip bgmClip;

    public void playBackgroundMusic(String musicFile, float volume) {
        try {
            File audioFile = new File(musicFile);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            bgmClip = AudioSystem.getClip();
            bgmClip.open(audioStream);
            FloatControl gainControl = (FloatControl) bgmClip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(volume);
            bgmClip.loop(Clip.LOOP_CONTINUOUSLY);
            bgmClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void stopBackgroundMusic() {
        bgmClip.stop();
    }
}
