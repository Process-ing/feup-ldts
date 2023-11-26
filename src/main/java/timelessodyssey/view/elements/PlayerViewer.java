package timelessodyssey.view.elements;

import com.googlecode.lanterna.TextColor;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.game.elements.Player;
import timelessodyssey.view.Sprite;

import java.io.IOException;

public class PlayerViewer implements ElementViewer<Player> {
    private Sprite sprite = new Sprite("sprites/player/player.png");

    public PlayerViewer() throws IOException {
    }

    @Override
    public void draw(Player model, GUI gui) {
        sprite.draw(gui, model.getPosition().x(), model.getPosition().y());
    }
}
