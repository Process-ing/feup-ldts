package timelessodyssey.states;

import timelessodyssey.Game;
import timelessodyssey.gui.GUI;

import java.io.IOException;

public class State {
    public void step(Game game, GUI gui, long startTime) throws IOException {
        gui.clear();
        gui.drawPixel(60, 28, "#777777");
        gui.refresh();
    }
}
