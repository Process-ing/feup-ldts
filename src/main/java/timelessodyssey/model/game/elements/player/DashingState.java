package timelessodyssey.model.game.elements.player;

import timelessodyssey.model.Vector;

public class DashingState extends PlayerState {
    public static final double MIN_VELOCITY = 2.0;

    public DashingState(Player player) {
        super(player);
    }

    @Override
    public Vector jump() {
        return updateVelocity(getPlayer().getVelocity());
    }

    @Override
    public Vector dash() {
        return updateVelocity(getPlayer().getVelocity());
    }

    @Override
    public Vector updateVelocity(Vector velocity) {
        Vector newVelocity = new Vector(
                velocity.x() * getPlayer().getScene().getFriction(),
                velocity.y() + getPlayer().getScene().getGravity()
        );
        return applyCollisions(newVelocity);
    }

    @Override
    public PlayerState getNextState() {
        if (getPlayer().getScene().isPlayerDying())
            return new DeadState(getPlayer(), 50);
        if (Math.abs(getPlayer().getVelocity().x()) < MIN_VELOCITY)
            return new AfterDashState(getPlayer());
        return this;
    }
}
