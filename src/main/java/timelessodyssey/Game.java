package timelessodyssey;

import timelessodyssey.gui.*;
import timelessodyssey.model.menu.Menu;
import timelessodyssey.states.*;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game {
    private final GUI gui;
    private State state;

    public Game() throws FontFormatException, IOException, URISyntaxException {
        int SCREEN_WIDTH = 160;
        int SCREEN_HEIGHT = 90;

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        double maxFontWidth = width / SCREEN_WIDTH;
        double maxFontHeight = height / SCREEN_HEIGHT;
        int fontSize = (int) Math.min(maxFontWidth, maxFontHeight) - 1;

        this.gui = new LanternaGUI(SCREEN_WIDTH, SCREEN_HEIGHT, fontSize);
        this.state = new MenuState(new Menu());
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

    private void start() throws IOException, InterruptedException {
        int FPS = 30;
        int frameTime = 1000 / FPS;

        while (this.state != null) {
            long startTime = System.currentTimeMillis();

            state.step(this, gui, startTime);

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;

            if (sleepTime > 0) Thread.sleep(sleepTime);
        }

        gui.close();
    }
}
