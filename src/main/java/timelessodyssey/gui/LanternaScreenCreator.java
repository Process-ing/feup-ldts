package timelessodyssey.gui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class LanternaScreenCreator implements ScreenCreator {
    private DefaultTerminalFactory terminalFactory;
    private TerminalSize terminalSize;

    public LanternaScreenCreator(DefaultTerminalFactory terminalFactory, TerminalSize terminalSize) {
        this.terminalFactory = terminalFactory;
        this.terminalSize = terminalSize;
        terminalFactory.setInitialTerminalSize(terminalSize);
        terminalFactory.setForceAWTOverSwing(true);
    }

    @Override
    public Screen createScreen(Resolution resolution, KeyAdapter keyAdapter)
        throws IOException, URISyntaxException, FontFormatException {
        return new TerminalScreen(createTerminal(resolution, keyAdapter));
    }

    private Terminal createTerminal(Resolution resolution, KeyAdapter keyAdapter)
        throws IOException, URISyntaxException, FontFormatException {
        Rectangle terminalBounds = getTerminalBounds(resolution);
        int fontSize = getBestFontSize(terminalBounds);
        AWTTerminalFontConfiguration fontConfig = loadFont(fontSize);
        terminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);
        Terminal terminal = terminalFactory.createTerminal();
        ((AWTTerminalFrame)terminal).getComponent(0).addKeyListener(keyAdapter);
        ((AWTTerminalFrame)terminal).setTitle("Timeless Odyssey");
        return terminal;
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

    private Rectangle getTerminalBounds(Resolution resolution) {
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
