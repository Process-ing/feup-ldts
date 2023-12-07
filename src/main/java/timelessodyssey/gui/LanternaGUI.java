package timelessodyssey.gui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;
import timelessodyssey.model.Position;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import static java.awt.event.KeyEvent.*;


public class LanternaGUI implements GUI {
    private Screen screen;
    private final int width;
    private final int height;
    private Resolution resolution;
    private KeyEvent pressedKey = null;

    public LanternaGUI(Screen screen) {
        this.screen = screen;
        this.width = screen.getTerminalSize().getColumns();
        this.height = screen.getTerminalSize().getRows();
    }

    public LanternaGUI(int width, int height)
        throws IOException, URISyntaxException, FontFormatException {
        this.width = width;
        this.height = height;
        setResolution(null);
    }


    private Terminal createTerminal(int width, int height, int fontSize) throws IOException, URISyntaxException, FontFormatException {
        TerminalSize size = new TerminalSize(width, height);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                .setInitialTerminalSize(size);

        AWTTerminalFontConfiguration fontConfig = loadFont(fontSize);
        terminalFactory.setForceAWTOverSwing(true);
        terminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);
        Terminal terminal = terminalFactory.createTerminal();
        ((AWTTerminalFrame)terminal).getComponent(0).addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    pressedKey = e;
                }
                @Override
                public void keyReleased(KeyEvent e) {
                    pressedKey = null;
                }
        });
        ((AWTTerminalFrame)terminal).setTitle("Timeless Odyssey");
        return terminal;
    }

    private AWTTerminalFontConfiguration loadFont(int fontSize) throws URISyntaxException, IOException, FontFormatException {
        URL resource = getClass().getClassLoader().getResource("fonts/square.ttf");
        File fontFile = new File(resource.toURI());
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(Font.PLAIN, fontSize);
        return AWTTerminalFontConfiguration.newInstance(font);
    }

    private Screen createScreen(Terminal terminal) throws IOException {
        final Screen screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
        return screen;
    }

    private int getBestFontSize(int width, int height, Rectangle terminalBounds) {
        double maxFontWidth = terminalBounds.getWidth() / width;
        double maxFontHeight = terminalBounds.getHeight() / height;
        return (int) Math.min(maxFontWidth, maxFontHeight);
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public Resolution getResolution() {
        return resolution;
    }

    @Override
    public void setResolution(Resolution resolution) throws IOException, URISyntaxException, FontFormatException {
        if (screen != null)
            screen.close();
        this.resolution = resolution;

        Rectangle terminalBounds;
        if (resolution == null)
            terminalBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        else
            terminalBounds = new Rectangle(resolution.getWidth(), resolution.getHeight());
        int fontSize = getBestFontSize(width, height, terminalBounds);
        Terminal terminal = createTerminal(width, height, fontSize);
        this.screen = createScreen(terminal);
    }

    @Override
    public void clearAction(){
        this.pressedKey = null;
    }

    @Override
    public void clear() {
        screen.clear();
    }

    @Override
    public void drawPixel(Position position, TextColor color) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setBackgroundColor(color);
        tg.putString((int) position.x(), (int) position.y(), " ");
    }

    @Override
    public void drawRectangle(Position position, int width, int height, TextColor color) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setBackgroundColor(color);
        for (int dy = 0; dy < height; dy++) {
            for (int dx = 0; dx < width; dx++) {
                tg.putString((int) (position.x() + dx), (int) (position.y() + dy), " ");
            }
        }
    }

    @Override
    public Action getNextAction() throws IOException {
        if (pressedKey == null)
            return Action.NONE;

        return switch (pressedKey.getKeyCode()) {
            case VK_UP -> Action.UP;
            case VK_DOWN -> Action.DOWN;
            case VK_LEFT -> Action.LEFT;
            case VK_RIGHT -> Action.RIGHT;
            case VK_ESCAPE -> Action.QUIT;
            case VK_ENTER -> Action.SELECT;
            default -> Action.NONE;
        };
    }

    @Override
    public void refresh() throws IOException {
        screen.refresh();
    }

    @Override
    public void close() throws IOException {
        screen.close();
    }
}
