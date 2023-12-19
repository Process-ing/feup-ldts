package timelessodyssey.model.game.elements;

public class Spike extends Element {
    public static final int SPIKE_HEIGHT = 4;
    private final char character;

    public Spike(int x, int y, char character) {
        super(x, y);
        this.character = character;
    }

    public char getCharacter() {
        return character;
    }
}
