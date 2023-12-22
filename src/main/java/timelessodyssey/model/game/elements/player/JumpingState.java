package timelessodyssey.model.game.elements.player;

import timelessodyssey.model.Vector;

public class JumpingState extends PlayerState {
    public JumpingState(Player player) {
        super(player);
    }

    @Override
    public Vector jump() {
        return updateVelocity(getPlayer().getVelocity());
    }

    @Override
    public Vector dash() {
        return applyCollisions(new Vector(
                getPlayer().isFacingRight() ? getPlayer().getDashBoost() : -getPlayer().getDashBoost(),
                getPlayer().getVelocity().y()
        ));
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

        if (getPlayer().getVelocity().y() >= 0)
            return new FallingState(getPlayer());
        return this;
    }
}
