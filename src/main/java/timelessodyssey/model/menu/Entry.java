package timelessodyssey.model.menu;

import timelessodyssey.model.Vector;


public class Entry {
    public enum Type { START_GAME, SETTINGS, EXIT, RESOLUTION, TO_MAIN_MENU }

    private final Vector position;
    private final Type type;

    public Entry(int x, int y, Type type) {
        this.position = new Vector(x, y);
        this.type = type;
    }

    public Vector getPosition() {
        return position;
    }

    public Type getType() {
        return type;
    }
}
