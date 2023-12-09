package timelessodyssey.view.elements;

import timelessodyssey.gui.GUI;
import timelessodyssey.model.game.elements.Player;
import timelessodyssey.view.Sprite;

import java.io.IOException;

public class PlayerViewer implements ElementViewer<Player> {
    private final Sprite[] sprites;

    public PlayerViewer() throws IOException {
        sprites = new Sprite[14];
        for (int i = 0; i < 7; i++)
            sprites[i] = new Sprite("sprites/player/player-right-" + (i + 1) + ".png");
        for (int i = 0; i < 7; i++)
            sprites[i + 7] = new Sprite("sprites/player/player-left-" + (i + 1) + ".png");
    }

    @Override
    public void draw(Player model, GUI gui, long frameCount) {
        getSprite(model, frameCount).draw(gui, model.getPosition().x(), model.getPosition().y());
    }

    private Sprite getSprite(Player model, long frameCount) {
        int spriteIndex;
        if (model.isJumping())
            spriteIndex = 5;
        else if (model.isFalling() && model.getVelocity().y() > 0.5)
            spriteIndex = 6;
        else if (Math.abs(model.getVelocity().x()) > 1.7)
            spriteIndex = 3 + (int) ((frameCount / 3) % 2);
        else if (Math.abs(model.getVelocity().x()) > 0.7)
            spriteIndex = 1 + (int) ((frameCount / 3) % 2);
        else
            spriteIndex = 0;

        if (!model.isFacingRight())
            spriteIndex += 7;
        return sprites[spriteIndex];
    }
}
