package timelessodyssey.view.menu;

import timelessodyssey.gui.GUI;
import timelessodyssey.view.Sprite;
import timelessodyssey.view.SpriteLoader;

import java.io.IOException;

public class LogoViewer {
    private final Sprite sprite;

    public LogoViewer(SpriteLoader spriteLoader) throws IOException {
        this.sprite = spriteLoader.get("menu/logo.png");
    }

    public void draw(GUI gui, int x, int y) {
        sprite.draw(gui, x, y);
    }
}
