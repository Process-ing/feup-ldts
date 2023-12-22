package timelessodyssey.gui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import net.jqwik.api.*;
import net.jqwik.api.lifecycle.BeforeTry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import static java.awt.event.KeyEvent.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class LanternaGUITest {
    private static final int SCREEN_WIDTH = 160;
    private static final int SCREEN_HEIGHT = 160;

    private ScreenCreator screenCreator;
    private Screen screen;
    private TextGraphics tg;

    @BeforeTry
    @BeforeEach
    public void setup() throws IOException, URISyntaxException, FontFormatException {
        this.screenCreator = mock(ScreenCreator.class);
        this.screen = mock(Screen.class);
        this.tg = mock(TextGraphics.class);

        when(screenCreator.createScreen(any(), any(), any())).thenReturn(screen);
        when(screenCreator.getWidth()).thenReturn(SCREEN_WIDTH);
        when(screenCreator.getHeight()).thenReturn(SCREEN_HEIGHT);

        when(screen.newTextGraphics()).thenReturn(tg);
    }

    @Test
    public void constructor() throws IOException, URISyntaxException, FontFormatException {
        String title = "constructor test";
        LanternaGUI gui = new LanternaGUI(screenCreator, title);
        ResizableGUI.Resolution resolution = gui.getResolution();
        int width = gui.getWidth();
        int height = gui.getHeight();

        assertNull(resolution);
        assertEquals(SCREEN_WIDTH, width);
        assertEquals(SCREEN_HEIGHT, height);
        verify(screen, times(1)).setCursorPosition(null);
        verify(screen, times(1)).startScreen();
        verify(screenCreator, times(1)).createScreen(null, title, gui.getKeyAdapter());
    }

    @Test
    public void getNextActionWithoutKeySpam() throws IOException, URISyntaxException, FontFormatException {
        LanternaGUI gui = new LanternaGUI(screenCreator, "getNextAction without key spam test");
        KeyAdapter keyAdapter = gui.getKeyAdapter();

        Map<Integer, GUI.Action> keyToAction = new HashMap<>();
        keyToAction.put(VK_LEFT, GUI.Action.LEFT);
        keyToAction.put(VK_RIGHT, GUI.Action.RIGHT);
        keyToAction.put(VK_UP, GUI.Action.UP);
        keyToAction.put(VK_DOWN, GUI.Action.DOWN);
        keyToAction.put(VK_ESCAPE, GUI.Action.QUIT);
        keyToAction.put(VK_ENTER, GUI.Action.SELECT);
        keyToAction.put(VK_SPACE, GUI.Action.JUMP);
        keyToAction.put(VK_X, GUI.Action.DASH);
        keyToAction.put(VK_T, GUI.Action.NONE);

        gui.setKeySpam(false);
        gui.getNextAction();
        GUI.Action action1 = gui.getNextAction();
        assertEquals(GUI.Action.NONE, action1);

        for (Map.Entry<Integer, GUI.Action> entry: keyToAction.entrySet()) {
            KeyEvent event = mock(KeyEvent.class);
            when(event.getKeyCode()).thenReturn(entry.getKey());

            keyAdapter.keyPressed(event);
            GUI.Action action2 = gui.getNextAction();
            GUI.Action action3 = gui.getNextAction();
            keyAdapter.keyReleased(event);
            keyAdapter.keyPressed(event);
            keyAdapter.keyReleased(event);
            GUI.Action action4 = gui.getNextAction();

            assertEquals(entry.getValue(), action2);
            assertEquals(GUI.Action.NONE, action3);
            assertEquals(GUI.Action.NONE, action4);
        }
    }

    @Test
    public void getNextActionWithKeySpam() throws IOException, URISyntaxException, FontFormatException {
        LanternaGUI gui = new LanternaGUI(screenCreator, "getNextAction with key spam test");
        gui.setKeySpam(true);
        KeyAdapter keyAdapter = gui.getKeyAdapter();

        Map<Integer, GUI.Action> specialKeyToAction = new HashMap<>();
        specialKeyToAction.put(VK_UP, GUI.Action.UP);
        specialKeyToAction.put(VK_DOWN, GUI.Action.DOWN);
        specialKeyToAction.put(VK_ESCAPE, GUI.Action.QUIT);
        specialKeyToAction.put(VK_ENTER, GUI.Action.SELECT);
        specialKeyToAction.put(VK_SPACE, GUI.Action.JUMP);
        specialKeyToAction.put(VK_X, GUI.Action.DASH);
        specialKeyToAction.put(VK_T, GUI.Action.NONE);

        for (Map.Entry<Integer, GUI.Action> entry: specialKeyToAction.entrySet()) {
            KeyEvent event = mock(KeyEvent.class);
            when(event.getKeyCode()).thenReturn(entry.getKey());

            keyAdapter.keyPressed(event);
            GUI.Action action1 = gui.getNextAction();
            GUI.Action action2 = gui.getNextAction();
            keyAdapter.keyReleased(event);
            keyAdapter.keyPressed(event);
            keyAdapter.keyReleased(event);
            GUI.Action action3 = gui.getNextAction();

            assertEquals(entry.getValue(), action1);
            assertEquals(GUI.Action.NONE, action2);
            assertEquals(GUI.Action.NONE, action3);
        }

        Map<Integer, GUI.Action> arrowKeyToAction = new HashMap<>();
        arrowKeyToAction.put(VK_LEFT, GUI.Action.LEFT);
        arrowKeyToAction.put(VK_RIGHT, GUI.Action.RIGHT);

        for (Map.Entry<Integer, GUI.Action> entry: arrowKeyToAction.entrySet()) {
            KeyEvent event = mock(KeyEvent.class);
            when(event.getKeyCode()).thenReturn(entry.getKey());

            keyAdapter.keyPressed(event);
            GUI.Action action1 = gui.getNextAction();
            GUI.Action action2 = gui.getNextAction();
            keyAdapter.keyReleased(event);
            GUI.Action action3 = gui.getNextAction();

            assertEquals(entry.getValue(), action1);
            assertEquals(entry.getValue(), action2);
            assertEquals(GUI.Action.NONE, action3);
        }
    }

    @Test
    public void setResolution() throws IOException, URISyntaxException, FontFormatException {
        String title = "setResolution test";
        LanternaGUI gui = new LanternaGUI(screenCreator, title);
        ResizableGUI.Resolution resolution = ResizableGUI.Resolution.FOUR_K;

        gui.setResolution(resolution);

        verify(screen, times(1)).close();
        assertEquals(resolution, gui.getResolution());
        verify(screenCreator, times(1)).createScreen(resolution, title, gui.getKeyAdapter());
    }

    @Property
    public void drawPixel(@ForAll int x, @ForAll int y, @ForAll @From("color") TextColor color) throws IOException, URISyntaxException, FontFormatException {
        GUI gui = new LanternaGUI(screenCreator, "drawPixel test");

        gui.drawPixel(x, y, color);

        verify(tg, Mockito.times(1)).setBackgroundColor(color);
        verify(tg, Mockito.times(1)).setCharacter(x, y, ' ');
        verifyNoMoreInteractions(tg);
    }

    @Property
    public void drawRectangle(
        @ForAll int x,
        @ForAll int y,
        @ForAll int width,
        @ForAll int height,
        @ForAll @From("color") TextColor color
    ) throws IOException, URISyntaxException, FontFormatException {
        GUI gui = new LanternaGUI(screenCreator, "drawRectangle test");

        gui.drawRectangle(x, y, width, height, color);

        if (width > 0 && height > 0) {
            verify(tg, Mockito.times(1)).setBackgroundColor(color);
            verify(tg, Mockito.times(1))
                .fillRectangle(new TerminalPosition(x, y), new TerminalSize(width, height), ' ');
        }
        else {
            verify(tg, Mockito.times(0)).setBackgroundColor(any(TextColor.class));
            verify(tg, Mockito.times(0))
                    .fillRectangle(any(TerminalPosition.class), any(TerminalSize.class), anyChar());
        }
        verifyNoMoreInteractions(tg);
    }

    @Test
    public void clear() throws IOException, URISyntaxException, FontFormatException {
        LanternaGUI gui = new LanternaGUI(screenCreator, "clear test");

        gui.clear();

        verify(screen, atLeastOnce()).clear();
    }

    @Test
    public void refresh() throws IOException, URISyntaxException, FontFormatException {
        LanternaGUI gui = new LanternaGUI(screenCreator, "refresh test");

        gui.refresh();

        verify(screen, atLeastOnce()).refresh();
    }

    @Test
    public void close() throws IOException, URISyntaxException, FontFormatException {
        LanternaGUI gui = new LanternaGUI(screenCreator, "close test");

        gui.close();

        verify(screen, times(1)).close();
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
