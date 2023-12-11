package timelessodyssey.view.elements;

import com.googlecode.lanterna.TextColor;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.game.elements.Star;

public class StarViewer implements ElementViewer<Star>{

    @Override
    public void draw(Star model, GUI gui) {
        for (double w = 1; w < 8; w++){
            for (double h = 1; h < 8; h++){
                gui.drawPixel(model.getPosition().x() + w, model.getPosition().y() + h, new TextColor.RGB(255, 255, 255));
            }
        }
    }
}
