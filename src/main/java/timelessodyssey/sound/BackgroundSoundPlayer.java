package timelessodyssey.sound;

import javax.sound.sampled.Clip;

public class BackgroundSoundPlayer implements SoundPlayer{
    private Clip sound;

    public BackgroundSoundPlayer(Clip sound){
        this.sound = sound;
    }

    @Override
    public void start() {
        sound.setMicrosecondPosition(0);
        sound.start();
        sound.loop(Clip.LOOP_CONTINUOUSLY);
    }

    @Override
    public void stop() {
        sound.stop();
    }

    @Override
    public void setSound(Clip sound) {
        this.sound = sound;
    }

    @Override
    public Clip getSound() {
        return sound;
    }
}
