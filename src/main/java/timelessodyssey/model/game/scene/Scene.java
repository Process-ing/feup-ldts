package timelessodyssey.model.game.scene;

import timelessodyssey.model.Vector;
import timelessodyssey.model.game.elements.Player;
import timelessodyssey.model.game.elements.Spike;
import timelessodyssey.model.game.elements.Tile;

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
    private Vector transitionPosition;

    public Scene(int width, int height, int sceneCode) {
        this.width = width;
        this.height = height;
        this.sceneCode = sceneCode;
        this.gravity = 0.25;
        this.friction = 0.75;
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

    public Vector getTransitionPosition() {
        return transitionPosition;
    }

    public void setTransitionPosition(Vector transitionPosition) {
        this.transitionPosition = transitionPosition;
    }

    public Vector canMove(Vector position) {
        return position;
    }

    public boolean isAtTransitionPosition() {
        return  player.getPosition().x() >= transitionPosition.x() && player.getPosition().y() >= transitionPosition.y();
    }

    public boolean isPlayerColliding(Vector position, Direction direction) {
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
}
