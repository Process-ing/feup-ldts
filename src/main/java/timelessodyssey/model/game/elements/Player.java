package timelessodyssey.model.game.elements;

import timelessodyssey.model.Vector;

public class Player extends Element {

    private static final int WIDTH = 6;
    private static final int HEIGHT = 8;

    private Vector velocity;
    private final Vector maxVelocity;
    private final double acceleration;
    private final double boost;

    private boolean isJumping;

    private boolean hasLanded;
    private boolean isFalling;
    private boolean isFacingRight;

    public Player(double x, double y) {
        super(x, y);
        this.velocity = new Vector(0, 0);
        this.maxVelocity = new Vector(2.0, 3.0);
        this.acceleration = 0.5;
        this.boost = 4;
        this.isJumping = false;
        this.isFalling = false;
        this.hasLanded = true;
        this.isFacingRight = true;
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

    public boolean isJumping() {
        return isJumping;
    }

    public void setJumping(boolean jumping) {
        isJumping = jumping;
    }

    public boolean hasLanded() {
        return hasLanded;
    }

    public void setHasLanded(boolean hasLanded) {
        this.hasLanded = hasLanded;
    }

    public boolean isFalling() {
        return isFalling;
    }

    public void setFalling(boolean falling) {
        isFalling = falling;
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
}
