package timelessodyssey.model.game.elements.player;

import timelessodyssey.model.Vector;

public class FallingState extends PlayerState {
    public FallingState(Player player) {
        super(player);
    }

    @Override
    public Vector jump() {
        return updateVelocity(getPlayer().getVelocity());
    }

    @Override
    public Vector dash() {
        return applyCollisions(
            new Vector(getPlayer().isFacingRight() ? getPlayer().getDashBoost() : -getPlayer().getDashBoost(), 0));
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
        if (getPlayer().getScene().isPlayerDying())
            return new DeadState(getPlayer(), 50);
        if (getPlayer().isOverMaxXVelocity())
            return new DashingState(getPlayer());

        if (getPlayer().isOnGround())
            return getNextGroundState();
        return this;
    }
}
