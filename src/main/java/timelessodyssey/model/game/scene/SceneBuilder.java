package timelessodyssey.model.game.scene;

import com.googlecode.lanterna.TextColor;
import timelessodyssey.model.Vector;
import timelessodyssey.model.game.elements.Player;
import timelessodyssey.model.game.elements.Spike;
import timelessodyssey.model.game.elements.Tile;
import timelessodyssey.model.game.elements.particles.Particle;
import timelessodyssey.model.game.elements.particles.Snow;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.nio.charset.StandardCharsets.UTF_8;

public class SceneBuilder {
    private final List<String> lines;
    private final int sceneCode;

    public Scene createScene() {
        int numberParticles = 30;
        Scene scene = new Scene(getWidth(), getHeight(), sceneCode);

        scene.setPlayer(createPlayer());
        scene.setTiles(createWalls());
        scene.setSpikes(createSpikes());
        scene.setTransitionPosition(createTransitionPosition());
        scene.setParticles(createParticles(numberParticles, scene));

        return scene;
    }

    public SceneBuilder(int n) throws IOException {
        this.sceneCode = n;
        URL resource = getClass().getClassLoader().getResource("levels/playground.lvl");
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

    private int getWidth() {
        int width = 0;
        for (String line : lines)
            width = Math.max(width, line.length());
        return width;
    }

    private int getHeight() {
        return lines.size();
    }

    private Tile[][] createWalls() {
        Tile[][] walls = new Tile[lines.size()-2][lines.get(0).length()+1];

        for (int y = 0; y < lines.size() - 2; y++) {
            String line = lines.get(y);
            Tile[] lineTiles = new Tile[21];
            for (int x = 0; x < line.length(); x++)
                if (line.charAt(x) == '#') {
                    lineTiles[x] = new Tile(x * 8, y * 8);
                }
                else{
                    lineTiles[x] = null;
                }
            lineTiles[20] = null;
            walls[y] = lineTiles;
        }
        return walls;
    }

    private Spike[][] createSpikes() {
        Spike[][] spikes = new Spike[lines.size()][];

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            Spike[] lineSpikes = new Spike[line.length()];
            for (int x = 0; x < line.length(); x++)
                if (line.charAt(x) == '^')
                    lineSpikes[x] = new Spike(x*8, y*8);
            spikes[y] = lineSpikes;
        }
        return spikes;
    }

    private Player createPlayer() {
        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++)
                if (line.charAt(x) == 'P') return new Player(x * Tile.SIZE, y * Tile.SIZE);
        }
        return null;
    }

    private Vector createTransitionPosition() {
        return new Vector(Integer.parseInt(lines.get(lines.size()-2)) * 8,
                            Integer.parseInt(lines.get(lines.size()-1)) * 8);
    }

    private List<Particle> createParticles(int number, Scene scene) {
        List<Particle> particles = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < number; i++) {
            Particle particle = new Snow(
                    random.nextInt(scene.getWidth() * Tile.SIZE),
                    random.nextInt(scene.getHeight() * Tile.SIZE),
                    random.nextInt(2, 5) / 2,
                    TextColor.ANSI.WHITE_BRIGHT,
                    random.nextDouble(.5, 2)
            );
            particles.add(particle);
        }

        return particles;
    }
}
