package timelessodyssey.view.elements;

import timelessodyssey.gui.GUI;
import timelessodyssey.model.game.elements.Player;
import timelessodyssey.view.Sprite;

import java.io.IOException;

public class PlayerViewer implements ElementViewer<Player> {
    private final Sprite sprite;

    public PlayerViewer() throws IOException {
        sprite = new Sprite("sprites/player/playersq.png");
    }

    @Override
    public void draw(Player model, GUI gui) {
        sprite.draw(gui, model.getPosition());
    }
}
