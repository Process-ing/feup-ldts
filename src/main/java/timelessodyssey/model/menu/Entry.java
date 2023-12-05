package timelessodyssey.model.menu;

import timelessodyssey.Game;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.Position;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;


public abstract class Entry {
    private Position position;
    private String text;

    public Entry(int x, int y) {
        this.position = new Position(x, y);
        this.text = createEntryText();
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    protected abstract String createEntryText();
    public abstract void doAction(Game game, GUI gui, GUI.Action action) throws IOException, URISyntaxException, FontFormatException;
}
