package timelessodyssey.gui;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import net.jqwik.api.*;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

public class LanternaGUITest {
    Screen screen;
    TextGraphics tg;
    LanternaGUI gui;

    @BeforeEach
    public void setup() {
        screen = Mockito.mock(Screen.class);
        tg = Mockito.mock(TextGraphics.class);
        Mockito.when(screen.newTextGraphics()).thenReturn(tg);
        gui = new LanternaGUI(screen);
    }

    @Property
    public void drawPixel(@ForAll int x, @ForAll int y, @ForAll @From("color") String color) {
        setup();
        gui.drawPixel(x, y, color);

        Mockito.verify(tg, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString(color));
        Mockito.verify(tg, Mockito.times(1)).putString(x, y, " ");
    }

    @Provide
    public Arbitrary<String> color() {
        final String HEX_CHARS = "0123456789ABCDEF";
        return Combinators.combine(
            Arbitraries.strings().withChars(HEX_CHARS).ofLength(2),
            Arbitraries.strings().withChars(HEX_CHARS).ofLength(2),
            Arbitraries.strings().withChars(HEX_CHARS).ofLength(2)
        ).as((r, g, b) -> "#" + r + g + b);
    }
}
