package timelessodyssey.model;

public record Vector(double x, double y) {
    public Vector getLeft() {
        return new Vector(x - 1, y);
    }

    public Vector getRight() {
        return new Vector(x + 1, y);
    }

    public Vector getUp() {
        return new Vector(x, y - 1);
    }

    public Vector getDown() {
        return new Vector(x, y + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector position)) return false;
        return x == position.x && y == position.y;
    }
}
