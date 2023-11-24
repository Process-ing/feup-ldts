package timelessodyssey.view.screens;

import com.googlecode.lanterna.TextColor;
import org.w3c.dom.Text;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.game.elements.Element;
import timelessodyssey.model.menu.Entry;
import timelessodyssey.model.menu.Menu;
import timelessodyssey.view.elements.ElementViewer;
import timelessodyssey.view.menu.EntryViewer;

import java.io.IOException;
import java.util.List;

public class MenuViewer extends ScreenViewer<Menu> {
    public MenuViewer(Menu model) {
        super(model);
    }

    @Override
    public void draw(GUI gui) throws IOException {
        gui.clear();

        this.drawEntries(gui, getModel().getEntries(), new EntryViewer());

        gui.refresh();
    }

    private void drawEntries(GUI gui, List<Entry> entries, EntryViewer viewer) throws IOException {
        TextColor.RGB selected = new TextColor.RGB(255,255,50);
        TextColor.RGB deselected = new TextColor.RGB(255,255,255);
        for (int idx = 0; idx < entries.size(); idx++){
            if (getModel().isSelected(idx)){
                viewer.draw(entries.get(idx), gui, selected);
            }
            else {
                viewer.draw(entries.get(idx), gui, deselected);
            }
        }
    }
}
