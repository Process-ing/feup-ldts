package timelessodyssey.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class SoundLoader {

    public Clip loadSound(String filename) throws Exception{
        try {
            String musicPath = "./src/main/resources/sounds/" + filename;
            File musicFile = new File(musicPath);
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);
            Clip musicClip = AudioSystem.getClip();
            musicClip.open(audioInput);
            FloatControl gainControl = (FloatControl) musicClip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-15f);
            return musicClip;
        } catch (Exception e) {
            throw new Exception("Unable to load sound file!");
        }
    }

}
