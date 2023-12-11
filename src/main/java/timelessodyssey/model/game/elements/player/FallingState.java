package timelessodyssey.model.game.elements.player;

import timelessodyssey.model.Vector;
import timelessodyssey.model.game.scene.Scene;

public class FallingState extends PlayerState {
    public FallingState(Player player) {
        super(player);
    }

    @Override
    public Vector jump() {
        return updateVelocity(getPlayer().getVelocity());
    }

    @Override
    public Vector updateVelocity(Vector velocity) {
        Vector newVelocity = new Vector(
                velocity.x() * getPlayer().getScene().getFriction(),
                velocity.y() + getPlayer().getScene().getGravity()
        );
        return limitVelocity(applyCollisions(newVelocity));
    }

    @Override
    public PlayerState getNextState() {
        Vector positionBelow = new Vector(
                getPlayer().getPosition().x(),
                getPlayer().getPosition().y() + 1
        );
        if (getPlayer().getScene().isColliding(positionBelow, Scene.Direction.DOWN)) {
            if (Math.abs(getPlayer().getVelocity().x()) >= RunningState.MIN_VELOCITY)
                return new RunningState(getPlayer());
            if (Math.abs(getPlayer().getVelocity().x()) >= WalkingState.MIN_VELOCITY)
                return new WalkingState(getPlayer());
            return new IdleState(getPlayer());
        }
        return this;
    }
}
