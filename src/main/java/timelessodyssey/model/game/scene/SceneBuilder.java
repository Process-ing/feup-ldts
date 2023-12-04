package timelessodyssey.model.game.scene;

import timelessodyssey.model.game.elements.Player;
import timelessodyssey.model.game.elements.Spike;
import timelessodyssey.model.game.elements.Tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class SceneBuilder {
    private final List<String> lines;

    public Scene createScene() {
        Scene scene = new Scene(getWidth(), getHeight());

        scene.setPlayer(createPlayer());
        scene.setTiles(createWalls());
        scene.setSpikes(createSpikes());

        return scene;
    }

    public SceneBuilder() throws IOException {
        URL resource = getClass().getClassLoader().getResource("levels/level.lvl");
        assert resource != null;
        BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(resource.getFile()), UTF_8);

        lines = readLines(bufferedReader);
    }

    private List<String> readLines(BufferedReader bufferedReader) throws IOException {
        List<String> lines = new ArrayList<>();
        for (String line; (line = bufferedReader.readLine()) != null; )
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

        for (int y = 0; y < lines.size(); y ++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x ++)
                if (line.charAt(x) == '#') walls.add(new Tile(x * 8, y * 8));
        }

        return walls;
    }

    protected List<Spike> createSpikes() {
        List<Spike> spikes = new ArrayList<>();

        for (int y = 0; y < lines.size(); y ++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x ++)
                if (line.charAt(x) == '^') spikes.add(new Spike(x * 8, y * 8));
        }

        return spikes;
    }

    protected Player createPlayer() {
        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++)
                if (line.charAt(x) == 'P') return new Player(x * 8, y * 8);
        }
        return null;
    }
}
