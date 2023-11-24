package timelessodyssey.view.sprites;

import com.googlecode.lanterna.TextColor;
import timelessodyssey.model.Position;

public class Pixel {
    private Position position;
    private TextColor.RGB color;

    public Position getPosition() {
        return position;
    }

    public TextColor.RGB getColor() {
        return color;
    }
}
