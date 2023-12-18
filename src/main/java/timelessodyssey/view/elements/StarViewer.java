package timelessodyssey.view.elements;

import timelessodyssey.gui.GUI;
import timelessodyssey.model.game.elements.Star;
import timelessodyssey.view.Sprite;
import timelessodyssey.view.SpriteLoader;

import java.io.IOException;

public class StarViewer implements ElementViewer<Star>{
    private final Sprite sprite;

    public StarViewer(SpriteLoader spriteLoader) throws IOException {
        this.sprite = spriteLoader.get("sprites/star.png");
    }

    @Override
    public void draw(Star model, GUI gui, long frameCount) {
        sprite.draw(gui, model.getPosition().x(), model.getPosition().y());
    }
}
