package timelessodyssey.view.elements;

import com.googlecode.lanterna.TextColor;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.game.elements.Star;

public class StarViewer implements ElementViewer<Star>{

    @Override
    public void draw(Star model, GUI gui, long frameCount) {
        for (double w = 2; w < 6; w++){
            for (double h = 2; h < 6; h++){
                gui.drawPixel(model.getPosition().x() + w, model.getPosition().y() + h, new TextColor.RGB(255, 255, 255));
            }
        }
        gui.drawPixel(model.getPosition().x() + 3, model.getPosition().y(), new TextColor.RGB(255, 255, 255));
        gui.drawPixel(model.getPosition().x() + 3, model.getPosition().y()+1, new TextColor.RGB(255, 255, 255));
        gui.drawPixel(model.getPosition().x() + 4, model.getPosition().y()+1, new TextColor.RGB(255, 255, 255));

        gui.drawPixel(model.getPosition().x(), model.getPosition().y() + 4, new TextColor.RGB(255, 255, 255));
        gui.drawPixel(model.getPosition().x() + 1, model.getPosition().y() + 3, new TextColor.RGB(255, 255, 255));
        gui.drawPixel(model.getPosition().x() + 1, model.getPosition().y() + 4, new TextColor.RGB(255, 255, 255));

        gui.drawPixel(model.getPosition().x() + 6, model.getPosition().y() + 3, new TextColor.RGB(255, 255, 255));
        gui.drawPixel(model.getPosition().x() + 7, model.getPosition().y() + 3, new TextColor.RGB(255, 255, 255));

        gui.drawPixel(model.getPosition().x() + 3, model.getPosition().y() + 6, new TextColor.RGB(255, 255, 255));
        gui.drawPixel(model.getPosition().x() + 4, model.getPosition().y() + 6, new TextColor.RGB(255, 255, 255));
        gui.drawPixel(model.getPosition().x() + 4, model.getPosition().y() + 7, new TextColor.RGB(255, 255, 255));

    }
}
