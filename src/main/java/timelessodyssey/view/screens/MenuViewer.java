package timelessodyssey.view.screens;

import com.googlecode.lanterna.TextColor;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.menu.Entry;
import timelessodyssey.model.menu.Menu;
import timelessodyssey.view.Sprite;
import timelessodyssey.view.menu.EntryViewer;

import java.io.IOException;
import java.util.List;

public class MenuViewer<T extends Menu> extends ScreenViewer<T> {
    private static final TextColor unselectedColor = new TextColor.RGB(234,234,234);
    private static final TextColor selectedColor = new TextColor.RGB(255,234,69);
    private static final TextColor backgroundColor = new TextColor.RGB(28, 28, 46);
    private static final TextColor frameColor = new TextColor.RGB(255, 255, 255);
    private final EntryViewer entryViewer;

    private final Sprite logoSprite;

    public MenuViewer(T model) throws IOException {
        super(model);
        this.entryViewer = new EntryViewer();
        this.logoSprite = loadLogo();
    }

    @Override
    public void draw(GUI gui, long frameCount) throws IOException {
        gui.clear();
        drawBackgroundAndFrame(gui);
        drawEntries(gui, getModel().getEntries());
        logoSprite.draw(gui, 10, 10);
        gui.refresh();
    }

    public Sprite loadLogo() throws IOException {
        return new Sprite("sprites/player/player-left-1.png");
    }

    private void drawBackgroundAndFrame(GUI gui) {
        gui.drawRectangle(0, 0, gui.getWidth(), 1, frameColor);
        gui.drawRectangle(0, gui.getHeight() - 1, gui.getWidth(), 1, frameColor);
        gui.drawRectangle(0, 1, 1, gui.getHeight() - 2, frameColor);
        gui.drawRectangle(gui.getWidth() - 1, 1, 1, gui.getHeight() - 2, frameColor);
        gui.drawRectangle(1, 1, gui.getWidth() - 2, gui.getHeight() - 2, backgroundColor);
    }

    private void drawEntries(GUI gui, List<Entry> entries) {
        for (Entry entry: entries)
            entryViewer.draw(entry, gui, getModel().getCurrentEntry() == entry ? selectedColor : unselectedColor);
    }
}
