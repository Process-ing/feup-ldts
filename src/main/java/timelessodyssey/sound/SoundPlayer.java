package timelessodyssey.sound;

import javax.sound.sampled.Clip;

public interface SoundPlayer {

    void start();
    void stop();

    void setSound(Clip sound);
    Clip getSound();
}
