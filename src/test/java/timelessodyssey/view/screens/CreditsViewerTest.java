package timelessodyssey.view.screens;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.credits.Credits;
import timelessodyssey.view.text.TextViewer;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static timelessodyssey.view.screens.CreditsViewer.*;

public class CreditsViewerTest {
    private CreditsViewer creditsViewer;
    private GUI gui;
    private TextViewer textViewer;
    private Credits credits;

    @BeforeEach
    public void setup() throws IOException {
        this.gui = mock(GUI.class);

        this.credits = mock(Credits.class);
        when(credits.getDeaths()).thenReturn(0);
        when(credits.getScore()).thenReturn(0);
        when(credits.getMinutes()).thenReturn(0);
        when(credits.getSeconds()).thenReturn(0);
        String[] names = new String[1];
        names[0] = "name test 1";
        when(credits.getNames()).thenReturn(names);
        String[] messages = new String[1];
        messages[0] = "message test 1";
        when(credits.getMessages()).thenReturn(messages);

        this.creditsViewer = new CreditsViewer(credits);

        this.textViewer = mock(TextViewer.class);
        creditsViewer.setTextViewer(textViewer);
    }


    @Test
    public void draw() throws IOException {
        creditsViewer.draw(gui,0);

        // Drawing Background and Frame
        verify(gui, times(1))
                .drawRectangle(Mockito.anyDouble(), Mockito.anyDouble(), Mockito.anyInt(), Mockito.anyInt(),
                        eq(backgroundColor));
        verify(gui, times(4))
                .drawRectangle(Mockito.anyDouble(), Mockito.anyDouble(), Mockito.anyInt(), Mockito.anyInt(),
                        eq(frameColor));

        // Drawing Messages
        verify(textViewer, times(1))
                .draw(Mockito.anyString(), Mockito.anyDouble(), Mockito.anyDouble(),
                        eq(messageColor), eq(gui));

        // Drawing Names
        verify(textViewer, times(1))
                .draw(Mockito.anyString(), Mockito.anyDouble(), Mockito.anyDouble(),
                        eq(nameColor), eq(gui));

        // Drawing Score
        verify(textViewer, times(1))
                .draw(Mockito.anyString(), Mockito.anyDouble(), Mockito.anyDouble(),
                        eq(scoreColor), eq(gui));

        // Drawing Deaths
        verify(textViewer, times(1))
                .draw(Mockito.anyString(), Mockito.anyDouble(), Mockito.anyDouble(),
                        eq(deathColor), eq(gui));

        // Drawing Duration
        verify(textViewer, times(1))
                .draw(Mockito.anyString(), Mockito.anyDouble(), Mockito.anyDouble(),
                        eq(timeColor), eq(gui));
    }
}
