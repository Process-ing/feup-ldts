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
        int xIdx1 = (int) (position.x() / 8), yIdx1 = (int) (position.y() / 8);
        int xIdx2 = (int) ((position.x()+7) / 8), yIdx2 = (int) (position.y() / 8);
        if (yIdx1 >= 11 || yIdx2 >= 11) {return true;}
        return tiles[yIdx1+1][xIdx1] != null || tiles[yIdx2+1][xIdx2] != null;
    }
    public Position resolveCollisions(Position newPosition) {
        Position oldPosition = player.getPosition();
        if (oldPosition.equals(newPosition)){
            return newPosition;
        }

        Position resolvedCollision = new Position(newPosition.x(), newPosition.y());

        boolean movedRight = oldPosition.x() < resolvedCollision.x();
        boolean movedLeft = oldPosition.x() > resolvedCollision.x();

        if (movedRight){
            int xIdx1 = (int) ((resolvedCollision.x()+7) / 8), yIdx1 = (int) (resolvedCollision.y() / 8);
            int xIdx2 = (int) ((resolvedCollision.x()+7) / 8), yIdx2 = (int) ((resolvedCollision.y()+7) / 8);
            if (tiles[yIdx1][xIdx1] != null || tiles[yIdx2][xIdx2] != null){
                resolvedCollision = new Position(8.0 * (int) (resolvedCollision.x() / 8),  resolvedCollision.y());
            }
            System.out.println("Move right");
        }
        else if(movedLeft) {
            int xIdx1 = (int) (resolvedCollision.x() / 8), yIdx1 = (int) (resolvedCollision.y() / 8);
            int xIdx2 = (int) (resolvedCollision.x() / 8), yIdx2 = (int) ((resolvedCollision.y()+7) / 8);
            if (tiles[yIdx1][xIdx1] != null || tiles[yIdx2][xIdx2] != null){
                resolvedCollision = new Position(8.0 * (int) (resolvedCollision.x() / 8) + 8.0,  resolvedCollision.y());
            }
            System.out.println("Move left");
        }
        boolean movedDown = oldPosition.y() < resolvedCollision.y();
        if (movedDown){
            int xIdx1 = (int) (resolvedCollision.x() / 8), yIdx1 = (int) ((resolvedCollision.y()+7) / 8);
            int xIdx2 = (int) ((resolvedCollision.x()+7) / 8), yIdx2 = (int) ((resolvedCollision.y()+7) / 8);
            if (tiles[yIdx1][xIdx1] != null || tiles[yIdx2][xIdx2] != null){
                resolvedCollision = new Position(resolvedCollision.x(), 8.0 * (int) (resolvedCollision.y() / 8));
                player.setvy(0);
            }
            System.out.println("Move down");
        }
        boolean movedUp = oldPosition.y() > resolvedCollision.y();
        if (movedUp){
            int xIdx1 = (int) (resolvedCollision.x() / 8), yIdx1 = (int) (resolvedCollision.y() / 8);
            int xIdx2 = (int) ((resolvedCollision.x()+7) / 8), yIdx2 = (int) (resolvedCollision.y() / 8);
            if (tiles[yIdx1][xIdx1] != null || tiles[yIdx2][xIdx2] != null){
                resolvedCollision = new Position(newPosition.x(), 8.0 * (int) (resolvedCollision.y() / 8) + 8.0);
                player.setvy(0);
            }
            System.out.println("Move up");
        }


        return resolvedCollision;
    }
}
