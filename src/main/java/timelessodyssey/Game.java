package timelessodyssey;

import timelessodyssey.gui.GUI;
import timelessodyssey.gui.LanternaGUI;
import timelessodyssey.model.menu.MainMenu;
import timelessodyssey.states.MainMenuState;
import timelessodyssey.states.State;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game {
    private static final int PIXEL_WIDTH = 160;
    private static final int PIXEL_HEIGHT = 90;
    private final GUI gui;
    private State state;

    public Game() throws FontFormatException, IOException, URISyntaxException {
        this.gui = new LanternaGUI(PIXEL_WIDTH, PIXEL_HEIGHT);
        this.state = new MainMenuState(new MainMenu());
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

    public GUI.Resolution getResolution() {
        return gui.getResolution();
    }

    public void setResolution(GUI.Resolution resolution) throws IOException, URISyntaxException, FontFormatException {
        gui.setResolution(resolution);
    }

    private void start() throws IOException, InterruptedException, URISyntaxException, FontFormatException {
        int FPS = 30;
        double frameTime = 1000.0 / FPS;

        while (this.state != null) {
            double startTime = System.currentTimeMillis();

            state.step(this, gui, frameTime);

            double elapsedTime = System.currentTimeMillis() - startTime;
            double sleepTime = frameTime - elapsedTime;

            if (sleepTime > 0) Thread.sleep((long) sleepTime);
        }

        gui.close();
    }
}
