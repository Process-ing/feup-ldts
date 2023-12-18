package timelessodyssey.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class SoundLoader {

    public Clip loadSound(AudioInputStream audioInput, Clip musicClip) throws Exception {
        try {
            musicClip.open(audioInput);
            return musicClip;
        } catch (Exception e) {
            throw new Exception("Unable to load sound file!");
        }
    }

}
