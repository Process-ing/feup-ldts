package timelessodyssey.model.game.elements.player;

import timelessodyssey.model.Vector;
import timelessodyssey.model.game.elements.Element;
import timelessodyssey.model.game.scene.Scene;

public class Player extends Element {
    private static final int WIDTH = 6;
    private static final int HEIGHT = 8;

    private Vector velocity;
    private final Vector maxVelocity;
    private final double acceleration;
    private final double boost;
    private PlayerState state;
    private boolean isFacingRight;
    private Scene scene;

    public Player(double x, double y, Scene scene) {
        super(x, y);
        this.velocity = new Vector(0, 0);
        this.maxVelocity = new Vector(2.0, 3.0);
        this.acceleration = 0.65;
        this.boost = 4;
        this.isFacingRight = true;
        this.scene = scene;
        this.state = new IdleState(this);
    }

    public Vector getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }

    public boolean isFacingRight() {
        return isFacingRight;
    }

    public void setFacingRight(boolean facingRight) {
        isFacingRight = facingRight;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public double getBoost() {
        return boost;
    }

    public Vector getMaxVelocity() {
        return maxVelocity;
    }

    public PlayerState getState() {
        return state;
    }

    public void setState(PlayerState state) {
        this.state = state;
    }

    public PlayerState getNextState() {
        return state.getNextState();
    }

    public Vector updatePosition() {
        return new Vector(getPosition().x() + velocity.x(), getPosition().y() + velocity.y());
    }

    public Vector moveLeft() {
        return state.movePlayerLeft();
    }

    public Vector moveRight() {
        return state.movePlayerRight();
    }

    public Vector jump() {
        return state.jump();
    }

    public Vector updateVelocity() {
        return state.updateVelocity(velocity);
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }


    public boolean isOnGround() {
        Vector positionBelow = new Vector(
            getPosition().x(),
            getPosition().y() + 1
        );
        return scene.isColliding(positionBelow, Scene.Direction.DOWN);
    }
}
