package timelessodyssey.sound;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public interface SoundPlayer {

    void start();
    void stop();

    void setSound(Clip sound);
    Clip getSound();
}
