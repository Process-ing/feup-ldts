package timelessodyssey.model.game.scene;

import timelessodyssey.model.Vector;
import timelessodyssey.model.game.elements.Element;
import timelessodyssey.model.game.elements.Spike;
import timelessodyssey.model.game.elements.Star;
import timelessodyssey.model.game.elements.Tile;
import timelessodyssey.model.game.elements.particles.Particle;
import timelessodyssey.model.game.elements.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Scene {
    private final int width;
    private final int height;
    private final int sceneCode;
    private final double gravity;
    private final double friction;

    private Player player;
    private Tile[][] tiles;
    private Spike[][] spikes;
    private Star[][] stars;
    private Tile[][] goals;
    private List<Particle> snow;
    private List<Particle> deathParticles;
    private Vector transitionPositionBegin;
    private Vector transitionPositionEnd;

    private Vector startingPosition;

    public Scene(int width, int height, int sceneCode) {
        this.width = width;
        this.height = height;
        this.sceneCode = sceneCode;
        this.gravity = 0.25;
        this.friction = 0.75;
        this.tiles = new Tile[height][width];
        this.goals = new Tile[height][width];
        this.spikes = new Spike[height][width];
        this.stars = new Star[height][width];
        this.snow = new ArrayList<>();
        this.deathParticles = new ArrayList<>();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getGravity() {
        return gravity;
    }

    public double getFriction() {
        return friction;
    }

    public int getSceneCode() {
        return sceneCode;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }

    public Spike[][] getSpikes() {
        return spikes;
    }

    public void setSpikes(Spike[][] spikes) {
        this.spikes = spikes;
    }

    public Star[][] getStars() {
        return stars;
    }

    public void setStars(Star[][] stars) {
        this.stars = stars;
    }

    public Tile[][] getGoals() {
        return goals;
    }

    public void setGoals(Tile[][] goals) {
        this.goals = goals;
    }

    public List<Particle> getSnow() {
        return snow;
    }

    public void setSnow(List<Particle> snow) {
        this.snow = snow;
    }

    public Vector getTransitionPositionBegin() {
        return transitionPositionBegin;
    }

    public void setTransitionPositionBegin(Vector transitionPositionBegin) {
        this.transitionPositionBegin = transitionPositionBegin;
    }

    public Vector getTransitionPositionEnd() {
        return transitionPositionEnd;
    }

    public void setTransitionPositionEnd(Vector transitionPositionEnd) {
        this.transitionPositionEnd = transitionPositionEnd;
    }

    public boolean isAtTransitionPosition() {
        double x1 = player.getPosition().x(), x2 = player.getPosition().x() + player.getWidth();
        double y1 = player.getPosition().y(), y2 = player.getPosition().y() + player.getHeight();
        return x1 <= transitionPositionEnd.x() && x2 >= transitionPositionBegin.x()
                && y1 <= transitionPositionEnd.y() && y2 >= transitionPositionBegin.y();
    }

    public Vector getStartingPosition() {
        return startingPosition;
    }

    public void setStartingPosition(Vector startingPosition) {
        this.startingPosition = startingPosition;
    }

    private boolean checkOutsideScene(double x1, double x2, double y1, double y2) {
        return x1 < 0 || x2 >= width * Tile.SIZE || y1 < 0 || y2 >= height * Tile.SIZE;
    }

    private boolean checkCollision(double x1, double x2, double y1, double y2, Element[][] layer) {
        if (checkOutsideScene(x1, x2, y1, y2))
            return true;
        for (int tileY: List.of((int)y1 / Tile.SIZE, (int)y2 / Tile.SIZE)) {
            for (int tileX: List.of((int)x1 / Tile.SIZE, (int)x2 / Tile.SIZE)) {
                if (layer[tileY][tileX] != null)
                    return true;
            }
        }
        return false;
    }

    public boolean collidesLeft(Vector position, Vector size) {
        double x = position.x(), y = position.y();
        return checkCollision(x, x + 1, y, y + size.y() - 1, tiles);
    }

    public boolean collidesRight(Vector position, Vector size) {
        double x = position.x(), y = position.y();
        return checkCollision(x + size.x() - 1, x + size.x() - 1, y, y + size.y() - 1, tiles);
    }

    public boolean collidesUp(Vector position, Vector size) {
        double x = position.x(), y = position.y();
        return checkCollision(x, x + size.x() - 1, y, y + 1, tiles);
    }

    public boolean collidesDown(Vector position, Vector size) {
        double x = position.x(), y = position.y();
        return checkCollision(x, x + size.x() - 1, y + size.y() - 2, y + size.y() - 1, tiles);
    }

    public boolean isPlayerDying() {
        final int spikeHeightDiff = Tile.SIZE - Spike.SPIKE_HEIGHT;
        double x = player.getPosition().x(), y = player.getPosition().y();
        return checkCollision(x, x + player.getWidth() - 1, y, y + player.getHeight() - 1 - spikeHeightDiff, spikes);
    }

    public boolean updateStars() {
        double x = getPlayer().getPosition().x(), y = getPlayer().getPosition().y();
        double width = player.getWidth(), height = player.getHeight();

        boolean caughtStars = false;
        for (int tileY: List.of((int)y / Tile.SIZE, (int)(y + height - 1) / Tile.SIZE)) {
            for (int tileX: List.of((int)x / Tile.SIZE, (int)(x + width - 1) / Tile.SIZE)) {
                if (stars[tileY][tileX] != null) {
                    caughtStars = true;
                    player.increaseStars();
                    stars[tileY][tileX] = null;
                }
            }
        }
        return caughtStars;
    }

    public List<Particle> getDeathParticles() {
        return deathParticles;
    }

    public void setDeathParticles(List<Particle> deathParticles) {
        this.deathParticles = deathParticles;
    }
}
