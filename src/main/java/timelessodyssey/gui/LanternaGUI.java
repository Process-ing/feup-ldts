package timelessodyssey.gui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static java.awt.event.KeyEvent.*;


public class LanternaGUI implements ResizableGUI {
    private static final List<Integer> SPAM_KEYS = List.of(VK_LEFT, VK_RIGHT);

    private final ScreenCreator screenCreator;
    private final String title;
    private Screen screen;
    private boolean keySpam;
    private Resolution resolution;
    private KeyEvent priorityKeyPressed;
    private final KeyAdapter keyAdapter;
    private KeyEvent keyPressed;

    public LanternaGUI(ScreenCreator screenCreator, String title) throws IOException, URISyntaxException, FontFormatException {
        this.screenCreator = screenCreator;
        this.title = title;
        this.keySpam = false;
        this.priorityKeyPressed = null;
        this.keyAdapter = createKeyAdapter();
        this.keyPressed = null;
        setResolution(null);
    }

    private Screen createScreen(Resolution resolution) throws IOException, URISyntaxException, FontFormatException {
        Screen screen = screenCreator.createScreen(resolution, title, getKeyAdapter());

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
        return screen;
    }

    private KeyAdapter createKeyAdapter() {
        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (keySpam && SPAM_KEYS.contains(e.getKeyCode()))
                    keyPressed = priorityKeyPressed = e;
                else
                    keyPressed = e;
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (keySpam && SPAM_KEYS.contains(e.getKeyCode()))
                    keyPressed = priorityKeyPressed = null;
                else
                    keyPressed = priorityKeyPressed;
            }
        };
    }

    @Override
    public int getWidth() {
        return screenCreator.getWidth();
    }

    @Override
    public int getHeight() {
        return screenCreator.getHeight();
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
        this.screen = createScreen(resolution);
    }

    @Override
    public void drawPixel(double x, double y, TextColor color) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setBackgroundColor(color);
        tg.setCharacter((int) x, (int) y, ' ');
    }

    @Override
    public void drawRectangle(double x, double y, int width, int height, TextColor color) {
        if (width > 0 && height > 0) {
            TextGraphics tg = screen.newTextGraphics();
            tg.setBackgroundColor(color);
            tg.fillRectangle(new TerminalPosition((int) x, (int) y), new TerminalSize(width, height), ' ');
        }
    }

    @Override
    public Action getNextAction() {
        if (keyPressed == null)
            return Action.NONE;

        int keyCode = keyPressed.getKeyCode();
        keyPressed = priorityKeyPressed;

        return switch (keyCode) {
            case VK_LEFT -> Action.LEFT;
            case VK_RIGHT -> Action.RIGHT;
            case VK_UP -> Action.UP;
            case VK_DOWN -> Action.DOWN;
            case VK_ESCAPE -> Action.QUIT;
            case VK_ENTER -> Action.SELECT;
            case VK_SPACE -> Action.JUMP;
            case VK_X -> Action.DASH;
            default -> Action.NONE;
        };
    }


    @Override
    public void clear() {
        screen.clear();
    }

    @Override
    public void refresh() throws IOException {
        screen.refresh();
    }

    @Override
    public void close() throws IOException {
        screen.close();
    }

    public void setKeySpam(boolean keySpam) {
        if (!keySpam)
            priorityKeyPressed = null;
        this.keySpam = keySpam;
    }

    public KeyAdapter getKeyAdapter() {
        return keyAdapter;
    }
}
