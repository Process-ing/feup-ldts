package timelessodyssey.model.game.elements;

import timelessodyssey.gui.GUI;
import timelessodyssey.model.Vector;
import timelessodyssey.model.game.scene.Scene;

public class Player extends Element {

    private Vector velocity;

    private boolean isJumping = false;
    private boolean hasLanded = true;
    private boolean isFalling = false;
    private final int width = 8;
    private final int height = 8;
    private boolean isFacingRight;

    public Player(double x, double y) {
        super(x, y);
        this.velocity = new Vector(0, 0);

    }

    public Vector getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }

    public boolean isJumping() {
        return isJumping;
    }

    public void setJumping(boolean jumping) {
        isJumping = jumping;
    }

    public Vector updateVelocity(GUI.Action action, Scene scene) {
        return null;
    }

    public Vector updatePosition(GUI.Action action, Scene scene) {
        double x = getPosition().x(), y = getPosition().y();
        double vx = velocity.x(), vy = velocity.y();
        double gravity = 0.25, friction = 0.75, acceleration = 0.5, boost = 4;
        vy += gravity;
        vx *= friction;

        double MAX_VELOCITY_X = 2;
        if (action == GUI.Action.LEFT) {
            vx = Math.max(vx - acceleration, -MAX_VELOCITY_X);
            isFacingRight = false;
        } if (action == GUI.Action.RIGHT) {
            vx = Math.min(vx + acceleration, MAX_VELOCITY_X);
            isFacingRight = true;
        }

        if (action == GUI.Action.JUMP && hasLanded) {
            vy = -boost;
            hasLanded = false;
        }

        if (vy > 0) {
            isFalling = true;
            hasLanded = false;
            isJumping = false;

            double MAX_VELOCITY_Y = 3;
            vy = Math.min(vy, MAX_VELOCITY_Y);
            if (scene.isPlayerColliding(new Vector(x, y + vy), Scene.Direction.DOWN)) {
                hasLanded = true;
                isFalling = false;
                vy = 0;
            }
        } else if (vy < 0) {
            isJumping = true;
            if (scene.isPlayerColliding(new Vector(x, y + vy), Scene.Direction.UP)) {
                vy = 0;
            }
        }

        if (vx < 0) {
            vx = Math.max(vx, -MAX_VELOCITY_X);
            if (scene.isPlayerColliding(new Vector(x + vx, y + vy), Scene.Direction.LEFT)) {
                vx = 0;
            }
        } else if (vx > 0) {
            vx = Math.min(vx, MAX_VELOCITY_X);
            if (scene.isPlayerColliding(new Vector(x + vx, y + vy), Scene.Direction.RIGHT)) {
                vx = 0;
            }
        }

        if (Math.abs(vx) < 0.2)
            vx = 0;

        setVelocity(new Vector(vx, vy));
        x += vx;
        y += vy;

        return new Vector(x, y);
    }

    public boolean updateJumping(GUI.Action action) {
        return action == GUI.Action.JUMP;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isFalling() {
        return isFalling;
    }

    public boolean isFacingRight() {
        return isFacingRight;
    }
}
