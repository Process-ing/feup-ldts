package timelessodyssey.view.screens;

import com.googlecode.lanterna.TextColor;
import org.w3c.dom.Text;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.game.elements.Element;
import timelessodyssey.model.menu.Menu;
import timelessodyssey.view.elements.ElementViewer;

import java.io.IOException;
import java.util.List;

public class MenuViewer extends ScreenViewer<Menu> {
    public MenuViewer(Menu model) {
        super(model);
    }

    @Override
    public void draw(GUI gui) throws IOException {
        gui.clear();

        this.drawEntries(gui, getModel().getEntries());

        gui.refresh();
    }

    private void drawEntries(GUI gui, List<String> entries) throws IOException {
        TextColor.RGB selected = new TextColor.RGB(255,255,50);
        TextColor.RGB deselected = new TextColor.RGB(255,255,255);
        for (int idx = 0; idx < entries.size(); idx++){
            if (getModel().isSelected(idx)){
                gui.drawPixel(80, 40 + 5 * idx, selected);
                gui.drawPixel(81, 40 + 5 * idx, selected);
                gui.drawPixel(82, 40 + 5 * idx, selected);
                gui.drawPixel(83, 40 + 5 * idx, selected);
                gui.drawPixel(84, 40 + 5 * idx, selected);
                gui.drawPixel(85, 40 + 5 * idx, selected);
                gui.drawPixel(86, 40 + 5 * idx, selected);
            }
            else {
                gui.drawPixel(80, 40 + 5 * idx, deselected);
                gui.drawPixel(81, 40 + 5 * idx, deselected);
                gui.drawPixel(82, 40 + 5 * idx, deselected);
                gui.drawPixel(83, 40 + 5 * idx, deselected);
                gui.drawPixel(84, 40 + 5 * idx, deselected);
                gui.drawPixel(85, 40 + 5 * idx, deselected);
                gui.drawPixel(86, 40 + 5 * idx, deselected);
            }
        }
    }
}
