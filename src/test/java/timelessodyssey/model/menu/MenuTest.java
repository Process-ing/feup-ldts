package timelessodyssey.model.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenuTest {
    private Menu menu;
    private List<Entry> entryList;

    @BeforeEach
    public void setup(){
        this.menu = new Menu(){
            @Override
            protected List<Entry> createEntries() {
                Entry a = new Entry(0, 0, Entry.Type.START_GAME);
                Entry b = new Entry(0, 0, Entry.Type.SETTINGS);
                Entry c = new Entry(0, 0, Entry.Type.EXIT);
                return Arrays.asList(a, b, c);
            }
        };

        this.entryList =  menu.getEntries();
    }

    @Test
    public void menuTestEntries(){
        assertEquals(3, menu.getNumberEntries());
        assertEquals(entryList, menu.getEntries());

        assertEquals(entryList.get(0), menu.getCurrentEntry());

        menu.moveUp();

        assertEquals(entryList.get(2), menu.getCurrentEntry());

        menu.moveDown();

        assertEquals(entryList.get(0), menu.getCurrentEntry());
    }
}
