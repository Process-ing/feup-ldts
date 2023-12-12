package timelessodyssey.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BackgroundSoundPlayer implements SoundPlayer{

    private Clip sound;

    public BackgroundSoundPlayer(String filename){
        this.sound = loadSound(filename);
    }

    @Override
    public Clip loadSound(String filename){
        Logger logger = Logger.getLogger(BackgroundSoundPlayer.class.getName());
        try {
            String rootPath = new File(System.getProperty("user.dir")).getPath();
            String musicPath = rootPath + "/src/main/resources/sounds/" + filename;
            File musicFile = new File(musicPath);
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);
            Clip musicClip = AudioSystem.getClip();
            musicClip.open(audioInput);
            FloatControl gainControl = (FloatControl) musicClip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-15f);
            return musicClip;
        } catch (Exception e) {
            logger.log(Level.INFO, "An error occurred", e);
        }
        return null;
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
