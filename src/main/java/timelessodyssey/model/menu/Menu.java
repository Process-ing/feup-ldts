package timelessodyssey.model.menu;

import java.util.Arrays;
import java.util.List;

import static java.lang.Math.abs;

public class Menu {
    private final List<Entry> entries;
    private int currentEntry = 0;

    public Menu() {
        Entry start = new Entry(5, 55, "Start");
        Entry settings = new Entry(5, 65, "Settings");
        Entry exit = new Entry(5, 75, "Exit");
        this.entries = Arrays.asList(start, settings, exit);
    }

    public List<Entry> getEntries(){
        return entries;
    }

    public int getNumberEntries() {
        return this.entries.size();
    }

    public void moveDown() {
        currentEntry++;
        currentEntry = currentEntry % getNumberEntries();
    }

    public void moveUp() {
        currentEntry += getNumberEntries() - 1;
        currentEntry = currentEntry % getNumberEntries();
    }

    public Entry getEntry(int i) {
        return entries.get(i);
    }

    public boolean isSelected(int i) {
        return currentEntry == i;
    }

    public boolean isSelectedExit() {
        return isSelected(getNumberEntries()-1);
    }

    public boolean isSelectedStart() {
        return isSelected(0);
    }
}
