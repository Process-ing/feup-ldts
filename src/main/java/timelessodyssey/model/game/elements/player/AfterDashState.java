package timelessodyssey.model.game.elements.player;

import timelessodyssey.model.Vector;

public class AfterDashState extends PlayerState {
    public AfterDashState(Player player) {
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
        return limitVelocity(applyCollisions(newVelocity));
    }

    @Override
    public PlayerState getNextState() {
        if (getPlayer().getScene().isPlayerDying())
            return new DeadState(getPlayer(), 50);
        if (getPlayer().isOnGround())
            return getNextGroundState();
        return this;
    }
}
