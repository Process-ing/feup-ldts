package timelessodyssey.model.game.elements;

public class Spike extends Element{
    private char character;

    public Spike(int x, int y, char character) {
        super(x, y);
        this.character = character;
    }

    public char getCharacter() {
        return character;
    }
}
