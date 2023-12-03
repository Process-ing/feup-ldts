package timelessodyssey.model.menu;

import timelessodyssey.model.Position;


public class Entry {
    private Position position;
    private String text;

    public Entry(int x, int y, String text) {
        this.position = new Position(x, y);
        this.text = text;
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
}
