package timelessodyssey.model.game.scene;

import timelessodyssey.model.game.elements.Player;
import timelessodyssey.model.game.elements.Tile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SceneBuilder {

    private final List<String> lines;

    public Scene createScene() {
        Scene scene = new Scene(getWidth(), getHeight());

        scene.setPlayer(createPlayer());
        scene.setTiles(createWalls());

        return scene;
    }

    public SceneBuilder() throws IOException {
        URL resource = getClass().getClassLoader().getResource("levels/level.lvl");
        BufferedReader br = new BufferedReader(new FileReader(resource.getFile()));

        lines = readLines(br);
    }

    private List<String> readLines(BufferedReader br) throws IOException {
        List<String> lines = new ArrayList<>();
        for (String line; (line = br.readLine()) != null; )
            lines.add(line);
        return lines;
    }

    protected int getWidth() {
        int width = 0;
        for (String line : lines)
            width = Math.max(width, line.length());
        return width;
    }

    protected int getHeight() {
        return lines.size();
    }

    protected List<Tile> createWalls() {
        List<Tile> walls = new ArrayList<>();

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++)
                if (line.charAt(x) == '#') walls.add(new Tile(x, y));
        }

        return walls;
    }

    protected Player createPlayer() {
        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++)
                if (line.charAt(x) == 'P') return new Player(x, y);
        }
        return null;
    }
}
