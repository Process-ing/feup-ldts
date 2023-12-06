package timelessodyssey.model.menu;

import timelessodyssey.model.Position;


public class Entry {
    public enum Type { START_GAME, SETTINGS, EXIT, RESOLUTION, TO_MAIN_MENU }

    private final Position position;
    private final Type type;

    public Entry(int x, int y, Type type) {
        this.position = new Position(x, y);
        this.type = type;
    }

    public Position getPosition() {
        return position;
    }

    public Type getType() {
        return type;
    }
}
