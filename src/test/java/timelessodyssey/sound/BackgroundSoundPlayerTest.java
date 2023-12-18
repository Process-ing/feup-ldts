package timelessodyssey.sound;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;

import static javax.sound.sampled.AudioSystem.getAudioInputStream;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class BackgroundSoundPlayerTest {
    @Test
    public void SoundLoaderWorking() throws Exception {
        Clip sound = new SoundLoader().loadSound(AudioSystem.getAudioInputStream(Objects
                        .requireNonNull(getClass().getClassLoader().getResource("sounds/demo.wav"))),
                            AudioSystem.getClip());
        assertNotNull(sound);

        BackgroundSoundPlayer backgroundSoundPlayer = new BackgroundSoundPlayer(sound);
        assertEquals(sound, backgroundSoundPlayer.getSound());
    }

    @Test
    public void SoundLoaderException(){
        Exception thrown = Assertions.assertThrows(Exception.class,
                () -> {new BackgroundSoundPlayer(new SoundLoader()
                        .loadSound(AudioSystem.getAudioInputStream(Objects
                                .requireNonNull(getClass().getClassLoader().getResource("sounds/invalid.wav"))),
                                null));},
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
