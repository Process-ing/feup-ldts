package timelessodyssey;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import timelessodyssey.gui.LanternaGUI;
import timelessodyssey.gui.LanternaScreenCreator;
import timelessodyssey.gui.ResizableGUI;
import timelessodyssey.gui.ScreenCreator;
import timelessodyssey.model.menu.MainMenu;
import timelessodyssey.sound.BackgroundSoundPlayer;
import timelessodyssey.states.MainMenuState;
import timelessodyssey.states.State;
import timelessodyssey.view.GameSpriteLoader;
import timelessodyssey.view.SpriteLoader;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game {
    public static final int PIXEL_WIDTH = 160;
    public static final int PIXEL_HEIGHT = 90;
    private final LanternaGUI gui;
    private final SpriteLoader spriteLoader;
    private State state;
    private final BackgroundSoundPlayer backgroundSoundPlayer;
    private static final int NUMBER_OF_LEVELS = 11;

    public Game() throws FontFormatException, IOException, URISyntaxException {
        ScreenCreator screenCreator = new LanternaScreenCreator(
            new DefaultTerminalFactory(),
            new TerminalSize(PIXEL_WIDTH, PIXEL_HEIGHT),
            GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds()
        );
        this.gui = new LanternaGUI(screenCreator, "Timeless Odyssey");
        this.spriteLoader = new GameSpriteLoader();
        this.state = new MainMenuState(new MainMenu(), spriteLoader);
        this.backgroundSoundPlayer = new BackgroundSoundPlayer("demo.wav");
    }

    public static void main(String[] args) {
        Logger logger = Logger.getLogger(Game.class.getName());
        try {
            new Game().start();
        } catch (Exception e) {
            logger.log(Level.INFO, "An error occurred", e);
        }
    }

    public void setState(State state) {
        this.state = state;
    }

    public ResizableGUI.Resolution getResolution() {
        return gui.getResolution();
    }

    public void setResolution(ResizableGUI.Resolution resolution)
        throws IOException, URISyntaxException, FontFormatException {
        gui.setResolution(resolution);
    }

    public void setKeySpam(boolean keySpam) {
        gui.setKeySpam(keySpam);
    }

    public SpriteLoader getSpriteLoader() {
        return spriteLoader;
    }

    public BackgroundSoundPlayer getBackgroundSoundPlayer() {
        return backgroundSoundPlayer;
    }

    public static int getNumberOfLevels() {
        return NUMBER_OF_LEVELS;
    }

    private void start() throws IOException, InterruptedException, URISyntaxException, FontFormatException {
        int FPS = 30;
        long frameTime = 1000 / FPS;
        long frameCount = 0;

        backgroundSoundPlayer.start();
        while (this.state != null) {
            long startTime = System.currentTimeMillis();

            state.step(this, gui, frameCount);

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;

            if (sleepTime > 0) Thread.sleep(sleepTime);
            frameCount++;
        }

        gui.close();
    }
}
