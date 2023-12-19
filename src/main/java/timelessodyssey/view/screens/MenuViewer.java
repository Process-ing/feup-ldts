package timelessodyssey.view.screens;

import com.googlecode.lanterna.TextColor;
import timelessodyssey.gui.ResizableGUI;
import timelessodyssey.model.menu.Entry;
import timelessodyssey.model.menu.Menu;
import timelessodyssey.view.ViewerProvider;
import timelessodyssey.view.menu.EntryViewer;
import timelessodyssey.view.menu.LogoViewer;

import java.io.IOException;
import java.util.List;

public class MenuViewer<T extends Menu> extends ScreenViewer<T> {
    public static final TextColor unselectedColor = new TextColor.RGB(234,234,234);
    public static final TextColor selectedColor = new TextColor.RGB(99,155,255);
    public static final TextColor backgroundColor = new TextColor.RGB(28, 28, 46);
    public static final TextColor frameColor = new TextColor.RGB(255, 255, 255);
    private final EntryViewer entryViewer;
    private final LogoViewer logoViewer;

    public MenuViewer(T model, ViewerProvider viewerProvider) {
        super(model);
        this.entryViewer = viewerProvider.getEntryViewer();
        this.logoViewer = viewerProvider.getLogoViewer();
    }

    @Override
    public void draw(ResizableGUI gui, long frameCount) throws IOException {
        gui.clear();
        drawBackgroundAndFrame(gui, frameColor, backgroundColor);
        drawEntries(gui, getModel().getEntries());
        logoViewer.draw(gui, 44, 16);
        gui.refresh();
    }

    private void drawEntries(ResizableGUI gui, List<Entry> entries) {
        for (Entry entry: entries)
            entryViewer.draw(entry, gui, getModel().getCurrentEntry() == entry ? selectedColor : unselectedColor);
    }
}
