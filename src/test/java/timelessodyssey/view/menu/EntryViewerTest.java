package timelessodyssey.view.menu;

import com.googlecode.lanterna.TextColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import timelessodyssey.gui.ResizableGUI;
import timelessodyssey.model.menu.Entry;
import timelessodyssey.view.text.GameTextViewer;
import timelessodyssey.view.text.TextViewer;

import static org.mockito.Mockito.*;

public class EntryViewerTest {
    private ResizableGUI gui;
    private TextViewer textViewer;
    private EntryViewer entryViewer;

    @BeforeEach
    public void setup() {
        this.gui = mock(ResizableGUI.class);
        this.textViewer = mock(GameTextViewer.class);

        this.entryViewer = new EntryViewer(textViewer);
    }

    @Test
    public void drawTesting(){
        Entry[] entries = new Entry[5];
        for (int idx = 0; idx < Entry.Type.values().length; idx++){
            entries[idx] = new Entry(0, 10*idx, Entry.Type.values()[idx]);
        }
        for (Entry e : entries){
            entryViewer.draw(e, gui, TextColor.ANSI.BLACK);
        }
        verify(textViewer, times(5))
                .draw(anyString(), anyDouble(), anyDouble(), eq(TextColor.ANSI.BLACK), any());
        verify(textViewer, times(0))
                .draw(eq(""), anyDouble(), anyDouble(), eq(TextColor.ANSI.BLACK), any());
    }

    @Test
    public void resolutionLabels(){
        Entry entry = new Entry(0,0, Entry.Type.RESOLUTION);
        for (ResizableGUI.Resolution gr : ResizableGUI.Resolution.values()){
            when(gui.getResolution()).thenReturn(gr);
            entryViewer.draw(entry, gui, TextColor.ANSI.BLACK);
        }
        verify(textViewer, times(4))
                .draw(anyString(), anyDouble(), anyDouble(), eq(TextColor.ANSI.BLACK), any());
        verify(textViewer, times(0))
                .draw(eq(""), anyDouble(), anyDouble(), eq(TextColor.ANSI.BLACK), any());
    }


}
