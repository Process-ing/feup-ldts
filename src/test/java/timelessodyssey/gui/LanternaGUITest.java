package timelessodyssey.gui;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import net.jqwik.api.*;
import net.jqwik.api.lifecycle.BeforeTry;
import org.mockito.Mockito;

public class LanternaGUITest {
    Screen screen;
    TextGraphics tg;
    LanternaGUI gui;

    @BeforeTry
    public void setup() {
        screen = Mockito.mock(Screen.class);
        tg = Mockito.mock(TextGraphics.class);
        Mockito.when(screen.newTextGraphics()).thenReturn(tg);
        gui = new LanternaGUI(screen);
    }

    @Property
    public void drawPixel(@ForAll int x, @ForAll int y, @ForAll @From("color") TextColor.RGB color) {
        gui.drawPixel(x, y, color);

        Mockito.verify(tg, Mockito.times(1))
            .setBackgroundColor(color);
        Mockito.verify(tg, Mockito.times(1)).putString(x, y, " ");
    }

    @Provide
    public Arbitrary<TextColor.RGB> color() {
        return Combinators.combine(
            Arbitraries.integers().between(0, 255),
            Arbitraries.integers().between(0, 255),
            Arbitraries.integers().between(0, 255)
        ).as(TextColor.RGB::new);
    }
}
