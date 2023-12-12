package timelessodyssey.sound;

import javax.sound.sampled.Clip;

public interface SoundPlayer {

    Clip loadSound(String filename);
    void start();
    void stop();
    void setSound(Clip sound);
    Clip getSound();
}
