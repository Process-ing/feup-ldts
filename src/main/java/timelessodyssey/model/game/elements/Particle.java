package timelessodyssey.model.game.elements;

import com.googlecode.lanterna.TextColor;

public class Particle extends Element {
    private int size;
    private TextColor color;
    private double velocity;

    public Particle(int x, int y, int size, TextColor color, double velocity) {
        super(x, y);
        this.size = size;
        this.color = color;
        this.velocity = velocity;
    }


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public TextColor getColor() {
        return color;
    }

    public void setColor(TextColor color) {
        this.color = color;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }
}
