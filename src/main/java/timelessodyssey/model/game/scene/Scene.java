package timelessodyssey.model.game.scene;

import timelessodyssey.model.Position;
import timelessodyssey.model.game.elements.Player;
import timelessodyssey.model.game.elements.Spike;
import timelessodyssey.model.game.elements.Tile;

import java.util.List;

public class Scene {
    private final int width;
    private final int height;
    private final int sceneCode;

    private Player player;
    private Tile[][] tiles;
    private Spike[][] spikes;
    private Position transitionPosition;

    public Scene(int width, int height, int sceneCode) {
        this.width = width;
        this.height = height;
        this.sceneCode = sceneCode;
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

    public Position getTransitionPosition() {
        return transitionPosition;
    }

    public void setTransitionPosition(Position transitionPosition) {
        this.transitionPosition = transitionPosition;
    }

    public Position canMove(Position position) {
        return position;
    }

    public boolean isAtTransitionPosition() {
        return  player.getPosition().x() >= transitionPosition.x() && player.getPosition().y() >= transitionPosition.y();
    }

    public boolean isGrounded(){
        Position position = player.getPosition();
        int xIdx = (int) (position.x() / 8), yIdx = (int) (position.y() / 8);
        if (yIdx >= 11) {return true;}
        return tiles[yIdx+1][xIdx] != null;
    }
    public Position resolveCollisions(Position newPosition) {
        Position oldPosition = player.getPosition();
        if (oldPosition.equals(newPosition)){
            return newPosition;
        }

        Position resolvedCollision = new Position(newPosition.x(), newPosition.y());

        boolean movedDown = oldPosition.y() < newPosition.y();
        if (movedDown){
            int xIdx = (int) (resolvedCollision.x() / 8), yIdx = (int) (resolvedCollision.y() / 8);
            int xIdxc = (int) ((resolvedCollision.x()+7) / 8), yIdxc = (int) (resolvedCollision.y() / 8);
            if (tiles[yIdx+1][xIdx] != null || tiles[yIdxc+1][xIdxc] != null){
                resolvedCollision = new Position(resolvedCollision.x(), 8.0 * yIdx);
            }
            else {
                resolvedCollision = new Position(resolvedCollision.x(), resolvedCollision.y());
            }
        }
        boolean movedUp = oldPosition.y() > newPosition.y();
        if (movedUp){
            int xIdx = (int) (resolvedCollision.x() / 8), yIdx = (int) (resolvedCollision.y() / 8);
            if (tiles[yIdx][xIdx] != null){
                resolvedCollision = new Position(resolvedCollision.x(), 8.0 * yIdx + 8.0);
            }
        }
        boolean movedRight = oldPosition.x() < resolvedCollision.x();
        if (movedRight){
            int xIdx = (int) (resolvedCollision.x() / 8), yIdx = (int) (resolvedCollision.y() / 8);
            int xIdxc = (int) (resolvedCollision.x() / 8), yIdxc = (int) ((resolvedCollision.y()+7) / 8);
            if (tiles[yIdx][xIdx+1] != null || tiles[yIdxc][xIdxc+1] != null){
                resolvedCollision = new Position(8.0 * xIdx,  resolvedCollision.y());
            }
        }
        boolean movedLeft = oldPosition.x() > resolvedCollision.x();
        if(movedLeft) {
            int xIdx = (int) (resolvedCollision.x() / 8), yIdx = (int) (resolvedCollision.y() / 8);
            int xIdxc = (int) (resolvedCollision.x() / 8), yIdxc = (int) ((resolvedCollision.y()+7) / 8);
            if (tiles[yIdx][xIdx] != null || tiles[yIdxc][xIdxc] != null){
                resolvedCollision = new Position(8.0 * xIdx + 8.0,  resolvedCollision.y());
            }
        }

        return resolvedCollision;
    }
}
