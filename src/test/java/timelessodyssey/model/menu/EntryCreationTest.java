package timelessodyssey.model.menu;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class EntryCreationTest {
    @Test
    public void MainMenuEntriesTest(){
        MainMenu mainMenu = new MainMenu();
        List<Entry> entries = mainMenu.createEntries();

        assertNotEquals(Collections.EMPTY_LIST, entries);
    }

    @Test
    public void SettingsMenuEntriesTest(){
        SettingsMenu settingsMenu = new SettingsMenu();
        List<Entry> entries = settingsMenu.createEntries();

        assertNotEquals(Collections.EMPTY_LIST, entries);
    }
}
