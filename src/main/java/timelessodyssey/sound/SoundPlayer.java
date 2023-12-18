package timelessodyssey.sound;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public interface SoundPlayer {

    Clip loadSound(String filename) throws UnsupportedAudioFileException, IOException, LineUnavailableException;
    void start();
    void stop();
}
