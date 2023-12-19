package timelessodyssey.model.menu;

import java.util.List;

public abstract class Menu {
    private final List<Entry> entries;
    private int currentEntry = 0;

    public Menu() {
        this.entries = createEntries();
    }

    protected abstract List<Entry> createEntries();

    public List<Entry> getEntries() {
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

    public Entry getCurrentEntry() {
        return entries.get(currentEntry);
    }
}
