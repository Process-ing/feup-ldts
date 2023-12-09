package timelessodyssey.model.game.elements;

import timelessodyssey.model.Vector;

public class Element {
    private Vector position;

    public Element(double x, double y) {
        this.position = new Vector(x, y);
    }

    public Vector getPosition() {
        return this.position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }
}
