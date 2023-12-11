package timelessodyssey.model.game.scene;

import timelessodyssey.model.Vector;
import timelessodyssey.model.game.elements.Star;
import timelessodyssey.model.game.elements.particles.Particle;
import timelessodyssey.model.game.elements.Player;
import timelessodyssey.model.game.elements.Spike;
import timelessodyssey.model.game.elements.Tile;

import java.util.ArrayList;
import java.util.List;

import static timelessodyssey.model.game.elements.Spike.SPIKE_HEIGHT;

public class Scene {
    public double getGravity() {
        return gravity;
    }

    public double getFriction() {
        return friction;
    }

    public enum Direction { UP, DOWN, LEFT, RIGHT }

    private final int width;
    private final int height;
    private final int sceneCode;
    private final double gravity;
    private final double friction;

    private Player player;
    private Tile[][] tiles;
    private Spike[][] spikes;
    private Star[][] stars;
    private List<Particle> particles;
    private Vector transitionPosition;
    private Vector startingPosition;

    public Scene(int width, int height, int sceneCode) {
        this.width = width;
        this.height = height;
        this.sceneCode = sceneCode;
        this.gravity = 0.25;
        this.friction = 0.75;
        this.tiles = new Tile[height][width];
        this.spikes = new Spike[height][width];
        this.stars = new Star[height][width];
        this.particles = new ArrayList<>();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
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

    public List<Particle> getParticles() {
        return particles;
    }

    public void setParticles(List<Particle> particles) {
        this.particles = particles;
    }

    public Vector getTransitionPosition() {
        return transitionPosition;
    }

    public void setTransitionPosition(Vector transitionPosition) {
        this.transitionPosition = transitionPosition;
    }

    public boolean isAtTransitionPosition() {
        return  player.getPosition().x() >= transitionPosition.x() && player.getPosition().y() >= transitionPosition.y();
    }

    public Vector getStartingPosition() {
        return startingPosition;
    }

    public void setStartingPosition(Vector startingPosition) {
        this.startingPosition = startingPosition;
    }


    public boolean isColliding(Vector position, Direction direction) {
        double x = position.x(), y = position.y();
        double width = player.getWidth(), height = player.getHeight();
        double x1 = 0, x2 = 0, y1 = 0, y2 = 0;  // Hitbox corners
        switch (direction) {
            case LEFT:
                x1 = x;
                x2 = x;
                y1 = y;
                y2 = y + height - 1;
                break;
            case RIGHT:
                x1 = x + width - 1;
                x2 = x + width - 1;
                y1 = y;
                y2 = y + height - 1;
                break;
            case UP:
                x1 = x;
                x2 = x + width - 1;
                y1 = y;
                y2 = y;
                break;
            case DOWN:
                x1 = x;
                x2 = x + width - 1;
                y1 = y + height - 1;
                y2 = y + height - 1;
        }

        int tilex1 = (int)x1 / 8, tilex2 = (int)x2 / 8, tiley1 = (int)y1 / 8, tiley2 = (int)y2 / 8;

        return tiles[tiley1][tilex1] != null || tiles[tiley1][tilex2] != null
                || tiles[tiley2][tilex1] != null || tiles[tiley2][tilex2] != null;
    }

    public boolean isDying() {
        double x = getPlayer().getPosition().x(), y = getPlayer().getPosition().y();
        double width = player.getWidth(), height = player.getHeight();
        double x1 = x, x2 = x + width - 1, y1 = y + height - 1 - SPIKE_HEIGHT, y2 = y + height - 1 - SPIKE_HEIGHT;
        int tilex1 = (int)x1 / 8, tilex2 = (int)x2 / 8, tiley1 = (int)y1 / 8, tiley2 = (int)y2 / 8;

        return spikes[tiley1][tilex1] != null || spikes[tiley2][tilex2] != null;
    }

    public boolean updateStars() {
        double x = getPlayer().getPosition().x(), y = getPlayer().getPosition().y();
        double width = player.getWidth(), height = player.getHeight();
        double x1 = x, x2 = x + width - 1, y1 = y, y2 = y + height - 1;
        int tilex1 = (int)x1 / 8, tilex2 = (int)x2 / 8, tiley1 = (int)y1 / 8, tiley2 = (int)y2 / 8;
        if (stars[tiley1][tilex1] != null){
            stars[tiley1][tilex1] = null;
            System.out.println("Got a star!");
            return true;
        }
        if (stars[tiley2][tilex2] != null){
            stars[tiley2][tilex2] = null;
            System.out.println("Got a star!");
            return true;
        }
        return false;
    }

}
