package timelessodyssey.gui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LanternaScreenCreatorTest {
    private DefaultTerminalFactory terminalFactory;
    private TerminalScreen screen;
    private AWTTerminalFrame terminal;
    private TerminalSize terminalSize;
    private Rectangle defaultBounds;
    private Component component;
    private KeyAdapter keyAdapter;

    @BeforeEach
    public void setup() throws IOException {
        this.terminalFactory = mock(DefaultTerminalFactory.class);
        this.terminalSize = new TerminalSize(80, 50);
        this.defaultBounds = new Rectangle(40, 80);
        this.screen = mock(TerminalScreen.class);
        this.terminal = mock(AWTTerminalFrame.class);
        this.component = mock(Component.class);
        this.keyAdapter = mock(KeyAdapter.class);

        when(terminalFactory.createScreen()).thenReturn(screen);
        when(screen.getTerminal()).thenReturn(terminal);
        when(terminal.getComponent(any(Integer.class))).thenReturn(component);
    }

    @Test
    public void constructor() {
        LanternaScreenCreator screenCreator = new LanternaScreenCreator(terminalFactory, terminalSize, defaultBounds);
        int width = screenCreator.getWidth();
        assertNotEquals(0, width);
        int height = screenCreator.getHeight();
        assertNotEquals(0, height);

        verify(terminalFactory, atLeastOnce()).setInitialTerminalSize(terminalSize);
        verify(terminalFactory, atLeastOnce()).setForceAWTOverSwing(true);
        assertEquals(terminalSize.getColumns(), width);
        assertEquals(terminalSize.getRows(), height);
    }

    @Test
    public void createScreenWithoutResolution() throws IOException, URISyntaxException, FontFormatException {
        String terminalTitle = "testWithoutRes";
        LanternaScreenCreator screenCreator = new LanternaScreenCreator(terminalFactory, terminalSize, defaultBounds);

        TerminalScreen screen = (TerminalScreen) screenCreator.createScreen(null, terminalTitle, keyAdapter);

        assertSame(this.screen, screen);
        verify(terminalFactory, atLeastOnce()).setTerminalEmulatorFontConfiguration(argThat(fontConfig ->
            fontConfig.getFontWidth() * terminalSize.getColumns() <= defaultBounds.getWidth()
                && fontConfig.getFontHeight() * terminalSize.getRows() <= defaultBounds.getHeight()
        ));
        verify(component, times(1)).addKeyListener(keyAdapter);
        verify(terminal, atLeastOnce()).setTitle(terminalTitle);
    }

    @Test
    public void createScreenWithResolution() throws IOException, URISyntaxException, FontFormatException {
        ResizableGUI.Resolution resolution = ResizableGUI.Resolution.FHD;
        assertNotEquals(0, resolution.getHeight());
        assertNotEquals(0, resolution.getWidth());
        String terminalTitle = "testWithRes";
        LanternaScreenCreator screenCreator = new LanternaScreenCreator(terminalFactory, terminalSize, defaultBounds);

        TerminalScreen screen = (TerminalScreen) screenCreator.createScreen(resolution, terminalTitle, keyAdapter);

        assertSame(this.screen, screen);
        verify(terminalFactory, atLeastOnce()).setTerminalEmulatorFontConfiguration(argThat(fontConfig ->
            fontConfig.getFontWidth() * terminalSize.getColumns() <= resolution.getWidth()
            && fontConfig.getFontHeight() * terminalSize.getRows() <= resolution.getHeight()
        ));
        verify(component, times(1)).addKeyListener(keyAdapter);
        verify(terminal, atLeastOnce()).setTitle(terminalTitle);
    }
}
