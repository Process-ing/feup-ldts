package timelessodyssey.sound;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BackgroundSoundPlayer implements SoundPlayer{

    private Clip sound;

    public BackgroundSoundPlayer(String filename) throws Exception {
        try {
            this.sound = loadSound(filename);
        } catch (Exception e) {
            throw new Exception("Unable to load sound file!");
        }
    }

    @Override
    public Clip loadSound(String filename) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        String rootPath = new File(System.getProperty("user.dir")).getPath();
        String musicPath = rootPath + "/src/main/resources/sounds/" + filename;
        File musicFile = new File(musicPath);
        AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);
        Clip musicClip = AudioSystem.getClip();
        musicClip.open(audioInput);
        FloatControl gainControl = (FloatControl) musicClip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-15f);
        return musicClip;
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

    public void setSound(Clip sound) {
        this.sound = sound;
    }

    public Clip getSound() {
        return sound;
    }
}
