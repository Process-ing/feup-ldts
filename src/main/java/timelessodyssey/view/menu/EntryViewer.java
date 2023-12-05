package timelessodyssey.view.menu;

import com.googlecode.lanterna.TextColor;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.menu.Entry;
import timelessodyssey.view.text.GameTextViewer;
import timelessodyssey.view.text.TextViewer;

import java.io.IOException;

public class EntryViewer {
    private final TextViewer textViewer;

    public EntryViewer() throws IOException {
        this.textViewer = new GameTextViewer();
    }


    public void draw(Entry model, GUI gui, TextColor color) {
        textViewer.draw(model.getText(), model.getPosition().x(), model.getPosition().y(), color, gui);
    }
}
