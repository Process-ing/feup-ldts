package timelessodyssey.sound;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.sound.sampled.Clip;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class BackgroundSoundPlayerTest {

    @Test
    public void SoundLoaderWorking() throws Exception {
        Clip sound = new SoundLoader().loadSound("demo.wav");
        assertNotNull(sound);

        BackgroundSoundPlayer backgroundSoundPlayer = new BackgroundSoundPlayer(sound);
        assertEquals(sound, backgroundSoundPlayer.getSound());
    }

    @Test
    public void SoundLoaderException(){
        Exception thrown = Assertions.assertThrows(Exception.class,
                () -> {new BackgroundSoundPlayer(new SoundLoader().loadSound("invalid.wav"));},
                "BackgroundSoundPlayer was supposed to throw Exception");

        Assertions.assertEquals("Unable to load sound file!", thrown.getMessage());
    }
    @Test
    public void SoundTesting() {
        Clip sound=mock(Clip.class);
        BackgroundSoundPlayer backgroundSoundPlayer = new BackgroundSoundPlayer(sound);
        backgroundSoundPlayer.setSound(sound);
        backgroundSoundPlayer.start();
        Mockito.verify(sound, times(1)).setMicrosecondPosition(0);
        Mockito.verify(sound, times(1)).start();
        Mockito.verify(sound, times(1)).loop(Clip.LOOP_CONTINUOUSLY);

        backgroundSoundPlayer.stop();
        Mockito.verify(sound, times(1)).stop();
    }
}
