package timelessodyssey.gui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import net.jqwik.api.*;
import net.jqwik.api.lifecycle.BeforeTry;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LanternaGUITest {
    private static final int SCREEN_WIDTH = 160;
    private static final int SCREEN_HEIGHT = 160;

    private ScreenCreator screenCreator;
    private Screen screen;
    private TextGraphics tg;

    @BeforeTry
    public void setup() throws IOException, URISyntaxException, FontFormatException {
        this.screenCreator = mock(ScreenCreator.class);
        this.screen = mock(Screen.class);
        this.tg = mock(TextGraphics.class);

        when(screenCreator.createScreen(any(), any())).thenReturn(screen);

        when(screen.newTextGraphics()).thenReturn(tg);
        when(screen.getTerminalSize()).thenReturn(new TerminalSize(SCREEN_WIDTH, SCREEN_HEIGHT));
    }

    @Property
    public void drawPixel(@ForAll int x, @ForAll int y, @ForAll @From("color") TextColor color) throws IOException, URISyntaxException, FontFormatException {
        GUI gui = new LanternaGUI(screenCreator);
        gui.drawPixel(x, y, color);

        Mockito.verify(tg, Mockito.times(1))
            .setBackgroundColor(color);
        Mockito.verify(tg, Mockito.times(1)).putString(x, y, " ");
    }

    @Provide
    public Arbitrary<TextColor> color() {
        return Combinators.combine(
            Arbitraries.integers().between(0, 255),
            Arbitraries.integers().between(0, 255),
            Arbitraries.integers().between(0, 255)
        ).as(TextColor.RGB::new);
    }
}
