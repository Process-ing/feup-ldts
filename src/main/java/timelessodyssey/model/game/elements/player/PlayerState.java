package timelessodyssey.model.game.elements.player;

import timelessodyssey.model.Vector;
import timelessodyssey.model.game.scene.Scene;

import static java.lang.Math.max;

public abstract class PlayerState {
    private final Player player;

    public PlayerState(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public Vector movePlayerLeft() {
        Vector newVelocity = new Vector(
                player.getVelocity().x() - player.getAcceleration(),
                player.getVelocity().y()
        );
        return updateVelocity(newVelocity);
    }

    public Vector movePlayerRight() {
        Vector newVelocity = new Vector(
                player.getVelocity().x() + player.getAcceleration(),
                player.getVelocity().y()
        );
        return updateVelocity(newVelocity);
    }

    protected Vector applyCollisions(Vector velocity) {
        double x = player.getPosition().x(), y = player.getPosition().y();
        double vx = velocity.x(), vy = velocity.y();

        while (vy > 0 && player.getScene().isColliding(new Vector(x, y + vy), Scene.Direction.DOWN))
            vy = Math.max(vy - 1, 0);

        while (vy < 0 && player.getScene().isColliding(new Vector(x, y + vy), Scene.Direction.UP))
            vy = Math.min(vy + 1, 0);

        while (vx < 0 && player.getScene().isColliding(new Vector(x + vx, y + vy), Scene.Direction.LEFT))
            vx = Math.min(vx + 1, 0);

        while (vx > 0 && player.getScene().isColliding(new Vector(x + vx, y + vy), Scene.Direction.RIGHT))
            vx = max(vx - 1, 0);

        return new Vector(vx, vy);
    }

    protected Vector limitVelocity(Vector velocity) {
        double vx = Math.min(player.getMaxVelocity().x(), Math.max(-player.getMaxVelocity().x(), velocity.x()));
        double vy = Math.min(player.getMaxVelocity().y(), velocity.y());
        if (Math.abs(vx) < 0.2)
            vx = 0;
        return new Vector(vx, vy);
    }

    protected PlayerState getNextGroundState() {
        if (Math.abs(getPlayer().getVelocity().x()) >= RunningState.MIN_VELOCITY)
            return new RunningState(getPlayer());
        if (Math.abs(getPlayer().getVelocity().x()) >= WalkingState.MIN_VELOCITY)
            return new WalkingState(getPlayer());
        return new IdleState(getPlayer());
    }

    public abstract Vector jump();
    public abstract Vector dash();
    public abstract Vector updateVelocity(Vector velocity);
    public abstract PlayerState getNextState();
}
