package timelessodyssey.view.menu;

import com.googlecode.lanterna.TextColor;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.menu.Entry;

import java.io.IOException;

public class EntryViewer {

    public void draw(Entry model, GUI gui, TextColor.RGB color) throws IOException {
        for (int oidx = 0; oidx < 8; oidx++){
            for (int idx = 0; idx < 6; idx++) {
                gui.drawPixel(model.getPosition().x() + idx + oidx*8, model.getPosition().y(), color);
                gui.drawPixel(model.getPosition().x() + idx + oidx*8, model.getPosition().y() + 1, color);
                gui.drawPixel(model.getPosition().x() + idx + oidx*8, model.getPosition().y() + 2, color);
                gui.drawPixel(model.getPosition().x() + idx + oidx*8, model.getPosition().y() + 3, color);
                gui.drawPixel(model.getPosition().x() + idx + oidx*8, model.getPosition().y() + 4, color);
                gui.drawPixel(model.getPosition().x() + idx + oidx*8, model.getPosition().y() + 5, color);
                gui.drawPixel(model.getPosition().x() + idx + oidx*8, model.getPosition().y() + 6, color);
                gui.drawPixel(model.getPosition().x() + idx + oidx*8, model.getPosition().y() + 7, color);
            }
        }
    }
}
