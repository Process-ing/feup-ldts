package timelessodyssey.gui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;

import java.awt.*;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class LanternaScreenCreator implements ScreenCreator {
    private final DefaultTerminalFactory terminalFactory;
    private final TerminalSize terminalSize;

    public LanternaScreenCreator(DefaultTerminalFactory terminalFactory, TerminalSize terminalSize) {
        this.terminalFactory = terminalFactory;
        this.terminalSize = terminalSize;
        terminalFactory.setInitialTerminalSize(terminalSize);
        terminalFactory.setForceAWTOverSwing(true);
    }

    @Override
    public Screen createScreen(ResizableGUI.Resolution resolution, String title, KeyListener keyListener)
        throws IOException, URISyntaxException, FontFormatException {
            Rectangle terminalBounds = getTerminalBounds(resolution);
            int fontSize = getBestFontSize(terminalBounds);
            AWTTerminalFontConfiguration fontConfig = loadFont(fontSize);
            terminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);
            TerminalScreen screen = terminalFactory.createScreen();
            AWTTerminalFrame terminal = (AWTTerminalFrame) screen.getTerminal();
            terminal.getComponent(0).addKeyListener(keyListener);
            terminal.setTitle(title);
            return screen;
    }

    private AWTTerminalFontConfiguration loadFont(int fontSize) throws URISyntaxException, IOException, FontFormatException {
        URL resource = getClass().getClassLoader().getResource("fonts/square.ttf");
        File fontFile = new File(resource.toURI());
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(Font.PLAIN, fontSize);
        return AWTTerminalFontConfiguration.newInstance(font);
    }

    private int getBestFontSize(Rectangle terminalBounds) {
        double maxFontWidth = terminalBounds.getWidth() / terminalSize.getColumns();
        double maxFontHeight = terminalBounds.getHeight() / terminalSize.getRows();
        return (int) Math.min(maxFontWidth, maxFontHeight);
    }

    private Rectangle getTerminalBounds(ResizableGUI.Resolution resolution) {
        if (resolution == null)
            return GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        return new Rectangle(resolution.getWidth(), resolution.getHeight());
    }

    @Override
    public int getWidth() {
        return terminalSize.getColumns();
    }

    @Override
    public int getHeight() {
        return terminalSize.getRows();
    }
}
