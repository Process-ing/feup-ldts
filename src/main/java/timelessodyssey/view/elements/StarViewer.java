package timelessodyssey.view.elements;

import com.googlecode.lanterna.TextColor;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.game.elements.Star;

public class StarViewer implements ElementViewer<Star>{

    @Override
    public void draw(Star model, GUI gui, long frameCount) {
        for (double w = 3; w < 5; w++){
            for (double h = 3; h < 5; h++){
                gui.drawPixel(model.getPosition().x() + w, model.getPosition().y() + h, new TextColor.RGB(255, 255, 255));
            }
        }
        gui.drawPixel(model.getPosition().x() + 3, model.getPosition().y()+1, new TextColor.RGB(255, 255, 255));
        gui.drawPixel(model.getPosition().x() + 3, model.getPosition().y()+2, new TextColor.RGB(255, 255, 255));
        gui.drawPixel(model.getPosition().x() + 4, model.getPosition().y()+2, new TextColor.RGB(255, 255, 255));

        gui.drawPixel(model.getPosition().x() + 1, model.getPosition().y() + 4, new TextColor.RGB(255, 255, 255));
        gui.drawPixel(model.getPosition().x() + 2, model.getPosition().y() + 3, new TextColor.RGB(255, 255, 255));
        gui.drawPixel(model.getPosition().x() + 2, model.getPosition().y() + 4, new TextColor.RGB(255, 255, 255));

        gui.drawPixel(model.getPosition().x() + 5, model.getPosition().y() + 3, new TextColor.RGB(255, 255, 255));
        gui.drawPixel(model.getPosition().x() + 5, model.getPosition().y() + 4, new TextColor.RGB(255, 255, 255));
        gui.drawPixel(model.getPosition().x() + 6, model.getPosition().y() + 3, new TextColor.RGB(255, 255, 255));

        gui.drawPixel(model.getPosition().x() + 3, model.getPosition().y() + 5, new TextColor.RGB(255, 255, 255));
        gui.drawPixel(model.getPosition().x() + 4, model.getPosition().y() + 5, new TextColor.RGB(255, 255, 255));
        gui.drawPixel(model.getPosition().x() + 4, model.getPosition().y() + 6, new TextColor.RGB(255, 255, 255));

    }
}
