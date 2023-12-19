package timelessodyssey.view.screens;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import timelessodyssey.gui.ResizableGUI;
import timelessodyssey.model.menu.Entry;
import timelessodyssey.model.menu.Menu;
import timelessodyssey.view.ViewerProvider;
import timelessodyssey.view.menu.EntryViewer;
import timelessodyssey.view.menu.LogoViewer;

import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.*;

public class MenuViewerTest {
    private final int SCREEN_WIDTH = 160;
    private final int SCREEN_HEIGHT = 90;

    private ViewerProvider viewerProvider;
    private EntryViewer entryViewer;
    private LogoViewer logoViewer;
    private Menu menu;
    private ResizableGUI gui;

    @BeforeEach
    public void setup() {
        this.viewerProvider = mock(ViewerProvider.class);
        this.entryViewer = mock(EntryViewer.class);
        this.logoViewer = mock(LogoViewer.class);
        this.menu = new Menu() {
            @Override
            protected List<Entry> createEntries() {
                return List.of(new Entry(10, 20, Entry.Type.START_GAME), new Entry(10, 30, Entry.Type.EXIT));
            }
        };
        this.gui = mock(ResizableGUI.class);

        when(viewerProvider.getEntryViewer()).thenReturn(entryViewer);
        when(viewerProvider.getLogoViewer()).thenReturn(logoViewer);

        when(gui.getWidth()).thenReturn(SCREEN_WIDTH);
        when(gui.getHeight()).thenReturn(SCREEN_HEIGHT);
    }

    @Test
    public void draw() throws IOException {
        MenuViewer<Menu> menuViewer = new MenuViewer<>(menu, viewerProvider);
        long frameCount = 0;

        menuViewer.draw(gui, frameCount);

        verify(gui, times(1)).clear();

        verify(gui, times(4))
                .drawRectangle(anyDouble(), anyDouble(), anyInt(), anyInt(), eq(MenuViewer.frameColor));
        verify(gui, times(1)).drawRectangle(1.0, 1.0,
                SCREEN_WIDTH - 2, SCREEN_HEIGHT - 2, MenuViewer.backgroundColor);

        verify(entryViewer, times(1))
                .draw(menu.getCurrentEntry(), gui, MenuViewer.selectedColor);
        verify(entryViewer, times(1))
                .draw(menu.getEntries().get(1), gui, MenuViewer.unselectedColor);
        verify(logoViewer, times(1)).draw(same(gui), anyInt(), anyInt());

        verify(gui, times(1)).refresh();
    }
}
