package timelessodyssey.gui;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import timelessodyssey.gui.ScreenCreator.Resolution;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URISyntaxException;

import static java.awt.event.KeyEvent.*;


public class LanternaGUI implements GUI {
    private ScreenCreator screenCreator;
    private Screen screen;
    private final int width;
    private final int height;
    private boolean arrowSpam;
    private Resolution resolution;
    private KeyEvent arrowKeyPressed;
    private KeyEvent specialKeyPressed;

    public LanternaGUI(ScreenCreator screenCreator) throws IOException, URISyntaxException, FontFormatException {
        this.screenCreator = screenCreator;
        this.width = screenCreator.getWidth();
        this.height = screenCreator.getHeight();
        setResolution(null);
        this.arrowSpam = false;
        this.arrowKeyPressed = null;
        this.specialKeyPressed = null;
    }

    private Screen createScreen(Resolution resolution) throws IOException, URISyntaxException, FontFormatException {
        Screen screen = screenCreator.createScreen(resolution, new LanternaKeyAdapter(this));

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
        return screen;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public Resolution getResolution() {
        return resolution;
    }

    public void setResolution(Resolution resolution) throws IOException, URISyntaxException, FontFormatException {
        if (screen != null)
            screen.close();
        this.resolution = resolution;
        this.screen = createScreen(resolution);
    }

    @Override
    public void clear() {
        screen.clear();
    }

    @Override
    public void drawPixel(double x, double y, TextColor color) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setBackgroundColor(color);
        tg.putString((int) x, (int) y, " ");
    }

    @Override
    public void drawRectangle(double x, double y, int width, int height, TextColor color) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setBackgroundColor(color);
        for (int dy = 0; dy < height; dy++) {
            for (int dx = 0; dx < width; dx++) {
                tg.putString((int) (x + dx), (int) (y + dy), " ");
            }
        }
    }

    @Override
    public Action getNextAction() {
        if (specialKeyPressed != null) {
            int keyCode = specialKeyPressed.getKeyCode();
            specialKeyPressed = null;

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

        if (arrowKeyPressed == null)
            return Action.NONE;
        return switch (arrowKeyPressed.getKeyCode()) {
            case VK_LEFT -> Action.LEFT;
            case VK_RIGHT -> Action.RIGHT;
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

    public boolean isArrowSpam() {
        return arrowSpam;
    }

    public void setArrowSpam(boolean arrowSpam) {
        this.arrowSpam = arrowSpam;
    }

    protected void onKeyPressed(KeyEvent e) {
        if (arrowSpam && (e.getKeyCode() == VK_LEFT || e.getKeyCode() == VK_RIGHT))
            arrowKeyPressed = e;
        else
            specialKeyPressed = e;
    }

    protected void onKeyReleased(KeyEvent e) {
        if (arrowSpam && (e.getKeyCode() == VK_LEFT || e.getKeyCode() == VK_RIGHT))
            arrowKeyPressed = null;
        else
            specialKeyPressed = null;
    }
}
