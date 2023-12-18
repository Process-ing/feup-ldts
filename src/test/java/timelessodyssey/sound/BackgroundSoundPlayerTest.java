package timelessodyssey.sound;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.sound.sampled.Clip;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class BackgroundSoundPlayerTest {

    @Test
    public void SoundTesting(){
        BackgroundSoundPlayer backgroundSoundPlayer = new BackgroundSoundPlayer("demo.wav");
        Clip sound=mock(Clip.class);
        backgroundSoundPlayer.setSound(sound);
        backgroundSoundPlayer.start();
        Mockito.verify(sound, times(1)).setMicrosecondPosition(0);
        Mockito.verify(sound, times(1)).start();
        Mockito.verify(sound, times(1)).loop(Clip.LOOP_CONTINUOUSLY);

        backgroundSoundPlayer.stop();
        Mockito.verify(sound, times(1)).stop();

        assertEquals(sound, backgroundSoundPlayer.getSound());
    }

    @Test
    public void SoundException(){
        BackgroundSoundPlayer backgroundSoundPlayer = new BackgroundSoundPlayer("invalid.wav");
        assertNull(backgroundSoundPlayer.getSound());
    }
}
