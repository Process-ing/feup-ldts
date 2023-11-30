package timelessodyssey.view.screens;

import com.googlecode.lanterna.TextColor;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.menu.Entry;
import timelessodyssey.model.menu.Menu;
import timelessodyssey.view.menu.EntryViewer;

import java.io.IOException;
import java.util.List;

public class MenuViewer extends ScreenViewer<Menu> {
    private final EntryViewer entryViewer;

    public MenuViewer(Menu model) {
        super(model);
        this.entryViewer = new EntryViewer();
    }

    @Override
    public void draw(GUI gui) throws IOException {
        gui.clear();

        // Background color
        TextColor.RGB background = new TextColor.RGB(28, 28, 70);
        for (int w = 0; w < 160; w++) {
            for (int h = 0; h < 90; h++) {
                gui.drawPixel(w, h, background);
            }
        }
        TextColor.RGB white = new TextColor.RGB(255,255,255);
        for (int w = 0; w < 160; w++){
            gui.drawPixel(w, 0, white);
            gui.drawPixel(w, 89, white);
        }
        for (int h = 1; h < 89; h++){
            gui.drawPixel(0, h, white);
            gui.drawPixel(159, h, white);
        }

        drawEntries(gui, getModel().getEntries(), entryViewer);

        gui.refresh();
    }

    private void drawEntries(GUI gui, List<Entry> entries, EntryViewer viewer) throws IOException {
        TextColor.RGB selected = new TextColor.RGB(255,234,69);
        TextColor.RGB deselected = new TextColor.RGB(234,234,234);
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
