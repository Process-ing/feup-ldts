package timelessodyssey.view.elements;

import timelessodyssey.gui.GUI;
import timelessodyssey.model.game.elements.Player;

public class PlayerViewer implements ElementViewer<Player> {
    @Override
    public void draw(Player model, GUI gui) {
        gui.drawPixel(model.getPosition().x(), model.getPosition().y(), "#FFFFFF");
        gui.drawPixel(model.getPosition().x() + 1, model.getPosition().y(), "#FFFFFF");
        gui.drawPixel(model.getPosition().x() - 1, model.getPosition().y(), "#FFFFFF");
        gui.drawPixel(model.getPosition().x(), model.getPosition().y() + 1, "#FFFFFF");
        gui.drawPixel(model.getPosition().x(), model.getPosition().y() - 1, "#FFFFFF");
    }
}
