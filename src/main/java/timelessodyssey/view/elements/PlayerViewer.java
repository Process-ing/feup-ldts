package timelessodyssey.view.elements;

import com.googlecode.lanterna.TextColor;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.game.elements.Player;

public class PlayerViewer implements ElementViewer<Player> {
    @Override
    public void draw(Player model, GUI gui) {
        TextColor.RGB color = new TextColor.RGB(255,255,255);
        gui.drawPixel(model.getPosition().x(), model.getPosition().y(), color);
        gui.drawPixel(model.getPosition().x() + 1, model.getPosition().y(), color);
        gui.drawPixel(model.getPosition().x() - 1, model.getPosition().y(), color);
        gui.drawPixel(model.getPosition().x(), model.getPosition().y() + 1, color);
        gui.drawPixel(model.getPosition().x(), model.getPosition().y() - 1, color);
    }
}
