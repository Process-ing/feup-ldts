package timelessodyssey.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class SoundLoader {

    public Clip loadSound(String filepath) throws Exception {
        try {
            URL resource = getClass().getClassLoader().getResource(filepath);
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(resource);
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
