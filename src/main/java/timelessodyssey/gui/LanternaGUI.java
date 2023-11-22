package timelessodyssey.gui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class LanternaGUI implements GUI {
    private final Screen screen;

    public LanternaGUI(int width, int height) throws IOException {
        Terminal terminal = createTerminal(width, height);
        this.screen = createScreen(terminal);
    }

    private Terminal createTerminal(int width, int height) throws IOException {
        TerminalSize size = new TerminalSize(width, height + 1);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                .setInitialTerminalSize(size);
        return terminalFactory.createTerminal();
    }

    private Screen createScreen(Terminal terminal) throws IOException {
        final Screen screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
        return screen;
    }

    @Override
    public void clear() {
        screen.clear();
    }

    @Override
    public void drawPixel(int x, int y, String color) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setBackgroundColor(TextColor.Factory.fromString(color));
        tg.putString(x, y + 1, " ");
    }

    @Override
    public Action getAction() throws IOException {
        KeyStroke keyStroke = screen.pollInput();
        if (keyStroke == null)
            return Action.NONE;

        return switch (keyStroke.getKeyType()) {
            case ArrowUp -> Action.UP;
            case ArrowDown -> Action.DOWN;
            case ArrowLeft -> Action.LEFT;
            case ArrowRight -> Action.RIGHT;
            case Enter -> Action.SELECT;
            case EOF -> Action.QUIT;
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
